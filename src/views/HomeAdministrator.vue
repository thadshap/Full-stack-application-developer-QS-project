<template>
  <Header></Header>
  <menu-bar-administrator></menu-bar-administrator>
    <div id="HomePage">
    <div id="container">
        <button  v-on:click="archiveCourse" :disabled="disableButton"> Arkiver fag</button>
        <button v-on:click="showStudents" :disabled="disableButton" > Rediger</button>
        <button v-on:click="deleteCourse" :disabled="disableButton">Slett fag</button>
        <div id="courseTable">
          <table id="tableStudents">
            <tr>
              <th>Emnekode</th>
              <th>Emne navn</th>
              <th>Startdato</th>
              <th>Forventet sluttdato</th>
            </tr>
            <tr class="row" v-for="course in courses" v-on:click="select($event)" v-bind:id="course.index" :key="course">
              <td id="linkStyle">
                {{ course.courseCode }}
              </td>
              <td>
                {{course.courseName}}
              </td>
              <td>
                {{course.startDate}}
              </td>
              <td>
                {{course.endDate}}
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
export default {
  name: "HomeAdministrator",
  components: {MenuBarAdministrator, Footer, Header},
  data() {
    return {
      disableButton : true,
      checkedCourseId : null,
      hei:"",
      courses:[
      ],
    }
  },
  created : async function() {
    //testing
    this.courses.push({courseName: "statistikk", courseCode: "ISTT1001", index : 20, startDate : "22.03.2022"})
    this.courses.push({courseName: "statistikk", courseCode: "ISTT1001",index : 21, startDate : "22.03.2022"})

    await this.getCourses()
  },
  methods: {
    /**
     * method to get all courses
     * if it is an administrator it gets all courses in database
     * else it is a teacher, who will get the courses the teacher is registered on
     */
    getCourses: async function(){
      if (this.$store.state.typeOfUser === 3){
        try {
          await AXI.getAllCourses().then(function (response) {
            this.courses = response.data
            console.log(response.data)
          }.bind(this))
        }catch (error) {
          console.log(error)
        }
      } else{
          try{
            await AXI.getAllCoursesForTeacher(this.$store.state.userId).then(function (response) {
              this.courses = response.data;
            }.bind(this))
          }catch (error) {
            console.log(error)
          }
      }
    },
    /**
     * method to delete a course
     */
    deleteCourse: async function() {
      try{
        await AXI.deleteCourse(this.$store.state.courseId).bind(this);
        await this.getCourses();
      }catch (error) {
        console.log(error)
      }
    },
    /**
     * method to archive a course
     */
    archiveCourse: async function() {
      try{
        await AXI.archiveCourse(this.$store.state.courseId).bind(this);
        await this.getCourses();
      }catch (error) {
        console.log(error)
      }
    },
    showStudents(){
      this.$router.push({
        name: 'allStudents'
      })
    },
    /**
     * setting current courseid and styling rows when activated
     * @param e
     */
    select: function (e) {
      this.disableButton = false;
      this.$store.commit("SET_COURSEID", e.currentTarget.id);
      let rows = document.getElementsByClassName("row");
      for (let i = 0; i < rows.length; i++){
        rows[i].style.backgroundColor = '#202020';
      }
      document.getElementById(e.currentTarget.id).style.backgroundColor = '#4682B493';
    }
}
}
</script>

<style scoped>
@import './../styles/navBar.css';
@import './../styles/courses.css';

#HomePage{
  color: white;
  display: grid;
  justify-items: center;
}
#container{
  text-align: center;
}

button:disabled,
button[disabled]{
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
td{
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
#linkStyle{
  color:blue;
  text-decoration:underline;
}

</style>