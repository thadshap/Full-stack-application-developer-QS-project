<template>
  <footer class="footer-under">
    <h5 class="footer-bold-letter">Info∙Support∙Marketing</h5>
    <h5 class="footer-bold-letter">Terms of Use∙Privacy Policy</h5>
    <h5 class="footer-letter">© 2022 QS Website</h5>
  </footer>
</template>
<script>
export default {
  name: "Footer"
}
</script>

<style scoped>
.footer-under {
  background-color: #011c39;
  width: 100%;
  position:fixed;
  bottom:0;
}

.footer-bold-letter, .footer-letter {
  text-align: center;
  font-weight: normal;
  color: white;
  margin: 10px;
}

.footer-bold-letter{
  font-weight: bold;
}

</style>