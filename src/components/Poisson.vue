<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="poisson-dist-container" class="container">

    <h1 id="title" class="mt-3">Poisson Distribution Calculator</h1>

    <!-- Submission form -->
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="x-value-input">Enter X Value:</label>
        <input id="x-value-input" class="input" type='text' v-model='xValue' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidXValue">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="mean-input">Enter Mean:</label>
        <input id="mean-input" class="input" type='text' v-model='mean' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidMean">❗Your input can only include numbers and decimals</p>

      <p class="error-message" v-if="xValError">❗The x value must be a positive number</p>
      <p class="error-message" v-if="meanError">❗The mean must be a positive number</p>

      <div class="input-section row mt-3 float-right">
        <button id="submit-button" class="btn btn-primary" type="submit">Submit</button>
      </div>

    </form>

    <!-- Output section -->
    <div id="output-section" v-if="this.poissonProb !== ''">
      <h2 id="output-title">Poisson Probability</h2>
      <div id="output-probs" class="text-left" >
        <div class="mt-2"><b>{{"P(X = " + this.xValue + "): "}}</b> {{this.poissonProb}}</div>
        <div class="mt-2"><b>{{"P(X \u2264 " + this.xValue + "): "}}</b> {{this.cumulativePoissonProbLEq}}</div>
        <div class="mt-2"><b>{{"P(X &lt; " + this.xValue + "): "}}</b> {{this.cumulativePoissonProbL}}</div>
        <div class="mt-2"><b>{{"P(X \u2265 " + this.xValue + "): "}}</b> {{this.cumulativePoissonProbGEq}}</div>
        <div class="mt-2"><b>{{"P(X &gt; " + this.xValue + "): "}}</b> {{this.cumulativePoissonProbG}}</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'poisson',
    data() {
      return {
        xValue: '',
        mean: '',
        poissonProb: '',
        cumulativePoissonProbLEq: '',
        cumulativePoissonProbL: '',
        cumulativePoissonProbGEq: '',
        cumulativePoissonProbG: '',
        invalidXValue: false,
        invalidMean: false,
        meanError: false,
        xValError: false,
      }
    },
    methods: {
      //error booleans reset to false to hide error messages
      clearError() {
        this.invalidXValue = false;
        this.invalidMean = false;
        this.meanError = false;
        this.xValError = false;
        this.poissonProb = '';
      },
      //checks to make sure user input is valid
      checkInput() {
        if(parseInt(this.xValue) < 0) {
          this.xValError = true;
          this.xValue = '';
        }
        if(parseInt(this.mean) < 0) {
          this.meanError = true;
          this.mean = '';
        }
        let regex = /[^0-9.-]/;
        if(regex.test(this.xValue)) {
          this.invalidXValue = true;
          this.xValue = '';
        }
        if(regex.test(this.mean)) {
          this.invalidMean = true;
          this.mean = '';
        }
      },
      //this method is called when the submit button is clicked
      handleSubmit() {
        this.checkInput();
        this.poissonProb = this.computeProbability(this.xValue);
        this.cumulativePoissonProbLEq = this.computeCumulativeProbability(this.xValue);
        this.cumulativePoissonProbL = Math.round((this.cumulativePoissonProbLEq - this.poissonProb) * 1000000) / 1000000;
        this.cumulativePoissonProbGEq = Math.round((1 - this.cumulativePoissonProbL) * 1000000) / 1000000;
        this.cumulativePoissonProbG = Math.round((1 - this.cumulativePoissonProbLEq) * 1000000) / 1000000;

        //auto-scroll to make output visible
        window.scroll(0,300);
        document.getElementById('poisson-dist-container').addEventListener('keyup', function(event) {
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      //uses PDF of Poisson Distribution. https://en.wikipedia.org/wiki/Poisson_distribution
      computeProbability(x) {
        return Math.round((Math.exp(-this.mean) * Math.pow(this.mean, x) / this.factorial(x)) * 1000000) / 1000000;
      },
      //Adds up individual probabilities from 0 to X to get cumulative probability
      computeCumulativeProbability() {
        let sum = 0;
        for(let x = 0; x <= this.xValue; x++) {
          sum += this.computeProbability(x);
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
      },

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

  #poisson-dist-container {
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
