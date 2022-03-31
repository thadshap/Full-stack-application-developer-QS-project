<template>
  <Header></Header><br>
<div id="register-in-line-container">
  <button id="back-to-queue-btn">
    <img id="back-to-queue-btn-img" src="./../assets/back-to-queue.png">
  </button>

</div>
  <Footer></Footer>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "RegisterInLine",
  components: {Footer, Header},
};
</script>

<style scoped>
#back-to-queue-btn{
  color: inherit;
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
</style>