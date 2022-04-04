<template>
  <Header></Header>
  <menu-bar-administrator></menu-bar-administrator>
  <div id="HomePage">
    <div id="container">
        <button id="archive" v-on:click="archiveCourse" :disabled="disableButton"> Arkiver fag</button>
        <button id="edit" v-on:click="showStudents" :disabled="disableButton" > Rediger</button>
        <button id="delete" v-on:click="deleteCourse" :disabled="disableButton">Slett fag</button>
        <div id="courseTable">
          <table id="tableStudents">
            <tr>
              <th>Emnekode</th>
              <th>Emne navn</th>
              <th>Startdato</th>
              <th>Forventet sluttdato</th>
            </tr>
            <tr  class="row" v-for="course in courses" v-on:click="select($event)" v-bind:id="course.id" :key="course">
              <td id="linkStyle">
                {{ course.code }}
              </td>
              <td>
                {{course.name}}
              </td>
              <td>
                {{course.startDate}}
              </td>
              <td>
                {{course.expectedEndDate}}
              </td>
            </tr>
          </table>
            <div id="sub-feature-tabs">
            </div>
          </div>
        </div><br>
      </div>
  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import MenuBarAdministrator from "@/components/menuBarAdministrator";
import AXI from "../services/axiosService";
import store from "@/store";

export default {
  name: "HomeAdministrator",
  components: { MenuBarAdministrator, Footer, Header },
  data() {
    return {
      disableButton: true,
      checkedCourseId: null,
      hei: "",
      courses: [],
    };
  },
  created : async function() {
    await this.getCourses();
  },
  methods: {
    /**
     * method to get all courses
     * if it is an administrator it gets all courses in database
     * else it is a teacher, who will get the courses the teacher is registered on
     */
    getCourses: async function () {
      console.log(this.$store.state.userId);
      if (this.$store.state.typeOfUser === 3) {
          await AXI.getAllCourses().then(
            function (response) {
              store.state.courses = response.data;
            }.bind(this)
          );
      } else{
        await AXI.getAllCoursesForTeacher(this.$store.state.userId).then(function (response) {
          console.log(response.data);
          store.state.courses = response.data;
        }.bind(this))
      }
      this.courses = this.$store.state.courses;
      },
    /**
     * method to delete a course
     */
    deleteCourse: async function () {
        await AXI.deleteCourse(this.$store.state.courseId);
        await this.getCourses();
    },
    /**
     * method to archive a course
     */
    archiveCourse: async function () {
        await AXI.archiveCourse(this.$store.state.courseId);
        await this.getCourses();
    },
    showStudents() {
      this.$router.push({
        name: "allStudents",
      });
    },
    /**
     * setting current courseid and styling rows when activated
     * @param e
     */
    select: function (e) {
      this.disableButton = false;
      let rows = document.getElementsByClassName("row");
      for(let i = 0; i<this.courses.length; i++){
        if(this.courses[i].toString() === e.currentTarget.id){
          this.$store.commit("SET_COURSE", this.courses[i]);
        }
      }
      for (let i = 0; i < rows.length; i++) {
        rows[i].style.backgroundColor = "#202020";
      }
      document.getElementById(e.currentTarget.id).style.backgroundColor = '#4682B493';
    }
}
}
</script>

<style scoped>
@import "./../styles/navBar.css";
@import "./../styles/courses.css";

#HomePage {
  color: white;
  display: grid;
  justify-items: center;
}
#container {
  text-align: center;
}

button:disabled,
button[disabled] {
  border: 1px solid #999999;
  background-color: #cccccc;
  color: #666666;
  cursor: auto;
}
button {
  cursor: pointer;
  color: white;
  background-color: #2d2c2c;
  border-width: 2px;
  border-color: steelblue;
  border-radius: 7px;
  margin: 10px;
  padding: 3px 5px 3px 5px;
}
table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: auto;
  margin-top: 20px;
}
td {
  cursor: pointer;
}
td,
th {
  border: 1px solid steelblue;
  padding: 8px;
}

th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #011c39;
  color: white;
}
#linkStyle {
  color: blue;
  text-decoration: underline;
}
</style>
