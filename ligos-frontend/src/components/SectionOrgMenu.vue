<template>
	<div >
		<b-card no-body>
			<b-tabs card >
				<!-- Render Tabs, supply a unique `key` to each tab -->
				<b-tab v-if="sections" v-for="section in sections" :key="`dyn-tab-${section.id}`" :title="`${section.name}`">
					<div v-if="section.new">
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
						<div>Описание: {{ section.description }}</div>
						<div>
							Фото:
							<img v-if="section.photo === undefined" src="../assets/logo.png">
							<img v-else v-bind:src="'data:image/jpeg;base64,'+ section.photo"/>
						</div>
						<div>
							Адрес:
							<b-table striped hover :fields="section.address"></b-table>
						</div>
						<div>
							Детали:
							<b-table striped hover :items="section.details"></b-table>
						</div>
						<b-button size="sm" variant="danger" class="float-right" @click="() => closeTab(section)">
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
					Нет секций<br>
					Добавьте секцию используя кнопку <b>+</b>.
				</div>
			</b-tabs>
		</b-card>
	</div>
</template>

<script>
	import DataService from "../services/DataService";

	export default {
		name: "SectionOrgMenu",
		data() {
			return {
				sections: null,
				details: null,
				tabCounter: 0,
				newSection:{
					name: 'Новая секция',
					description: 'Описание новой секции',
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
				for (let i = 0; i < this.sections.length; i++) {
					if (this.sections[i] === x) {
						this.sections.splice(i, 1)
					}
				}
			},
			newTab() {
				this.sections.push(this.newSection)
			},
			onSubmit(evt){
				evt.preventDefault();
				DataService.createSection(this.form)
					.then(response => {
						this.getMySections()
					})
					.catch(e => {
						console.log(e)
					})

			},
			getMySections: function () {
				DataService.getMySections()
					.then(response => {
						this.sections = response.data;
						this.getMySectionDetails();
					})
					.catch(e => {
						console.log(e)
					})
			},
			getMySectionDetails: function () {
				this.sections.forEach(section => {
					DataService.getSectionDetails(section.id)
						.then(response => {
							section.details = response.data
						})
				})
			}
		},
		mounted() {
			this.getMySections();
		}
	}
</script>

<style scoped>
	img{
		width: 30%;
	}
</style>