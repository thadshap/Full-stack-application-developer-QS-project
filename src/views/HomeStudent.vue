<template>
  <div id="container">
    <Header></Header>
    <br>
    <div id="student-home-page">
      <div id="tabs-bar-wrapper">
        <div id="tabs-bar">
          <button id="left-btn">
            <img id="student-img" src="./../assets/student.png" />
            Student
          </button>
          <button id="middle-btn" v-on:click="showPageForStudentAssistant">
            <img
              id="student-ass-img"
              src="./../assets/student-ass.png"
            />
            Student.ass.
          </button>
          <button id="right-btn">
            <div id="adjust-archive">
              <img id="archive-img" src="./../assets/archive.png" />
              Arkivert
            </div>
          </button>
        </div>
      </div>
      <div id="active-subject-container-table-wrapper">
        <div id="active-subject-container-wrapper">
          <div
            class="active-subject-container"
            v-for="course in coursess"
            v-bind:id="course.courseId"
            :key="course.courseId"
          >
            <div id="sub-name-container">
              <p id="sub-name">{{ course.name }}</p>
              <p id="sub-code">{{ course.code }}</p>
            </div>
            <div id="sub-feature-tabs">
              <button
                class="studentButtonsAssignment"
                v-bind:id="course.id"
                v-on:click="showAssignments($event)"
              >
                <img id="assigment-img" src="./../assets/assigment.png" />
                Øvinger
              </button>
              <button
                class="studentButtonsQueue"
                v-bind:id="course.id"
                v-on:click="goToQueue($event)"
                @change="disableQueueBtnIfNotActive($event)"
              >
                <img id="in-to-que-img" src="./../assets/in-to-que.png" />
                Til kø
              </button>
            </div>
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
import HomeStudentAss from "./HomeStudentAss";
import AssigmentView from "./AssigmentView";
import QueueStudent from "./QueueStudent";
import axiosService from "@/services/axiosService";
import store from "@/store";

export default {
  name: "Student",
  components: { Footer, Header },
  data() {
    return {
      coursess: [
        {
          id : null,
          name : null,
          code : null,
          minApprovedAssignments: null,
          numberOfAssignments: null
        }
      ],
    };
  },
  created: async function () {
    await this.getAllCoursesForStudent();
    this.$store.commit("SET_ISSTUDENTASSISTANT", false);
  },
  methods: {
    /**
     * Disable the queue button if the queue is not active and the button will be abled if the queue is active
     * @param event course id
     * @returns {Promise<void>}
     */
    disableQueueBtnIfNotActive: async function(event){
      const targetId = event.currentTarget.id;
      if (await axiosService.getTypeActiveQueue(targetId) === false){
        document.getElementById(targetId).disable = false
      }
      else {
        document.getElementById(targetId).disable = true
      }
    },
    /**
     * method to get all courses registered for a student from database
     */
    getAllCoursesForStudent: async function () {
      await axiosService.getAllCoursesForStudent(this.$store.state.userId).then(
        function (response) {
          store.state.courses = response.data;
        }.bind());
      this.coursess = this.$store.state.courses;
    },
    showPageForStudentAssistant() {
      this.$router.push({
        name: "studentAss",
        component: HomeStudentAss,
      });
    },
    /**
     * method to change view to 'QueueStudent'
     * saves selected course to store
     */
    goToQueue: function (event) {
      const targetId = event.currentTarget.id;
      for (let i = 0; i < this.coursess.length; i++) {
        if (this.coursess[i].id.toString() === targetId) {
          this.$store.commit("SET_COURSE", this.coursess[i]);
        }
      }
      this.$router.push({
        name: "queueStudent",
        component: QueueStudent,
      });
    },
    /**
     * method to change view to 'AssignmentView'
     * saves selected course to store
     */
    showAssignments: function (event) {
      const targetId = event.currentTarget.id;
      for (let i = 0; i < this.coursess.length; i++) {
        if (this.coursess[i].id.toString() === targetId) {
          this.$store.commit("SET_COURSE", this.coursess[i]);
        }
      }
      this.$store.commit("SET_COURSEID", targetId);
      this.$router.push({
        name: "assigmentView",
        component: AssigmentView,
      });
    },
  },
};
</script>

<style scoped>
#container {
  height: 100%;
}
#student-home-page {
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
#tabs-bar-wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
}
#active-subject-container-wrapper {
  display: table;
}
#tabs-bar {
  height: 47px;
  margin: 10px 10px 0 10px;
  background-color: #011c39;
}
#left-btn,
#middle-btn,
#right-btn {
  display: inline;
  height: 46px;
  background: rgb(40, 40, 40);
  color: inherit;
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  color: white;
  border-radius: 0.2em;
  font-size: 1rem;
  font-family: sans-serif;
  position: relative;
  top: -4px;
  padding-left: 10px;
  padding-right: 10px;
}
#middle-btn{
  position: relative;
  top: -7px;
}
#left-btn {
  position: relative;
  top: -7px;
  background-color: #011c39;
}
#adjust-archive {
  position: relative;
  top: -3px;
}
#left-btn {
  background-color: #011c39;
}
.active-subject-container {
  width: 295px;
  background-color: rgba(255, 255, 255, 0.82);
  margin: 20px 0 0 0;
  border-radius: 0.3em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  font-family: sans-serif;
  padding: 30px;
}
#sub-name,
#sub-code,
#que-details {
  margin: 3px;
  letter-spacing: 1px;
  font-weight: lighter;
}
#sub-name,
#sub-code {
  font-size: 22px;
}

#sub-name {
  color: #011c39;
}
#sub-name-container{
  width: 140px;
  height: 80px;
  display: inline-block;
}
#sub-name-container {
  margin-right: 10px;
}

#sub-feature-tabs {
  height: 40px;
  margin-top: 20px;
}
.studentButtonsAssignment,.studentButtonsQueue {
  border-color: #0a64c2;
  padding: 0;
  margin: 10px;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  height: 30px;
  background-color: rgba(255, 255, 255, 0.01);
  border-style: solid;
  border-width: 2px;
  border-radius: 0.3em;
  font-weight: lighter;
  font-size: 14px;
  padding: 5px;
}
.studentButtonsAssignment{
  margin-right: 37px;
  margin-left: 15px;
}
.studentButtonsQueue{
  margin-left: 60px;
  margin-right: 10px;
}

#assigment-img,
#in-to-que-img {
  width: 15px;
  position: relative;
  top: 2px;
}
#student-img,
#student-ass-img,
#archive-img {
  width: 23px;
  margin-right: 5px;
}
#archive-img {
  position: relative;
  top: 5px;
}
#active-subject-container-table-wrapper {
  display: flex;
  width: 100%;
  justify-content: center;
}
@media only screen and (min-height: 800px) {
  #student-home-page {
    height: 710px;
  }
}
</style>
