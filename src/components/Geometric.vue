<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="geomet-dist-container" class="container">

    <h1 id="title" class="mt-3">Geometric Distribution Calculator</h1>

    <!-- Submission form -->
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="x-value-input">Enter X Value:</label>
        <input id="x-value-input" class="input" type='text' v-model='xValue' @focus="clearError" @keypress="clearError" />
        <div id="type-choice-section">
          <p>The random variable </p>
          <select id="type-choice" v-model="distType" required>
            <option value="includes">includes</option>
            <option value="doesNotInclude">does not include</option>
          </select>
          <p> a success trial.</p>
       </div>
      </div>
      <p class="error-message" v-if="invalidXValue">❗Your input can only include numbers and decimals</p>

      <div class="input-section mt-3 row">
        <label htmlFor="mean-input">Enter Probability:</label>
        <input id="mean-input" class="input" type='text' v-model='probability' @focus="clearError" @keypress="clearError" />
      </div>
      <p class="error-message" v-if="invalidProb">❗Your input can only include numbers and decimals</p>

      <p class="error-message" v-if="xValError">❗The x value must be a positive number</p>
      <p class="error-message" v-if="probError">❗The probability must be a positive number between 0 and 1</p>

      <div class="input-section row mt-3 float-right">
        <button id="submit-button" class="btn btn-primary" type="submit">Submit</button>
      </div>

    </form>

    <!-- Output section -->
    <div id="output-section" v-if="this.geometProb !== ''">
      <h2 id="output-title">Geometric Probability</h2>
      <div id="output-probs" class="text-left" >
        <div class="mt-2"><b>{{"P(X = " + this.xValue + "): "}}</b> {{this.geometProb}}</div>
        <div class="mt-2"><b>{{"P(X \u2264 " + this.xValue + "): "}}</b> {{this.cumulativeGeometProbLEq}}</div>
        <div class="mt-2"><b>{{"P(X &lt; " + this.xValue + "): "}}</b> {{this.cumulativeGeometProbL}}</div>
        <div class="mt-2"><b>{{"P(X \u2265 " + this.xValue + "): "}}</b> {{this.cumulativeGeometProbGEq}}</div>
        <div class="mt-2"><b>{{"P(X &gt; " + this.xValue + "): "}}</b> {{this.cumulativeGeometProbG}}</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'geometric',
    data() {
      return {
        xValue: '',
        probability: '',
        geometProb: '',
        distType: '',
        cumulativeGeometProbLEq: '',
        cumulativeGeometProbL: '',
        cumulativeGeometProbGEq: '',
        cumulativeGeometProbG: '',
        invalidXValue: false,
        invalidProb: false,
        probError: false,
        xValError: false,
      }
    },
    methods: {
      //error booleans reset to false to hide error messages
      clearError() {
        this.invalidXValue = false;
        this.invalidProb = false;
        this.probError = false;
        this.xValError = false;
        this.geometProb = '';
      },
      //checks to make sure user input is valid
      checkInput() {
        if(parseInt(this.xValue) < 0) {
          this.xValError = true;
          this.xValue = '';
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
      },
      //this method is called when the submit button is clicked
      handleSubmit() {
        this.checkInput();
        this.geometProb = this.computeProbability(this.xValue);
        this.cumulativeGeometProbLEq = Math.round(this.computeCumulativeProbability(this.xValue) * 1000000) / 1000000;
        this.cumulativeGeometProbL = Math.round((this.cumulativeGeometProbLEq - this.geometProb) * 1000000) / 1000000;
        this.cumulativeGeometProbGEq = Math.round((1 - this.cumulativeGeometProbL) * 1000000) / 1000000;
        this.cumulativeGeometProbG = Math.round((1 - this.cumulativeGeometProbLEq) * 1000000) / 1000000;

        //auto-scroll to make output visible
        window.scroll(0,300);
        document.getElementById('geomet-dist-container').addEventListener('keyup', function(event) {
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      //uses PDF of Geometric Distribution. https://en.wikipedia.org/wiki/Geometric_distribution
      computeProbability(x) {
        if(this.distType === 'includes') {
          return Math.round((Math.pow((1 - this.probability), x - 1) * this.probability) * 1000000) / 1000000;
        }
        else {
          return Math.round((Math.pow((1 - this.probability), x) * this.probability) * 1000000) / 1000000;
        }
      },
      //Adds up individual probabilities from 0 to X to get cumulative probability
      computeCumulativeProbability() {
        let sum = 0;
        for(let x = this.distType === 'includes' ? 1 : 0; x <= this.xValue; x++) {
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

  #geomet-dist-container {
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

  #type-choice-section {
    display: flex;

  }

  #type-choice {
    width: 100px;
    height: 40px;
    line-height: 13px;
    font-size: 14px;
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
