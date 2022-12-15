<template>
  <div class="whisperWindow">
      <header id="whisperHeader">This is your conversation with {{ player2.username }}
          <router-link id="backToConversations"  v-bind:to="{name: 'conversations', params: {playerId: player.playerId}}"> Go back </router-link>
          </header>
      <div v-for="whisper in realWhispers" v-bind:key="whisper.whisperId">
          <p class= "whisperSender" v-if="whisper.playerId === player.playerId"> {{whisper.whisperMessage}} </p>
          <p class= "whisperReceiver" v-else>{{ player2.username }}:  {{whisper.whisperMessage}} {{ whisper.isRead }}</p>
      </div>
      <send-whisper/>
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
#backToConversations:hover{
    color:silver;
}
#backToConversations{
    color:white;
    text-decoration: none;
    
}
#whisperHeader{
    padding-left: 20px;
    padding-right: 20px;
    display: flex;
    padding-top:20px;
    padding-bottom: 20px;
    color: black;
    background-color:orange;
    justify-content: space-between;
    align-items: center;
    border-radius: 4px;


}
.whisperWindow{
    display: flex;
    flex-direction: column;
    background: rgb(57, 57, 57);
    background-repeat: no-repeat;
    background-position:center;
    margin-top: 100px;
    margin-left: auto;
    margin-right: auto;
    justify-content: center;
    text-align: start;
    width: 600px;
    height: auto;
    font-size: 15px;
    color: white;
    border-radius: 4px;
}
.whisperSender{
    margin: 20px;
    color:orange;
    text-align: right;
}
.whisperReceiver{
    margin: 20px;
    text-align: left;
}

</style>