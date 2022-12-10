<template>
  <div>
    <p v-show="isThereDuplicates">You Cannot Have A Player Be In The Tournament Twice</p>
    <p v-show="usernameIncorrect">The Username You Put In Does Not Exist</p>
      <br>
      <p>{{tournament.participants}}</p>

        <div v-show="didYouMakeAllTheMatches">
            <label for="homeUsername">Home Player</label> &nbsp;
            <select type="text" class="homePlayer" name="homeUsername" v-model="player" >
                <option v-for="player in players" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;

            <label for="awayUsername">Away Player</label> &nbsp;
            <select type="text" class="awayPlayer" name="awayUsername" v-model="player2" >
                <option v-for="player in players" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;

            <button :disabled="isDisabled2 || isNotVerified"  type="button" v-on:click="createMatches"  >Add Match</button>
            <button v-on:click="verifyMatches">Verify</button>

        </div>
        <h3>Players Added To Tournament</h3>

        <div v-for="match in matches" v-bind:key="match.id">
          <p>Matches : {{match.playerUsername}} vs {{match.awayPlayerUsername}}</p>
        </div>
        <p>Matches You Have: {{matches.length}}</p>
        <p>Matched You Need: {{tournament.participants / 2}}</p>
        <!-- <button v-show="isDisabled2" v-on:click="createMatches">Add Players To Tournament</button> -->
        <!-- <div v-for="player in players" v-bind:key="player.id">
          <p>{{player.playerId}}</p>
        </div> -->
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
  name: "create-matches",
data() {
  return {
    players: [],
    tournamentId : this.$route.params.tournamentId,
    tournament: {},
    show: false,
    player: "",
    player2: "",
    currentPlayers: [],
    isThereDuplicates: false,
    usernameIncorrect: false,
    matches: [],
    didYouMakeAllTheMatches: true,
    isNotVerified: true
 
  };
},

created() {
  authService.getAllPlayers().then((response) => {
    this.players = response.data;
  });

   authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  });
},

methods: {

  verifyMatches(){

      if(this.player.playerId == this.player2.playerId) {
          this.isThereDuplicates = true;
      }
      
   else if (this.matches.filter(e => e.playerId === this.player.playerId || e.playerId === this.player2.playerId || e.awayPlayerId === this.player.playerId || e.awayPlayerId === this.player2.playerId).length > 0){
        this.isThereDuplicates = true;    
      }

    else {
  
      this.isThereDuplicates = false;
      this.isNotVerified = false;
    }   
  },

  clearPlayers(){
    this.currentPlayers = [];
  },

  createMatches(){
      let match = {
          round: 1,
          winner: 0,
          tournamentId: this.tournamentId,
          playerId: this.player.playerId,
          awayPlayerId: this.player2.playerId,
          playerUsername: this.player.username,
          awayPlayerUsername: this.player2.username
      }

           authService.createTournamentUser(this.player.playerId, this.tournamentId)
       authService.createTournamentUser(this.player2.playerId, this.tournamentId)

        this.matches.push(match)
       authService.createMatch(match);
       this.finishedCreatingMatches();
       this.isNotVerified = true;
    
  },



finishedCreatingMatches() {
    if(this.matches.length === this.tournament.participants / 2) {
        this.didYouMakeAllTheMatches = false;
    }

    else {
        this.didYouMakeAllTheMatches = true;
    }
}
  
}, computed: {
  isDisabled2(){
    if(this.player == "" || this.player2 == "") {
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