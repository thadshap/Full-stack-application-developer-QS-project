<template>
  <div id="queue-container">
    <Header></Header><br>
    <div id="sub-header-container">
    <p id="sub-name">Fullstack</p>
    <p id="sub-code">IDATT2105</p>
    </div>
    <p id="status">Status: Ikke godkjent</p>
    <hr class="blue-line">
    <div id="oving-rules-container">
      <p id="oving-rules">Øvingsregler</p>
      <button id="oving-rules-btn">
        <img id="drop-down-arrow-img" src="./../assets/drop-down-arrow.png">
      </button>
      <hr class="blue-line">
    </div>
    <div id="oving-container">
      <p id="oving-header">Øving 1</p>
      <p id="approve-header">Ikke godkjent</p>
      <hr class="line-under-oving">
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Footer from "../components/Footer";
import Header from "../components/Header";

export default {
  name: "AssigmentView",
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
#status,#oving-rules{
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  text-align: center;
  font-size: 24px;
}
#oving-container{
  color: rgba(255, 255, 255, 0.89);
  letter-spacing: 1px;
  font-weight: lighter;
  font-size: 24px;
}
.blue-line{
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: #0a64c2;
  border: none;
}
.line-under-oving{
  margin: 24px 20px 24px 20px;
  height: 2.5px;
  background-color: rgba(66, 66, 66, 0.73);
  border: none;
}
#oving-header,#approve-header{
  display: inline;
  margin: 0 35px 0 35px;
  font-size: 20px;
}
#oving-header{
  width: 70px;
}
#approve-header{
  float: right;
}
#oving-rules-container{
 text-align: center;
}
#oving-rules,#oving-rules-btn{
  display: inline;

}
#oving-rules{
  margin-left: 35px;
}
#oving-rules-btn{
  float: right;
  margin-right: 40px;
  position: relative;
  top: 4px;
  color: inherit;
  border: none;
  padding: 0;
  font: inherit;
  cursor: pointer;
  outline: inherit;
  width: 20px;
  height: 20px;
  background-color: inherit;
}
#drop-down-arrow-img{
  height: 15px;
  width: 25px;
}
</style>