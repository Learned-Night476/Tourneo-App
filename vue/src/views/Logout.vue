<template>
  <h1 class="logoutButton">Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
<style scoped>
 .logoutButton {
   color:white;
 }

 
</style>
