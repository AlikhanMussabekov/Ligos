<template>
<div>
  <div class="grid">
    <Section v-for="section in sections" v-bind:key=section.id v-bind:event="section">
    </Section>
  </div>
</div>
</template>

<script>
  import Section from './Section'
  import DataService from '../services/DataService'

  export default {
    name: 'Sections',
    components:{
      Section
    },
    data (){
      return {
        sections:[]
      }
    },
    created() {
      DataService.getSections(7)
              .then(response => {
                this.sections = response.data.content;
                console.log(this.sections)
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
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 20px;
    align-items: stretch;
    margin: 50px 20px 20px;
  }
</style>
