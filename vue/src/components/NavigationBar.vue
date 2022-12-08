<template>
<div class="nav">
    <header>
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
                <router-link class="headLogout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
        </div>
     </header>
</div>  
</template>

<script>
export default {
 name: "navigation-bar",
 data() {
     return {
         search: {
             username: ''
         }
     }
 },
 computed: {
     
 },
 created() {
     
 }
}
</script>

<style>

header {
    display: flex;
    background: rgb(57, 57, 57, 0.75);
    justify-content: space-between ;
    padding: 10px;
    margin: -8px;
}

#left-half {
    padding-left: 10px;
    padding-top: 10px;
    display: flex;
    
}

.resizedTextbox {width: 500px; height: 30px}


 #logout {
     
     padding: 10px;
 
 }
 .headLogout {
     color:white;
     text-decoration:none;
 }
 .headLogout:visited {
     color:silver;
     text-decoration: none;
 }
 .headCreateTournament {
     color:white;
     padding-left: 20px;
     text-decoration:none;
 }

 .headCreateTournament:visited {
     color:silver;
     text-decoration:none;
 }

 .headHome {
     color:white;
     text-decoration:none;
 }

 .headHome:visited {
     color:silver;
     text-decoration:none;
 }

</style>