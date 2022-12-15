<template>
  <div>
    <router-link style="color: white;" v-bind:to="{name: 'viewTournament', params: {tournamentId: this.tournamentId}}">Go Back To Tournament</router-link>
    <header id="tourNameHeader">
    <h1 > {{ this.tournament.tournamentName }}  </h1>
    </header>
    <div  v-for="player in players" v-bind:key="player.playerId">
    <router-link class="adminStyle" v-bind:to="{name: 'givePlayerWins', params: {tournamentId: tournament.tournamentId, playerId: player.playerId}}" >
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


.adminStyle {
  color: white;
  text-decoration: none;
  display: flex;
  flex-direction: column;
  justify-content: center;
    border: 1px solid black;
    margin-left: auto;
    margin-right: auto;
    margin-top: 5%;
    height: 75px;
    width: 400px;
    text-align: center;
    background: rgba(57, 57, 57);
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/testpng.svg");
    background-size: 75px;
    background-repeat: no-repeat; 
    align-items: center;
    border-radius: 5px;
    margin-top: 50px;

}

#routerColor {
  color: white;
  text-decoration: none;
}

.adminStyle:hover {
  background-color: rgb(65, 65, 65);

}

#tourNameHeader {
  display: flex;
  text-align: center;
  width: 25%;
  text-size-adjust: auto;
  height: 50px;
  justify-content: center;
  border: 1px solid black;
  background-color: orange;
  align-content: center;
  margin-left: auto;
  margin-right: auto;
  border-radius: 4px;
  align-items: center;
}
</style>