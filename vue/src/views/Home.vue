<template>
  <div class="home">
    <profile-stats />
    <view-all-users-tournaments/>
  </div>
</template>

<script>
import ProfileStats from '../components/ProfileStats.vue';
import ViewAllUsersTournaments from '../components/ViewAllUsersTournaments.vue';
export default {
  components: { ProfileStats, ViewAllUsersTournaments },
  name: "home"
};
</script>
