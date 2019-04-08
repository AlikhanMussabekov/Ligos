<template>
	<transition name="modal">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">

					<div class="close-button">
						<button class="modal-default-button" @click="$emit('close')">
							<img src="../assets/close.png"/>
						</button>
					</div>

					<div class="login-inputs" v-show="registration">

						<p>
							<label for="nameReg">Имя</label>
						</p>
						<p>
							<input v-bind:class="failure ? 'fail' : null" type="text" name="name" id="nameReg" v-model="name"/>
						</p>

						<p>
							<label for="emailReg">Email</label>
						</p>
						<p>
							<input v-bind:class="failure ? 'fail' : null" type="email" name="email" id="emailReg" v-model="email"/>
						</p>

						<p>
							<label for="passwordReg">Пароль</label>
						</p>
						<p>
							<input v-bind:class="failure ? 'fail' : null" type="password" name="password" id="passwordReg" v-model="password"/>
						</p>

						<p>
							<button class="login-button" v-on:click="register">Зарегестрироваться</button>
						</p>
						<div class="registration">
							<button class="registration" v-on:click="registration = false">Уже есть аккаунт?</button>
						</div>

					</div>

					<div class="login-inputs" v-show="!registration">

						<p>
							<label for="email">Email</label>
						</p>
						<p>
							<input v-bind:class="failure ? 'fail' : null" type="email" name="email" id="email" v-model="email"/>
						</p>

						<p>
							<label for="password">Пароль</label>
						</p>
						<p>
							<input v-bind:class="failure ? 'fail' : null" type="password" name="password" id="password" v-model="password"/>
						</p>

						<p>
							<button class="login-button" v-on:click="login">Войти</button>
						</p>

						<div class="registration">
							<button class="registration" v-on:click="registration = true">Регистрация</button>
						</div>

					</div>

				</div>
			</div>
		</div>
	</transition>
</template>

<script>

	import axios from 'axios';
	import DataService from '../services/DataService';

	export default {
		name: 'Login',
		data() {
			return {
				registration: false,
				failure: false,
				name: '',
				email: '',
				password: ''
			}
		},
		methods : {
			login: function () {
				DataService.login(this.email, this.password)
					.then(response => {
						if (response.status === 200){
							this.$store.dispatch('SET_TOKEN',response.data['accessToken']);
							console.log(localStorage.token);
							this.$emit('close')
							this.$store.dispatch('SET_LOGGED');
						}
					})
					.catch(e => {
						console.log(e);
						if (e.response.status === 401){
							console.log('Incorrect credentials')
							this.failure = true
						}

					})
			},
			register: function () {

				if(this.email === null || this.email === "" || this.password === null || this.password === ""){

					this.failure = true

				}else{
					DataService.register(this.name, this.email, this.password)
						.then(response => {
							if (response.status === 201){
								this.registration = false;
							}
						})
						.catch(e => {
							if (e.response.status === 401){
								console.log('Incorrect credentials');
								this.failure = true
							}
						})
				}
			}
		}
	}
</script>

<style scoped>
	.modal-mask {
		position: fixed;
		z-index: 9998;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, .5);
		display: table;
		transition: opacity .3s ease;
	}

	.modal-wrapper {
		display: table-cell;
		vertical-align: middle;
	}

	.modal-container {
		width: 300px;
		margin: 0px auto;
		padding: 20px 30px;
		background-color: #51B291;
		border-radius: 2px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
		transition: all .3s ease;
	}

	.login-inputs{
		text-align: center;
	}

	.close-button{
		position: fixed;
	}

	.modal-default-button {
		float: right;
		background-color: Transparent;
		background-repeat:no-repeat;
		border: none;
		cursor:pointer;
		overflow: hidden;
		outline:none;
	}

	.modal-default-button img{
		width: 25px;
		height: 25px;
	}

	.modal-enter {
		opacity: 0;
	}

	.modal-leave-active {
		opacity: 0;
	}

	.modal-enter .modal-container,
	.modal-leave-active .modal-container {
		-webkit-transform: scale(1.1);
		transform: scale(1.1);
	}

	.login-button{
		color: #51B291;
		font-size: 15px;
		background-color: white;
	}

	.fail {
		border:  1px solid red;
	}

	.registration button{
		color: #213035;
	}

</style>