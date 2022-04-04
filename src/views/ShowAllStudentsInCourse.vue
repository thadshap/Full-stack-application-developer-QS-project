<template>
  <Header></Header>
  <div id="studentsToShow">
    <button id="goBack" v-on:click="goBack">Gå tilbake</button><br />
    <button id="addStudent" v-on:click="addNewStudent">Legg til student</button>
    <button id="addTeacher" v-on:click="addNewTeacher">Legg til lærer</button>
    <button id="addStudentTeacher" v-on:click="addStudentTeacher">
      Legg til studentassistent
    </button>
    <br>
    <div id="showAddStudent" v-if="showAddPerson">
      <br>
      fornavn:
      <input
        @validate="validate('firstName')"
        v-model="addPerson.firstName"
        id="firstName"
        class="inputFields"
        type="text"
        placeholder="fornavn"
      />
      <br>
      etternavn:
      <input
        @validate="validate('lastName')"
        v-model="addPerson.lastName"
        id="lastName"
        class="inputFields"
        type="text"
        placeholder="etternavn"
      />
      <br>
      email:
      <input
        @validate="validate('email')"
        v-model="addPerson.email"
        id="email"
        class="inputFields"
        type="text"
        placeholder="email"
      />
      <br>
      <div class="errorbox" v-if="showErrors">{{ errors.email }}</div>
      <div class="errorbox" v-if="showErrors">{{ errors.firstName }}</div>
      <div class="errorbox" v-if="showErrors">{{ errors.lastName }}</div>
      <button id="sendStudent" v-on:click="validateStudent">Send</button>
      <br>
    </div>
    Fjern student fra faget:
    <input
      id="studentToDelete"
      class="inputFields"
      type="text"
      v-model="deleteEmail"
      v-on:click="deleteStudent"
      placeholder="email"
    />
    <button id="deleteStudent" v-on:click="deleteStudent">Fjern student</button>
    <p id="add-student-file-header">Legg til CSV fil med studenter:</p>
    <input
      id="file"
      class="inputFields"
      type="file"
      accept=".csv"
      v-on:change="changeFile"
      hidden
    />
    <label id="file-button" for="file" v-on:click="changeFileDesign">
      Velg fil
    </label>
    <span id="file-chosen">No file chosen</span>
    <button id="sendFile" v-on:click="sendFile" v-if="showSendFile">
      Send
    </button>
    <h3>Studenter i emnet {{ course }}</h3>
    <br>
    Øvingskrav i faget: {{ demandOnPractices }}
    <div id="tableStudents-wrapper">
      <table id="tableStudents">
        <tr>
          <th>Email</th>
          <th>Navn</th>
          <th>Godkjente øvinger</th>
        </tr>
        <tr v-for="student in students" :key="student">
          <td>
            {{ student.email }}
          </td>
          <td>{{ student.firstName }} {{ student.lastName }}</td>
          <td>
            <div v-for="assign in student.assignments" :key="assign">
              Øving : {{ assign.assignmentNr }}
              <br>
              Godkjent : {{ assign.approved }}
              <br>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { object, string } from "yup";
import AXI from "@/services/axiosService";

const validationSchema = object({
  firstName: string()
    .required()
    .matches(/^[A-ÅÆØa-æøå ]*$/, "Please enter valid name"),
  lastName: string()
    .required()
    .matches(/^[A-ÅÆØa-æøå ]*$/, "Please enter valid name"),
  email: string().email("Please enter a valid email").required(),
});

