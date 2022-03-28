<template>
  <div id="nav">
    <img id="logo" alt="logo" src="./assets/logo_for_qs.png"/><br>
    <router-link to="/">student</router-link> |
    <router-link to="/administrator">administrator</router-link>
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
  padding: 30px;
}
body{
  background-color: #202020;
  height: 825px;
  display: grid;
  align-items: center;
  align-content: center;
  justify-content: center;
  justify-items: center;
}
#nav a {
  font-weight: bold;
  color: white;
}

#nav a.router-link-exact-active {
  color: #4682b4;
}

#logo{
  width: 150px;
  height: auto;
}
</style>
