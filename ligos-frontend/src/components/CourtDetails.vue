<template>
	<div class="wrap" v-if="court">
		<div class="main-info">
			<h1>{{ court.name }}</h1>
			<h2>{{ court.description }}</h2>
			<img v-if="court.photo === undefined" src="../assets/logo.png">
			<img v-else v-bind:src="'data:image/jpeg;base64,'+ court.photo"/>
		</div>
		<div v-if="payments">
			<table>

				<tr>
					<td>
						Цена
					</td>
					<td colspan="2">
						Время брони
					</td>
				</tr>

				<tr v-for="payment in payments">
					<td>
						{{ payment.price }}
					</td>
					<td>
						{{ parseDate(payment.from) }}
					</td>
					<td>
						{{ parseDate(payment.to) }}
					</td>
					<td>
						<button :disabled="payment.status" @click="book(payment.id)">Записаться</button>
					</td>
				</tr>
			</table>
		</div>
		<div v-else>
			<h1>Нет данных</h1>
		</div>
		<div class="reviews">

			<b-form @submit="onSubmit">
				<b-form-textarea
						id="textarea"
						v-model="text"
						placeholder="Оставьте отзыв..."
						required
						rows="3"
						max-rows="8"
				></b-form-textarea>

				<b-input-group prepend="1" v-bind:append="rating" class="mt-3">
					<b-form-input v-model="rating" type="range" min="1" max="5"></b-form-input>
				</b-input-group>

				<b-button type="submit" variant="primary">Отправить</b-button>
			</b-form>

			<div v-for="review in reviews">
				<div class="review-header">
					<h1>{{review.user.name}}</h1>
					<span>{{ parseDateComment(review.date) }}</span>
					<span>Рейтинг: {{ review.raiting }}</span>
				</div>
				<span>{{ review.review }}</span>
			</div>
		</div>
	</div>
</template>

<script>

	import DataService from '../services/DataService'

	export default {
		name: "CourtDetails",
		data(){
			return{
				court: null,
				payments: null,
				reviews: null,
				text: '',
				rating: '5'
			}
		},
		methods:{
			onSubmit: function(event){
				event.preventDefault();
				DataService.addCourtReview(this.$route.params.id, this.text,this.rating)
					.then(response => {
						this.text = '';
						this.getReviews();
					})
					.catch(e => {
						if(e.response.status === 403){
							alert('Вы не можете оставить отзыв, так как не посещали данную площадку.')
						}
						console.log(e)
					});
			},
			parseDate: function (date) {
				let parseDate = new Date(date)
				return parseDate.getDay() + '/' + parseDate.getMonth() + ' ' +
					parseDate.getHours() + ':' + parseDate.getMinutes()
			},
			parseDateComment: function (date) {
				let parseDate = new Date(date)
				return parseDate.getDay() + '/' + parseDate.getMonth() + '/' + parseDate.getFullYear() + ' ' +
					parseDate.getHours() + ':' + parseDate.getMinutes()
			},
			book: function (id) {
				DataService.bookCourt(this.$route.params.id,id)
					.then(response => {
						alert("Площадка забронирована")
						console.log(response)
						this.getCourtPayments()
					})
					.catch(e => {
						console.log(e)
					})
			},
			getCourtPayments: function(){
				DataService.getCourtPayments(this.$route.params.id)
					.then(response => {
						this.payments = response.data;
						console.log(this.payments);
					})
					.catch(e => {
						console.log(e.response);
						if (e.response.status === 404){
							this.$router.push({
								name: 'notFound'
							})
						}
					});
			},
			getReviews: function () {
				DataService.getCourtReviews(this.$route.params.id)
					.then(response => {
						this.reviews = response.data;
						console.log('reviews ' + response.data)
					})
					.catch(e => {
						console.log(e.response);
						if (e.response.status === 404){
							this.$router.push({
								name: 'notFound'
							})
						}
					})
			}
		},
		created() {
			DataService.getCourt(this.$route.params.id)
				.then(response => {
					this.court = response.data;
					console.log(response);
				})
				.catch(e => {
					console.log(e.response);
					if (e.response.status === 404){
						this.$router.push({
							name: 'notFound'
						})
					}

				})

			this.getCourtPayments();
			this.getReviews()
		}

	}
</script>

<style scoped>

	.wrap{
		display: flex;
	}

	.main-info{
		width: 40%;
		text-align: center;
	}

	table{
		text-align: center;
	}

	table td{
		padding: 10px;
	}

	img{
		width: 100%;
		max-width: 500px;
	}

	.reviews{
		margin-left: 20px;
	}
	.review-header{
		display: list-item;
	}

</style>