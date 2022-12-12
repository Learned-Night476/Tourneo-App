<template>
  <div>
      <p>{{message.senderUsername}}</p>
      <p>{{message.winner}}</p>
      <p>{{message.matchDescription}}</p>
  </div>
</template>

<script>
import authService from '../services/AuthService'

export default {
    name: "view-a-message",
    data() {
        return {
        messageId: this.$route.params.messageId,
        message: {},
        };
    },

    created() {
          authService.getMessageByMessageId(this.messageId).then((response) => {
      this.message = response.data;
  });
    }
}
</script>

<style>

</style>