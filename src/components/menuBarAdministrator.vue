<template>
  <div id="menuBar">
    <button id="newCourse"  v-on:click="makeNewCourse">+ Nytt emne</button>
    <button id="title"  v-on:click="showCourses">Dine fag</button>
  </div>
</template>

<script>
export default {
  name: "menuBarAdministrator",
  data() {
    return {
    }
  },
  methods: {
    makeNewCourse() {
      this.$router.push({
        name: 'addNewCourse'
      })
    },
    showCourses() {
      this.$router.push({
        name: 'administrator'
      })
    }

  }
}
</script>

<style scoped>
  #menuBar{
    text-align: center;
  }
  #newCourse{
    margin-top: 20px;
    display: flex inline;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
    border-width: 0 0 2px 0;
    border-color: steelblue;
    border-radius: 20px 0px 0px 0px;
  }
  #title{
    display: flex inline;
    text-align: center;
    font-size: 28px;
    font-weight: bold;
    border-width: 0 0 2px 2px;
    border-color: steelblue;
    margin-right: 65px;
    border-radius: 0px 20px 0px 0px;
  }

  #newCourse, #title{
    height: 100%;
    cursor: pointer;
    color:white;
    background-color: #2d2c2c;
  }

  #newCourse:hover{
    background-color: steelblue;
    border-radius: 20px 0px 0px 0px;

  }
  #title:hover{
    background-color: steelblue;
    border-radius: 0px 20px 0px 0px;
  }
</style>