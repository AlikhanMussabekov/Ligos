<template>
	<div id="footer">
		<div>О нас</div>
		<div>Способы оплаты</div>
		<div>Контакты</div>
		<b-button v-b-modal.modal-center class="org-log-btn">Добавить площадку</b-button>

		<b-modal id="modal-center" ref="modal-center" hide-footer centered title="Организация">
			<b-form @submit="onSubmit">
				<b-form-group
					label="Email"
					label-for="email-input"
				>
					<b-form-input
						id="email-input"
						v-model="form.email"
						type="email"
						required
						:state="validation"
						placeholder="Введите email"
					>

					</b-form-input>
				</b-form-group>

				<b-form-group
						label="Password"
						label-for="password-input"
				>
					<b-form-input
							id="password-input"
							v-model="form.password"
							type="password"
							required
							:state="validation"
							placeholder="Введите пароль"
					>

					</b-form-input>
				</b-form-group>

				<b-button type="submit" variant="primary">Войти</b-button>

			</b-form>
		</b-modal>

	</div>
</template>

<script>
	import DataService from "../services/DataService";

	export default {
		name: 'Footer',
		methods:{
			onSubmit: function(evt){
				evt.preventDefault();
				DataService.organizationLogin(this.form)
					.then(response => {
						this.$store.dispatch('SET_TOKEN',response.data['accessToken']);
						this.$refs['modal-center'].hide();
						this.$store.dispatch('SET_LOGGED', 'org');
						this.form.email = '';
						this.form.password = '';
					})
					.catch(e => {
						console.log(e);
						if (e.response.status === 401){
							console.log('Incorrect credentials');
							this.validation = false;
						}
					})
			}
		},
		data(){
			return{
				showLogin: false,
				validation: null,
				form:{
					email:'',
					password:''
				}
			}
		}
	}
</script>

<style scoped>
	#footer{
		background-color: #78f994;
		text-align: center;
		font: 12px "SF Pro Display";
	}

	.org-log-btn{
		font-size: 11px;
	}

</style>