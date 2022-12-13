<template>
  <div class="msgDisplay">
      <p>From: {{message.senderUsername}}<br>
                {{message.winner}}  {{message.matchDescription}} <br>
        </p>
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
.msgDisplay {
    display: flex;
    background: rgb(57, 57, 57);
    background-image: url("C:/Users/Student/workspace/java-finalcapstone-team0/vue/src/capstone-images/winnerstrophy.png");
    border: 1px solid black;
    background-repeat: no-repeat;
    background-position:center;
    margin-top: 100px;
    margin-left: auto;
    margin-right: auto;
    justify-content: center;
    text-align: center;
    width: 520px;
    height: 520px;
    font-size: 20px;
    color: white;
    border-radius: 5px;
}
</style>