<template>
  <div>
      <h2>Tournaments You Are In</h2>
      <div class="usersTournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <router-link id="linkToMyTournaments" v-bind:to="{name: 'nonAdminView', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}}</router-link>

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
</style>