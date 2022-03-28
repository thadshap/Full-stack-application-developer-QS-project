<template>
  <div id="loginPage">
    <router-view />
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
      header: 'Please log in as student',
      user: {
        email: '',
        password: ''
      }
    }
  },
}
</script>

<style scoped>
  #loginPage{
    color: white;
  }
  input{
    margin-top: 10px;
  }
</style>