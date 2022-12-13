<template>
  <div>
      <div v-for="whisper in realWhispers" v-bind:key="whisper.whisperId">
          <h1>{{whisper.playerId}} {{whisper.whisperMessage}} {{whisper.toPlayerId}} {{player2.username}}</h1>
      </div>
      {{player.playerId}}
  </div>
</template>

<script>
import AuthService from '../services/AuthService';
export default {
name: 'whispers',
    data(){
        return {
            whispers: [],
            realWhispers: [],
            player: {
                playerId: ''
            },
            player2: {}
            
        };
    }, 
    created() {

        AuthService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
         AuthService.getWhisperByPlayerId(this.player.playerId).then( response => {
            this.whispers = response.data;
            this.createConversations();
        });

        AuthService.getProfile(this.$route.params.playerId).then (response => {
            this.player2 = response.data;
        })
         
     });

        
    },
    methods: {
        createConversations() {
            for (let i = 0; i < this.whispers.length; i++) {
                if (this.whispers[i].playerId != this.$route.params.playerId && this.whispers[i].toPlayerId != this.$route.params.playerId) {
                    continue;
                } else {
                    this.realWhispers.unshift(this.whispers[i]);
                }
            }
        },

    }
}
</script>

<style>

</style>