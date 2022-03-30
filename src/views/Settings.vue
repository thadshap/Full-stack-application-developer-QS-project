<template>
  <Header></Header>
  <div id="settingsContainer">
    <h3>Dine brukerinstillinger</h3>
    Your name: <input id="name" class="inputFields" type="text" v-model="name" disabled><br>
    Your email: <input id="email" class="inputFields" type="text" v-model="email"  disabled><br>
    <p>
    Your pronouns: <input id="pronouns" class="inputFields" type="text" v-model="pronouns"><br>
    <button id="changePronouns">Change pronouns</button>
    </p>

  </div>
  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "Settings",
  components: {Footer, Header},
  data() {
    return {
      name : "",
      email: "",
      pronouns: "hei",
    }
  },
}
</script>

<style scoped>
#settingsContainer{
  color: white;
  text-align: center;
  padding-top: 10%;
}
.inputFields{
  background-color: #2d2c2c;
  border-width: 0 0 2px 0;
  border-color: steelblue;
  color: white;
  outline: none;
}
#changePronouns{
  margin-left: 10px;
  cursor: pointer;
  color:white;
  background-color: #2d2c2c;
  border-width: 2px;
  border-color: steelblue;
  border-radius: 7px;
  margin-top: 10px;
}

</style>