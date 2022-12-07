<template>
  <div class="home">
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
