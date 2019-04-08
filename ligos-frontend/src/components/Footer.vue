<template>
	<div id="footer">
		<div>О нас</div>
		<div>Способы оплаты</div>
		<div>Контакты</div>
		<button class="organization">Организация</button>
	</div>
</template>

<script>
	export default {
		name: 'Footer'
	}
</script>

<style scoped>
	#footer{
		background-color: #545454;
		text-align: center;
		color: white;
		font: 12px "SF Pro Display";
	}

	.organization{
		color: white;
		border: 0px;
		background-color: #51B391;
		height: 20px;
	}

</style>