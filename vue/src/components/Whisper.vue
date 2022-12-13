<template>
  <div>
      <header>This is your conversation with {{ player2.username }} <router-link  v-bind:to="{name: 'conversations', params: {playerId: player.playerId}}"> Go back </router-link></header>
      <div v-for="whisper in realWhispers" v-bind:key="whisper.whisperId">
          <h1 v-if="whisper.playerId === player.playerId">{{ player.username }} {{whisper.whisperMessage}} </h1>
          <h1 v-else>{{ player2.username }} {{whisper.whisperMessage}} {{ whisper.isRead }}</h1>
      </div>
      <send-whisper />
  </div>
</template>

<script>
import AuthService from '../services/AuthService';
import SendWhisper from './SendWhisper.vue';
export default {
  components: { SendWhisper },
name: 'whispers',
    data(){
        return {
            whispers: [],
            realWhispers: [],
            playa: {},
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
        convertPlayerId(playerId) {
             AuthService.getPlayerByPlayerId(playerId).then(response => {
                this.playa = response.data;
            })
        }

    }
}
</script>

<style>

</style>