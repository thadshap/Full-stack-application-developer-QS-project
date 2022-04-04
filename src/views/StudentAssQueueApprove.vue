<template>
  <div id="container">
    <Header></Header>
    <div id="queue-student-ass-container">
      <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
        <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png" />
      </button>
      <div id="sub-header-container">
        <p id="sub-name">{{ subName }}</p>
        <p id="sub-code">{{ subCode }}</p>
        <p id="student-name" class="student-details-in-queue">
          {{ studentFirstName }} {{ studentLastName }}
        </p>
      </div>
      <div
        id="oving-container"
        v-for="assigment in assigments"
        :key="assigment"
      >
        <p id="oving-header">{{ assigment.name }}</p>
        <input
          type="radio"
          class="approve-checkbox"
          name="oving"
          v-bind:id="assigment.index"
          @change="onChange($event)"
        />
        <hr class="line-under-oving" />
      </div>
      <div id="approve-student-in-que-choices-btns">
        <button id="delete-btn" v-on:click="deleteBtn">Slett fra kø</button>
        <button id="wait-btn" v-on:click="studentWaitBtn">Vent</button>
        <button id="approve-btn" v-on:click="approveBtn">Godkjenn</button>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "../components/Footer";
import Header from "../components/Header";
import AXI from "../services/axiosService";
import QueueStudent from "./QueueStudent";

export default {
  name: "StudentAssQueueApprove",
  components: { Footer, Header },
  data() {
    return {
      subName: "",
      subCode: "",
      studentFirstName: "",
      studentLastName: "",
      ovingId: 0,
      assigments: [
        {
          name: "Øving 1",
          approved: "Ikke godkjent",
          index: 1,
        },
        {
          name: "Øving 2",
          approved: "Ikke godkjent",
          index: 2,
        },
        {
          name: "Øving 3",
          approved: "Ikke godkjent",
          index: 3,
        },
        {
          name: "Øving 4",
          approved: "Ikke godkjent",
          index: 4,
        },
        {
          name: "Øving 5",
          approved: "Ikke godkjent",
          index: 5,
        },
      ],
    };
  },
  created() {
    this.setStudentName();
  },
  methods: {
    /**
     * sets student's firstname, lastname, subject name and subject code
     */
    setStudentName() {
      this.studentFirstName = this.$store.state.student.firstName;
      this.studentLastName = this.$store.state.student.lastName;
      this.subCode = this.$store.state.course.courseId;
      this.subName = this.$store.state.course.name;
    },
    /**
     * go back to previous router
     */
    backToPreviousPage() {
      this.$router.go(-1);
    },
    /**
     * get clicked radio button's id and save it in 'ovingId' variable
     * @param event radio button's id
     */
    onChange(event) {
      this.ovingId = event.target.id;
    },
    /**
     * this method kicks in when approve button is clicked
     * sends some attributes needed to approve a specific student in database
     * then deletes the student from queue in database
     * at the end the student assistant is sent back to queue router
     * @returns {Promise<void>}
     */
    approveBtn: async function () {
      await AXI.approveAssignmentForStudent(
        this.$store.state.courseId,
        this.$store.state.student.id,
        this.$store.state.student.id,
        this.ovingId
      );
      await AXI.deleteStudentFromQueue(
        this.$store.state.courseId,
        this.$store,
        this.$store.state.student.id
      );
      await this.$router.push({
        name: "queueStudent",
        component: QueueStudent,
      });
    },
    /**
     * this method kicks in when delete button is clicked
     * deletes the student from queue in database
     * at the end the student assistant is sent back to queue router
     * @returns {Promise<void>}
     */
    deleteBtn: async function () {
      await AXI.deleteStudentFromQueue(
        this.$store.state.courseId,
        this.$store.state.student.id,
        this.$store.state.student.id
      );
      await this.$router.push({
        name: "queueStudent",
        component: QueueStudent,
      });
    },
    /**
     * this method kicks in when wait button is clicked
     * changes the state of student in queue to {WAITING}
     * at the end the student assistant is sent back to queue router
     * @returns {Promise<void>}
     */
    studentWaitBtn: async function () {
      await AXI.changeStateInQueueForStudent(
        this.$store.state.student.id,
        this.$store.state.courseId,
        this.$store.state.student.id,
        "TAKEN"
      );
      await this.$router.push({
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
#queue-student-ass-container {
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
#back-to-queue-btn {
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
#back-to-queue-btn-img {
  height: 20px;
  width: 20px;
}
#sub-name,
#sub-code,
.student-details-in-queue {
  letter-spacing: 1px;
  font-weight: lighter;
  color: rgba(255, 255, 255, 0.89);
}
.student-details-in-queue {
  font-size: 16px;
}
#sub-name {
  font-size: 24px;
  margin-bottom: 0;
  font-weight: bold;
}
#sub-header-container {
  border-radius: 0.2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 0 20px 24px 20px;
  text-align: center;
  padding-top: 20px;
}
#oving-container {
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 24px;
}
.line-under-oving {
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: rgba(66, 66, 66, 0.73);
  border: none;
}
#oving-header,
.approve-checkbox {
  display: inline;
  margin: 0 35px 0 35px;
  font-size: 20px;
}
#oving-header {
  width: 70px;
}
.approve-checkbox {
  float: right;
  margin: 10px 35px 0 35px;
  transform: scale(1.5);
}
#delete-btn,
#wait-btn,
#approve-btn {
  display: inline;
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  margin: 5px;
  padding: 5px 15px 5px 15px;
  border-radius: 0.3em;
  color: rgba(255, 255, 255, 0.89);
}
#delete-btn {
  background-color: red;
}
#wait-btn {
  background-color: #0a64c2;
}
#approve-btn {
  background-color: green;
}
#approve-student-in-que-choices-btns {
  position: absolute;
  bottom: 120px;
  width: 100%;
  display: flex;
  justify-content: center;
}
@media only screen and (min-height: 800px) {
  #queue-student-ass-container {
    height: 710px;
  }
}
</style>
