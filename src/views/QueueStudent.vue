<template>
  <div id="container">
  <Header></Header><br>
  <div id="queue-student-container">
    <button id="back-to-queue-btn" v-on:click="backToPreviousPage">
      <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
    </button>
    <button id="add-to-queue-btn" v-on:click="select($event)">
      <img id="add-to-queue-btn-img" src="./../assets/ovinger-btn.png">
    </button>
    <br>
    <p id="amount-of-students-in-queue">Antall studenter i kø: {{amountOfStudentsInQueue}}</p>
    <table id="queue-table">
      <tr id="queue-table-headers">
        <th id="name">Navn</th>
        <th id="room">Rom</th>
        <th id="type">Type</th>
      </tr>
      <tr id="student-column" v-for="student in students" :key="student">
        <td id="name-column">{{student.name}}</td>
        <td id="room-column">{{ student.location }}</td>
        <td id="type-column">{{ student.type }}</td>
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


export default {
  name: "QueueStudent",
  components: {Footer, Header},
  data() {
    return {
      students:[
        {
          name: "Sander Hansen",
          studentId: "1",
          location: "Realfagsbygget A4 112 6",
          type: "Godkjenning"
        },
        {
          name: "Hanne Hansen",
          studentId: "2",
          location: "digital",
          type: "Hjelp"
        },
        {
          name: "Sogne fri",
          studentId: "3",
          location: "digital",
          type: "Hjelp"
        },
      ],
      amountOfStudentsInQueue: 0,
    }
  },
  created() {
    this.amountOfStudentsInQueue= this.students.length

  },
  methods:{
    backToPreviousPage(){
      this.$router.go(-1)
    },
    select: function(event) {
      const targetId = event.currentTarget.id;
      if (targetId === "add-to-queue-btn") {
        this.$router.push({
          name: 'registerInLine',
          component: RegisterInLine,
        })
      }
    },
  },
};
</script>

<style scoped>
#container{
  height: 100%;
}
#queue-student-container{
  height: 410px;
  overflow: auto;
  object-fit: cover;
}
#back-to-queue-btn,#add-to-queue-btn{
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
#back-to-queue-btn-img{
  height: 20px;
  width: 20px;
  position: absolute;
  left: 15px;
  top: 80px;
}
#add-to-queue-btn-img{
  height: 40px;
  width: 40px;
  position: absolute;
  right: 15px;
  top: 80px;
}
#amount-of-students-in-queue{
  text-align: center;
  color: rgba(255, 255, 255, 0.82);
  font-weight: lighter;
}

#queue-table{
  width: 100%;
  border-collapse: collapse;
}
#queue-table th ,#queue-table td{
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  color: rgba(255, 255, 255, 0.82);
  font-family: inherit;
  padding-left: 30px;
}
#queue-table td{
  font-weight: lighter;
}
#queue-table-headers{
  outline: 0.5px solid white;
  width: 100%;
}
#type-column{
  padding-right: 10px;
}
#student-column{
  /*background-color: rgba(0, 128, 0, 0.55);*/
}
</style>