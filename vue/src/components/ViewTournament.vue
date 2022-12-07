<!--<template>
  <div class="match" :style="matchStyle" @mouseover="onMouseover">
  <table>
    <tr @mouseover="$store.commit('setTeam', home)" :class="home === $store.state.team ? 'chosen' : null" @mouseout="$store.commit('clearTeam')">
      <td style="border-bottom:solid 1px black;">{{this.home}}</td>
      <td class="score" style="border-bottom:solid 1px black;">{{this.homeScore}}</td>
    </tr>
    <tr @mouseover="$store.commit('setTeam', away)" :class="away === $store.state.team ? 'chosen' : null" @mouseout="$store.commit('clearTeam')">
      <td>{{this.away}}</td>
      <td class="score">{{this.awayScore}}</td>
    </tr>
  </table>
</div>
</template>

<script>
export default {

},
  computed: {
    matchStyle() {
      return {
        left: `${this.xpos}px`,
        top: `${this.ypos}px`,
      };
    },
  },
  props: [
    'matchId'
    'home',
    'away',
    'round',
    'winner',
    'tournamentId'
  ],
  methods: {
    onMouseover() {
      this.$store.commit('setMatchId', this.matchId);
    }
  }
})

const bracketApp = new Vue({
  store,
  data: () => ({
    rawData,
    width: 1000,
    height: 500,
    padding: 50,
    matches: [],
    root: {}, // root is exposed for global
    matchWidth: 100,
    matchHeight: 50,
    tree: null, // tree is also exposed for global
  }),
  mounted() {
    this.refresh();
  },
  methods: {
    refresh() {
      this.tree = d3.tree()
        .size([this.height - this.matchHeight, this.width - (this.matchWidth * 2 + this.padding * 2)]);
      this.root = this.tree(d3.hierarchy(this.rawData));
      this.matches = this.root.descendants();
      this.drawLinks();
    },
    drawLinks() {
      const g = d3.select('svg > g');
      const links = g.selectAll('path.link')
        .data(this.root.descendants().slice(1));
      const elbow = d3.line()
        .curve(d3.curveStep);
      links.enter()
        .append('path', 'g')
        .attr('class', 'link')
        .attr('d', function (d) {
          let targetY = d.x;
          let sourceY = d.parent.x;
          let targetX = d.y;
          let sourceX = d.parent.y;
          if (d.data.homeScore < d.data.awayScore) {
            targetY = d.x + 25;
          } else {
            targetY = d.x - 25;
          }
          return elbow([[sourceX, sourceY], [targetX, targetY]])
        })
      
    }
  }
}).$mount('#bracketApp');

</script>

<style>

</style> -->
<template>
  <div>
    <div>
      <h1>{{tournament.participants}}</h1>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService"
export default {
  name: "view-tournament",
data() {
  return{
    tournamentId : this.$route.params.tournamentId,
    tournament: {}
  }
},

created() {
  authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  })
}
}
</script>

<style>

</style>
