<template>
      <div id="wins-and-losses">
      <div class="wins-in-profile" id="player-wins">
          <h2>{{ player.wins }} </h2>
          <p>Total Match Wins</p>
      </div>
      <div class="losses-in-profile" id="player-losses">
          <h2>{{ player.losses }}  </h2>
          <p>Total Match Losses</p>

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
     }
 };

},
created() {
     authService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
     })
}
}

</script>

<style>


#wins-and-losses {
    display: flex;
    justify-content: space-evenly;
   
}

div.wins-in-profile {
    background: rgb(57, 57, 57);
    width: 300px;
    text-align: center;
    margin-top: 20px;
}


div.losses-in-profile {
    background: rgb(57, 57, 57);
    width: 300px;
    text-align: center;
    margin-top: 20px;
}
h2 {
    font-size: 50px;
    padding: -25px;
}

</style>