<template>
  <Header></Header>
  <div id="settingsContainer">
    <button id="goBack" v-on:click="goBack">Gå tilbake</button>
    <h3>Dine brukerinstillinger</h3>
    Navnet ditt: <input id="name" class="inputFields" type="text" v-model="name" disabled><br>
    Emailen din: <input id="email" class="inputFields" type="text" v-model="email"  disabled><br>
    <p>
    Dine foretrukne pronomen: <input id="pronouns" class="inputFields" type="text" v-model="pronouns"><br>
    <button id="changePronouns" v-on:click="postPronomen">Change pronouns</button>
    </p>

  </div>
  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import AXI from "@/services/axiosService";
export default {
  name: "Settings",
  components: {Footer, Header},
  data() {
    return {
      name : "",
      email: "",
      pronouns: "",
    }
  },
  created : async function() {
    await this.getPronomen()
  },
  methods : {
    goBack() {
      this.$router.push({
        name: "administrator",
      });
    },
    /**
     * method to get pronouns for user
     */
    getPronomen : async function(){
      try {
        await AXI.getPronouns(this.$store.state.userId, this.$store.state.typeOfUser).then(
            function (response) {
              this.pronouns = response.data.pronouns;
            }.bind(this)
        );
      } catch (error) {
        console.log(error);
      }
    },
    /**
     * method to post pronouns for user
     */
    postPronomen : async function(){
      await AXI.postPronouns(this.$store.state.userId, this.$store.state.typeOfUser, this.pronouns);
    }
  }
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

button{
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