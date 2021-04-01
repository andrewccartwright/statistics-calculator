<template>
  <div id="app">
    <input-form @submit:data="displaySummaryStats" @update:summaryStats="updateSummaryStats"/>
    <Output :summaryStats="summaryStats"/>
  </div>
</template>

<script>
import InputForm from './components/InputForm.vue'
import Output from "./components/Output.vue"

export default {
  name: 'App',
  components: {
    InputForm,
    Output,
  },
  data() {
    return {
      summaryStats: []
    }
  },
  methods: {
    displaySummaryStats(summaryStats) {
      this.summaryStats = summaryStats;
    },
    updateSummaryStats(summaryStats) {
      this.summaryStats = summaryStats;
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
