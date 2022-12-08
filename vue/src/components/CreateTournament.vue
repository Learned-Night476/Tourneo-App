<template>
  <div>
    <p v-show="isThereDuplicates">You Cannot Have A Player Be In The Tournament Twice</p>
    <p v-show="usernameIncorrect">The Username You Put In Does Not Exist</p>
      <br>
      <label for="tName">Name of the Tournament</label> &nbsp;
      <input type="text" id="tName" name="tName" v-model="tournament.tournamentName" ><br><br>
      <label for="participants">Number of players</label> &nbsp;
      <!-- <input type="number" id="participants" name="participants"> -->
      <select :disabled="currentPlayers.length > 0" id="participants" name="participants" v-model="tournament.participants">
            <option value="4">4</option>
            <option value="8">8</option>
            <option value="16">16</option>
            <option value="32">32</option>
          </select> <br><br>
          <button :disabled="isDisabled" type="button" v-on:click="submitTournament" >Create Tournament</button>
        <div>
        <label for="homeUsername">Add Player To Tournament</label> &nbsp;
          <input type="text" class="homePlayer" name="homeUsername" v-model="player" /> &nbsp;
          <button :disabled="isDisabled2"  type="button" v-on:click="addPlayer"  >Add Player</button>
          <button v-show="currentPlayers.length > 0" v-on:click="clearPlayers">Clear Players</button>

        </div>
        <h3>Players Added To Tournament</h3>
        <div v-for="player in currentPlayers" v-bind:key="player.id">
          <p>Player : {{player}}</p>
        </div>
        <button v-show="didSubmitTournament && isDisabled2" v-on:click="createMatches">Add Players To Tournament</button>
        <!-- <div v-for="player in players" v-bind:key="player.id">
          <p>{{player.playerId}}</p>
        </div> -->
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
  name: "create-tournament",
data() {
  return {
    players: [],
    tournament: {
      tournamentId: 0,
      participants: null,
      adminUser: this.$store.state.user.id,
      tournamentStatus: "Ongoing",
      tournamentType: "0",
      tournamentName: "",
      winner: null
    },
    show: false,
    player: "",
    currentPlayers: [],
    isThereDuplicates: false,
    usernameIncorrect: false,
    didSubmitTournament: false
  };
},

created() {
  authService.getAllPlayers().then((response) => {
    this.players = response.data;
  })
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
        this.show = true;
        this.didSubmitTournament = true;
        // this.$router.push('/');
      
  },

  addPlayer(){
    if (this.players.filter(e => e.username === this.player).length > 0){
      if(!this.currentPlayers.includes(this.player)) {
        this.currentPlayers.push(this.player);
        this.isThereDuplicates = false;
        this.usernameIncorrect = false;
      }
      else {this.isThereDuplicates = true;
            this.usernameIncorrect = false;
      }
    }
    else {
      this.usernameIncorrect = true;
      this.isThereDuplicates = false;
    }
    
    this.player ="";
  },

  clearPlayers(){
    this.currentPlayers = [];
  },

  createMatches(){
    for (let i = 0; i < this.currentPlayers.length / 2; i++) {
      // let j = i + 1;
      
      // let homePlayer = authService.getPlayerByUsername(this.currentPlayers[i])
      // let awayPlayer = authService.getPlayerByUsername(this.currentPlayers[this.currentPlayers.length - j]);

      let match = {
        home: 1,
        away: 2,
        round: 1,
        winner: 0,
        tournamentId: this.tournament.tournamentId
      };
      

      authService.createMatch(match);
    }
  }
  
}, computed: {
  isDisabled(){
    if(this.tournament.tournamentName ==="" || this.tournament.participants === null) {
      return true;
    }
    else{
      return false;
    }
  },

  isDisabled2(){
    if(this.currentPlayers.length == this.tournament.participants) {
      return true;
    }
    else{
      return false;
    }
  }
},
}

</script>

<style>

</style>