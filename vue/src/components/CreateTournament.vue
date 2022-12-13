<template>
  <div class="newTourneyForm">
    <p v-show="isThereDuplicates">You Cannot Have A Player Be In The Tournament Twice</p>
    <p v-show="usernameIncorrect">The Username You Put In Does Not Exist</p>
      <br>
      <label id="tournamentName" for="tName">Name Your Tournament</label> &nbsp;
      <input class="createTournamentName" type="text" id="tName" name="tName" v-model="tournament.tournamentName" ><br><br>
     
     <label for="tourneyType">Type of Tournament</label> &nbsp;
      <select class="tourneyType" id="tourneyType" :disabled="currentPlayers.length > 0" v-model="tournament.tournamentType">
        <option value="0">Other</option>
        <option value="1">Sports</option>
        <option value="2">Video Games</option>
        <option value="3">League of Legends </option>
        <option value="4">Cards</option>
      </select>
     
      <label  for="participants">Number of Competitors</label> &nbsp;
     <!-- <input type="number" id="participants" name="participants"> -->
      <select  class="numberOfPlayers" :disabled="currentPlayers.length > 0" id="participants" name="participants" v-model="tournament.participants">
            <option value="4">Four (IV)</option>
            <option value="8">Eight (XIII)</option>
            <option value="16">Sixteen (XVI)</option>
            <option value="32">Thirty-Two (XXXII)</option>
          </select> <br><br>
          <button id="createTournamentButton" :disabled="isDisabled" type="button" v-on:click="submitTournament" >Create Tournament</button>
        <!-- <div>
        <label for="homeUsername">Add Player To Tournament</label> &nbsp;
          <input type="text" class="homePlayer" name="homeUsername" v-model="player" /> &nbsp;
          <button :disabled="isDisabled2"  type="button" v-on:click="addPlayer"  >Add Player</button>
          <button v-show="currentPlayers.length > 0" v-on:click="clearPlayers">Clear Players</button>

        </div>
        <h3>Players Added To Tournament</h3>
        <div v-for="player in currentPlayers" v-bind:key="player.id">
          <p>Player : {{player}}</p>
        </div>
        <button v-show="didSubmitTournament && isDisabled2" v-on:click="createMatches">Add Players To Tournament</button> -->
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
      tournamentType: "",
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
        this.tournament = newTournament;
        this.show = true;
        this.didSubmitTournament = true;
       this.$router.push('/');
      
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
      let j = i + 1;
      
    
     let homePlayer = 1;
     let awayPlayer = 1;
      authService.getPlayerIdByUsername(this.currentPlayers[i]).then((response) => {
        homePlayer = response.data;
      });
       authService.getPlayerIdByUsername(this.currentPlayers[this.currentPlayers.length - j]).then((response) => {
         awayPlayer = response.data;
       });

      let match = {
        round: 1,
        winner: 0,
        tournamentId: 19,
        playerId: homePlayer,
        awayPlayerId : awayPlayer
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

.tourneyType {
   background: rgba(128, 128, 128, 0.664);
  color: white;
  margin-bottom: 50px;
  width: 200px;
  height: 40px;
  text-align: center;
}
.newTourneyForm {
  display: flex;
  flex-direction: column;
  color: white;
  margin-top: 10%;
  text-align: center;
  justify-content: space-evenly;
  align-items: center;
  
 }
 .form-create {
  width: 40%;
  justify-content: space-evenly;
  padding: 10px;
  margin: 15px;
}

#tournamentName {
  color: orange;
  font-size: 25px;
}

#createTournamentButton {
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 40px;
  width: 200px;
  border-radius: 4px;

}

#createTournamentButton:hover {
  background-color: rgba(140, 0, 255, 0.75);
}
#createTournamentButton:hover[disabled] {
    background-color:  rgba(0, 0, 0, 0.15);
    opacity:0.4;
    cursor : not-allowed !important;
}

.createTournamentName {
  width: 40%;
  justify-content: space-evenly;
  padding: 10px;
  margin: 15px;
  background-color:rgba(128, 128, 128, 0.664);
  color: orange;
}

.numberOfPlayers {
  background: rgba(128, 128, 128, 0.664);
  color: white;
  margin-bottom: 50px;
  width: 200px;
  height: 40px;
  text-align: center;
}


</style>