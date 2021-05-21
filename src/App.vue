<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="app">
    <div>
      <!-- Navbar that remains at the top in every window -->
      <nav id="nav-bar" class="navbar navbar-toggleable-md navbar-light bg-faded">
        <div id="navbarNav">
          <router-link id="home-link" to="/" class="nav-link">Home</router-link>
        </div>
      </nav>
      <!-- Each router component is displayed as the path changes -->
      <router-view class="view"></router-view>
    </div>
  </div>
</template>

<script>

  export default {
    name: 'App',
  }
</script>

<style>
  #navbarNav {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    margin-top: auto;
    margin-bottom: auto;
  }

  #home-link {
    text-decoration: none;
    color: white;
    font-weight: 700;
    font-size: 22px;
  }

  #nav-bar {
    height: 10vh;
    background-color: black;
  }

  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
