<template>
  <div>
      <h2 class="tourneyLinkHeader" id="tourPart">Tournaments You Are In</h2>
      <div class="tournamentsIn" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <router-link id="linkToTournamentsIn" v-bind:to="{name: 'nonAdminView', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}} <p>Status: {{tournament.tournamentStatus}}</p><p> Winner: {{tournament.winner}}</p></router-link>

      </div>
  </div>
</template>

<script>
import authService from '../services/AuthService'
export default {
    name: 'tournaments-participated',

    data() {
        return {
            tournaments: []
        };
    },

    created() {
        authService.getTournamentsParticipated(this.$store.state.user.id).then((response) => {
            this.tournaments = response.data;
        });
    }
}
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

.tournamentsIn {
    display: flex;
    flex-direction: column;
    align-items: stretch;
    background: rgb(57, 57, 57);
    height: 200px;
    margin-top: 10px;
    border-radius: 4px;
    

}
#linkToTournamentsIn:hover {
    background: orangered;
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/colosseum.svg");
    background-repeat: no-repeat;
    background-position: center;
    
}
#linkToTournamentsIn {
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/colosseum.svg");
    background-repeat: no-repeat;
    background-position: center;
    text-align:left;
    padding: 10px;
    height: 200px;
    font-size: x-large;
    color:white;
    text-decoration: none;
    border-radius: 4px;
}
.tourneyLinkHeader {
    padding-left: 10px;
    font-size: 20px;
    background-color: rgb(57, 57, 57);
}

#tourPart {
  color: orangered;
}
</style>