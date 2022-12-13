<template>
<div>
  <div v-for="player in people" v-bind:key="player.playerId">
      <h1>{{player.playerId}} </h1>
  </div>
</div>
</template>

<script>
import AuthService from '../services/AuthService';
export default {
name: 'conversation',
    data(){
        return {
            whispers: [],
            conversations: [],
            people: [],
            player: {
            }
        };
    },
    created() {

        AuthService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
         AuthService.getWhisperByPlayerId(this.player.playerId).then( response => {
            this.whispers = response.data;
            this.addToPeople();
        });
         
     });

        
    },
    methods: {
        addToPeople() {

            for (let i = 0; i < this.whispers.length; i++) {
                let loop = true;
                if (this.whispers[i].playerId != this.player.playerId) {
                    for (let j = 0; j < this.people.length; j++) {
                        if (this.people[j] === this.whispers[i]) {
                            loop = false
                            break;
                        }
                    }
                    if (loop) {
                    this.people.unshift(AuthService.getProfile(this.whispers[i].playerId));
                    }
                }
            }
        }
    }
}
</script>

<style>

</style>