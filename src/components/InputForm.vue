<!--
  Author: Andrew Hatch
  Date: 04/12/2021
 -->

<template>
  <div id="input-area">
    <h1 id="title">Statistics Calculator</h1>
    <form @submit.prevent="handleSubmit">
      <p>Please enter data in a comma-separated list.</p>
      <div id="text-area" class="container">
        <textarea id="input-nums" type="text" v-model="data" @focus="clearError" @keypress="clearError"/>
        <p class="error-message" v-if="error">❗Please enter a list of data</p>
        <p class="error-message" v-if="invalidInput">❗Your input can only include numbers and commas</p>
        <div class="btn-holder mt-2">
          <button id="submit-button" type="submit">Submit</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'input-form',
    data() {
      return {
        data: [],
        summaryStats: [],
        error: false,
        invalidInput: false,
      }
    },
    methods: {
      clearError() {
        this.error = false;
        this.invalidInput = false;
      },
      sortData() {
        for(let i = 0; i < this.data.length; i++) {
          for(let j = 0; j < this.data.length - 1 - i; j++) {
            if(this.data[j+1] < this.data[j]) {
              let temp = this.data[j];
              this.data[j] = this.data[j+1];
              this.data[j+1] = temp;
            }
          }
        }
      },
      handleSubmit() {
        if(this.data.length > 0) {
          while(/,$/.test(this.data)) {
            this.data = this.data.slice(0, -1);
          }
          if(/[^0-9,]/.test(this.data)) {
            this.invalidInput = true;
            this.data = [];
          }
          if(/(,){2,}/.test(this.data)) {
            console.log('replace');
            this.data = this.data.replace(/(,){2,}/, ",");
          }
          this.computeSummaryStats(this.data);
          this.summaryStats = [];
        }
        else {
          this.error = true;
        }

      },
      computeSummaryStats() {
        this.data = this.data.split(',');
        this.data = this.data.map(val => parseInt(val));
        this.sortData();

        this.summaryStats[0] = {"Count": this.data.length, id:0}
        this.summaryStats[1] = {"Minimum": this.findMin(this.data), id:1};
        this.summaryStats[2] = {"Maximum": this.findMax(this.data), id:2};
        this.summaryStats[3] = {"Range": (this.summaryStats[2]["Maximum"] - this.summaryStats[1]["Minimum"]), id:3};
        this.summaryStats[4] = {"Mean": this.calculateMean(this.data), id:4}; //Mean
        this.summaryStats[5] = {"Variance": this.calculateVariance(this.data, this.summaryStats[4]["Mean"]), id:5}; //Variance
        this.summaryStats[6] = {"Standard Deviation": Math.sqrt(this.summaryStats[5]["Variance"]), id:6}; //Standard Deviation
        this.$emit('submit:data', this.summaryStats);
      },
      findMin(data) {
        let min = data[0];
        for(let i = 0; i < data.length; i++) {
          if(data[i] < min) {
            min = data[i];
          }
        }
        return min;
      },
      findMax(data) {
        let max = data[0];
        for(let i = 0; i < data.length; i++) {
          if(data[i] > max) {
            max = data[i];
          }
        }
        return max;
      },
      calculateMean(data) {
        let sum = 0;
        for(let i = 0; i < data.length; i++) {
          sum = sum + data[i];
        }
        const mean = sum / data.length;
        return mean;
      },
      calculateVariance(data, mean) {
        let sum = 0;
        for(let i = 0; i < data.length; i++) {
          let temp = Math.pow((data[i] - mean), 2);
          sum += temp;
        }
        const variance = sum / data.length;
        return variance;
      }
    }
  }
</script>

<style scoped>
  .container {
    display: flex;
    justify-content: space-between;
    width: 40vw;
    flex-direction: column;
  }

  .container .button-holder {
    display: flex;
    justify-content: flex-end;
  }

  .error-message {
    color: red;
  }

  #input-nums {
    width: 100vw;
    height: 20vh;
    margin-left: auto;
    margin-right: auto;
  }

  @media (max-width: 800px) {
    #input-nums {
      width: 100vw;
    }
  }
</style>
