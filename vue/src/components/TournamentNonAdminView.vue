<template>
  <div>
      <router-link v-bind:to="{name: 'sendMessage', params: {tournamentId: this.tournamentId}}">Send The Admin Your Match Score</router-link>
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
   
  </div>
</template>

<script>
import authService from "../services/AuthService"
import Match from './Match.vue'
export default {
  components: { Match },
  name: "tournament-non-admin-view",
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
    firstTimeThrough : true
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
  }
}
};


</script>

<style>

.allRounds {
  padding-top: 10%;
  display: flex;
  align-items: center;
  
  margin: auto;

  
  
  
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

</style>
