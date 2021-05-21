<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="z-score-container" class="container">

    <h1 id="title" class="mt-3">Z Score Calculator</h1>

    <!-- Submission form -->
    <form @submit.prevent="handleSubmit" class="mb-10">
      <div class="input-section mt-3 row">
        <label htmlFor="x-value-input">Enter X Value:</label>
        <input id="x-value-input" class="input" type='text' v-model='xValue' @focus="clearError" @keypress="clearError" />
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

      <div class="input-section row mt-3 float-right">
        <button id="submit-button" class="btn btn-primary" type="submit">Submit</button>
      </div>

    </form>

    <!-- Output section -->
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
    name: 'z-score-calc',
    data() {
      return {
        xValue: '',
        popMean: '',
        stDev: '',
        zScore: '',
        areaLeft: '',
        areaRight: '',
        invalidXValue: false,
        invalidPopMean: false,
        invalidStDev: false,
      }
    },
    methods: {
      //resets error booleans to false to hide error messages
      clearError() {
        this.invalidXValue = false;
        this.invalidPopMean = false;
        this.invalidStDev = false;
      },
      //checks that the user input is valid
      checkInput() {
        let regex = /[^0-9.-]/;
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
      },

      //this method is run when the user clicks the submit button
      handleSubmit() {
        this.checkInput();
        this.computeZScore();
        this.normalDistCDF();

        //Autoscroll to make output visible
        window.scroll(0,300);
        document.getElementById('z-score-container').addEventListener('keyup', function(event) {
          //Event listener on "Enter" key
          if(event.keyCode === 13) {
            event.preventDefault();
            window.scroll(0,300);
          }
        });
      },
      //z score is computed as (x - Mu)/stdev
      computeZScore() {
        console.log(this.xValue + " " + this.popMean + " " + this.stDev);
        this.zScore = (Math.round((parseFloat(this.xValue) - parseFloat(this.popMean))/parseFloat(this.stDev) * 1000))/ 1000;
      },
      //Normal distribution cumulative density function. Read more at https://en.wikipedia.org/wiki/Normal_distribution
      normalDistCDF() {
        this.areaLeft = Math.round((1/2)*(1 + this.erfFunction(0, this.zScore/Math.sqrt(2), 0.00001))* 10000)/ 10000;

        //right area is the complement of left area
        this.areaRight = Math.round((1 - this.areaLeft) * 10000) / 10000;

        //if z Score is negative, the probabilities are switched
        if(this.zScore < 0) {
          let temp = this.areaLeft;
          this.areaLeft = this.areaRight;
          this.areaRight = temp;
        }
      },
      //integration of error function using the midpoint rule. https://en.wikipedia.org/wiki/Error_function
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
  #title {
    font-size: 28px;
  }

  #output-title {
    font-size: 24px;
  }

  #z-score-container {
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
