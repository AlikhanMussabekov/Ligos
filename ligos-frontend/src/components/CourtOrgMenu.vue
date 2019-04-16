<template>
	<div >
		<b-card no-body>
			<b-tabs card >
				<!-- Render Tabs, supply a unique `key` to each tab -->
				<b-tab v-if="courts" v-for="court in courts" :key="`dyn-tab-${court.id}`" :title="`${court.name}`">
					<div v-if="court.new">
						<b-form @submit="onSubmit">
							<b-form-group
									label="Название"
									label-for="name-input"
							>
								<b-form-input
										id="name-input"
										v-model="form.name"
										required
										placeholder="Введите название"
								>

								</b-form-input>
							</b-form-group>

							<b-form-group
									label="Описание"
									label-for="desc-input"
							>
								<b-form-input
										id="desc-input"
										v-model="form.description"
										required
										placeholder="Введите описание"
								>

								</b-form-input>
							</b-form-group>

							<b-form-group
									label="Фото"
									label-for="photo-input"
							>
								<b-form-file
										id="photo-input"
										v-model="form.photo"
										required
										placeholder="Выберите фото..."
										drop-placeholder="Drop file here..."
								></b-form-file>
							</b-form-group>

							<b-button type="submit" variant="primary">Добавить</b-button>

						</b-form>
					</div>
					<div v-else>
						<div>Описание: {{ court.description }}</div>
						<div>
							Фото:
							<img v-if="court.photo === undefined" src="../assets/logo.png">
							<img v-else v-bind:src="'data:image/jpeg;base64,'+ court.photo"/>
						</div>
						<div>
							Адрес:
							<b-table striped hover :fields="court.address"></b-table>
						</div>
						<b-button size="sm" variant="danger" class="float-right" @click="() => closeTab(court)">
							Удалить
						</b-button>
					</div>

				</b-tab>

				<!-- New Tab Button (Using tabs slot) -->
				<template slot="tabs">
					<b-nav-item @click.prevent="newTab" href="#"><b>+</b></b-nav-item>
				</template>

				<!-- Render this if no tabs -->
				<div slot="empty" class="text-center text-muted">
					Нет площадок<br>
					Добавьте площадку используя кнопку <b>+</b>.
				</div>
			</b-tabs>
		</b-card>
	</div>
</template>

<script>

	import DataService from '../services/DataService'

	export default {
		name: "CourtOrgMenu",
		data() {
			return {
				courts: null,
				tabCounter: 0,
				newSection:{
					name: 'Новая площадка',
					description: 'Описание новой площадки',
					new: true
				},
				form:{
					name: '',
					description: '',
					photo: null,
					address: null
				}
			}
		},
		methods: {
			closeTab(x) {
				for (let i = 0; i < this.courts.length; i++) {
					if (this.courts[i] === x) {
						this.courts.splice(i, 1)
					}
				}
			},
			newTab() {
				this.courts.push(this.newSection)
			},
			onSubmit(evt){
				evt.preventDefault();
				DataService.createCourt(this.form)
					.then(response => {
						this.getMyCourts()
					})
					.catch(e => {
						console.log(e)
					})

			},
			getMyCourts: function () {
				DataService.getMyCourts()
					.then(response => {
						this.courts = response.data;
					})
					.catch(e => {
						console.log(e)
					})
			}
		},
		mounted() {
			this.getMyCourts()
		}
	}
</script>

<style scoped>

</style>