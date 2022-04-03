<template>
  <Header></Header><br>
<div id="register-in-line-container">
  <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
    <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
  </button>
  <div id="digital-or-not-checkbox">
    <input type="radio" id="school-queue-btn" value="all-queue" name="queue-btn" v-on:click="campusChosen">
    <label id="school-queue-header" for="school-queue-btn">Campus</label>
    <input type="radio" id="digital-queue-btn" value="all-queue" name="queue-btn" v-on:click="digitalChosen">
    <label id="digital-queue-header" for="digital-queue-btn" >Digital</label>
  </div>
  <div id="list-of-ovinger-wrapper">
    <div id="list-of-ovinger-container">
      <div id="list-of-ovinger" v-for="assigment in assigments" :key="assigment">
        <label id="oving-checkbox-header" class="oving-lable" for="oving-checkbox">{{assigment.name}}</label> <!--For å vise at en box er cheked så er det bare: const cb = document.querySelector('#oving-checkbox-header'); også cd.value ==true-->
        <input type="radio" id="oving-checkbox" class="oving-checkbox" name="oving-checkbox">
      </div>
    </div>
  </div>
  <div id="approval-or-help-checkbox">
    <input type="radio" id="approval-queue-btn" value="approval-queue" name="queue-btn">
    <label id="approval-queue-header" for="approval-queue-btn">Godkjenning</label>
    <input type="radio" id="help-queue-btn" value="help-queue" name="queue-btn">
    <label id="help-queue-header" for="help-queue-btn">Help</label>
  </div>
  <div id="destination-on-campus" v-if="showCampusDetails">
    <label id="campus-label" class="destination-lable" for="campus-input">Campus:</label>
    <input id="campus-input" class="destination-input">
    <br>
    <label id="building-label" class="destination-lable" for="building-input">Bygning:</label>
    <input id="building-input" class="destination-input">
    <br>
    <label id="room-label" class="destination-lable" for="room-input">Rom:</label>
    <input id="room-input" class="destination-input">
    <br>
    <label id="table-label" class="destination-lable" for="table-input">Bord:</label>
    <input id="table-input" class="destination-input">
  </div>
  <div id="stand-in-line-wrapper">
  <button id="stand-in-queue-btn">Still i kø</button>
  </div>
</div>
  <Footer></Footer>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "RegisterInLine",
  components: { Footer, Header },
  data() {
    return {
      showCampusDetails: false,
      assigments: [
        {
          name: "Øving 1",
          approved: "Ikke godkjent",
        },
        {
          name: "Øving 2",
          approved: "Ikke godkjent",
        },
        {
          name: "Øving 3",
          approved: "Ikke godkjent",
        },
        {
          name: "Øving 4",
          approved: "Ikke godkjent",
        },
        {
          name: "Øving 5",
          approved: "Ikke godkjent",
        },
      ],
    }
  },
  methods: {
    campusChosen() {
      this.showCampusDetails = true;
    },
    digitalChosen() {
      this.showCampusDetails = false;
    },
    backToPreviousPage() {
      this.$router.go(-1)
    },
  }
}
</script>

<style scoped>
#back-to-queue-btn{
  color: inherit;
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  margin: 5px;
  padding: 5px 15px 5px 15px;
  border-radius: 0.3em;
  color: rgba(255, 255, 255, 0.89);
  margin: 10px 10px 20px 0;
  background-color: inherit;
}
#back-to-queue-btn-img{
  height: 20px;
  width: 20px;
  position: absolute;
  left: 15px;
  top: 80px;
}
#school-queue-btn,#digital-queue-btn{
  display: inline;
  margin-bottom: 20px;
}
#digital-queue-btn,#help-queue-btn{
  margin-left: 50px;
}
#digital-or-not-checkbox,#list-of-ovinger,#approval-or-help-checkbox,#destination-on-campus{
  text-align: center;
  color: rgba(255, 255, 255, 0.82);
  font-weight: lighter;
}
#digital-or-not-checkbox,#approval-or-help-checkbox{
  font-size: 17px;
}
#list-of-ovinger{
  font-size: 20px;
  display: inline;
}
#approval-or-help-checkbox,#list-of-ovinger{
  margin-top: 50px;
}
#approval-or-help-checkbox{
  margin-bottom: 55px;
}
.oving-lable{
  margin-left: 20px;
}
.destination-input{
  margin-bottom: 40px;
  margin-left: 10px;
  font-size: 20px;
}
.destination-lable{
  font-size: 20px;
}
#room-label,#table-label{
  margin-left: 36px;
}
#stand-in-queue-btn{
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  margin-top: 20px;
  padding: 5px 15px 5px 15px;
  border-radius: 0.3em;
  color: rgba(255, 255, 255, 0.89);
  background-color: #0a64c2;
  width: 300px;
  height: 35px;
  margin-left: 10px;
}
#stand-in-line-wrapper{
  width: 100%;
  display: flex;
  justify-content: center;
}
footer{
  position: relative;
  bottom: 0;
}
#register-in-line-container{
  height: 400px;
  overflow: auto;
  object-fit: cover;
}
#list-of-ovinger-wrapper{
  width: 100%;
  justify-content: center;
}
#list-of-ovinger-container{
  text-align: center;
}
@media only screen and (min-height: 800px) {
  #register-in-line-container{
    height: 710px;
    overflow: auto;
    object-fit: cover;
  }
}
</style>