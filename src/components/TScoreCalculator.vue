<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="t-score-container" class="container">

    <h1 id="title" class="mt-3">T Score Calculator</h1>

    <!-- Submission form -->
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="sample-mean-input">Enter Sample Mean:</label>
        <input id="sample-mean-input" class="input" type='text' v-model='sampleMean' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidSampleMean">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="pop-mean-input">Enter Population Mean:</label>
        <input id="pop-mean-input" class="input" type='text' v-model='popMean' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidPopMean">❗Your input can only include numbers and decimals</p>


      <div class="input-section mt-3 row">
        <label htmlFor="st-dev-input">Enter Sample Standard Deviation:</label>
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

    <!-- Output section -->
    <div v-if="this.tScore !== ''" id="output-section">
      <h2 id="output-title">T Score Statistics:</h2>
      <div id="t-score" class="mt-2">T Score: {{this.tScore}}</div>
      <div id="area-left" class="mt-2">Area to the Left: {{this.areaLeft}}</div>
      <div id="area-right" class="mt-2">Area to the Right: {{this.areaRight}}</div>
    </div>
  </div>
</template>

<script>
  import * as stats from "js-stats";

  export default {
    name: 't-score-calc',
    data() {
      return {
        sampleMean: '',
        popMean: '',
        stDev: '',
        sampleSize: '',
        degreesFreedom: '',
        tScore: '',
        areaLeft: '',
        areaRight: '',
        invalidPopMean: false,
        invalidSampleMean: false,
        invalidStDev: false,
        invalidSampleSize: false,
      }
    },
    methods: {
      clearError() {
        this.invalidPopMean = false;
        this.invalidSampleMean = false;
        this.invalidStDev = false;
        this.invalidSampleSize = false;
      },
      //checks to make sure that user input is valid
      checkInput() {
        let regex = /[^0-9.]/;
        if(regex.test(this.popMean)) {
          this.invalidPopMean = true;
          this.popMean = '';
        }
        if(regex.test(this.sampleMean)) {
          this.invalidSampleMean = true;
          this.sampleMean = '';
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
        this.degreesFreedom = this.sampleSize - 1;
        this.checkInput();
        this.computeTScore();
        this.calculateArea();

        //auto-scroll to make output visible
        window.scroll(0,300);
        document.getElementById('t-score-container').addEventListener('keyup', function(event) {
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      //further functionality, such as t-CDF calculation, will be included at a later date
      computeTScore() {
        this.tScore = (Math.round((parseFloat(this.sampleMean) - parseFloat(this.popMean))/parseFloat(this.stDev / Math.sqrt(this.sampleSize)) * 1000))/ 1000;
      },

      //using js-stats package https://www.npmjs.com/package/js-stats
      calculateArea() {
        var t_dist = new stats.TDistribution(this.degreesFreedom);
        this.areaLeft = t_dist.cumulativeProbability(this.tScore);
        this.areaRight = 1 - this.areaLeft;
      },
    },
  }
</script>

<style>
  #title {
    font-site: 28px;
  }

  #output-title {
    font-site: 24px;
  }

  #t-score-container {
    display: flex;
    width: 50vh;
    flex-direction: column;
  }

  #output-section {
    margin-top: 15vh;
    margin-bottom: 5vh;
    border: 2px solid black;
    border-radius: 30px;
    padding: 20px;
  }

  #submit-button {
    position: absolute;
    right: 0;
  }

  .input {
    width: 40vw;
    max-width: 400px;
    display: flex;
    margin-left: auto;
    margin-right: auto;
    text-align: left;
  }

  .error-message {
    color: red;
  }

  .input-section {
    display: flex;
    width: 100%;
    max-width: 600px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
