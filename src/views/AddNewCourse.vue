<template>
  <div id="registerCourse">
    <Header></Header>
    <menu-bar-administrator></menu-bar-administrator>
    <h3>Registrer et nytt fag</h3>
    <div id="inputContainer">
      <div id="firstColumn">
        Emnekode: <br><input id="courseCode" class="inputFields" type="text" placeholder="emnekode"><br>
        Navn: <br><input id="name" class="inputFields" type="text" placeholder="navn"><br>
        Oppstartsdato: <br><input id="date" class="inputFields" type="date"><br>
        Forventet sluttdato: <br><input id="endDate" class="inputFields" type="date"><br>
        Hvor mange øvinger har emnet? <br><input id="NumOfAssignments" class="inputFields" type="number"><br>
        Hvor mange øvinger må være godkjente? <br><input id="NumOfAssignmentsApproved" class="inputFields" type="number"><br>
        sdflgkjrterwle<br>olikujyhtgf<br>ytgfg<br>
      </div>
      <div id="secondColumn">
        Skal øvingene være delt inn i undergrupper?<br>
        (f.eks. 2 av øving 1-4, 3 av øving 5-10)<br>
        <input type="radio" id="normal" name="typeAssignment" v-on:click="sendButtonShow"> Vanlig
        <input type="radio" id="undergroups" name="typeAssignment" v-on:click="showUnderGroups"> Undergrupper<br>


        <br><button id="send" v-if="showButton">Opprett nytt emne</button>
      </div>
      <div id="invisibleChoices" v-if="showGroupDetails">
        HALOOOOO
      </div>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import MenuBarAdministrator from "@/components/menuBarAdministrator";
import Footer from "@/components/Footer";
import Header from "@/components/Header";
export default {
  name: "AddNewCourse",
  components: {Footer, MenuBarAdministrator, Header},
  data() {
    return {
      showButton : false,
      showGroupDetails: false
      }
    },
  methods: {
    sendButtonShow(){
      this.showButton = true;
      this.showGroupDetails = false;
    },
    showUnderGroups(){
      this.showButton = false;
      this.showGroupDetails = true;
    }
  }
}
</script>

<style scoped>
  #registerCourse{
    color : white;
    text-align: center;
  }
  .inputFields{
    background-color: #2d2c2c;
    border-width: 0 0 2px 0;
    border-color: steelblue;
    color: white;
    outline: none;
  }
  #inputContainer{
    display: grid;
    justify-content: center;
    grid-gap: 50px;
    grid-template-columns: auto auto auto;
    grid-template-areas:
    'firstColumn secondColumn invisibleChoices';
  }
  #firstColumn{
    grid-area: firstColumn;
  }
  #secondColumn{
    grid-area: secondColumn;
  }
  #send{
    cursor: pointer;
    color:white;
    background-color: #2d2c2c;
    border-width: 2px;
    border-color: steelblue;
    border-radius: 7px;
    margin-top: 10px;
  }
  #invisibleChoices{
    grid-area: invisibleChoices;
  }
  #date, #endDate{
    width: 166px;
  }

</style>