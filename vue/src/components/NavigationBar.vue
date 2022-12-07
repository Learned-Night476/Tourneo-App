<template>
<div class="nav">
    <header>
        <div id="left-half">
            <div id="home">
                <router-link v-bind:to="{ name: 'home' }">Home</router-link> 
            </div>
            <div>  
                <router-link v-bind:to="{ name: 'newTournament'}"> Create Tournament </router-link>
            </div>
        </div>
        <div>
            <input type="text" class="resizedTextbox" v-model="search.username"/>
        </div>
            <div id="logout">
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
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
    background: grey;
    justify-content: space-between ;
    padding: 10px;
}

#left-half {
    display: flex;
    background: red;
    
}

.resizedTextbox {width: 500px; height: 20px}


 #logout {
     background: red;
     padding: 5px;
 
 }
</style>