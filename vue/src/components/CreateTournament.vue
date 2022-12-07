<template>
  <div>
      <!-- <p>have a list of users here and select who you want in your tournament</p>
      
      <div v-for="user in users" v-bind:key="user.id" >
        <p>{{user.username}}</p>
      </div> -->
      <br>
      <label for="tName">Name of the Tournament</label> &nbsp;
      <input type="text" id="tName" name="tName" v-model="tournament.tournamentName" ><br><br>
      <label for="participants">Number of players</label> &nbsp;
      <!-- <input type="number" id="participants" name="participants"> -->
      <select id="participants" name="participants" v-model="tournament.participants">
            <option value="4">4</option>
            <option value="8">8</option>
            <option value="16">16</option>
            <option value="32">32</option>
          </select>
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
  name: "create-tournament",
data() {
  return {
    users: [],
    tournament: {
      tournamentId: Math.floor(Math.random() * (1000 - 100) + 100),
      participants: null,
      adminUser: this.$store.state.user.id,
      tournamentStatus: "Ongoing",
      tournamentType: 1,
      tournamentName: ""
    }
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