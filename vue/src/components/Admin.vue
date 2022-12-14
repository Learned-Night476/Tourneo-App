<template>
  <div>
    <router-link style="color: white;" v-bind:to="{name: 'viewTournament', params: {tournamentId: this.tournamentId}}">Go Back To Tournament</router-link>
    <h1> {{ this.tournament.tournamentName }} </h1>
    <div v-for="player in players" v-bind:key="player.playerId">
    <router-link style="color: white;" v-bind:to="{name: 'givePlayerWins', params: {tournamentId: tournament.tournamentId, playerId: player.playerId}}" >
       {{player.playerId}} {{player.username}}
    </router-link>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';
export default {
    name: 'admin',
    data(){
        return {
          tournamentId: this.$route.params.tournamentId,
            tournament: {},
            tournamentUsers : [],
            players : []
        };
    }, 
    created() {
        authService.getTournamentById(this.$route.params.tournamentId).then((response) =>{
      this.tournament = response.data;
    })
      authService.getTournamentUsers(this.$route.params.tournamentId).then( (response) => {
        this.players = response.data;
      })

}
}

</script>

<style>

</style>