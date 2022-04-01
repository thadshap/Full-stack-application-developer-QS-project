<template>
  <Header></Header><br>
  <div id="studentAss-home-page">
    <div id="tabs-bar-wrapper">
    <div id="tabs-bar">
      <button id="left-btn" v-on:click="select($event)"><img id="student-img" src="./../assets/student.png">Student</button>
      <button id="middle-btn"><img id="student-ass-img" src="./../assets/student-ass.png">Student.ass.</button>
      <button id="right-btn"><div id="adjust-archive"><img id="archive-img" src="./../assets/archive.png">Arkivert</div></button>
    </div>
    </div>
    <div id="active-subject-container-wrapper">
    <div id="active-subject-container">
      <div id="sub-name-container">
        <p id="sub-name">Fullstack</p>
        <p id="sub-code">IDATT2105</p>
      </div>
      <div id="que-details-container">
        <p id="que-details">
          <img id="amount-of-students-img" src="./../assets/amount-students.png">
          100
        </p>
      </div>
      <div id="sub-feature-tabs">
        <button id="assigment-btn" v-on:click="select($event)" ><img id="assigment-img" src="./../assets/assigment.png"> Øvinger</button>
        <button id="que-btn" v-on:click="select($event)" ><img id="in-to-que-img" src="./../assets/in-to-que.png"> Til kø</button>
        <button id="active-que-btn" @click="changeActivityStatus()">Aktiver kø</button>
      </div>
    </div>
    </div>
  </div>
  <Footer></Footer>  
</template>
<script>
import Footer from "../components/Footer";
import Header from "../components/Header";
import HomeStudent from "./HomeStudent";
import QueueStudent from "./QueueStudent";
import AssigmentViewForStudentAss from "./AssigmentViewForStudentAss";

export default {
  name: "HomeStudentAss",
  components: {Footer, Header},
        methods:{
          select: function(event) {
            const targetId = event.currentTarget.id;
            if (targetId === "left-btn") {
              this.$router.push({
                name: 'student',
                component: HomeStudent,
              })
            }
            else if (targetId === "assigment-btn"){
              this.$router.push({
                name:'assigmentViewForStudentAss',
                component: AssigmentViewForStudentAss
              })
            }
            else if (targetId === "que-btn"){
              this.$router.push({
                name:'queueStudent',
                component: QueueStudent
              })
            }
          },
          changeActivityStatus(){
            if(document.getElementById("active-que-btn").innerHTML === "Aktiver kø"){
              document.getElementById("active-que-btn").innerHTML = "Deaktiver kø"
              document.getElementById("active-que-btn").style.position='relative'
              document.getElementById("active-que-btn").style.top='-30px'
            }else{
              document.getElementById("active-que-btn").innerHTML = "Aktiver kø"
              document.getElementById("active-que-btn").style.position='relative'
              document.getElementById("active-que-btn").style.top='0'
            }
          }
        },
};
</script>

<style scoped>
#studentAss-home-page{
  height: 710px;
  overflow: auto;
  object-fit: cover;
}
#tabs-bar-wrapper,#active-subject-container-wrapper{
    width: 100%;
    display: flex;
    justify-content: center;
}
#tabs-bar{
  height: 47px;
  margin: 10px 10px 0 10px;
  background-color: #011c39;
}
#left-btn,#middle-btn,#right-btn {
  display: inline;
  height: 46px;
  background: rgb(40, 40, 40);
  color: inherit;
  border: none;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  color: white;
  border-radius: .2em;
  font-size: 1.1rem;
  font-family: sans-serif;
  position: relative;
  top: -4px;
  padding-left: 15px;
  padding-right: 15px;
}
#left-btn,#middle-btn{
  position: relative;
  top: -7px;
}
#adjust-archive{
  position: relative;
  top: -3px;
}
#middle-btn{
  background-color: #011c39;
}
#active-subject-container{
  width: 295px;
  height: 130px;
  background-color: rgba(255, 255, 255, 0.82);
  margin: 20px 0 0 0;
  border-radius: .3em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  font-family: sans-serif;
  padding: 30px;
}
#sub-name,#sub-code,#que-details{
  margin: 3px;
  letter-spacing: 1px;
  font-weight: lighter;
}
#sub-name,#sub-code{
  font-size: 22px;
}
#que-details{
  margin-left: 20px;
}
#sub-name{
  color: #011c39;
}
#sub-name-container, #que-details-container{
  width: 140px;
  height: 80px;
  display: inline-block;
}
#sub-name-container{
  margin-right: 10px;
}
#que-details-container{
  position: relative;
  top: -30px;
}
#sub-feature-tabs{
  height: 40px;
}
#assigment-btn,#que-btn, #active-que-btn{
  display: inline;
  color: inherit;
  padding: 0;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  height: 30px;
  background-color: rgba(255, 255, 255, 0.01);
  border-style: solid;
  border-width: 2px;
  border-radius: .3em;
  font-weight: lighter;
  font-size: 14px;
}
#assigment-btn{
  float: left;
  border-color: rgba(1, 28, 57, 0.75);
  width: 92px;
  margin: 0;
}
#que-btn{
  border-color: green;
  width: 72px;
  margin: 0 9% 0 9%;
}
#amount-of-students-img{
  position: relative;
  top: 5px;
  margin-left: 38px;
}
#assigment-img,#in-to-que-img{
  width: 15px;
  position: relative;
  top: 2px;
}
#student-img,#student-ass-img,#archive-img{
  width: 23px;
  margin-right: 5px;
}
#archive-img{
  position: relative;
  top: 5px;
}
#active-que-btn{
  float: right;
  border-color: #0a64c2;
  padding: 5px;
}
</style>