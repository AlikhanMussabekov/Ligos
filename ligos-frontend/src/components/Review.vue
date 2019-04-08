<template>
	<div v-if="review">
		<h2>{{ review }}</h2>
	</div>
</template>

<script>
	export default {
		name: "Review",
		props:{
			review: Object
		}
	}
</script>

<style scoped>

</style>