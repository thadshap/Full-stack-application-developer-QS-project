<template>
  <div id="loginPage">
    <div id="nav">
      <router-link to="/">student</router-link> |
      <router-link to="/LogInAdministrator">administrator</router-link>
      <router-view />
      <router-view />
      <br>
    </div>

    <img id="logo" alt="logo" src="./../assets/logo_white_qs.png"/><br>
    <h3 id="head">{{ header }}</h3>
    Email:<br>
    <input
        id="email"
        type="text"
        placeholder="Email"
        v-model="user.email"
    />
    <p></p>
    Password:
    <br>
    <input
        id="password"
        placeholder="Password"
        type="password"
        v-model.lazy="user.password"
    />
    <p></p>
    <button id="logging" v-on:click="loggingIn">Log in</button><p></p>
  </div>
</template>
<script>
export default {
  data() {
    return {
      header: 'Please log in as administrator',
      user: {
        email: '',
        password: ''
      }
    }
  },
}
</script>

<style scoped>
@import './../styles/navBar.css';
#logo{
  margin: 30px 0 0 0;
}
</style>