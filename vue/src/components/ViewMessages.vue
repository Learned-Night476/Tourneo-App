<template>
  <div>
    <div v-for="message in messages" v-bind:key="message.id">
      <router-link id="fromWhom" style="color: white;" v-bind:to="{name: 'viewAMessage', params: {tournamentId: tournament.tournamentId, messageId: message.tournamentMessageId}}"> From: {{message.senderUsername}}</router-link>

    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
name: 'view-messages',

data() {
    return {
        tournamentId: this.$route.params.tournamentId,
        messages: [],
        tournament: {}
    };
},

created() {
   authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  });

  authService.getMessagesByTournamentId(this.tournamentId).then((response) => {
      this.messages = response.data;
  });
}
}
</script>

<style>
#fromWhom {
  display: flex;
  width: 100%;
  height: 100%;
  margin-left: auto;
  margin-right: auto;
  justify-content: center;
  width: 400px;
}
</style>
