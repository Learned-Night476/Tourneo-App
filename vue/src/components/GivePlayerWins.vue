<template>
    <div class="roundButtons">
        <router-link v-bind:to="{name: 'admin', params: {tournamentId: this.tournamentId}}" ><button>Go Back</button></router-link>
  
  <div class="playerWins">
      
      <h1>{{player.username}}</h1>
      <p>{{player.username}} has {{player.wins}} wins</p>
      <p>{{player.username}} has {{player.losses}} losses</p>
      <router-link v-bind:to="{name: 'admin', params: {tournamentId: this.tournamentId}}" style="color: white;">
      <button class="msgButton" type="button" v-on:click="givePlayerAWin">Give Player A Win</button> &nbsp;
        </router-link>
      <router-link v-bind:to="{name: 'admin', params: {tournamentId: this.tournamentId}}" style="color: white;">
      <button class="msgButton" :disabled="!addedALoss" type="button" v-on:click="givePlayerALoss">Give Player A Loss</button>
      </router-link>
  </div>
    </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
name: 'give-player-wins',
data(){
    return {
        player: {},
        tournament: {},
        tournamentId: this.$route.params.tournamentId
    }
},

created(){
    authService.getPlayerByPlayerId(this.$route.params.playerId).then((response) => {
        this.player = response.data;
    });
},

methods: {
    givePlayerAWin(){
        authService.givePlayerAWin(this.player.playerId, this.player.wins + 1);
    },

    givePlayerALoss() {
        authService.givePlayerALoss(this.player.playerId, this.player.losses + 1);
    }
},

computed: {
    addedALoss(){
        return true;
    }
}
}
</script>

<style>
.playerWins {
   display: flex;
    flex-direction: column;
    justify-content: center;
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    height: 528px;
    width: 800px;
    text-align: center;
    background: rgba(57, 57, 57);
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/loser.svg");
    background-repeat: no-repeat; 
    align-items: center;
    border-radius: 5px;
    margin-top: 50px;
}

.roundButtons {
  text-decoration: none;
  background-color: orange;
  color: black;
  height: 40px;
  width: 200px;
  border-radius: 4px;
  font-family: Kanit;
}
.roundButtons:hover {
  background-color: rgb(255, 182, 46);
}
</style>