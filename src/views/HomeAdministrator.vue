<template>
    <div id="HomePage">
      <Header></Header><br>
      <div id="container">
        <div id="buttons">
        <button id="newCourse">+ Nytt emne</button>
        <button id="title">Dine fag</button>
        </div>
        <div id="courses">
          <h3>hallo</h3>
        </div><br>
      </div>
      <Footer></Footer>
    </div>

</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
export default {
  name: "HomeAdministrator",
  components: {Footer, Header},
  data() {
    return {

    }
  }
}
</script>

<style scoped>
@import './../styles/navBar.css';
#HomePage{
  display: grid;
  color: white;
  justify-items: center;
  margin: -8px;
}
#container{
  display: grid;
  gap:1rem;
  grid-template-rows:  min-content;
  grid-template-columns: max-content min-content;
  grid-template-areas:
    'buttons'
    'courses';
}
#newCourse{
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
  border-width: 0 0 2px 0;
  border-color: steelblue;
}
#title{
  font-size: 28px;
  font-weight: bold;
  border-width: 0 0 2px 2px;
  border-color: steelblue;
}
#courses{
  grid-area: courses;
  text-align: center;
}
#newCourse, #title{
  height: 100%;
  background: transparent;
  cursor: pointer;
  color:white;
}
#buttons{
  background-color: #2d2c2c;
  grid-area: buttons;
  border-radius: 20px 20px 0px 0px;
}
#newCourse:hover{
  background-color: steelblue;
  border-radius: 20px 0px 0px 0px;

}
#title:hover{
  background-color: steelblue;
  border-radius: 0px 20px 0px 0px;
}
input{
  margin-top: 10px;
}
</style>