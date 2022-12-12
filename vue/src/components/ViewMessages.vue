<template>
  <div>

  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
name: 'view-messages',

date() {
    return {
        tournamentId: this.$route.params.tournamentId,
        messages: [],
        tournament: {}
    }
},

created() {
   authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  });

  authService.getMessagesByTournamentId(this.tournamentId).then((response) => {
      this.messages = response.data;
  });
},
}
</script>

<style>

</style>