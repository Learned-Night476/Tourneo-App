<template>

  <div id="app">
    <div id="nav">
      <navigation-bar />
    
    </div>
    <router-view />
    <div class="graphic">
      <!-- <img class="bg" src='../capstone-images/olivebranch.svg'> -->
      <svg class="background-poster"></svg>
      </div>
      
  </div>
  
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
html{
  background-image:
  linear-gradient(black, rgb(145, 68, 145, 0.85)),
  url("../capstone-images/olivebranch.svg");
  background-repeat: no-repeat;
  min-height: 100%;
  background-attachment: fixed;
  background-position: center;
  height: auto;  
  color: white;
  font-family: Kanit, Helvetica, sans-serif;
  
}
.needAnAccount {
  color:white;
  text-decoration: none;
}

.needAnAccount:visited {
  color:silver;
  text-decoration: none;
}




</style>
