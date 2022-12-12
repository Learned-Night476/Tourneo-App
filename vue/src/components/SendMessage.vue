<template>
  <div>
      <label for="winner">Who Was The Winner Of Your Match?</label> &nbsp;
      <input type="text" name="winner" v-model="winner"/><br><br>
      <label for="matchDescription">What Was The Score And What Happened?</label> &nbsp;
      <input type="text" name="matchDescription" v-model="matchDescription" /><br><br>
      <button :disabled="isDisabled" type="button" v-on:click="sendMessage">Send Message</button>
      <!-- <p>{{tournament.adminUser}}</p>
      <p>{{senderUsername}}</p> -->
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
    name: 'send-message',
data() {
    return {
        randomString: "Hi",
        tournamentId : this.$route.params.tournamentId,
        tournament: {},
        message: {},
        senderUsername: this.$store.state.user.username,

        winner: "",
        matchDescription: ""
    };
},

created() {
    authService.getTournamentById(this.tournamentId).then((response) => {
        this.tournament = response.data;
    });
},

methods: {
    sendMessage(){
       let newMessage = {
            adminUser: this.tournament.adminUser,
            tournamentId: this.tournamentId,
            senderUsername: this.senderUsername,
            matchDescription: this.matchDescription,
            winner: this.winner,
            unread: true
        }

        authService.sendMessage(newMessage);
    }
},

computed: {
    isDisabled() {
        if(this.winner === "" || this.matchDescription === "") {
            return true;
        }

        else {
            return false;
        }
    }
}
}
</script>

<style>

</style>