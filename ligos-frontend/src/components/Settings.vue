<template>
	<div class="content">
		<b-form @submit="onSubmit" @reset="onReset" v-show="this.$store.getters.GET_LOGGED">
			<b-form-group
					id="input-group-1"
					label="Email address:"
					label-for="input-1"
			>
				<b-form-input
						id="input-1"
						v-model="form.email"
						type="email"
						required
						v-bind:placeholder="userInfo.email"
				></b-form-input>
			</b-form-group>

			<b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
				<b-form-input
						id="input-2"
						v-model="form.name"
						required
						v-bind:placeholder="userInfo.name"
				></b-form-input>
			</b-form-group>

			<b-button type="submit" variant="primary">Submit</b-button>
			<b-button type="reset" variant="danger">Reset</b-button>
		</b-form>
	</div>
</template>

<script>
	export default {
		name: "Settings",
		data(){
			return{
				form: {
					email: '',
					name: ''
				},
				userInfo: JSON.parse(this.$store.getters.GET_USER)
			}
		},
		methods:{
			onSubmit(evt) {
				evt.preventDefault()
				alert(JSON.stringify(this.form))
			},
			onReset(evt) {
				evt.preventDefault()
				// Reset our form values
				this.form.email = '';
				this.form.name = '';
				this.$nextTick(() => {
					this.show = true
				})
			}
		}
	}
</script>

<style scoped>
	.content{
		margin: 20px;
	}
</style>