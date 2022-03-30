<template>
  <div id="inputFields">
    <Header></Header>
    <menu-bar-administrator></menu-bar-administrator>
    <Footer></Footer>
  </div>
</template>

<script>
import MenuBarAdministrator from "@/components/menuBarAdministrator";
import Footer from "@/components/Footer";
import Header from "@/components/Header";
export default {
  name: "AddNewCourse",
  components: {Footer, MenuBarAdministrator, Header}
}
</script>

<style scoped>

</style>