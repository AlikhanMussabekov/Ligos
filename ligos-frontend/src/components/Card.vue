<template>
	<article>
		<img src="../../build/logo.png" alt="Sample photo">
		<div class="text">
			<h3>Seamlessly visualize quality</h3>
			<p>Collaboratively administrate empowered markets via plug-and-play networks.</p>
			<button>Here's why</button>
		</div>
	</article>
</template>

<script>
	export default {
		name: 'Card'
	}
</script>

<style scoped>

	article {
		border: 0px solid #ccc;
		box-shadow: 2px 2px 6px 0px  rgba(0,0,0,0.3);
		text-align: center;
	}

	article img {
		max-width: 100%;
	}

	.text {
		padding: 0 20px 20px;
	}
	.text > button {
		background: gray;
		border: 0;
		color: white;
		padding: 10px;
		width: 100%;
	}
</style>