<template>
  <div id="loginPage">
    <div id="nav">
      <router-link to="/">student</router-link> |
      <router-link to="/LoginTeacher">teacher</router-link> |
      <router-link to="/LogInAdministrator">administrator</router-link>
      <router-view />
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
import AXI from "../services/axiosService";

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
  created() {
    this.$store.commit("SET_TYPEOFUSER", 1);
  },
  methods : {
    /**
     * method that calls on backend to check if email and password matches
     * if the user info is correct, userid and email will be stored in state
     * */
    loggingIn:async function() {
      await AXI.getTrueIfLoginSuccess(this.user.email, this.user.password, 1).then(function (response) {
        if (response.data.loggedIn){
          this.$store.commit("SET_USERID", response.data.personId);
          this.$store.commit("SET_EMAIL", this.user.email);
          this.$router.push({
            name: 'student'
          })
        } else{
          this.header = "Login failed";
        }
      }.bind(this))
    }
  }
}
</script>

<style scoped>
@import './../styles/navBar.css';

  #logo{
    margin: 30px 0 0 0;
  }

</style>