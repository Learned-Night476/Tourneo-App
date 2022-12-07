
 <!-- #bracketApp
 <template>
  #wrapper(:style="`width:${width}px;`")
    div(class="details")
      h1 Interactive Tournament Bracket with Vue.js + D3.js
      p Sungryeol Park 박성렬(sungryeolp@gmail.com)
      p Match-Id: {{$store.state.matchId}}
      p Team: {{$store.state.team}}
    svg(class="bracket-svg" :style="`width:${width}px;height:${height}px`")
      rect(class="background" :style="`width:${width}px;height:${height}px`")
      g(:transform="`translate(${padding} ${padding})`")
    div(class="bracket-div"
    :style="`width:${width}px;height:${height}px`")
      match(v-for="matchData in matches" :xpos="padding + matchData.y" :ypos="matchData.x" :key="matchData.matchId" :home="matchData.data.home" :away="matchData.data.away" :home-score="matchData.data.homeScore" :away-score="matchData.data.awayScore" :match-id="matchData.data.matchId")
</template>

<script>
export default {

}
const rawData = {
  matchId: 5,
  home: 'Incheon',
  away: 'Seoul',
  homeScore: 1,
  awayScore: 2,
  children: [
    {
      matchId: 4,
      home: 'Incheon',
      away: 'Gangwon',
      homeScore: 2,
      awayScore: 1,
      children: [
        {
          matchId: 1,
          home: 'Mokpo',
          away: 'Incheon',
          homeScore: 2,
          awayScore: 3,
        },
        {
          matchId: 2,
          home: 'Bucheon',
          away: 'Gangwon',
          homeScore: 3,
          awayScore: 4,
        }
      ]
    },
    {
      matchId: 3,
      home: 'Busan',
      away: 'Seoul',
      homeScore: 0,
      awayScore: 5,
    },
  ]
}
//This is going to be pulling from the database instead of the store

const store = new Vuex.Store({
  state: {
    matchId: null,
    team: null,
  },
  mutations: {
    setMatchId (state, matchId) {
      state.matchId = matchId;
    },
    setPlayer (state, player) {
      state.player = player;
    },
    clearTeam (state) {
      state.team = null;
    },
  }
})

</script>

<style>
body {
  background: #00416A;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #E4E5E6, #00416A);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #E4E5E6, #00416A); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  color: #FFFDF7;
  text-shadow: 0 0 3px black;
}
#bracketApp {
  display:flex;
  justify-content:center;
}
.details {
  background-color:rgba(0,0,0,.3);
  padding:10px;
}
.bracket-div {
  position: relative;
}
.bracket-svg {
  position: absolute;
  .background {
    fill: black;
    fill-opacity:0.3;
  }
  .link {
    fill: none;
    stroke: lightgray;
    stroke-width: 1px;
  }
}
.match {
  background-color:#2CA58D;
  display:block;
  color: #FFFDF7;
  border-radius: 10px;
  position:absolute;
  width: 150px;
  height: 100px;
  table {
    width: 100%;
    height: 100%;
    padding:5px;
    border:none;
    .score{
      text-align:center;
    }
    .chosen {
      background-color: rgba(0,0,0,0.3);
      transition:.3s;
    }
  }
}

</style>