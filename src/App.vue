<template>
  <div id="nav">
    <router-link to="/">student</router-link> |
    <router-link to="/administrator">administrator</router-link>
    <br>
    <img id="logo" alt="logo" src="./assets/logo_white_qs.png"/><br>
  </div>
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px 30px 0 30px;
}
body{
  background-color: #202020;
  height: 825px;
  display: grid;
  align-items: center;
}
#nav a {
  font-weight: bold;
  color: white;
}

#nav a.router-link-exact-active {
  color: #4682b4;
}

#logo{
  width: 100px;
  height: auto;
  margin: 30px 0 0 0;
}
</style>
