<template>
<div>
    <h1 id="displayuser">{{ player.username }}</h1>
      <div id="wins-and-losses">
      <div class="wins-in-profile" id="player-wins">
          <h2>{{ player.wins }} </h2>
          <p>Total Match Wins</p>
      </div>
      <div class="losses-in-profile" id="player-losses">
          <h2>{{ player.losses }}  </h2>
          <p>Total Match Losses</p>

      </div>
      <!-- <div v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
          <p>{{tournament.name}}</p> -->
          <!-- <router-link v-bind:to="{name: 'viewTournament', params: {tournamentId: 5}}">{{tournament.name}}</router-link> -->
      <!-- </div> -->
      </div>
</div>
    
</template>

<script>
import authService from '../services/AuthService'

export default {

name: 'profile-stats',
data() {
 return {
     player : {
         wins: '',
         losses: ''
     },

    //  tournaments: []
 };

},
created() {
     authService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
     });

    //  authService.getTournamentByUserId(this.$store.state.user.id).then((response) => {
    //      this.tournaments = response.data;
    //  });
}
}

</script>

<style>

#displayuser {
    text-align: center;
}

#wins-and-losses {
    display: flex;
    justify-content: space-evenly;
   
}

div.wins-in-profile {
    background: rgb(57, 57, 57);
    width: 300px;
    text-align: center;
    margin-top: 20px;
    border-radius: 4px;
}


div.losses-in-profile {
    background: rgb(57, 57, 57);
    width: 300px;
    text-align: center;
    margin-top: 20px;
    border-radius: 4px;
}
h2 {
    font-size: 50px;
    padding: -25px;
}

</style>