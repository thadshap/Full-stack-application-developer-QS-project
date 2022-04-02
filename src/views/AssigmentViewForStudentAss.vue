<template>
  <Header></Header>
  <div id="queue-container">
    <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <div id="sub-header-container">
      <p id="sub-name">{{subjectName}}</p>
      <p id="sub-code">{{subjectCode}}</p>
    </div>
      <div id="table-container">
        <table id="tableStudents">
          <tr>
            <th>Student navn</th>
          </tr>
          <tr v-for="student in students" v-on:click="select($event)" v-bind:id="student.index" :key="student">
            <td>
              {{ student.name }}
              <div v-if="student.showAmountOfOvingerDetails">
              <p v-bind:id="student.index" class="oving-approval-header" v-for="assigment in assignmentsList" :key="assigment">
                {{assigment.name}}: {{assigment.approved}}
              </p>
              </div>
            </td>
          </tr>
        </table>
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
      subjectName: "Fullstack",
      subjectCode: "IDATT2101",
      assignmentsList:[],
      idCheckedStudent: null,
      students:[
        {
        name:"Sander Hansen",
        index:1,
        showAmountOfOvingerDetails : false,
        assigments:[{
          name:"Øving 1",
          approved:"Ikke godkjent"
        },
          {
            name:"Øving 2",
            approved:"Ikke godkjent"
          }]
        },
        {
          name:"Helene Hansen",
          index:2,
          showAmountOfOvingerDetails : false,
          assigments:[{
            name:"Øving 1",
            approved:"Godkjent"
          }]
        },
      ]
    }
  },
  created(){

  },
  methods : {
    backToPreviousPage(){
      this.$router.go(-1)
    },
    select: function (e) {
      this.idCheckedStudent = e.currentTarget.id
      let id = this.idCheckedStudent
        this.assignmentsList = []
        for(let i=0 ; i<this.students.length; i++){
          if (this.students[i].index == id){
            this.assignmentsList = this.students[i].assigments
            if (this.students[i].showAmountOfOvingerDetails == false){
              this.students[i].showAmountOfOvingerDetails = true
            }
            else if (this.students[i].showAmountOfOvingerDetails == true){
              this.students[i].showAmountOfOvingerDetails = false
            }
          }
        }
      }
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
</style>