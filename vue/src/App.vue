<template>
<!-- <div style="background-image: ../capstone-images/olivebranch.svg; background-size: 400px 200px;"> -->

  <div id="app">
    <div id="nav">
      <navigation-bar />
      
    </div>
    <router-view />
    <div class="graphic">
      <img class="nothing"/>
      <svg class="background-poster"></svg>
      </div>
      
  </div>
  
  <!-- </div> -->

</template>

<script>

import NavigationBar from '../src/components/NavigationBar.vue';
export default {
  name: "app",
  components: {
    NavigationBar
    // ReviewList
  }
};
</script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&display=swap');
body {
  
  background: linear-gradient(black, rgb(145, 68, 145));
  color: white;
  font-family: Kanit, Helvetica, sans-serif;
  
}
#app {
  background-image: url("../capstone-images/olivebranch.svg");
  background-size:100% 100%;
}

</style>
