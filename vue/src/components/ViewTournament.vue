<template>
  <div>
    <div class="test">
    
    <router-link  v-show="matchesRound1.length === 0" id=createMatches v-bind:to="{name: 'createMatches', params: {tournamentId: this.tournamentId}}"><button class="roundButtons">Add Matches</button></router-link><br>
     <router-link  id=viewMessagesFromViewTourney v-bind:to="{name: 'viewMessages', params: {tournamentId: this.tournamentId}}" > <button class="roundButtons">Tournament Messages</button></router-link><br>
     <router-link id=addNewRounds v-show="matchesRound1.length !== 0 && matchesRound2.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 2}}"><button class="roundButtons">Add Round 2 Matches</button></router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 4 && matchesRound2.length !== 0 && matchesRound3.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 3}}" ><button class="roundButtons">Add Round 3 Matches</button></router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 8 && matchesRound3.length !== 0 && matchesRound4.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 4}}"><button class="roundButtons">Add Round 4 Matches</button></router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 16 && matchesRound4.length !== 0 && matchesRound5.length === 0"  v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 5}}"><button class="roundButtons">Add Round 5 Matches</button></router-link>
    <router-link v-bind:to="{name: 'admin', params: {tournamentId: this.tournamentId}}"> <button id="updateMatchInVT" class="roundButtons">Update Match Result</button></router-link>
    <button class="roundButtons" id="updateMatchInVT" type="button" v-on:click="markTournamentAsCompleted">End Tournament</button>
 
    </div>
    <h1 class="championOfTheWorld">{{tournament.winner}}</h1>
    <div class="allRounds">
      <div class="round1">
          <match v-for="n in (tournament.participants / 2)" v-bind:key="n.id" :match="matchesRound1[n-1]" />

      </div>
      <div class="round2" >
          <match v-for="n in (tournament.participants / 4)" v-bind:key="n.id" :match="matchesRound2[n-1]"/>
      </div>
       <div class="round3"  >
          <match  v-for="n in updateRound3()" v-bind:key="n.id" :match="matchesRound3[n-1]"/>
      </div>
      <div class="round4"  >
          <match v-for="n in updateRound4()" v-bind:key="n.id" :match="matchesRound4[n-1]"/>
      </div>
      <div class="round5"  >
          <match v-for="n in updateRound5()" v-bind:key="n.id" :match="matchesRound5[n-1]"/>
      </div>
    </div>
    <button v-show="isTournamentCompleted" type="button" v-on:click="addWinner">Declare A Winner</button> &nbsp;
    <label v-show="isTournamentCompleted" for="winner">Winner Username</label> &nbsp;
    <input v-show="isTournamentCompleted" name="winner" type="text" v-model="winner"/><br>
    
   
  </div>
</template>

<script>
import authService from "../services/AuthService"
import Match from './Match.vue'
export default {
  components: { Match },
  name: "view-tournament",
data() {
  return{
    tournamentId : this.$route.params.tournamentId,
    tournament: {},
    matchesRound1: [],
    matchesRound2: [],
    matchesRound3: [],
    matchesRound4: [],
    matchesRound5: [],
    topPixelNumber: 100,
    firstTimeThrough : true,
    winner: "",
    isTournamentCompleted: false
  }
},

created() {
  authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  });

  authService.getMatchByTournamentIdAndRound(this.tournamentId, 1).then((response) => {
    this.matchesRound1 = response.data;
  });

  authService.getMatchByTournamentIdAndRound(this.tournamentId, 2).then((response) => {
    this.matchesRound2 = response.data;
  });

   authService.getMatchByTournamentIdAndRound(this.tournamentId, 3).then((response) => {
    this.matchesRound3 = response.data;
  });

  authService.getMatchByTournamentIdAndRound(this.tournamentId, 4).then((response) => {
    this.matchesRound4 = response.data;
  });

  authService.getMatchByTournamentIdAndRound(this.tournamentId, 5).then((response) => {
    this.matchesRound5 = response.data;
  });
},

methods: {
  add100px(){
    if(this.firstTimeThrough){
      this.firstTimeThrough = false;
      return 0;
    }

    else {
      return 100;
    }
  },

// We might not have to use this logic, but keep it just in case.
   updateRound3()  {
    if ((this.tournament.participants / 8) < 1) {
      return 0;
    }
    else {
      return this.tournament.participants / 8;
    }
   },
  


   updateRound4() {
    if ((this.tournament.participants / 16) < 1) {
      return 0;
    }
    else {
      return this.tournament.participants / 16;
    }
   },

   updateRound5() {
    if ((this.tournament.participants / 32) < 1) {
      return 0;
    }
    else {
      return this.tournament.participants / 32;
    }
  },

  markTournamentAsCompleted() {
    authService.markTournamentCompleted(this.tournamentId);
    this.isTournamentCompleted = true;
    // this.$router.push('/');
  },

  addWinner() {
      authService.setTournamentWinner(this.tournamentId, this.winner);
      this.$router.push('/');
  }
}
};


</script>

<style scoped>
.championOfTheWorld{
  display:flex;
  background-color: rgb(57,57,57);
  background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/goldbranch.svg");
  background-size: 170px;
  background-repeat: no-repeat;
  background-position: center;
  color:white;
  width: 350px;
  height: 150px;
  align-items: center;
  justify-content: center;
  border-radius: 100px;
  margin-left: auto;
  margin-right: auto;
}
#updateMatchInVT{
  margin: 20px
}
.test {
  display: flex;
  flex-direction: row;
  margin-left: auto;
  margin-right: auto;
  justify-content: center;
  align-items: center;
  margin-top: auto;
  margin-bottom: auto;

  
}
.allRounds {
  padding-top: 5%;
  display: flex;
  align-items: center;
  margin: auto;
  margin-bottom: bottom;
}
.roundButtons {
  text-decoration: none;
  background-color: orange;
  color: black;
  height: 40px;
  width: 200px;
  border-radius: 4px;
  font-family: Kanit;
}
.roundButtons:hover {
  background-color: rgb(255, 182, 46);
}


.viewMsgBtn:hover {
  background-color: rgb(255, 182, 46);
}



.round1 {
  padding-left:100px;
  padding-right: 100px;

  
}
.round2 {
  padding-right: 100px;

  
}

.round3 {
  padding-right: 100px;

  
}

.round4 {
  padding-right: 100px;

  
}
#viewMessagesFromViewTourney {
  margin: 20px;
  color:white;
  text-decoration: none;
  
}

#viewMessagesFromViewTourney:hover {
  color:silver;
}
#createMatches:hover {
  color:silver;
}

#createMatches {
  margin: 20px;
  color:white;
  text-decoration: none;
  
}
#addNewRounds {
  margin: 20px;
  color:white;
  text-decoration: none;
}
#addNewRounds:hover {
  color: silver;
}


</style>
