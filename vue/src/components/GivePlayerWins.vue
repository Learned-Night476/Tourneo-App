<template>
  <div class="playerWins">
      <router-link v-bind:to="{name: 'admin', params: {tournamentId: this.tournamentId}}" style="color: white;">Go Back</router-link>
      <h1>{{player.username}}</h1>
      <p>{{player.username}} has {{player.wins}} wins</p>
      <p>{{player.username}} has {{player.losses}} losses</p>
      <button class="msgButton" type="button" v-on:click="givePlayerAWin">Give Player A Win</button> &nbsp;
      <button class="msgButton" :disabled="!addedALoss" type="button" v-on:click="givePlayerALoss">Give Player A Loss</button>
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
    background-color: rgb(57, 57, 57);
    display: flex;
    justify-content: center;
    justify-content: center;
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    height: 528px;
    width: 800px;
    text-align: center;
}
</style>