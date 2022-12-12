<template>
  <div class="home">
    <profile-stats />
    <view-all-users-tournaments/>
    <tournaments-participated/>
  </div>
</template>

<script>
import ProfileStats from '../components/ProfileStats.vue';
import TournamentsParticipated from '../components/TournamentsParticipated.vue';
import ViewAllUsersTournaments from '../components/ViewAllUsersTournaments.vue';
export default {
  components: { ProfileStats, ViewAllUsersTournaments, TournamentsParticipated },
  name: "home"
};
</script>
