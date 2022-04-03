<template>
  <div id="header">
    <div id="picture">
      <img id="logo" alt="logo" src="./../assets/logo_white_qs.png"/><br>
    </div>
    <div id="email">{{email}}</div>
    <button id="settings" class="button" v-on:click="showSettings">
      Instillinger
    </button>
    <button id="logout" class="button" v-on:click="logOut">Log out</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
    }
  },
  created() {
    this.email = this.$store.state.email;
  },
  methods : {
    showSettings(){
      this.$router.push({
        name: 'settings'
      })
    },
    logOut(){
      this.$router.push({
        name: 'Log in as student'
      })
    }
  }
}
</script>

<style scoped>
  body{
    color:white;
    width: 100%;
  }
  #header{
    color:white;
    background-color: #011c39;
    display: grid;
    width: 100%;
    grid-template-rows: 1fr;
    grid-template-columns: max-content auto min-content max-content;
    grid-template-areas:
    'picture email settings logout';
  }
  #logo{
    width: auto;
    height: 32px;
    padding: 5px;
  }
  #picture{
    grid-area: picture;
    padding: 10px;
  }
  #email{
    grid-area: email;
    color:white;
    text-align: center;
    margin: auto;
  }
  #settings{
    grid-area: settings;
  }
  .button {
    text-align: center;
    background: transparent;
    border: none !important;
    cursor: pointer;
    color:white;
    transition: 0.3s;
  }
  #logout{
    grid-area: logout;
  }
  #settings:hover, #logout:hover{
    background-color: steelblue;
  }

</style>