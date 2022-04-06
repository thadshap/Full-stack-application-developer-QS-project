<template>
  <div id="container">
    <Header></Header>
    <div id="queue-container">
      <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
        <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png" />
      </button>
      <div id="sub-header-container">
        <p id="sub-name">{{ subjectName }}</p>
        <p id="sub-code">{{ subjectCode }}</p>
      </div>
      <p id="status">Status: {{ status }}</p>
      <hr class="blue-line" />
      <div id="oving-rules-container-wrapper">
        <div id="oving-rules-container">
          <p id="oving-rules">Øvingsregler</p>
          <button id="oving-rules-btn" v-on:click="showRules">
            <img
              id="drop-down-arrow-img"
              src="./../assets/drop-down-arrow.png"
            />
          </button>
        </div>
      </div>
      <div id="oving-rules-drop-down-container" v-if="showOvingRules">
        <p id="oving-rules-header">
          For å bestå faget må {{ minimumAssignmentsApproved }} av {{ totalAmountOfAssignments }} øvinger godkjennes.
        </p>
      </div>
      <div id="table-wrapper">
        <div id="table-container">
          <div
            id="oving-container"
            v-for="assigment in assigments"
            :key="assigment"
          >
            <p id="oving-header">Øving {{ assigment.assignmentNumber }}</p>
            <p class="approve-header" v-if="assigment.approved === true">Godkjent</p>
            <p class="approve-header" v-else>Ikke godkjent</p>
            <hr class="line-under-oving" />
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";
import axiosService from "@/services/axiosService";

export default {
  name: "AssigmentView",
  components: { Footer, Header },
  data() {
    return {
      status: "",
      totalAmountOfAssignments: null,
      minimumAssignmentsApproved: null,
      subjectName: "",
      subjectCode: "",
      showOvingRules: false,
      assigments: []
    };
  },
  created: async function () {
    this.statusOfAssignmentApproval()
    this.subjectName = this.$store.state.course.name;
    this.subjectCode = this.$store.state.course.code;
    this.totalAmountOfAssignments =
    this.$store.state.course.numberOfAssignments;
    this.minimumAssignmentsApproved =
    this.$store.state.course.minApprovedAssignments;
    await this.getAssignments();
  },
  methods: {
    /**
     * changes the status bare based on how many assignments the student has approved
     */
    statusOfAssignmentApproval(){
      let aprovalAssignments = 0
      for (let i = 0; i<this.assigments.length; i++){
        if (this.assigments[i].approved===true){
          aprovalAssignments += 1
        }
      }
      if (this.minimumAssignmentsApproved>aprovalAssignments){
        this.status = "Ikke godkjent"
      }
      else{
        this.status = "Godkjent"
      }
    },
    /**
     * method to get all assignments in current course for a student,
     * as well as getting whether they are approved or not
     */
    getAssignments: async function () {
      await axiosService
        .getAllAssignmentsInCourseForStudentAndIfApproved(
          this.$store.state.userId
        )
        .then(
          function (response) {
            this.assignments = response.data;
          }.bind(this)
        );
    },
    showRules() {
      if (this.showOvingRules === false) {
        this.showOvingRules = true;
        document.getElementById("drop-down-arrow-img").style.transform =
          "rotate(360deg)";
      } else if (this.showOvingRules === true) {
        this.showOvingRules = false;
        document.getElementById("drop-down-arrow-img").style.transform =
          "rotate(450deg)";
      }
    },
    backToPreviousPage() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
#container {
  height: 100%;
}
#queue-container {
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
#back-to-queue-btn {
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
#back-to-queue-btn-img {
  height: 20px;
  width: 20px;
}
#sub-name,
#sub-code {
  letter-spacing: 1px;
  font-weight: lighter;
  color: rgba(255, 255, 255, 0.89);
  text-align: center;
}
#sub-name {
  font-size: 24px;
  margin-bottom: 0;
}
#sub-header-container {
  border-radius: 0.2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 0 20px 0 20px;
}
#status,
#oving-rules {
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  text-align: center;
  font-size: 24px;
}
#oving-container {
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 24px;
  margin-top: 30px;
}
.blue-line {
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: #0a64c2;
  border: none;
}
.line-under-oving {
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: rgba(66, 66, 66, 0.73);
  border: none;
}
#oving-header,
.approve-header {
  display: inline;
  margin: 0 10% 0 10%;
  font-size: 20px;
}
.approve-header{
  float: right;
}
#oving-header {
  width: 70px;
}
#oving-rules-container {
  text-align: center;
}
#oving-rules,
#oving-rules-btn {
  display: inline;
}
#oving-rules {
  margin-left: 35px;
}
#oving-rules-btn {
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
#drop-down-arrow-img {
  height: 15px;
  width: 25px;
  transform: rotate(90deg);
}
#oving-rules-container-wrapper {
  width: 100%;
}
#oving-rules-drop-down-container {
  border-radius: 0.2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 20px;
}
#oving-rules-header {
  text-align: center;
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 15px;
  padding: 5px;
}
#table-container {
  display: table;
  width: 100%;
}
#table-wrapper {
  display: flex;
  width: 100%;
  justify-content: center;
}
@media only screen and (min-height: 800px) {
  #queue-container {
    height: 710px;
  }
}
</style>
