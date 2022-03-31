<template>
  <div id="studentsToShow">
    <Header></Header>
    <button id="goBack" v-on:click="goBack"> Gå tilbake</button>
    <button id="addStudent" v-on:click="addNewStudent"> Legg til student</button>
    Legg til CSV fil med studenter: <button id="addFile" v-on:click="addFile"> Send</button>
    <div id="showAddStudent" v-if="showAddStudent"><br>
      fornavn: <input v-model="addStudent.firstName" id="firstName" class="inputFields" type="text" placeholder="fornavn"><br>
      etternavn: <input v-model="addStudent.lastName" id="lastName" class="inputFields" type="text" placeholder="etternavn"><br>
      email: <input v-model="addStudent.email" id="email" class="inputFields" type="text" placeholder="email"><br>
      <button id="sendStudent" v-on:click="sendStudent"> Send student</button>
    </div>

    <h3>Studenter i emnet {{course}}</h3>

    <table id="tableStudents">
      <tr>
        <th>Email</th>
        <th>Navn</th>
        <th>Slett student</th>
      </tr>
      <tr v-for="student in students" :key="student">
        <td>
          {{student.email}}
        </td>
        <td>
          {{student.name}}
        </td>
        <td id="deleteRow">
          <button id="deleteStudent" v-on:click="deleteStudent"> Slett student</button>
        </td>
      </tr>
    </table>

    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
export default {
  name: "ShowAllStudentsInCourse",
  components: {Footer, Header},
  data() {
    return {
      showAddStudent: false,
      addStudent : {firstName:"", lastName:"", email:""},
      course: "",
      students: [
        {
          email:"",
          name:""
        }
      ]
    }
  },
  methods: {
    goBack(){
      this.$router.push({
        name: 'administrator'
      })
    },
    addNewStudent(){
      this.showAddStudent = true;
    },
    deleteStudent(){

    },
    sendStudent(){

    },
    addFile(){

    }
  }
}
</script>

<style scoped>
  button {
    cursor: pointer;
    color:white;
    background-color: #2d2c2c;
    border-width: 2px;
    border-color: steelblue;
    border-radius: 7px;
    margin: 10px;
  }

  #studentsToShow{
    text-align: center;
    color: white;
  }
  #tableStudents{
    color: white;
  }
  table{
    margin-left: 40%;
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: auto;
    margin-top: 20px;
  }

  td, th {
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

  #deleteStudent{
    margin: 0px;
  }
  input{
    background-color: #2d2c2c;
    border-width: 0 0 2px 0;
    border-color: steelblue;
    color: white;
    outline: none;
  }
</style>