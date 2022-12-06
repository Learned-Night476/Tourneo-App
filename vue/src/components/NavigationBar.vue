<template>
<div class="nav">
    <header>
        <div id="left-half">
            <div id="home">
                <router-link v-bind:to="{ name: 'home' }">Home</router-link> 
            </div>
            <div>  
                <a href="#"> Create Tournament </a>
            </div>
        </div>
        <div>
            <input type="text" class="resizedTextbox" v:bind:value="search"/>
        </div>
            <div id="logout">
                <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> 
        </div>
     </header>
</div>  
</template>

<script>
export default {
 name: "navigation-bar"
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