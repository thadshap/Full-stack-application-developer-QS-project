<template>
  <div id="container">
    <Header></Header><br />
    <div id="queue-student-container">
      <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
        <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png" />
      </button>
      <button id="add-to-queue-btn" v-on:click="select($event)">
        <img id="add-to-queue-btn-img" src="./../assets/ovinger-btn.png" />
      </button>
      <br>
      <p id="amount-of-students-in-queue">
        Antall studenter i kø: {{ amountOfStudentsInQueue }}
      </p>
      <table id="queue-table">
        <tr id="queue-table-headers">
          <th id="name">Navn</th>
          <th id="state">Status</th>
          <th id="digitalt"></th>
          <th id="room">Rom</th>
          <th id="type">Type</th>
        </tr>
        <tr
          class="student-column"
          v-for="student in students"
          v-on:click="selectRow($event)"
          v-bind:id="student.id"
          :key="student.studentId"
        >
          <td id="name-column">{{ student.name }}</td>
          <td>{{ student.statusInQueue }}</td>
          <td id="digital-column">{{ student.digital }}</td>
          <td id="room-column">
            {{ student.campus }} {{ student.building }} {{ student.room }}
            {{ student.tableNumber }}
          </td>
          <td id="type-column">{{ student.assessmentHelp }}</td>
        </tr>
      </table>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";
import RegisterInLine from "./RegisterInLine";
import axiosService from "@/services/axiosService";

export default {
  name: "QueueStudent",
  components: { Footer, Header },
  data() {
    return {
      students: [
        {
          name: "Sander Hansen",
          id: "1",
          location: "Realfagsbygget A4 112 6",
          type: "Godkjenning",
        },
        {
          name: "Hanne Hansen",
          studentId: "2",
          location: "digital",
          type: "Hjelp",
        },
        {
          name: "Sogne fri",
          studentId: "3",
          location: "digital",
          type: "Hjelp",
          statusInQueue: "BUSY",
        },
      ],
      amountOfStudentsInQueue: 0,
    };
  },
  created: async function () {
    await this.getAllStudentsCurrentlyInQueue();
    this.amountOfStudentsInQueue = this.students.length;
  },
  methods: {
    /**
     * method to get all the students in a queue from the database
     * changing assessmenthelp and digital or not to strings instead of booleans
     */
    getAllStudentsCurrentlyInQueue: async function () {
      await axiosService
        .getAllStudentsInQueue(this.$store.state.course.courseId)
        .then(
          function (response) {
            this.students = response.data;
            for (let i = 0; i < this.students.length; i++) {
              if (this.students[i].assessmentHelp) {
                this.students[i].assessmentHelp = "Godkjenning";
              } else {
                this.students[i].assessmentHelp = "Hjelp";
              }
              if (this.students[i].digital) {
                this.students[i].digital = "Digitalt";
              } else {
                this.students[i].digital = "På campus";
              }
            }
          }.bind(this)
        );
    },
    backToPreviousPage() {
      this.$router.go(-1);
    },
    select: function (event) {
      const targetId = event.currentTarget.id;
      if (targetId === "add-to-queue-btn") {
        this.$router.push({
          name: "registerInLine",
          component: RegisterInLine,
        });
      }
    },

    selectRow: async function (event) {
      if (this.$store.state.isStudentAssistant) {
        await axiosService.changeStateInQueueForStudent(
          event.currentTarget.id,
          this.$store.state.course.courseId,
          "BUSY"
        );
        for (let i = 0; i < this.students.length; i++) {
          if (this.students[i].toString() === event.currentTarget) {
            this.$store.commit("SET_STUDENTASSISTANT", this.students[i]);
          }
        }
        await this.$router.push({
          name: "studentAssQueueApprove",
        });
      }
    },
  },
};
</script>

<style scoped>
#container {
  height: 100%;
}
#queue-student-container {
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
#back-to-queue-btn,
#add-to-queue-btn {
  display: inline;
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
  position: absolute;
  left: 15px;
  top: 80px;
}
#add-to-queue-btn-img {
  height: 40px;
  width: 40px;
  position: absolute;
  right: 15px;
  top: 80px;
}
#amount-of-students-in-queue {
  text-align: center;
  color: rgba(255, 255, 255, 0.82);
  font-weight: lighter;
}

#queue-table {
  width: 100%;
  border-collapse: collapse;
}
#queue-table th,
#queue-table td {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  color: rgba(255, 255, 255, 0.82);
  font-family: inherit;
  padding-left: 30px;
}
#queue-table td {
  font-weight: lighter;
}
#queue-table-headers {
  outline: 0.5px solid white;
  width: 100%;
}
#type-column {
  padding-right: 10px;
}
td {
  cursor: pointer;
}
#student-column {
  /*background-color: rgba(0, 128, 0, 0.55);*/
}
@media only screen and (min-width: 800px) {
  #queue-student-container {
    height: 710px;
  }
}
</style>
