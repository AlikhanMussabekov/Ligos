<template>
<div class="nav-bar">
	<nav>
		<b-row>
			<b-col cols="10">
				<router-link to="/">
					<img class="logo" src="../assets/logo.png"/>
				</router-link>
			</b-col>
			<b-col cols="2" style="text-align: left">
				<button class="login-button" v-if="!this.$store.getters.GET_LOGGED" @click="showLogin = true">
					<img class="login" src="../assets/login.png"/>
				</button>
				<div>
					<b-dropdown class="m-md-2" v-bind:text="JSON.parse(this.$store.getters.GET_USER).name" v-if="this.$store.getters.GET_LOGGED">
						<b-dropdown-item @click="$router.push({name: 'Settings'})">Настройки</b-dropdown-item>
						<b-dropdown-divider></b-dropdown-divider>
						<b-dropdown-item @click="logout">Выйти</b-dropdown-item>
					</b-dropdown>
				</div>

			</b-col>
		</b-row>
		<div class="links">
			<router-link class="spacing"
						 v-for="routes in links"
						 v-bind:key="routes.id"
						 :to="`${routes.page}`">
				{{routes.text}}
			</router-link>
		</div>
	</nav>
	<Login v-if="showLogin" @close="showLogin = false">
	</Login>
</div>
</template>

<script>

	import Login from '../components/Login';
	import { VueContext } from 'vue-context';

	export default {
		name: 'NavBar',
		components:{
	  		Login,
			VueContext
		},
		data () {
			return {
				links: [
					{
						id: 0,
						text: 'Секции',
						page: '/sections'
					},
					{
						id: 1,
						text: 'Площадки',
						page: '/courts'
					},
					{
						id: 2,
						text: 'Команды',
						page: '/teams'
					}
				],
				showLogin: false,
				isDropdownActive: false,
				selected: ''
			}
	  },
		methods:{
			logout: function () {
				delete localStorage.token
				this.$store.dispatch('SET_LOGGED_OUT');
			},
			away() {
				this.isDropdownActive = false;
			},
			onClick (text) {
				alert(`You clicked ${text}!`);
			}

		}
	}
</script>

<style scoped>
	.spacing{
		margin-left: 10px;
		margin-right: 10px;
		color: white;
	}

	.links{
		text-align: left;
	}

	.images{
		width: 100%;
		display: flex;
		align-items: center;
	}

	.nav-bar{
		width: 100%;
		background-color: #51B291;
	}

	.links a{
		text-decoration: none;
		font-size: 30px;
		min-font-size: 20px;
		font-weight: bold;
	}

	.links .router-link-exact-active{
		text-decoration: underline;
	}

	.login-button{
		background-color: Transparent;
		background-repeat:no-repeat;
		border: none;
		cursor:pointer;
		overflow: hidden;
		outline:none;
	}

	.nav-bar .logo {
		margin: 10px;
		width: 100%;
		max-width: 200px;
		height: auto;
	}

	.nav-bar .login{
		margin: 10px;
		width: 50px;
		height: auto;
	}

</style>
