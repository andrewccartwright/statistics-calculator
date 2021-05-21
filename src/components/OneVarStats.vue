<!--
  Author: Andrew Hatch
  Date: 04/12/2021
  Subcomponents: InputForm.vue and Output.vue
 -->

<template>
  <div id="one-var-stats-page">
    <input-form @submit:data="displaySummaryStats" @update:summaryStats="updateSummaryStats"/>
    <Output :summaryStats="summaryStats"/>
  </div>
</template>

<script>
  import InputForm from './InputForm.vue'
  import Output from "./Output.vue"

  export default {
    name: 'OneVarStats',
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
  #one-var-stats-page {
    margin-top: 5vh;
  }
</style>
