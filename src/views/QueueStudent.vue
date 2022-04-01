<template>
  <Header></Header><br>
  <div id="queue-student-container">
    <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <br>
    <div id="queue-choices">
    <input type="radio" id="all-queue-btn" value="all-queue" name="queue-btn">
    <label id="all-queue-header" for="all-queue-btn">Alle</label>
    <input type="radio" id="school-queue-btn" value="all-queue" name="queue-btn">
    <label id="school-queue-header" for="school-queue-btn">Campus</label>
    <input type="radio" id="digital-queue-btn" value="all-queue" name="queue-btn">
    <label id="digital-queue-header" for="digital-queue-btn">Digital</label>
    <input type="radio" id="approve-queue-btn" value="approve-queue" name="queue-btn">
    <label id="approve-queue-header" for="all-queue-btn">Godkjenning</label>
    <input type="radio" id="help-queue-btn" value="help-queue" name="queue-btn">
    <label id="help-queue-header" for="all-queue-btn">Hjelp</label>
    </div>
    <p id="amount-of-students-in-queue">Antall studenter i kø: 0</p>
    <table id="queue-table">
      <tr id="queue-table-headers">
        <th id="name">Navn</th>
        <th id="room">Rom</th>
        <th id="type">Type</th>
      </tr>
      <tr id="student-column">
        <td id="name-column">Thadshajini Paramsothy</td>
        <td id="room-column">Realfagsbygget A4 112</td>
        <td id="type-column">Godkjenning</td>
      </tr>
    </table>
  </div>
  <Footer></Footer>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "QueueStudent",
  components: {Footer, Header},
  methods:{
    backToPreviousPage(){
      this.$router.go(-1)
    },
  },
};
</script>

<style scoped>
#queue-student-container{
  height: 710px;
  overflow: auto;
  object-fit: cover;
}
#back-to-queue-btn{
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
#all-queue-btn,#approve-queue-btn,#help-queue-btn, #school-queue-btn,#digital-queue-btn{
  display: inline;
  margin-left: 10px;
  margin-bottom: 20px;
}
#queue-choices, #amount-of-students-in-queue{
  text-align: center;
  color: rgba(255, 255, 255, 0.82);
  font-weight: lighter;
}

#queue-table{
  width: 100%;
  border-collapse: collapse;
}
#queue-table th ,#queue-table td{
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  color: rgba(255, 255, 255, 0.82);
  font-family: inherit;
  padding-left: 30px;
}
#queue-table td{
  font-weight: lighter;
}
#queue-table-headers{
  outline: 0.5px solid white;
  width: 100%;
}
#type-column{
  padding-right: 10px;
}
#student-column{
  background-color: rgba(0, 128, 0, 0.55);
}
</style>