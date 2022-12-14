<template>
  <div>
      <h2 class="tourneyLinkHeader">Tournaments You Created</h2>
      <div class="usersTournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <router-link id="linkToMyTournaments" v-bind:to="{name: 'viewTournament', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}} <p> Status: {{tournament.tournamentStatus}}</p></router-link>
      </div>
  </div>
</template>

<script>
import authService from '../services/AuthService'
export default {
    name: 'view-all-users-tournaments',
    data(){ return {
        tournaments: [],
        tournamentType: {}
    }
    }, 
    created() {
        authService.getTournamentByUserId(this.$store.state.user.id).then((response) => {
            this.tournaments = response.data;
        });
}
}

</script>


<style>
.usersTournaments {
    display: flex;
    flex-direction: column;
    align-items: stretch;
    background: rgb(57, 57, 57);
    height: 200px;
    margin-top: 10px;
    border-radius: 4px;
    

}
#linkToMyTournaments:hover {
    background: orange;
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/colosseum.svg");
    background-repeat: no-repeat;
    background-position: center;
    
}
#linkToMyTournaments {
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
}
 

</style>