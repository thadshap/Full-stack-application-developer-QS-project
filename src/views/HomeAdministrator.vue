<template>
    <div id="HomePage">
      <Header></Header><br>
      <menu-bar-administrator></menu-bar-administrator>
      <div id="container">
        <div id="courses">
          <div id="active-subject-container">
            <div id="sub-name-container">
              <p id="sub-name">Fullstack</p>
              <p id="sub-code">IDATT2105</p>
            </div>
            <div id="que-details-container">
              <div id="que-details">
                <img id="amount-of-students-img" src="./../assets/amount-students.png">
                100 <br>
                Startdato: 20.03.01
                Forventet sluttdato: 25.31.12
              </div>
            </div>
            <div id="sub-feature-tabs">
              <button id="assigment-btn"><img id="assigment-img" src="./../assets/assigment.png"> Arkiver fag</button>
              <button id="allStudents"> Se studenter</button>
              <button id="que-btn"><img id="in-to-que-img"> Slett fag</button>

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
    makeNewCourse: {

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

</style>