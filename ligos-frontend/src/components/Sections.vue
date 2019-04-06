<template>
<div>
  <div class="grid">
    <Card v-for="i in 1" v-bind:key=i>
    </Card>
  </div>
</div>
</template>

<script>
  import Card from './Card'

  export default {
    name: 'Sections',
    components:{
      Card
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
