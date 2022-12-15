<template>
  <div class="newMatchForm">
    <p v-show="isThereDuplicates">You Cannot Have A Player Be In The Tournament Twice</p>
    <p v-show="usernameIncorrect">The Username You Put In Does Not Exist</p>
      <br>
      <h1 id="tourneyNameInMatch">{{tournament.tournamentName}}</h1>
      <p id="displayRoman" >{{ displayParticipantsRoman() }}</p>
  

        <div >
            <label id="inputLabel" for="homeUsername" v-show="this.num - 1 != tournament.participants">Seed {{this.num}}</label> &nbsp;
            <select type="text" class="homePlayer" v-show="tournament.participants != tournamentUsers.length" name="homeUsername" v-model="player" >
                <option v-for="player in players" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;
        </div>
         <button id="addMatch" v-show="tournament.participants != tournamentUsers.length"  type="button" v-on:click="createTournamentUser" >Add user</button>
          <button id="addMatch" v-show="tournament.participants === tournamentUsers.length && show"  type="button" v-on:click="createMatches" >Create Matches</button>
          
        <h3>Competitors Added</h3>

        <div v-for="match in matches" v-bind:key="match.id">
          <p>Matches : {{match.playerUsername}} vs {{match.awayPlayerUsername}}</p>
        </div>
        <p>Users You Have: {{tournamentUsers.length}}</p>
        <div v-for="user in usernames" v-bind:key="user.id">
            <p>{{user}}</p>
        </div>
       <router-link id="toTourneyFromMatches" v-bind:to="{name: 'viewTournament', params: {tournamentId: this.tournamentId}}"><button class="backToTourny"> Back To Tournament</button></router-link>
                                             <!-- v-show="!didYouMakeAllTheMatches" :logic is wrong -->
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
  name: "create-matches",
data() {
  return {
    players: [],
    usernames: [],
    tournamentUsers: [],
    tournamentId : this.$route.params.tournamentId,
    tournament: {},
    tournamentName: "",
    show: true,
    player: "",
    player2: "",
    currentPlayers: [],
    isThereDuplicates: false,
    usernameIncorrect: false,
    matches: [],
    didYouMakeAllTheMatches: true,
    isNotVerified: false,
    num: 1
 
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
  createTournamentUser() {

    let add = true;
  
    let tournamentUser = {
        tournamentId: this.tournamentId,
        playerId: this.player.playerId,
        seed: this.num
      }

    this.tournamentUsers.forEach( (x) => {
      if (x.playerId === this.player.playerId) {
        add = false;
        this.isThereDuplicates = true;
      }
    })
    if (add) {
      this.isThereDuplicates = false;
    authService.createTournamentUser(this.player.playerId, this.tournamentId, tournamentUser)
    this.num += 1;

    this.tournamentUsers.unshift(tournamentUser)
    this.usernames.unshift(this.player.username)
    }

  },
  createMatches(){

    for (let i = 0; i <  (this.tournament.participants) / 2; i++) {
      let j = i + 1;
      let match = {
          round: 1,
          winner: 0,
          tournamentId: this.tournamentId,
          playerId: this.tournamentUsers[i].playerId,
          awayPlayerId: this.tournamentUsers[this.tournament.participants - j].playerId,
          playerUsername: this.usernames[i],
          awayPlayerUsername: this.usernames[this.usernames.length - j]
      }

      authService.createMatch(match);
      this.show = false;

    }
    
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
    if(this.matches.length === this.tournament.participants) {
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
  font-family:'Times New Roman', Times, serif;
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

 #inputSeed {
   display: flex;
   padding-left: 300px;
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
  margin-right: 30px;
}
.awayPlayer {
  background: rgba(128, 128, 128, 0.664);
  color: white;
  margin-bottom: 50px;
  width: 200px;
  height: 40px;
  text-align:center;
}
#toTourneyFromMatches {
  color: orange;
  text-decoration: none;
}

#toTourneyFromMatches:hover {
  color:silver;
}



</style>