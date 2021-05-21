<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="z-score-container" class="container">
    <h1 id="title" class="mt-3">Distribution of Sample Means</h1>
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="sample-mean-input">Enter Sample Mean:</label>
        <input id="sample-mean-input" class="input" type='text' v-model='sampleMean' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidXValue">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="population-mean-input">Enter Population Mean:</label>
        <input id="sample-mean-input" class="input" type='text' v-model='popMean' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidPopMean">❗Your input can only include numbers and decimals</p>


      <div class="input-section mt-3 row">
        <label htmlFor="st-dev-input">Enter Population Standard Deviation:</label>
        <input id="st-dev-input" class="input" type='text' v-model='stDev' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidStDev">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="sample-size-input">Enter Sample Size:</label>
        <input id="sample-size-input" class="input" type='text' v-model='sampleSize' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidSampleSize">❗Your input can only include numbers and decimals</p>


      <div class="input-section row mt-3 float-right">
        <button id="submit-button" class="btn btn-primary" type="submit">Submit</button>
      </div>

    </form>

    <div v-if="this.zScore !== ''" id="output-section">
      <h2 id="output-title">Z Score Statistics:</h2>
      <div id="z-score" class="mt-2">Z Score: {{this.zScore}}</div>
      <div id="area-left" class="mt-2">Area to the Left: {{this.areaLeft}}</div>
      <div id="area-right" class="mt-2">Area to the Right: {{this.areaRight}}</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'dist-of-sample-means',
    data() {
      return {
        xValue: '',
        popMean: '',
        stDev: '',
        sampleSize: '',
        zScore: '',
        areaLeft: '',
        areaRight: '',
        invalidXValue: false,
        invalidPopMean: false,
        invalidStDev: false,
        invalidSampleSize: false,
      }
    },
    methods: {
      clearError() {
        this.invalidXValue = false;
        this.invalidPopMean = false;
        this.invalidStDev = false;
        this.invalidSampleSize = false;
      },
      checkInput() {
        let regex = /[^0-9.]/;
        if(regex.test(this.xValue)) {
          this.invalidXValue = true;
          this.xValue = '';
        }
        if(regex.test(this.popMean)) {
          this.invalidPopMean = true;
          this.popMean = '';
        }
        if(regex.test(this.stDev)) {
          this.invalidStDev = true;
          this.stDev = '';
        }
        if(regex.test(this.sampleSize)) {
          this.invalidSampleSize = true;
          this.sampleSize = '';
        }
      },
      handleSubmit() {
        this.checkInput();
        this.computeZScore();
        this.normalDistCDF();
        window.scroll(0,300);
        document.getElementById('z-score-container').addEventListener('keyup', function(event) {
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      computeZScore() {
        console.log(this.sampleMean + " " + this.popMean + " " + this.stDev);
        this.zScore = (Math.round((parseFloat(this.sampleMean) - parseFloat(this.popMean))/parseFloat(this.stDev / Math.sqrt(this.sampleSize)) * 1000))/ 1000;
      },
      normalDistCDF() {
        this.areaLeft = Math.round((1/2)*(1 + this.erfFunction(0, this.zScore/Math.sqrt(2), 0.00001))* 10000)/ 10000;
        this.areaRight = Math.round((1 - this.areaLeft) * 10000) / 10000;
        if(this.zScore < 0) {
          let temp = this.areaLeft;
          this.areaLeft = this.areaRight;
          this.areaRight = temp;
        }
      },
      //integration of error function using the midpoint rule
      erfFunction(start, end, step) {
        let area = 0;

        if(end < 0) {
          for(let z = start; z > end; z -= step) {
            area += Math.exp(-Math.pow((z + step/2),2)) * step;
          }
        }
        else {
          for(let z = start; z < end; z += step) {
            area += Math.exp(-Math.pow((z + step/2),2)) * step;
          }
        }
        return area * (2/Math.sqrt(Math.PI));
      }
    },
  }
</script>

<style>

</style>
