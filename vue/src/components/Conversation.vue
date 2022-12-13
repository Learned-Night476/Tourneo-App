<template>
<div>
  <div v-for="playa in players" v-bind:key="playa.playerId">
      <router-link  v-bind:to="{name: 'whispers', params: {playerId: playa.playerId}}"> {{playa.username}} </router-link>
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
            players: [],
            player: {
                playerId: '',
                username: ''
            },
        };
    },
    created() {

        AuthService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
         AuthService.getWhisperByPlayerId(this.player.playerId).then( response => {
            this.whispers = response.data;
            this.addToPeople();

            for (let i = 0; i < this.people.length; i++) {
                
                AuthService.getPlayerByPlayerId(this.people[i]).then( response => {
                   this.players.unshift(response.data);
                })
                
            }
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