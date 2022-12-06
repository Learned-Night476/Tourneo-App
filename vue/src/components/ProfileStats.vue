<template>
  <div>
      <div id="player-wins">
          <h1>Wins {{ player.wins }} </h1>
      </div>
      <div id="player-loses">
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

</style>