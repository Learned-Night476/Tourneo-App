<template>
  <div id="app">
    <div id="nav">
      <navigation-bar />
      <!-- <navbar>
        <h1>he</h1>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </navbar> -->
      
    </div>
    <router-view />
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

</style>
