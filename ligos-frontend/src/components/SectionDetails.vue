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
						<button @click="book">Записаться</button>
					</td>
				</tr>
			</table>
		</div>
		<div v-else>
			<h1>Нет данных</h1>
		</div>
		<div class="reviews" v-if="reviews">

		</div>
		<div class="reviews" v-else>
			<h1>Нет отзывов</h1>
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
			book: function () {
				alert("booked")
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

				})

			DataService.getSectionDetails(this.$route.params.id)
				.then(response => {
					this.details = response.data
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
		text-align: center;
		margin-left: 20px;
	}

</style>