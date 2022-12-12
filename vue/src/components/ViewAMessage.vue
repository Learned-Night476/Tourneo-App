<template>
  <div>

  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
    name: "view-a-message",
    data() {
        return {
        tournamentId: this.$route.params.tournamentId,
        messages: [],
        tournament: {}
        };
    },

    created() {
          authService.getMessagesByTournamentId(this.tournamentId).then((response) => {
      this.messages = response.data;
  });
    }
}
</script>

<style>

</style>