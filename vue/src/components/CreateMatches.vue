<template>
  <div class="newMatchForm">
    <p v-show="isThereDuplicates">You Cannot Have A Player Be In The Tournament Twice</p>
    <p v-show="usernameIncorrect">The Username You Put In Does Not Exist</p>
      <br>
      <h1 id="tourneyNameInMatch">{{tournament.tournamentName}}</h1>
      <p id=displayRoman >{{ displayParticipantsRoman() }}</p>

        <div v-show="didYouMakeAllTheMatches">
            <label for="homeUsername">Home Player</label> &nbsp;
            <select type="text" class="homePlayer" name="homeUsername" v-model="player" >
                <option v-for="player in players" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;

            <label for="awayUsername">Away Player</label> &nbsp;
            <select type="text" class="awayPlayer" name="awayUsername" v-model="player2" >
                <option v-for="player in players" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;

           
            <button id="verify" v-on:click="verifyMatches">Verify</button>

        </div>
         <button id="addMatch" :disabled="isDisabled2 || isNotVerified"  type="button" v-on:click="createMatches"  >Add Match</button>
        <h3>Competitors Added</h3>

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
    tournamentName: "",
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

  displayParticipantsRoman() {
      if(this.tournament.participants == 4) {
        return "IV";
      }
      if(this.tournament.participants == 8) {
        return "VIII";
      }
      if(this.tournament.participants == 16) {
        return "XVI";
      }
      if(this.tournament.participants == 32) {
        return "XXXII";
      }

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
#displayRoman {
  display: flex;
  font-size: 60px;
  text-shadow: -5px -5px 5px black;
}

.newMatchForm {
  display: flex;
  flex-direction: column;
  color: white;
  margin-top: 10%;
  text-align: center;
  justify-content: space-evenly;
  align-items: center;
  
 }
 
#tourneyNameInMatch {
  color: orange;
  font-size: 25px;
}

#addMatch {
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 40px;
  width: 200px;
  border-radius: 4px;

}

#addMatch:hover {
  background-color: rgba(140, 0, 255, 0.75);
}
#addMatch:hover[disabled] {
    background-color:  rgba(0, 0, 0, 0.15);
    border-color: none;
    opacity:0.4;
    
}
#verify{
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 40px;
  width: 200px;
  border-radius: 4px;
}
#verify:hover {
  background-color: rgba(140, 0, 255, 0.75);
}
.createTournamentName {
  width: 40%;
  justify-content: space-evenly;
  padding: 10px;
  margin: 15px;
  background-color:rgba(128, 128, 128, 0.664);
  color: orange;
}

.homePlayer {
  background: rgba(128, 128, 128, 0.664);
  color: white;
  margin-bottom: 50px;
  width: 200px;
  height: 40px;
  text-align:center;
}
.awayPlayer {
  background: rgba(128, 128, 128, 0.664);
  color: white;
  margin-bottom: 50px;
  width: 200px;
  height: 40px;
  text-align:center;
}




</style>