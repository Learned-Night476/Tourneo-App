<template>
  <div>
      <h2 class="tourneyLinkHeader" id="tourCreated">Tournaments You Created</h2>
      <div class="usersTournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <router-link id="linkToMyTournaments" v-bind:to="{name: 'viewTournament', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}} <p> Status: {{tournament.tournamentStatus}}</p><p> Winner: {{tournament.winner}}</p></router-link>
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
    display: flex;
    background-color: rgb(57, 57, 57);
    font-size: 25px;
    margin-bottom: 25;
    padding-top: 20px;
    padding-bottom: 20px;
    border-radius: 4px;
}

#tourCreated {
    color: orange;
}
 

</style>