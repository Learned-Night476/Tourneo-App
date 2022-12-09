<template>
  <div class="chad">
      <p id="homePlayer">Home Player: </p>
      <span id="line"></span>
      <p id="awayPlayer">Away Player :</p>
  </div>
</template>

<script>
export default {
name:  'match',
props: ['match']
}

</script>

<style>
 .chad {
  display: flex;
  flex-direction: column;
  background-color: rgb(57, 57, 57);
  margin: 10px;
  height: 80px;
  width: 150px;
  border-radius: 4px;
  justify-content: space-evenly;
} 

#homePlayer {
 
  /* padding-bottom: -15px; */
  display: flex;
  flex-direction: column;
  margin: 10px;
  height: 80px;
  width: 140px;
  border-radius: 4px;
 


}

#awayPlayer {
  display: flex;
  flex-direction: column;
  margin: 10px;
  height: 80px;
  width: 140px;
  border-radius: 4px;
 
  
  

}

#line {
  border-top: solid 1px orange;
  align-items: center;
}

.chad:hover {
  background-color: rgba(218, 112, 214, 0.705);
}


</style>