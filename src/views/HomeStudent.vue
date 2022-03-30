<template>
<div id="student-home-page">
 <Header></Header><br>
  <div id="tabs-bar">
    <button id="left-btn"><img id="student-img" src="./../assets/student.png">Student</button>
    <button id="middle-btn"><img id="student-ass-img" src="./../assets/student-ass.png">Student.ass.</button>
    <button id="right-btn"><div id="adjust-archive"><img id="archive-img" src="./../assets/archive.png">Arkivert</div></button>
  </div>
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
      <button id="assigment-btn"><img id="assigment-img" src="./../assets/assigment.png"> Øvinger</button>
      <button id="que-btn"><img id="in-to-que-img" src="./../assets/in-to-que.png"> Til kø</button>
    </div>
  </div>
  <Footer></Footer>
</div>
</template>
<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "StudentSite",
  components: {Footer, Header},
};
</script>

<style scoped>
@import './../styles/courses.css';
#student-home-page{
  height: 850px;
  width: 412px;
  
}
#tabs-bar{
  height: 47px;
  margin: 10px 10px 0 10px;
  background-color: #011c39;
}
#left-btn,#middle-btn,#right-btn {
  display: inline;
  width: 130.6px;
  height: 46px;
  background: rgb(40, 40, 40);
  color: inherit;
  border: none;
  padding: 0;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  color: white;
  border-radius: .2em;
  font-size: 1.1rem;
  font-family: sans-serif;
  position: relative;
  top: -4px;
}
#left-btn,#middle-btn{
  position: relative; 
  top: -7px;
 }
#adjust-archive{
  position: relative; 
  top: -3px;

}
#active-subject-container{
  width: 295px;
  height: 130px;
  background-color: rgba(255, 255, 255, 0.82);
  border-color: #0a64c2;
}

</style>