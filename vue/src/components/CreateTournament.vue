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
          </select> <br><br>
          <button :disabled="isDisabled" type="button" v-on:click="submitTournament" >Create Tournament</button>
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
      tournamentId: 0,
      participants: null,
      adminUser: this.$store.state.user.id,
      tournamentStatus: "Ongoing",
      tournamentType: "0",
      tournamentName: "",
      winner: null
    }
  };
},

methods: {
  submitTournament() {
    const newTournament = {
        tournamentId: this.tournament.tournamentId,
        participants: this.tournament.participants,
        adminUser: this.tournament.adminUser,
        tournamentStatus: this.tournament.tournamentStatus,
        tournamentType: this.tournament.tournamentType,
        tournamentName: this.tournament.tournamentName,
        winner: 1
      };

    
        authService.createATournament(newTournament);
        this.$router.push('/');
      
  }
  
}, computed: {
  isDisabled(){
    if(this.tournament.tournamentName ==="" || this.tournament.participants === null) {
      return true;
    }
    else{
      return false;
    }
  }
}
}

</script>

<style>

</style>