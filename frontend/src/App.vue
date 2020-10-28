<template>
  <div id="container">
    <navbar></navbar>
    <b-container>
    <register></register>
    </b-container>
  </div>
</template>

<script>
import navbar from './components/Navbar'
import register from './components/Register'
export default {
  name: 'App',
  data(){
    return{
      username: "",
      password: "",
      email: ""
    }
  },
  components: {
    navbar,
    register
  },
  methods:{
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
