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
  <div id="hi">
    <div class="allRounds">
      <div class="round1">
          <match v-for="match in (tournament.participants / 2)" v-bind:key="match.id"  />

      </div>
      <div class="round2" >
          <match v-for="match in (tournament.participants / 4)" v-bind:key="match.id" />
      </div>
       <div class="round3"  >
          <match  v-for="match in matchesRound3" v-bind:key="match.id" />
      </div>
      <div class="round4"  >
          <match v-for="match in matchesRound4" v-bind:key="match.id" />
      </div>
      <div class="round5"  >
          <match v-for="match in matchesRound5" v-bind:key="match.id" />
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService"
import Match from './Match.vue'
export default {
  components: { Match },
  name: "view-tournament",
data() {
  return{
    tournamentId : this.$route.params.tournamentId,
    tournament: {},
    matchesRound2: [],
    matchesRound3: [],
    matchesRound4: [],
    matchesRound5: [],
    topPixelNumber: 100,
    firstTimeThrough : true
  }
},

created() {
  authService.getTournamentById(this.tournamentId).then((response) =>{
    this.tournament = response.data;
  })
},

methods: {
  add100px(){
    if(this.firstTimeThrough){
      this.firstTimeThrough = false;
      return 0;
    }

    else {
      return 100;
    }
  },

// We might not have to use this logic, but keep it just in case.
   updateRound3()  {
    if ((this.tournament.participants / 8) < 1) {
      this.matchesRound3 = 0;
    }
    else {
      this.matchesRound3 = this.tournament.participants / 8;
    }
    return this.matchesRound3;
   },
  


   updateRound4() {
    if ((this.tournament.participants / 16) < 1) {
      this.matchesRound4 = 0;
    }
    else {
      this.matchesRound4 = this.tournament.participants / 8;
    }
    return this.matchesRound4;
   },

   updateRound5() {
    if ((this.tournament.participants / 32) < 1) {
      this.matchesRound5 = 0;
    }
    else {
      this.matchesRound5 = this.tournament.participants / 8;
    }
    return this.matchesRound5;
  }
}
};


</script>

<style>

.allRounds {
  padding-top: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
  
  
}



.round1 {

  
}



/* .container {
  max-width: 1100px;
  margin: 0 auto;
  display:block;
  /* display: flex;
  flex-direction: column; */
  /* } */
/* 
  .split {
    display:block;
    float:left;
    flex-direction:row;
    }

  .round1 {
    display: block;
    float: right;
    display: flex;
  

  } */ 



 /* <header class="hero">
    <div class="hero-wrap">
     <p class="intro" id="intro">flexbox</p>
     <h1 id="headline">Tournament</h1>
     <p class="year"><i class="fa fa-star"></i> 2015 <i class="fa fa-star"></i></p>
     <p>Ballin' Outta Control</p>
   </div>
  </header> */

/* 
  <section id="bracket">
  <div class="container">
  <div class="split split-one">
    <div class="round round-one current">
      <div class="round-details">Round 1<br/><span class="date">March 16</span></div>
      <ul class="matchup">
        <li class="team team-top">Duke<span class="score">76</span></li>
        <li class="team team-bottom">Virginia<span class="score">82</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">Wake Forest<span class="score">64</span></li>
        <li class="team team-bottom">Clemson<span class="score">56</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">North Carolina<span class="score">68</span></li>
        <li class="team team-bottom">Florida State<span class="score">54</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">NC State<span class="score">74</span></li>
        <li class="team team-bottom">Maryland<span class="score">92</span></li>
      </ul>     
      <ul class="matchup">
        <li class="team team-top">Georgia Tech<span class="score">78</span></li>
        <li class="team team-bottom">Georgia<span class="score">80</span></li>
      </ul> 
      <ul class="matchup">
        <li class="team team-top">Auburn<span class="score">64</span></li>
        <li class="team team-bottom">Florida<span class="score">63</span></li>
      </ul> 
      <ul class="matchup">
        <li class="team team-top">Kentucky<span class="score">70</span></li>
        <li class="team team-bottom">Alabama<span class="score">59</span></li>
      </ul>
      <ul class="matchup">
        <li class="team team-top">Vanderbilt<span class="score">64</span></li>
        <li class="team team-bottom">Gonzaga<span class="score">68</span></li>
      </ul>                   
    </div>  <!-- END ROUND ONE --> */
</style>
