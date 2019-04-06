<template>
<div class="nav-bar">
	<nav>
		<div class="images">
			<div class="left">
				<router-link to="/">
					<img class="logo" src="../assets/logo.png"/>
				</router-link>
			</div>
			<div class="right">
				<button class="login-button" v-if="!logged" @click="showLogin = true">
					<img class="login" src="../assets/login.png"/>
				</button>
				<button class="logout-button" v-if="logged">
					<img class="login" src="../assets/login.png"/>
				</button>
			</div>
		</div>
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

	import Login from '../components/Login'

	export default {
		name: 'NavBar',
		components:{
	  		Login
		},
		data () {
		return {
			links: [
				{
					id: 0,
					text: 'Sections',
					page: '/sections'
				},
				{
					id: 1,
					text: 'Courts',
					page: '/courts'
				},
				{
					id: 2,
					text: 'Teams',
					page: '/teams'
				}
		  	],
			showLogin: false,
			logged: false
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

	.left .right{
		width: 50%;
	}

	.left{
		left: 0;
	}

	.right{
		right: 0;
	}

	.nav-bar .logo {
		margin: 10px;
		width: 300px;
		height: auto;
	}

	.nav-bar .login{
		margin: 10px;
		width: 70px;
		height: auto;
	}

</style>
