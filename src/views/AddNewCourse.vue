<template>
  <Header></Header>
  <menu-bar-administrator></menu-bar-administrator>
  <div id="registerCourse">
    <h3>Registrer et nytt fag</h3>
    <div id="inputContainer">
      <div id="firstColumn">
        Emnekode: <br />
        <input
          @validate="validate('courseCode')"
          v-model="course.courseCode"
          id="courseCode"
          class="inputFields"
          type="text"
          placeholder="emnekode"
        /><br />
        Navn: <br />
        <input
          @validate="validate('name')"
          v-model="course.name"
          id="name"
          class="inputFields"
          type="text"
          placeholder="navn"
        /><br />
        Oppstartsdato: <br />
        <input
          @validate="validate('startDate')"
          v-model="course.startDate"
          id="date"
          class="inputFields"
          type="date"
        /><br />
        Forventet sluttdato: <br />
        <input
          @validate="validate('endDate')"
          v-model="course.endDate"
          id="endDate"
          class="inputFields"
          type="date"
        /><br />
        Hvor mange øvinger har emnet? <br />
        <input
          @validate="validate('numPractices')"
          v-model="course.numPractices"
          id="NumOfAssignments"
          class="inputFields"
          type="number"
        /><br />
        Hvor mange øvinger må være godkjente? <br />
        <input
          @validate="validate('numOfAcceptedPractices')"
          v-model="course.numOfApprovedPractices"
          id="NumOfAssignmentsApproved"
          class="inputFields"
          type="number"
        /><br />
      </div>
      <div id="secondColumn">
        Skal øvingene være delt inn i undergrupper?<br />
        (f.eks. 2 av øving 1-4, 3 av øving 5-10)<br />
        <input
          type="radio"
          id="normal"
          name="typeAssignment"
          v-on:click="sendButtonShow"
        />
        Vanlig
        <input
          type="radio"
          id="undergroups"
          name="typeAssignment"
          v-on:click="showUnderGroups"
        />
        Undergrupper<br />

        <button id="send" v-if="showButton" v-on:click="validateCourse">
          Opprett nytt emne</button
        ><br />
        <div id="successful" v-if="sentSuccessful">
          Faget er registrert! <br />
          <span
            >Rediger faget under "Dine fag" for å legge til lærere, studenter og
            studentassistenser.</span
          >
        </div>
        <div class="errorbox" v-if="showErrors">
          {{ errors.courseCode }} <br />
          {{ errors.name }}<br />
          {{ errors.startDate }}<br />
          {{ errors.endDate }}<br />
          {{ errors.numPractices }}<br />
          {{ errors.numOfApprovedPractices }}<br />
        </div>
      </div>
      <div id="invisibleChoices" v-if="showGroupDetails">
        Hvor mange undergrupper har emnet?
        <input
          v-model="course.numOfUnderGroups"
          id="NumOfUnderGroups"
          class="inputFields"
          type="number"
          v-on:input="generateInputFields"
        /><br />

        <div v-for="input in underGroups" :key="input">
          <div id="inputFieldsVisible" v-if="input.courseId > 0">
            Hvor mange øvinger er det i undergruppe nummer {{ input.courseId }} ?
            <input
              v-model="input.inputNumOfPractices"
              class="inputFields"
              type="number"
            /><br />
            Hvor mange øvinger må være godkjente i denne gruppen?
            <input
              v-model="input.inputNumOfApproved"
              class="inputFields"
              type="number"
            /><br />
          </div>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script>
import MenuBarAdministrator from "@/components/menuBarAdministrator";
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { object, string, number, date } from "yup";
import AXI from "@/services/axiosService";

const validationSchema = object({
  courseCode: string().required(),
  name: string()
    .required()
    .matches(/^[A-ÅÆØa-æøå ]*$/, "Please enter valid name"),
  startDate: date().required("enter a valid date"),
  endDate: date().required(),
  numPractices: number()
    .required()
    .moreThan(0, "practices must be more than zero"),
  numOfAcceptedPractices: number().moreThan(
    0,
    "approved practices must be more than zero"
  ),
});

export default {
  name: "AddNewCourse",
  components: { Footer, MenuBarAdministrator, Header },
  data() {
    return {
      sentSuccessful: false,
      showButton: false,
      showGroupDetails: true,
      showInput: false,
      showErrors: false,
      course: {
        courseCode: "",
        name: "",
        startDate: "",
        endDate: "",
        numPractices: 0,
        numOfApprovedPractices: 0,
        numOfUnderGroups: 2,
      },
      underGroups: [
        {
          index: 0,
          inputNumOfPractices: 0,
          inputNumOfApproved: 0,
        },
      ],
      errors: {
        courseCode: "",
        name: "",
        startDate: "",
        endDate: "",
        numPractices: "",
        numOfApprovedPractices: "",
      },
    };
  },
  methods: {
    sendButtonShow() {
      this.showButton = true;
      this.showGroupDetails = false;
    },
    showUnderGroups() {
      this.showButton = false;
      this.showGroupDetails = true;
    },
    /**
     * will generate input fields for the correct amount of selected undergroups of assignments
     */
    generateInputFields() {
      if (this.course.numOfUnderGroups > 0) {
        this.showInput = true;
        for (let i = 0; i < this.course.numOfUnderGroups; i++) {
          this.underGroups.push({
            courseId: i + 1,
            inputNumOfApproved: 0,
            inputNumOfPractices: 0,
          });
        }
      }
      this.showButton = true;
    },
    /**
     * method that validates the input fields before sending data to database
     */
    validateCourse() {
      this.showErrors = true;
      validationSchema
        .validate(this.course, { abortEarly: false })
        .then(() => {
          this.sentSuccessful = true;
          this.errors = {};
          (async () => await this.sendCourse())();
        })
        .catch((error) => {
          error.inner.forEach((error) => {
            console.log(error);
            this.errors = { ...this.errors, [error.path]: error.message };
          });
        });
    },
    /**
     * method that sends course to database
     */
    sendCourse: async function () {
      try {
        await AXI.postNewCourse(
          this.$store.state.typeOfUser,
          this.course.courseCode,
          this.course.name,
          this.course.startDate,
          this.course.endDate,
          this.course.numPractices,
          this.course.numOfApprovedPractices,
          this.underGroups
        );
        await this.getCourses();
      } catch (error) {
        console.log(error);
      }
    },
    validate(field) {
      validationSchema
        .validateAt(field, this.course)
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
#registerCourse {
  color: white;
  text-align: center;
}
.inputFields, .inputFieldsUndergroup {
  background-color: #2d2c2c;
  border-width: 0 0 2px 0;
  border-color: steelblue;
  color: white;
  outline: none;
}
#inputContainer {
  overflow-y: auto;
  max-height: 300px;
  display: grid;
  justify-content: center;
  grid-gap: 50px;
  grid-template-columns: auto auto auto;
  grid-template-areas: "firstColumn secondColumn invisibleChoices";
}
#firstColumn {
  grid-area: firstColumn;
}
#secondColumn {
  grid-area: secondColumn;
}
#send {
  cursor: pointer;
  color: white;
  background-color: #2d2c2c;
  border-width: 2px;
  border-color: steelblue;
  border-radius: 7px;
  margin-top: 10px;
  font-size: 20px;
  margin-top: 60px;
}
#invisibleChoices {
  grid-area: invisibleChoices;
}
#date,
#endDate {
  width: 166px;
}
#inputFieldsVisible {
  font-weight: lighter;
  margin: 5px;
}
.errorbox {
  color: darkred;
}
#successful {
  padding: 10px;
  color: green;
}
span {
  font-size: 10px;
}
</style>
