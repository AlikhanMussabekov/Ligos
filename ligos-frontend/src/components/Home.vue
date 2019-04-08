<template>
  <div>
    <div v-if="sections">
      <h1>Секции</h1>
      <div class="grid">
          <Section v-for="section in sections" v-bind:key=section.id v-bind:event="section">
          </Section>
      </div>
    </div>
    <div v-else>
      <h1>Нет секций</h1>
    </div>

    <div v-if="courts">
      <h1>Площадки</h1>
      <div class="grid">
        <Section v-for="court in courts" v-bind:key=court.id v-bind:event="court">
        </Section>
      </div>
    </div>
    <div v-else>
      <h1>Нет площадок</h1>
    </div>

    <div v-if="teams">
      <h1>Команды</h1>
      <div class="grid">
        <Section v-for="team in teams" v-bind:key=team.id v-bind:event="team">
        </Section>
      </div>
    </div>
    <div v-else>
      <h1>Нет команд</h1>
    </div>
  </div>
</template>

<script>

  import Section from './Section'
  import DataService from '../services/DataService'
  export default {
    name: 'Home',
    components:{
      Section
    },
    data(){
      return {
        sections: null,
        courts: null,
        teams: null
      }
    },
    created() {
      DataService.getSections(3)
              .then(response => {
                this.sections = response.data.content;
                console.log(this.sections)
              })
              .catch(e => {
                console.log(e)
              })

      DataService.getCourts(3)
              .then(response => {
                this.courts = response.data.content;
                console.log(this.courts)
              })
              .catch(e => {
                console.log(e)
              })
    }

  }
</script>

<style scoped>

  .grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    grid-gap: 20px;
    align-items: stretch;
    margin: 50px 20px 20px;
  }

</style>
