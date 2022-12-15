<template>
<div>
<router-link :to="{ name: 'home' }"><button id="backToProfileFromConvo">Back to Home</button></router-link>
<div id="allOfConversations">
    <header id="yourConversations">Your Conversations</header>
    <p>Start a new whisper</p>
    <select type="text" class="whoToSendTo" name="whoToSendTo" v-model="playo" >
                <option v-for="player in allPlayers" v-bind:key="player.playerId" v-bind:value="player">{{player.username}}</option>
                </select> &nbsp;
    <router-link id="linkToConversation" v-show="playo.playerId" v-bind:to="{name: 'whispers', params: {playerId: playo.playerId}}" v-on:click="addToPlayers(playo)">  Start whisper with {{playo.username}} </router-link>
  <div v-for="playa in players" v-bind:key="playa.playerId">
      <router-link id="linkToConversation"  v-bind:to="{name: 'whispers', params: {playerId: playa.playerId}}"> {{playa.username}} </router-link>
  </div>
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
            allPlayers: [],
            players: [],
            player: {
                playerId: '',
                username: ''
            },
            playo: ''
        };
    },
    created() {
        AuthService.getAllPlayers().then( response => {
            this.allPlayers = response.data;
        })

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
            for (let i = 0; i < this.whispers.length; i++) {
                let loop = true;
                if (this.whispers[i].toPlayerId != this.player.playerId) {
                    for (let j = 0; j < this.people.length; j++) {
                        if (this.people[j] === this.whispers[i].toPlayerId) {
                            loop = false
                        }
                    }
                    if (loop) {
                    this.people.unshift(this.whispers[i].toPlayerId);
                    }
                }
                
            }
        },
        addToPlayers(playerId) {
            this.players.unshift(playerId);
        }
    }
}
</script>

<style>

.whoToSendTo{
display:flex;
background: rgba(128, 128, 128, 0.664);
text-align: center;
  color: white;
  margin-bottom: 20px;
  width: 80%;
  height: 20px;
  text-align: center;
}

#backToProfileFromConvo {
    margin: 20px;
  text-decoration: none;
  background-color: orange;
  color: black;
  height: 40px;
  width: 200px;
  border-radius: 4px;
  font-family: Kanit;

}

#backToProfileFromConvo:hover {
 background-color: rgb(255, 182, 46);
}
#allOfConversations{
    display: flex;
    flex-direction: column;
    background: rgb(57, 57, 57);
    margin-top: 100px;
    margin-left: auto;
    margin-right: auto;
    align-items: center;
    width: 60%;
    min-height: 250px;
    height: auto;
    font-size: 15px;
    color: white;
    border-radius: 4px;
    text-align: left;


}
#linkToConversation{
    margin-left: 20px;
    font-size: 20px;
    color: white;
    text-align: left;
    text-decoration: none;
   
}
#linkToConversation:hover{
    color:silver;
}
#yourConversations{
    font-size: 30px;;
    color:black;
    display: flex;
    padding-top:10px;
    padding-bottom: 10px;
    background-color:orange;
    width: 100%;
    justify-content: center;
    border-radius: 4px;
}
</style>