<template>
  <div id="registerCourse">
    <Header></Header>
    <menu-bar-administrator></menu-bar-administrator>
    <h3>Registrer et nytt fag</h3>
    <div id="inputContainer">
      <div id="firstColumn">
        Emnekode: <br><input v-model="courseCode" id="courseCode" class="inputFields" type="text" placeholder="emnekode"><br>
        Navn: <br><input v-model="name" id="name" class="inputFields" type="text" placeholder="navn"><br>
        Oppstartsdato: <br><input v-model="startDate" id="date" class="inputFields" type="date"><br>
        Forventet sluttdato: <br><input v-model="endDate" id="endDate" class="inputFields" type="date"><br>
        Hvor mange øvinger har emnet? <br><input v-model="numPractices" id="NumOfAssignments" class="inputFields" type="number"><br>
        Hvor mange øvinger må være godkjente? <br><input v-model="numOfAcceptedPractices" id="NumOfAssignmentsApproved" class="inputFields" type="number"><br>
      </div>
      <div id="secondColumn">
        Skal øvingene være delt inn i undergrupper?<br>
        (f.eks. 2 av øving 1-4, 3 av øving 5-10)<br>
        <input type="radio" id="normal" name="typeAssignment" v-on:click="sendButtonShow"> Vanlig
        <input type="radio" id="undergroups" name="typeAssignment" v-on:click="showUnderGroups"> Undergrupper<br>

        <br><button id="send" v-if="showButton" v-on:click="sendCourse">Opprett nytt emne</button>
      </div>
      <div id="invisibleChoices" v-if="showGroupDetails">
        Hvor mange undergrupper har emnet? <input v-model="numOfUnderGroups" id="NumOfUnderGroups" class="inputFields" type="number" v-on:input="generateInputFields"><br>

        <div v-for="input in inputs" :key="input">
          <div id="inputFieldsVisible" v-if="input.index>0">
            Hvor mange øvinger er det i undergruppe nummer {{input.index}} ?
            <input v-model="input.inputNumOfPractices" class="inputFields" type="number"><br>
            Hvor mange øvinger må være godkjente i denne gruppen?
            <input v-model="input.inputNumOfApproved" class="inputFields" type="number"><br>
          </div>
        </div>
      </div>
    </div>

    <Footer></Footer>
  </div>
</template>

<script>
import MenuBarAdministrator from "@/components/menuBarAdministrator";
import Footer from "@/components/Footer";
import Header from "@/components/Header";
//import {object, string} from "yup";
/**
const validationSchema = object({
  courseCode: string().required().('Please enter valid name'),
  name: string().required().matches(/^[A-ÅÆØa-æøå ]*$/, 'Please enter valid name'),
  email: string().email('Please enter a valid email').required()
})
 */
export default {
  name: "AddNewCourse",
  components: {Footer, MenuBarAdministrator, Header},
  data() {
    return {
      showButton : false,
      showGroupDetails: false,
      showInput: false,
      numOfUnderGroups: 0,
      courseCode:"",
      name:"",
      startDate:"",
      endDate:"",
      numPractices:0,
      numOfAcceptedPractices:0,
      inputs: [
        {
          label: "",
          index: 0,
          inputNumOfPractices: 0,
          inputNumOfApproved: 0
        }
      ]
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
    },
    generateInputFields(){
      if (this.numOfUnderGroups>0){
        this.showInput = true;
        for (let i = 0; i < this.numOfUnderGroups; i++) {
          this.inputs.push({label: "Hvor mange øvinger er det i undergruppe " + i, index: i+1, inputNumOfApproved: 0, inputNumOfPractices: 0});
        }
      }
      this.showButton = true;
    },
    sendCourse(){

    }
  }
}
</script>

<style scoped>
  #registerCourse{
    color : white;
    text-align: center;
    height: 300px;
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
    font-size: 20px;
    margin-top: 60px;
  }
  #invisibleChoices{
    grid-area: invisibleChoices;
  }
  #date, #endDate{
    width: 166px;
  }
  #inputFieldsVisible{
    font-weight: lighter;
    margin: 5px;
  }

</style>