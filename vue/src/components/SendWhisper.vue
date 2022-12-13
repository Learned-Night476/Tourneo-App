<template>
  <div>
      <input type="text" v-model="whisper.whisperMessage" >
      <button type="button" v-on:click="submitWhisper" >Send whisper</button>
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

</style>