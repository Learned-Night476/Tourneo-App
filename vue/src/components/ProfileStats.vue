<template>
      <div id="wins-and-losses">
      <div class="wins-in-profile" id="player-wins">
          <h1>Wins {{ player.wins }} </h1>
      </div>
      <div class="losses-in-profile" id="player-losses">
          <h1>Losses {{ player.losses }}  </h1>

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
     border: grey;
    border-width: 2px;
}

div.wins-in-profile {
    border: grey;
    border-width: 2px;

}

div.losses-in-profile {
    border: grey;
    border-width: 2px;


}

</style>