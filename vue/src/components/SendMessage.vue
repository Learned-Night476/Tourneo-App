<template>
  <div class="matchMessage">
      <label for="winner">Who Was The Winner Of Your Match?</label> &nbsp;
      <input class="msgInput" type="text" name="winner" v-model="winner"/><br><br>
      <label for="matchDescription">What Was The Score And What Happened?</label> &nbsp;
      <input class="msgInput" type="text" name="matchDescription" v-model="matchDescription" /><br><br>
      <button id="msgButton" :disabled="isDisabled" type="button" v-on:click="sendMessage">Send Message</button>
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
        this.$router.push({name: 'nonAdminView', params: {tournamentId: this.tournamentId}});
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

.matchMessage {
    display: flex;
    flex-direction: column;
    justify-content: center;
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    height: 528px;
    width: 800px;
    text-align: center;
}

.msgInput {
    width: 50%;
    align-self: center;
    background-color:rgba(128, 128, 128, 0.664);
    color: orange;
    height: ;
}

.msgInput::placeholder {
  color:white;
  font-size: 15px;

}

#msgButton {
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 40px;
  width: 200px;
  border-radius: 4px;
  border: 1px solid black;
  align-self: center;
}

#msgButton:hover {
    background-color: rgba(140, 0, 255, .75);
    
}
</style>