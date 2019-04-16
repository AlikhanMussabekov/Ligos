<template>
	<article v-if="event">
		<div class="image">
			<img v-if="event.photo === undefined" src="../assets/logo.png">
			<img v-else v-bind:src="'data:image/jpeg;base64,'+ event.photo" alt="Sample photo">
		</div>
		<div class="text">
			<h3>{{ event.name }}</h3>
			<p>{{ event.description }}</p>
			<button @click="$router.push({name: 'courtDetails', params: {id : event.id}})">Детали</button>
		</div>
	</article>
</template>

<script>
	export default {
		name: 'Court',
		props: {
			event: null
		}
	}
</script>

<style scoped>

	article {
		border: 0px solid #ccc;
		box-shadow: 2px 2px 6px 0px  rgba(0,0,0,0.3);
		text-align: center;
		position: relative;
	}

	.image{
		height: 50%;
		width: 100%;
	}

	.image img {
		width: 250px;
		height: 180px;
		background-size: cover;
		background-repeat: no-repeat;
		background-position: 50% 50%;
	}

	.text {
		padding: 0 20px 10px;

	}
	.text > button {
		background: gray;
		border: 0;
		color: white;
		padding: 10px;
		width: 100%;
	}
</style>