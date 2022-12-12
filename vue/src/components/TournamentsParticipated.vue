<template>
  <div>
      <h2>Tournaments You Are In</h2>
      <div class="usersTournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <!-- <router-link id="linkToMyTournaments" v-bind:to="{name: 'viewTournament', params: {tournamentId: tournament.tournamentId}}">{{tournament.tournamentName}}</router-link> -->
          <p>{{tournament.tournamentName}}</p>
      </div>
  </div>
</template>

<script>
import authService from '../services/AuthService'
export default {
    name: 'tournaments-participated',

    data() {
        return {
            randomString: "Hi",
            tournaments: []
        };
    },

    created() {
        authService.getTournamentsByUserId(this.$store.state.user.id).then((response) => {
            this.tournaments = response.data;
        });
    }
}
</script>

<style>

</style>