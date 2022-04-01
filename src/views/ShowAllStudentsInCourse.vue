<template>
  <div id="studentsToShow">
    <Header></Header>
    <button id="goBack" v-on:click="goBack"> Gå tilbake</button>
    <button id="addStudent" v-on:click="addNewStudent"> Legg til student</button><br>
    <p id="add-student-file-header">Legg til CSV fil med studenter:</p>
    <input id="file" class="inputFields" type="file" v-on:change="changeFile" hidden>
    <label id="file-button" for="file" v-on:click="changeFileDesigne">Velg fil</label>
    <span id="file-chosen">No file chosen</span>
    <button id="sendFile" v-on:click="sendFile" v-if="showSendFile"> Send</button>
    <div id="showAddStudent" v-if="showAddStudent"><br>
      fornavn: <input @validate="validate('firstName')" v-model="addStudent.firstName" id="firstName" class="inputFields" type="text" placeholder="fornavn"><br>
      etternavn: <input @validate="validate('lastName')" v-model="addStudent.lastName" id="lastName" class="inputFields" type="text" placeholder="etternavn"><br>
      email: <input @validate="validate('email')" v-model="addStudent.email" id="email" class="inputFields" type="text" placeholder="email"><br>
      <div class="errorbox" v-if="showErrors">{{errors.email}}</div>
      <div class="errorbox" v-if="showErrors">{{errors.firstName}}</div>
      <div class="errorbox" v-if="showErrors">{{errors.lastName}}</div>
      <button id="sendStudent" v-on:click="sendStudent"> Send student</button>
    </div>

    <h3>Studenter i emnet {{course}}</h3>
  <div id="tableStudents-wrapper">
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
          {{student.firstName}} {{student.lastName}}
        </td>
        <td id="deleteRow">
          <button id="deleteStudent" v-on:click="deleteStudent"> Slett student</button>
        </td>
      </tr>
    </table>
  </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { object, string } from "yup";

const validationSchema = object({
  firstName: string().required().matches(/^[A-ÅÆØa-æøå ]*$/, 'Please enter valid name'),
  lastName: string().required().matches(/^[A-ÅÆØa-æøå ]*$/, 'Please enter valid name'),
  email: string().email('Please enter a valid email').required()
})

export default {
  name: "ShowAllStudentsInCourse",
  components: {Footer, Header},
  data() {
    return {
      showAddStudent: false,
      showSendFile: false,
      showErrors: false,
      addStudent : {firstName:"", lastName:"", email:""},
      course: "",
      students: [

      ],
      fileInput : "",
      help : "",
      errors : {
        firstName: "",
          lastName: "",
          email : ""
      }
    }
  },
  methods: {
    changeFileDesigne(){
      const actualBtn = document.getElementById('file');

      const fileChosen = document.getElementById('file-chosen');

      actualBtn.addEventListener('change', function(){
        fileChosen.textContent = this.files[0].name
        fileChosen.style.color  = "green";
      })
    },
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
      this.showErrors = true;
      validationSchema
          .validate(this.addStudent, { abortEarly: false })
          .then(() => {
            this.errors = {}
          })
          .catch(error => {
            error.inner.forEach(error => {
              this.errors = { ...this.errors, [error.path]: error.message }
            })
          })
    },
    sendFile(){
      const lines = this.fileInput.split("\r\n");
      console.log(lines);
      for(let i = 0; i < lines.length; i++) {
        this.help = lines[i].toString();
        let student = this.help.split(",");
        console.log(student);
        this.students.push({lastName: student[0], firstName: student[1], email: student[2]});
      }
      //console.log(this.students);
    },
    changeFile(e){
      this.showSendFile = true;
      const files = e.target.files;
      if (!files.length) {return;}
      const reader = new FileReader();
      reader.onload = () => {
        this.fileInput = reader.result;
      }
      reader.readAsText(files[0]);
    },
    validate(field) {
      validationSchema
          .validateAt(field, this.addStudent)
          .then(() => {
            this.errors[field] = ''
          })
          .catch(error => {
            this.showErrors = true;
            this.errors[error.path] = error.message
          })
    }
  }
}
</script>

<style scoped>

  button{
    cursor: pointer;
    color:white;
    background-color: #2d2c2c;
    border-width: 2px;
    border-color: steelblue;
    border-radius: 7px;
    margin: 10px;
    padding: 3px 5px 3px 5px;
  }

  #studentsToShow{
    text-align: center;
    color: white;
  }
  #tableStudents{
    color: white;
  }
  table{
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
  .errorbox{
    color: darkred;
  }
  #tableStudents-wrapper{
    width: 100%;
    display: grid;
    justify-content: center;
  }
  #file-button {
    background-color: #2d2c2c;
    color: white;
    border-radius: 0.3rem;
    cursor: pointer;
    margin: 10px;
    padding: 3px 5px 3px 5px;
    font-size: 13px;
    font-family: Arial;
  }
  #file-chosen{
    color: darkred;
    margin-left: 10px;
  }
  #sendFile{
    margin: 0 0 0 10px;
  }
</style>