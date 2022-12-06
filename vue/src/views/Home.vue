<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <profile-stats />
  </div>
</template>

<script>
import ProfileStats from '../components/ProfileStats.vue';
export default {
  components: { ProfileStats },
  name: "home"
};
</script>
