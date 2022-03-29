<template>
  <div id="header">
    <div id="picture">
      <img id="logo" alt="logo" src="./../assets/logo_white_qs.png"/><br>
    </div>
    <div id="email">{{email}}</div>
    <button id="settings">
      Instillinger
    </button>
    <button id="logout"></button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
    }
  },
}
</script>

<style scoped>
  body{
    color:white
  }
  #header{
    color: #2c3e50;
    display: grid;
    grid-template-rows: 1fr 100px 70px 1fr;
    grid-template-columns: 1fr;
    grid-template-areas:
    'picture email settings logout';
  }
  #logo{
    width: auto;
    height: 100px;
    margin: 30px 0 0 0;
  }
  #picture{
    grid-area: picture;
  }
  #email{
    grid-area: email;
  }
  #settings{
    grid-area: settings;
  }
  #logout{
    grid-area: logout;
  }
</style>