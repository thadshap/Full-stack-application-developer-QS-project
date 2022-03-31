<template>
    <div id="HomePage">
      <Header></Header>
      <menu-bar-administrator></menu-bar-administrator>
      <div id="container">
        <div id="courses">
          <div id="active-subject-container">
            <div id="que-details">
              <img id="amount-of-students-img" src="./../assets/amount-students.png">
              100 <br>
            </div>
            <div id="sub-name-container">
              <p id="sub-name">Fullstack</p>
              <p id="sub-code">IDATT2105</p>
            </div>
            <div id="que-details-container">
                Startdato: <br>20.03.01<br>
                <br>Sluttdato(forventet): 25.31.12
            </div>
            <div id="sub-feature-tabs">
              <button id="assigment-btn"><img id="assigment-img" src="./../assets/assigment.png" v-on:click="alertArchiveCourse"> Arkiver fag</button>
              <button id="allStudents" v-on:click="showStudents"> Se studenter</button>
              <button id="que-btn"><img id="in-to-que-img" v-on:click="alertDeleteCourse"> Slett fag</button>
            </div>
          </div>
        </div><br>
      </div>
      <Footer></Footer>
    </div>

</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import MenuBarAdministrator from "@/components/menuBarAdministrator";
export default {
  name: "HomeAdministrator",
  components: {MenuBarAdministrator, Footer, Header},
  data() {
    return {

    }
  },
  methods: {
    alertDeleteCourse() {
    },
    alertArchiveCourse() {
    },
    showStudents(){
      this.$router.push({
        name: 'allStudents'
      })
    }
  }
}
</script>

<style scoped>
@import './../styles/navBar.css';
@import './../styles/courses.css';
#HomePage{
  color: white;
}

#courses{
  display: grid;
  justify-items: center;
}

#active-subject-container{
  color:black;
  background-color: grey;
  border-color:  #011c39;
  width: 300px;

}
#allStudents{
  margin-left: 0px;
  margin-right: 20px;
  border-color: #011c39;
  width: 92px;
}

#assigment-btn{
  margin-left: 0px;
  margin-right: 20px;
  border-color: #011c39;
  width: 92px;
}
#que-btn{
  margin-left: 0px;
  margin-right: 0px;
  border-color: green;
  width: 72px;
}
#sub-name-container{
  margin-right: 10px;
}
#que-details{
  margin-bottom: -40px;
  margin-left: 0px;
}
#que-details-container{
  position: relative;
  top: 0px;
}

</style>