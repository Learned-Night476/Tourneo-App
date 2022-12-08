<template>
  <div>
      <div v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <router-link v-bind:to="{name: 'viewTournament', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}}</router-link>
      </div>
  </div>
</template>

<script>
import authService from '../services/AuthService'
export default {
    name: 'view-all-users-tournaments',
    data(){ return {
        tournaments: []
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

</style>