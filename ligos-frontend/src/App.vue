<template>
  <div id="app">
    <div id="content-wrap">
      <NavBar></NavBar>
      <router-view/>
    </div>
    <Footer id="footer"></Footer>
  </div>
</template>

<script>
import NavBar from './components/NavBar'
import Footer from './components/Footer'
export default {
  name: 'App',
  components: {
    'NavBar': NavBar,
    'Footer': Footer
  }
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: black;
    position: relative;
    min-height: 100vh;
  }

  #content-wrap {
    padding-bottom: 5rem;    /* Footer height */
  }

  #footer{
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 5rem;
  }

  body{
    margin: 0;
  }
</style>
