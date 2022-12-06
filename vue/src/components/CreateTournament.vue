<template>
  <div>
      <p>have a list of users here and select who you want in your tournament</p>
      
      <div v-for="user in users" v-bind:key="user.id" >
        <p>{{user.username}}</p>
      </div>
      
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
data() {
  return {
    users: []
  };
},

created() {
  authService.getAllUsers().then((response) => {
    this.users = response.data;
  })
}
}
</script>

<style>

</style>