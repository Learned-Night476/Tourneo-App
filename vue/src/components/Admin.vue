<template>
  <div>
    <h1> {{ this.tournament.tournamentName }} </h1>
    <div v-for="player in players" v-bind:key="player.playerId">
       {{player.playerId}} {{player.username}} <button> Edit Player </button>
     </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';
export default {
    name: 'admin',
    data(){
        return {
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