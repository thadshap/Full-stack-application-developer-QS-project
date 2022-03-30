<template>
  <div id="queue-container">
    <Header></Header><br>
    <div id="sub-header-container">
    <p id="sub-name">Fullstack</p>
    <p id="sub-code">IDATT2105</p>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "Queue",
  components: {Footer, Header},
};
</script>

<style scoped>
#sub-name,#sub-code{
  letter-spacing: 1px;
  font-weight: lighter;
  color: rgba(255, 255, 255, 0.89);
  text-align: center;
}
#sub-name{
  font-size: 24px;
  margin-bottom: 0;
}
#sub-header-container{
  border-radius: .2em;
  border-style: solid;
  border-color: #0a64c2;
  border-width: 2.5px;
  margin: 0 20px 0 20px;
}
</style>