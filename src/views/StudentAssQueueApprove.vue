<template>
  <div id="container">
  <Header></Header>
  <div id="queue-student-ass-container">
    <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <div id="sub-header-container">
      <p id="sub-name">{{subName}}</p>
      <p id="sub-code">{{subCode}}</p>
      <p id="student-name" class="student-details-in-queue">{{studentName}}</p>
      <p id="campus" class="student-details-in-queue">{{campus}}</p>
      <p id="building" class="student-details-in-queue">{{building}}</p>
      <p id="room" class="student-details-in-queue">{{room}}</p>
      <p id="table" class="student-details-in-queue">{{table}}</p>
    </div>
    <div id="oving-container" >
      <p id="oving-header">Øving 1</p>
      <input type="checkbox" id="approve-checkbox" name="horns" >
      <hr class="line-under-oving">
    </div>
    <div id="approve-student-in-que-choices-btns">
      <button id="delete-btn">Slett fra kø</button>
      <button id="wait-btn">Vent</button>
      <button id="approve-btn">Godkjenn</button>
    </div>
  </div>
  <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "StudentAssQueueApprove",
  components: {Footer, Header},
  data(){
    return{
      subName:"Fullstack",
      subCode:"IDATT1001",
      studentName:"Tahdshajini Paramsothy",
      location:"campus",
      campus:"Realfagsbygget",
      building:"A4",
      room:112,
      table:4,
    }
  },
  methods:{
    backToPreviousPage(){
      this.$router.go(-1)
    },
  },
};
</script>

<style scoped>
#container{
  height: 100%;
}
#queue-student-ass-container{
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
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
}
#sub-name,#sub-code,.student-details-in-queue{
  letter-spacing: 1px;
  font-weight: lighter;
  color: rgba(255, 255, 255, 0.89);
}
.student-details-in-queue{
  font-size: 16px;
}
#sub-name{
  font-size: 24px;
  margin-bottom: 0;
  font-weight: bold;
}
#sub-header-container{
  border-radius: .2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 0 20px 24px 20px;
  text-align: center;
  padding-bottom: 24px;
  padding-top: 20px;
}
#campus,#building,#room,#table{
  display: inline;
  text-align: center;
  margin-right: 5px;
}
#oving-container{
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 24px;
}
.line-under-oving{
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: rgba(66, 66, 66, 0.73);
  border: none;
}
#oving-header,#approve-checkbox{
  display: inline;
  margin: 0 35px 0 35px;
  font-size: 20px;
}
#oving-header{
  width: 70px;
}
#approve-checkbox{
  float: right;
  margin: 10px 35px 0 35px;
  transform: scale(1.5);
}
#delete-btn,#wait-btn,#approve-btn{
  display: inline;
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  margin: 5px;
  padding: 5px 15px 5px 15px;
  border-radius: 0.3em;
  color: rgba(255, 255, 255, 0.89);
}
#delete-btn{
  background-color: red;
}
#wait-btn{
  background-color: #0a64c2;
}
#approve-btn{
  background-color: green;
}
#approve-student-in-que-choices-btns{
  position: absolute;
  bottom: 120px;
  width: 100%;
  display: flex;
  justify-content: center;
}
@media only screen and (min-height: 800px) {
  #queue-student-ass-container{
    height: 710px;
  }
}
</style>