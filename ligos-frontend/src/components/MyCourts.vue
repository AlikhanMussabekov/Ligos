<template>
	<b-card no-body>
		<b-tabs content-class="mt-3" pills card>
			<b-tab title="Секции" active>
				<SectionOrgMenu></SectionOrgMenu>
			</b-tab>
			<b-tab title="Площадки">
				<CourtOrgMenu></CourtOrgMenu>
			</b-tab>
			<b-tab title="Тренера">
				<p>Тренера</p>
			</b-tab>
			<b-tab title="Матчи">
				<p>Матчи</p>
			</b-tab>
		</b-tabs>
	</b-card>
</template>

<script>

	import SectionOrgMenu from './SectionOrgMenu'
	import CourtOrgMenu from './CourtOrgMenu'

	export default {
		name: "MyCourts",
		components:{
			SectionOrgMenu,
			CourtOrgMenu
		}
	}
</script>

<style scoped>

</style>