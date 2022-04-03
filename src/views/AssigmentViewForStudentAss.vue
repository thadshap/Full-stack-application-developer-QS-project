<template>
  <div id="container">
  <Header></Header>
  <div id="queue-container">
    <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <div id="sub-header-container">
      <p id="sub-name">{{course.subjectName}}</p>
      <p id="sub-code">{{course.subjectCode}}</p>
    </div>
      <div id="table-container">
        <table id="tableStudents">
          <tr>
            <th>Student navn</th>
          </tr>
          <tr v-for="student in students" v-on:click="select($event)" v-bind:id="student.index" :key="student.index">
            <td>
              {{ student.name }}
              <div v-if="showAmountOfOvingerDetails && student.index==this.idCheckedStudent">
              <div v-bind:id="student.index" v-for="assigment in student.assigments" :key="assigment">
                <p class="oving-approval-header" v-if="assigment.approved==true">{{assigment.name}}: godkjent</p>
                <p class="oving-approval-header" v-if="assigment.approved==false">{{assigment.name}}: ikke godkjent</p>
              </div>
              </div>
            </td>
          </tr>
        </table>
    </div>
  </div>
  <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";
import AXI from "../services/axiosService";

export default {
  name: "AssigmentViewForStudentAss",
  components: {Footer, Header},
  data() {
    return {
      showAmountOfOvingerDetails : false,
      assignmentsList:[],
      idCheckedStudent: null,
      //TODO REMOVE EVERYTHING INSIDE COURSE
      course:[
        {
        subjectName: "Fullstack",
        subjectCode: "IDATT2101",
        }
      ],
      //TODO REMOVE EVERYTHING INSIDE STUDENT
      students:[
        {
        name:"Sander Hansen",
        index:1,
        assigments:[{
          name:"Øving 1",
          approved: false,
        },
          {
            name:"Øving 2",
            approved: false,
          }]
        },
        {
          name:"Helene Hansen",
          index:2,
          assigments:[{
            name:"Øving 1",
            approved: true,
          }]
        },
      ]
    }
  },
  created: async function(){
    await this.getAllStudent()
    await this.getSubjectHeader()
  },
  methods : {
    /**
     * gets subject name and code from database
     * @returns {Promise<void>}
     */
    getSubjectHeader: async function(){
      await AXI.getCourseById(this.$store.state.courseId).then(function(response) {
        this.course = response.data
      })
    },
    /**
     * het all students from database
     * @returns {Promise<void>}
     */
    getAllStudent: async function(){
      await AXI.getAllStudentsInCourse(this.$store.state.courseId).then(function(response) {
        this.students = response.data
      })
    },
    /**
     * go back to previous router
     */
    backToPreviousPage(){
      this.$router.go(-1)
    },
    /**
     * able to only click on one row at a time and collect assignment info to the student that was clicked on
     * @param e student id
     */
    select: function (e) {
      this.idCheckedStudent = e.currentTarget.id
      this.showAmountOfOvingerDetails  = !this.showAmountOfOvingerDetails
      }
  },
};
</script>

<style scoped>
#container{
  height: 100%;
}
#queue-container{
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
table {
  border-collapse: collapse;
  width: 60%;
  margin-top: 20px;
  text-align: center;
  letter-spacing: 1px;
}
td, .oving-approval-header{
  cursor: pointer;
  font-weight: lighter;
}
td, th, .oving-approval-header{
  border: 1px solid steelblue;
  padding: 8px;
  color: rgba(255, 255, 255, 0.89);
}
th {
  padding-top: 12px;
  padding-bottom: 12px;
  background-color: #011c39;
  font-size: 20px;
}
#table-container{
  display: flex;
  width: 100%;
  justify-content: center;
}
@media only screen and (min-height: 800px) {
  #queue-container{
    height: 710px;
  }
}
</style>