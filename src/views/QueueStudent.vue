<template>
  <div id="queue-student-container">
    <Header></Header><br>
    <p id="amount-of-students-in-queue">Antall studenter i kø: 0</p>
    <table id="queue-table">
      <tr id="queue-table-headers">
        <th id="name">Navn</th>
        <th id="room">Rom</th>
        <th id="type">Type</th>
      </tr>
      <tr>
        <td id="name-column">Thadshajini Paramsothy</td>
        <td id="room-column">Realfagsbygget A4 112</td>
        <td id="type-column">Godkjenning</td>
      </tr>
    </table>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "QueueStudent",
  components: {Footer, Header},
};
</script>

<style scoped>
#amount-of-students-in-queue{
  text-align: center;
  color: rgba(255, 255, 255, 0.82);


}
#queue-table{
  width: 100%;
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
</style>