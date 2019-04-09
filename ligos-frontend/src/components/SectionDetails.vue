<template >
	<div class="wrap" v-if="section">
		<div class="main-info">
			<h1>{{ section.name }}</h1>
			<h2>{{ section.description }}</h2>
			<img v-bind:src="'data:image/jpeg;base64,'+ section.photo"/>
		</div>
		<div v-if="details">
			<table>

				<tr>
					<td>
						Возраст от-до
					</td>
					<td>
						Цена
					</td>
					<td colspan="2">
						Время занятия
					</td>
				</tr>

				<tr v-for="detail in details">
					<td>
						{{ detail.ageCategory.from + ' - ' + detail.ageCategory.to}}
					</td>
					<td>
						{{ detail.price }}
					</td>
					<td>
						{{ parseDate(detail.timeStart) }}
					</td>
					<td>
						{{ parseDate(detail.timeEnd) }}
					</td>
					<td>
						<button @click="book(detail.id)">Записаться</button>
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
		name: "SectionDetails",
		data(){
			return{
				section: null,
				details: null,
				reviews: null
			}
		},
		methods:{
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
			book: function (detailsId) {
				DataService.registerToSection(this.$route.params.id, detailsId)
					.then(response => {
						alert("success");
						console.log(response)
					})
					.catch(e => {
						console.log(e.response);
						if (e.response.status === 404){
							this.$router.push({
								name: 'notFound'
							})
						}else if (e.response.status === 401){
							alert('Войдите')
						}else if (e.response.status === 500){
							alert('Вы уже записаны')
						}
					})
			}
		},
		created() {
			DataService.getSection(this.$route.params.id)
				.then(response => {
					this.section = response.data;
					console.log(response);
				})
				.catch(e => {
					console.log(e.response);
					if (e.response.status === 404){
						this.$router.push({
							name: 'notFound'
						})
					}

				});

			DataService.getSectionDetails(this.$route.params.id)
				.then(response => {
					this.details = response.data
				})
				.catch(e => {
					console.log(e.response);
				})

			DataService.getSectionReviews(this.$route.params.id)
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
	}
</script>

<style scoped>

	.wrap{
		display: flex;
		margin: 20px;
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
		margin-left: 30px;
	}

	.review-header{
		display: list-item;
	}

</style>