<template>
<div>
  <div v-for="player in people" v-bind:key="player.playerId">
      <router-link  v-bind:to="{name: 'whispers', params: {playerId: player}}"> {{player}}</router-link>
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
                playerId: '',
                username: ''
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
                        if (this.people[j] === this.whispers[i].playerId) {
                            loop = false
                        }
                    }
                    if (loop) {
                    this.people.unshift(this.whispers[i].playerId);
                    }
                }
            }
        }
    }
}
</script>

<style>

</style>