<template>
  <div>
    <div class="grid">
      <Court v-for="court in courts" v-bind:key=court.id v-bind:event="court">
      </Court>
    </div>
  </div>
</template>

<script>
  import Court from './Court'
  import DataService from '../services/DataService'

  export default {
    name: 'Courts',
    components:{
      Court
    },
    data (){
      return {
        courts:[]
      }
    },
    created() {
      DataService.getCourts(7)
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
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    grid-gap: 20px;
    align-items: stretch;
    margin: 50px 20px 20px;
  }
</style>
