<template>
    <div id="HomePage">
      <Header></Header>
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
  color: white;
  display: grid;
  justify-items: center;
}
input{
  margin-top: 10px;
}
</style>