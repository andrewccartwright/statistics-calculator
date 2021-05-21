<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="binom-dist-container" class="container">

    <h1 id="title" class="mt-3">Binomial Distribution Calculator</h1>

    <!-- Submission form -->
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="x-value-input">Enter X Value:</label>
        <input id="x-value-input" class="input" type='text' v-model='xValue' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidXValue">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="prob-input">Enter Probability:</label>
        <input id="prob-input" class="input" type='text' v-model='probability' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidProb">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="sample-size-input">Enter Sample Size:</label>
        <input id="sample-size-input" class="input" type='text' v-model='sampleSize' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidSampleSize">❗Your input can only include numbers and decimals</p>

      <p class="error-message" v-if="xValError">❗The x value must be a positive number that is less than or equal to the sample size</p>
      <p class="error-message" v-if="probError">❗The probability must be a positive number between 0 and 1</p>
      <p class="error-message" v-if="sampSizeError">❗The sample size must be a positive number</p>

      <div class="input-section row mt-3 float-right">
        <button id="submit-button" class="btn btn-primary" type="submit">Submit</button>
      </div>

    </form>

    <!-- Output section -->
    <div id="output-section" v-if="this.binomProb !== ''">
      <h2 id="output-title">Binomial Probability</h2>
      <div id="output-probs" class="text-left" >
        <div class="mt-2"><b>{{"P(X = " + this.xValue + "): "}}</b> {{this.binomProb}}</div>
        <div class="mt-2"><b>{{"P(X \u2264 " + this.xValue + "): "}}</b> {{this.cumulativeBinomProbLEq}}</div>
        <div class="mt-2"><b>{{"P(X &lt; " + this.xValue + "): "}}</b> {{this.cumulativeBinomProbL}}</div>
        <div class="mt-2"><b>{{"P(X \u2265 " + this.xValue + "): "}}</b> {{this.cumulativeBinomProbGEq}}</div>
        <div class="mt-2"><b>{{"P(X &gt; " + this.xValue + "): "}}</b> {{this.cumulativeBinomProbG}}</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'binomial',
    data() {
      return {
        xValue: '',
        probability: '',
        sampleSize: '',
        binomProb: '',
        cumulativeBinomProbLEq: '',
        cumulativeBinomProbL: '',
        cumulativeBinomProbGEq: '',
        cumulativeBinomProbG: '',
        invalidXValue: false,
        invalidProb: false,
        invalidSampleSize: false,
        xValError: false,
        probError: false,
        sampSizeError: false,
      }
    },
    methods: {
      //resets error booleans reset to false to hide error messages
      clearError() {
        this.invalidXValue = false;
        this.invalidProb = false;
        this.invalidSampleSize = false;
        this.xValError = false;
        this.probError = false;
        this.sampSizeError = false;
        this.binomProb = '';
      },
      //checks to make sure user input is valid
      checkInput() {
        if(parseInt(this.xValue) > parseInt(this.sampleSize)) {
          this.xValError = true;
          this.sampleSize = '';
          this.xValue = '';
        }
        if(parseInt(this.xValue < 0)) {
          this.xValError = true;
          this.xValue = '';
        }
        if(parseInt(this.sampleSize < 0)) {
          this.sampSizeError = true;
          this.sampleSize = '';
        }
        if(parseFloat(this.probability) < 0 || parseFloat(this.probability) > 1) {
          this.probError = true;
          this.probability = '';
        }
        let regex = /[^0-9.-]/;
        if(regex.test(this.xValue)) {
          this.invalidXValue = true;
          this.xValue = '';
        }
        if(regex.test(this.probability)) {
          this.invalidProb = true;
          this.probability = '';
        }
        if(regex.test(this.sampleSize)) {
          this.invalidSampleSize = true;
          this.sampleSize = '';
        }
      },
      //this method is run when user clicks submit
      handleSubmit() {
        this.checkInput();
        this.computeProbability();

        //auto-scroll to make output visible
        window.scroll(0,300);
        document.getElementById('binom-dist-container').addEventListener('keyup', function(event) {
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      //Probability is computed using Binomial Distribution PDF. https://en.wikipedia.org/wiki/Binomial_distribution
      computeProbability() {
        this.binomProb = Math.round(this.calculateBinomProb(this.xValue) * 1000000) / 1000000;
        this.cumulativeBinomProbLEq = Math.round(this.calculateCumBinomProb(this.xValue) * 1000000) / 1000000;
        this.cumulativeBinomProbL = Math.round((this.cumulativeBinomProbLEq - this.binomProb) * 1000000) / 1000000;
        this.cumulativeBinomProbGEq = Math.round((1 - this.cumulativeBinomProbL) * 1000000) / 1000000;
        this.cumulativeBinomProbG = Math.round((1 - this.cumulativeBinomProbLEq) * 1000000) / 1000000;
      },
      calculateBinomProb(x) {
        return this.factorial(this.sampleSize)/(this.factorial(x) * this.factorial(this.sampleSize - x)) * Math.pow(this.probability, x) * Math.pow(1 - this.probability, this.sampleSize - x);
      },
      calculateCumBinomProb(x) {
        let sum = 0;
        for(let i = 0; i <= x; i++) {
          sum += this.calculateBinomProb(i);
        }
        return sum;
      },
      //simple regression formula to compute factorials
      factorial(n) {
        if(n === 0) {
          return 1;
        }
        else {
          return n * this.factorial(n-1);
        }
      }
    }
  }
</script>

<style>
  #title {
    font-site: 28px;
  }

  #output-title {
    font-size: 24px;
  }

  #binom-dist-container {
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

  #output-probs {
    width: 60%;
    margin-left: auto;
    margin-right: auto;
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
