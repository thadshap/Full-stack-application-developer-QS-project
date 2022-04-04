<template>
  <div id="container">
    <Header></Header><br />
    <div id="studentAss-home-page">
      <div id="tabs-bar-wrapper">
        <div id="tabs-bar">
          <button id="left-btn" v-on:click="select()">
            <img id="student-img" src="./../assets/student.png" />
            Student
          </button>
          <button id="middle-btn">
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
      <div id="info-about-how-to-active-queue">
        Aktiver kø ved å trykke på faget og deaktiver kø ved å trykke på fag igjen
      </div>
      <div id="active-subject-container-table-wrapper">
        <div id="active-subject-container-wrapper">
          <div
            class="active-subject-container"
            v-on:click="statusBtnClickedFunc(e)"
            v-for="course in courses"
            v-bind:id="course.index"
            :key="course.index"
          >
            <div id="sub-name-container">
              <p id="sub-name">{{ course.courseName }}</p>
              <p id="sub-code">{{ course.courseCode }}</p>
            </div>
            <div id="sub-feature-tabs">
              <button
                class="assigment-btn"
                v-bind:id="course.index"
                v-on:click="assignBtn($event)"
              >
                <img id="assigment-img" src="./../assets/assigment.png" />
                Øvinger
              </button>
              <button
                class="que-btn"
                v-bind:id="course.index"
                v-on:click="queueBtn($event)"
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
import HomeStudent from "./HomeStudent";
import QueueStudent from "./QueueStudent";
import AssigmentViewForStudentAss from "./AssigmentViewForStudentAss";
import AXI from "../services/axiosService";

export default {
  name: "HomeStudentAss",
  components: { Footer, Header },
  data() {
    return {
      //TODO REMOVE EVERYTHING INSIDE COURSE
      courses: [
        {
          courseCode: "IDATT2102",
          courseName: "Nettverk",
          activityStatus: true,
          index: 1,
        },
        {
          courseCode: "IDATT2103",
          courseName: "Fullstack",
          activityStatus: false,
          index: 2,
        },
      ],
      statusBtnClicked: true,
      statusBtnClickedDatabase: true,
      idCheckedCourse: 0,
    };
  },
  created: async function () {
    await this.getAllCourses();
    await this.queueStatusInDatabase();
    this.$store.commit("SET_ISSTUDENTASSISTANT", true);
  },
  methods: {
    /**
     * Pre-sets all the subjects containers due to if their queue is active or not
     * @returns {Promise<void>}
     */
    queueStatusInDatabase: async function () {
      await AXI.getTypeActiveQueue(this.$store.state.courseId).then(function (
        response
      ) {
        this.statusBtnClickedDatabase = response.data;
      });
      for (let i = 0; i < this.courses.length; i++) {
        if (this.courses[i].activityStatus === true) {
          document.getElementById(this.courses[i].index).style.backgroundColor =
            "rgba(3,164,3,0.25)";
        } else {
          document.getElementById(this.courses[i].index).style.backgroundColor =
            "rgba(133,0,10,0.36)";
        }
      }
    },
    /**
     * Student assistant can activate or deactivate a queue by clicking the subject
     * @param e course id
     * @returns {Promise<void>}
     */
    statusBtnClickedFunc: async function (e) {
      this.idCheckedCourse = e.currentTarget.id;
      await AXI.getTypeActiveQueue(this.$store.state.courseId).then(function (
        response
      ) {
        this.statusBtnClicked = response.data;
      });
      if (this.statusBtnClicked === true) {
        document.getElementById(this.idCheckedCourse).style.backgroundColor =
          "rgba(3,164,3,0.25)";
        await AXI.changeTypeOfActiveQueue(
          this.idCheckedCourse,
          this.statusBtnClicked
        );
        this.statusBtnClicked = !this.statusBtnClicked;
      } else {
        document.getElementById(this.idCheckedCourse).style.backgroundColor =
          "rgba(133,0,10,0.36)";
        await AXI.changeTypeOfActiveQueue(
          this.idCheckedCourse,
          this.statusBtnClicked
        );
        this.statusBtnClicked = !this.statusBtnClicked;
      }
    },
    /**
     * get all courses this given student is student assistant in
     */
    getAllCourses: async function () {
      await AXI.getAllCoursesForStudentAssistant().then(function (response) {
        this.courses = response.data;
        console.log(response.data); //TODO REMOVE THIS IF THIS METHOD WORKS
      });
    },
    /**
     * send the user to student
     * @param event id to the pressed button
     */
    select: function () {
      this.$router.push({
        name: "student",
        component: HomeStudent,
      });
    },
    assignBtn: function (event) {
      const targetId = event.currentTarget.id;
      this.$store.commit("SET_COURSEID", targetId);
      this.$router.push({
        name: "assigmentViewForStudentAss",
        component: AssigmentViewForStudentAss,
      });
    },
    queueBtn: function (event) {
      const targetId = event.currentTarget.id;
      this.$store.commit("SET_COURSEID", targetId);
      this.$router.push({
        name: "queueStudent",
        component: QueueStudent,
      });
    },
  },
};
</script>

<style scoped>
#container {
  height: 100%;
}
#studentAss-home-page {
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
#left-btn,
#middle-btn {
  position: relative;
  top: -7px;
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
  height: 130px;
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
#sub-code {
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
#sub-name-container {
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
.assigment-btn,
.que-btn {
  color: inherit;
  padding: 0;
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
}
.assigment-btn {
  margin-left: 15px;
  margin-right: 37px;
  border-color: rgba(1, 28, 57, 0.75);
  width: 92px;
}
.que-btn {
  margin-left: 60px;
  margin-right: 10px;
  border-color: green;
  width: 72px;
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
#tabs-bar,
#info-about-how-to-active-queue {
  display: block;
}
#info-about-how-to-active-queue {
  text-align: center;
  margin-top: 20px;
  font-style: italic;
  color: rgba(255, 255, 255, 0.82);
}
@media only screen and (min-width: 800px) {
  #studentAss-home-page {
    height: 710px;
  }
}
</style>