export default {
  name: "ShowAllStudentsInCourse",
  components: { Footer, Header },
  data() {
    return {
      currentAddUserType: "",
      deleteEmail: "",
      showAddPerson: false,
      showSendFile: false,
      showErrors: false,
      addPerson: { firstName: "", lastName: "", email: "" },
      demandOnPractices: "",
      course: "",
      students: [
        {
          email: "e@cool.no",
          firstName: "Eirin",
          lastName: "svins",
          assignments: [
            {
              assignmentNr: 1,
              approved: false,
            },
            {
              assignmentNr: 2,
              approved: true,
            },
          ],
        },
      ],
      fileInput: "",
      help: "",
      errors: {
        firstName: "",
        lastName: "",
        email: "",
      },
    };
  },
  created: async function () {
    await this.getAllStudents();
    await this.getPracticeDemandsForCourse();
  },
  methods: {
    /**
     * Method that gets how many assignments a course have, and how many who need to be approved
     */
    getPracticeDemandsForCourse: async function () {
      await AXI.getCourseById().then(
        function (response) {
          this.demandOnPractice =
            response.data.minApprovedAssignments +
            " av " +
            response.data.numberOfAssignment +
            "må være godkjent";
        }.bind(this)
      );
    },

    /**
     * Method that get all the students registered in a course
     * @returns {Promise<void>}
     */
    getAllStudents: async function () {
      await AXI.getAllStudentsInCourse(this.$store.state.courseId).then(
        function (response) {
          this.students = response.data;
        }.bind(this)
      );
      await this.addAssignmentsAndApprovedOrNot();
    },
    /**
     * method to get the courses assignments, and whether the assignments are approved or not for all students
     */
    addAssignmentsAndApprovedOrNot: async function () {
      for (let i = 0; i < this.students.length; i++) {
        await AXI.getAllAssignmentsInCourseForStudentAndIfApproved(
          this.$store.state.courseId,
          this.students[i].studentId
        ).then(
          function (response) {
            this.students[i].assignments = response.data;
          }.bind(this)
        );
      }
    },
    changeFileDesign() {
      const actualBtn = document.getElementById("file");

      const fileChosen = document.getElementById("file-chosen");

      actualBtn.addEventListener("change", function () {
        fileChosen.textContent = this.files[0].name;
        fileChosen.style.color = "green";
      });
    },
    goBack() {
      this.$router.push({
        name: "administrator",
      });
    },
    addNewStudent() {
      this.currentAddUserType = "student";
      this.showAddPerson = true;
    },
    addNewTeacher() {
      this.currentAddUserType = "teacher";
      this.showAddPerson = true;
    },
    addStudentTeacher() {
      this.currentAddUserType = "studentTeacher";
      this.showAddPerson = true;
    },
    deleteStudent: async function () {
      await AXI.deleteStudentFromCourse(
        this.$store.state.courseId,
        this.deleteEmail
      );
      await this.getAllStudents();
    },
    /**
     * Add a student, student teacher or teacher to the course
     * if the person is not registered in the database, it will be added in backend
     * @returns {Promise<void>}
     */
    sendPerson: async function () {
      if (this.currentAddUserType === "student") {
        await AXI.addStudentToCourse(
          this.$store.state.courseId,
          this.addPerson.email,
          this.addPerson.firstName,
          this.addPerson.lastName
        );
        await this.getAllStudents();
      }
      if (this.currentAddUserType === "teacher") {
        await AXI.addTeacherToCourse(
          this.$store.state.courseId,
          this.addPerson.email,
          this.addPerson.firstName,
          this.addPerson.lastName
        );
        await this.getAllStudents();

        if (this.currentAddUserType === "studentTeacher") {
          await AXI.addTeacherToCourse(
            this.$store.state.courseId,
            this.addPerson.email,
            this.addPerson.firstName,
            this.addPerson.lastName
          );
          await this.getAllStudents();
        }
      }
    },
    sendStudentsFromFile: async function (lastname, firstname, email) {
      this.students.push({
        email: email,
        firstName: firstname,
        lastName: lastname,
      });
      await AXI.addStudentToCourse(
        this.$store.state.courseId,
        email,
        firstname,
        lastname
      );
    },
    validateStudent: async function () {
      this.showErrors = true;
      validationSchema
        .validate(this.addPerson, { abortEarly: false })
        .then(() => {
          this.sendPerson();
          this.errors = {};
        })
        .catch((error) => {
          error.inner.forEach((error) => {
            this.errors = { ...this.errors, [error.path]: error.message };
          });
        });
    },
    sendFile: async function () {
      const lines = this.fileInput.split("\r\n");
      console.log(lines);
      for (let i = 0; i < lines.length; i++) {
        this.help = lines[i].toString();
        let student = this.help.split(",");
        console.log(student);
        await this.sendStudentsFromFile(student[0], student[1], student[2]);
      }
    },
    changeFile(e) {
      this.showSendFile = true;
      const files = e.target.files;
      if (!files.length) {
        return;
      }
      const reader = new FileReader();
      reader.onload = () => {
        this.fileInput = reader.result;
      };
      reader.readAsText(files[0]);
    },
    validate(field) {
      validationSchema
        .validateAt(field, this.addPerson)
        .then(() => {
          this.errors[field] = "";
        })
        .catch((error) => {
          this.showErrors = true;
          this.errors[error.path] = error.message;
        });
    },
  },
};
</script>

<style scoped>
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

#studentsToShow {
  text-align: center;
  color: white;
  overflow-y: auto;
  max-height: 400px;
}
#tableStudents {
  color: white;
}
table {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: auto;
  margin-top: 20px;
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

#deleteStudent {
  margin: 0px;
}
input {
  background-color: #2d2c2c;
  border-width: 0 0 2px 0;
  border-color: steelblue;
  color: white;
  outline: none;
}
.errorbox {
  color: darkred;
}
#tableStudents-wrapper {
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
#file-chosen {
  color: darkred;
  margin-left: 10px;
}
#sendFile {
  margin: 0 0 0 10px;
}
</style>
