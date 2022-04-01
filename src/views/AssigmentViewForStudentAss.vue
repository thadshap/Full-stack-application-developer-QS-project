<template>
  <Header></Header>
  <div id="queue-container">
    <button id="back-to-queue-btn">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <div id="sub-header-container">
      <p id="sub-name">Fullstack</p>
      <p id="sub-code">IDATT2105</p>
    </div>
    <div id="student-list">
    <div id="oving-rules-container">
      <p id="oving-rules">Thadshajini Paramsothy</p>
      <button id="oving-rules-btn" v-on:click="showOvinger">
        <img id="drop-down-arrow-img-oving-rules" class="drop-down-arrow-img" src="./../assets/drop-down-arrow.png">
      </button>
    </div>
    <div id="amount-of-approved-oving" v-if="showAmountOfOvingerDetails">
      <p id="oving-approval-header">Øving 1: Godkjent</p>
    </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "AssigmentViewForStudentAss",
  components: {Footer, Header},
  data() {
    return {
      showAmountOfOvingerDetails : false
    }
  },
  methods : {
    showOvinger()  {
      if (this.showAmountOfOvingerDetails === false){
        this.showAmountOfOvingerDetails = true;
        document.getElementById("drop-down-arrow-img-oving-rules").style.transform ='rotate(360deg)'
      }
      else if (this.showAmountOfOvingerDetails === true){
        this.showAmountOfOvingerDetails = false;
        document.getElementById("drop-down-arrow-img-oving-rules").style.transform ='rotate(450deg)'
      }
    },
  },
};
</script>

<style scoped>
#queue-container{
  height: 710px;
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
#sub-name,#sub-code{
  letter-spacing: 1px;
  font-weight: lighter;
  color: rgba(255, 255, 255, 0.89);
  text-align: center;
}
#sub-name{
  font-size: 24px;
  margin-bottom: 0;
}
#sub-header-container{
  border-radius: .2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 0 20px 20px 20px;
}
#oving-rules{
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  text-align: center;
  font-size: 20px;
}
#amount-of-approved-oving{
  float: left;
  font-size: 15px;
  width: 100%;
}
#oving-rules-container{
  text-align: center;
}
#oving-rules,#oving-rules-btn{
  display: inline;
}
#oving-rules{
  margin-left: 35px;
}
#oving-rules-btn{
  float: right;
  margin-right: 40px;
  position: relative;
  top: 4px;
  color: inherit;
  border: none;
  padding: 0;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  width: 20px;
  height: 20px;
  background-color: inherit;
}
#drop-down-arrow-img-oving-rules{
  height: 15px;
  width: 25px;
  transform: rotate(90deg);
}
#oving-approval-header{
  text-align: center;
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 15px;
}
</style>