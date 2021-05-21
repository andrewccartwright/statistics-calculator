<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="home-page">
    <h1 id="title" class="mt-5">Statistics Tools:</h1>

    <!-- Router links to each path -->
    <hr />
    <router-link id="ovs-link" to="/one-var-stats" class="nav-link">One Variable Summary Statistics</router-link>
    <hr />
    <router-link id="z-score-link" to="/z-score-calc" class="nav-link">Z Score Calculator</router-link>
    <hr />
    <router-link id="dist-of-sample-means-link" to="/dist-of-sample-means" class="nav-link">Distribution of Sample Means <br /> Z Score Calculator</router-link>
    <hr />
    <router-link id="t-score-link" to="/t-score-calc" class="nav-link">T Score Calculator</router-link>
    <hr />
    <router-link id="binomial-link" to="/binomial" class="nav-link">Binomial Distribution</router-link>
    <hr />
    <router-link id="poisson-link" to="/poisson" class="nav-link">Poisson Distribution</router-link>
    <hr />
    <router-link id="geomet-link" to="/geometric" class="nav-link">Geometric Distribution</router-link>
    <hr />
  </div>
</template>

<script>
  export default {
    name: 'Home',

  }
</script>

<style>
  hr {
    width: 50%;
    max-width: 400px;
  }

  #title {
    font-size: 28px;
  }

  #home-page {
    margin-bottom: 10vh;
  }

  .nav-link,
  .nav-link:link,
  .nav-link:hover,
  .nav-link:active {
    text-decoration: none;
    color: black;
  }
</style>
