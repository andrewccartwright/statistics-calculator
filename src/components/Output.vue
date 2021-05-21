<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="output" className="text-center mt-3">
    <table id="table">
      <thead>
        <tr v-if="summaryStats.length !== 0">
          <th>Summary Statistics</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="stat in summaryStats" :key="stat.id">
          <td>{{ Object.keys(stat)[0] + ":" }}</td>
          <td>{{ Math.round(Object.values(stat)[0] * 1000) / 1000 }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    name: 'output',
    props: {
      summaryStats: Array
    },
  }
</script>

<style>
 #output {
   width: 60vw;
   margin-left: auto;
   margin-right: auto;
 }
</style>
