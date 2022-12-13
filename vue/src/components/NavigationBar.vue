<template>
<div class="nav">
    <header class="navBarHeader">
        <div id="left-half">
            <div id="home">
                <router-link class="headHome" v-bind:to="{ name: 'home' }">Home</router-link> 
            </div>
            <div>  
                <router-link class='headCreateTournament' v-bind:to="{ name: 'newTournament'}"> Create Tournament </router-link>
            </div>
        </div>
        <div>
            <input type="text" class="resizedTextbox" v-model="search.username"/>
        </div>
            <div id="logout">
                <router-link id="whispersInNav" class="headLogout" v-bind:to="{ name: 'conversations', params: {playerId: player.playerId} }">Whispers</router-link>&nbsp;
                <router-link  class="headLogout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
        </div>
     </header>
</div>  
</template>

<script>
import AuthService from '../services/AuthService'
export default {
 name: "navigation-bar",
 data() {
     return {
         search: {
             username: ''
         },
         player: {}
     }
 },
 computed: {
     
 },
 created() {
     AuthService.getProfile(this.$store.state.user.id).then( response => {
         this.player = response.data;
     })
 }
}
</script>

<style>

.navBarHeader {
    display: flex;
    background-image: linear-gradient(rgb(57, 57, 57), rgb(57,57,57, 0.20) );
    justify-content: space-between ;
    padding: 10px;
    margin: -8px;
    margin-bottom: 20px ;
}

#left-half {
    padding-left: 10px;
    padding-top: 10px;
    display: flex;
    
}

.resizedTextbox {width: 500px; height: 30px}


 #logout {
     
     padding: 20px;
 
 }

 #whispersInNav{
     padding-right: 10px;
 }
 .headLogout {
     color:white;
     text-decoration:none;
 }
 .headLogout:hover {
     color:silver;
     text-decoration: none;
 }
 .headCreateTournament {
     color:white;
     padding-left: 20px;
     text-decoration:none;
     
 }

 .headCreateTournament:hover {
     color:silver;
     text-decoration:none;
 }
 

 .headHome {
     color: white;
     text-decoration:none;
 }

 .headHome:hover {
     color:silver;
     text-decoration:none;
 }

 

</style>