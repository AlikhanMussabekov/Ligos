<template>
	<transition name="modal">
		<div class="modal-mask">
			<div class="modal-wrapper">
				<div class="modal-container">

					<div>
						<button class="modal-default-button" @click="$emit('close')">
							<img src="../assets/close.png"/>
						</button>
					</div>

					<div class="login-inputs">

							<p>
								<label for="email">Email</label>
							</p>
							<p>
								<input type="email" name="email" id="email" v-model="email"/>
							</p>

							<p>
								<label for="password">Пароль</label>
							</p>
							<p>
								<input type="password" name="password" id="password" v-model="password"/>
							</p>

							<p>
								<button v-on:click="login">Войти</button>
							</p>
					</div>
				</div>
			</div>
		</div>
	</transition>
</template>

<script>

	import axios from 'axios';

	export default {
		name: 'Login',
		data() {
			return {
				email: '',
				password: ''
			}
		},
		methods : {
			login: function () {
				axios.post('http://localhost:8080/user/signin', null ,
					{
						useCredentials: true,
						params: {
							'email' : this.email,
							'password' : this.password
						}
					}
				)
				.then(response => {
					console.log(response)
					if (response.status === 200){
						localStorage.token = response.data['accessToken']
						console.log(localStorage.token)
					}
				})
				.catch(e => {
					if (e.response.status === 401){
						console.log('Incorrect credentials')
					}
				})
			},
			logout: function () {
				delete localStorage.token
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

</style>