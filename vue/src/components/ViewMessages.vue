<template>
  <div>
    <div>
       <router-link class="backToTourneyFromMessages" v-bind:to="{name: 'viewTournament', params: {tournamentId: this.tournamentId}}">Go Back To Tournament</router-link>
    </div>
    <div v-for="message in messages" v-bind:key="message.id">
      <router-link id="fromWhom" v-bind:to="{name: 'viewAMessage', params: {tournamentId: tournament.tournamentId, messageId: message.tournamentMessageId}}"> From: {{message.senderUsername}}</router-link>

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
.backToTourneyFromMessages{
  color: white;
  text-decoration: none;
  margin-left: 20px;
}
.backToTourneyFromMessages:hover{
  color: silver;
  text-decoration: none;
  
}

#fromWhom {
  color: white;
  text-decoration: none;
  display: flex;
  width: 100%;
  height: 100%;
  margin-left: auto;
  margin-right: auto;
  justify-content: center;
  width: 400px;
}
</style>
