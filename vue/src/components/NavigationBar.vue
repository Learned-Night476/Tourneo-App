<template>
<div id="nav">
    <div id="home">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
    </div>
    <div id="logout">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
     </div>
</div>  
</template>

<script>
export default {
 name: "navigation-bar"
}
</script>

<style>

#nav {
    display: flex;
    flex-direction: row;
    background: aliceblue;
}

 #home {
     background: aliceblue;
 }
 #logout {
     justify-content: flex-end;
     background: aliceblue;
 }
</style>