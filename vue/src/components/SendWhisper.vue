<template>
  <div>
      <input id="whisperInput" type="text" v-model="whisper.whisperMessage" ><br>
      <button id="sendWhisperButton" type="button" v-on:click="submitWhisper" >Whisper</button>
  </div>
</template>

<script>
import AuthService from '../services/AuthService';
export default {
    name: 'send-whisper',
    data() {
        return {
            whisper: {
                whisperId: 0,
                toPlayerId: this.$route.params.playerId,
                playerId: this.$store.state.user.id,
                whisperMessage: '',
                isRead: false
            }
            
        }
    }, 
    methods: {
        submitWhisper() {
            AuthService.sendWhisper(this.whisper).then( response => {
                return response.data;
            })
            window.location.reload();
        }
    }
}
</script>

<style>
#sendWhisperButton{
        margin: 10px;
  background-color: rgba(0, 0, 0, 0.15);
  color: white;
  height: 30px;
  width: 200px;
  border-radius: 4px;

}

#whisperInput{
    display: inline;
    width: 98.8%;
    height:50px;
    text-align: start;
}

#sendWhisperButton:hover {
  background-color: rgba(140, 0, 255, 0.75);
}
</style>