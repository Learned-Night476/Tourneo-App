<template>
  <div>
     <router-link v-show="matchesRound1.length === 0" id=createMatches v-bind:to="{name: 'createMatches', params: {tournamentId: this.tournamentId}}">Add Matches To The Tournament</router-link><br>
     <router-link id=viewMessagesFromViewTourney v-bind:to="{name: 'viewMessages', params: {tournamentId: this.tournamentId}}" >View Messages For This Tournament</router-link><br>
     <router-link id=addNewRounds v-show="matchesRound1.length !== 0 && matchesRound2.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 2}}">Add Round 2 Matches</router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 4 && matchesRound2.length !== 0 && matchesRound3.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 3}}">Add Round 3 Matches</router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 8 && matchesRound3.length !== 0 && matchesRound4.length === 0" v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 4}}">Add Round 4 Matches</router-link>
     <router-link id=addNewRounds v-show="tournament.participants > 16 && matchesRound4.length !== 0 && matchesRound5.length === 0"  v-bind:to="{name: 'createNewRounds', params: {tournamentId: this.tournamentId, roundNumber: 5}}">Add Round 5 Matches</router-link>
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
#addNewRounds {
  margin: 20px;
  color:white;
  text-decoration: none;
}
#addNewRounds:hover {
  color: silver;
}

/* html {
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/colosseum.svg");
    background-repeat: no-repeat;
    background-position: center;
} */




/* .container {
  max-width: 1100px;
  margin: 0 auto;
  display:block;
  /* display: flex;
  flex-direction: column; */
  /* } */
/* 
  .split {
    display:block;
    float:left;
    flex-direction:row;
    }

  .round1 {
    display: block;
    float: right;
    display: flex;
  

  } */ 



 /* <header class="hero">
    <div class="hero-wrap">
     <p class="intro" id="intro">flexbox</p>
     <h1 id="headline">Tournament</h1>
     <p class="year"><i class="fa fa-star"></i> 2015 <i class="fa fa-star"></i></p>
     <p>Ballin' Outta Control</p>
   </div>
  </header> */

/* 
  <section id="bracket">
  <div class="container">
  <div class="split split-one">
    <div class="round round-one current">
      <div class="round-details">Round 1<br/><span class="date">March 16</span></div>
      <ul class="matchup">
        <li class="team team-top">Duke<span class="score">76</span></li>
        <li class="team team-bottom">Virginia<span class="score">82</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">Wake Forest<span class="score">64</span></li>
        <li class="team team-bottom">Clemson<span class="score">56</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">North Carolina<span class="score">68</span></li>
        <li class="team team-bottom">Florida State<span class="score">54</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">NC State<span class="score">74</span></li>
        <li class="team team-bottom">Maryland<span class="score">92</span></li>
      </ul>     
      <ul class="matchup">
        <li class="team team-top">Georgia Tech<span class="score">78</span></li>
        <li class="team team-bottom">Georgia<span class="score">80</span></li>
      </ul> 
      <ul class="matchup">
        <li class="team team-top">Auburn<span class="score">64</span></li>
        <li class="team team-bottom">Florida<span class="score">63</span></li>
      </ul> 
      <ul class="matchup">
        <li class="team team-top">Kentucky<span class="score">70</span></li>
        <li class="team team-bottom">Alabama<span class="score">59</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">Vanderbilt<span class="score">64</span></li>
        <li class="team team-bottom">Gonzaga<span class="score">68</span></li>
      </ul>                   
    </div>  <!-- END ROUND ONE --> */
</style>
