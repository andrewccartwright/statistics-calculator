/**
  Author: Andrew Hatch
  Date: 04/12/2021
  Statistics Calculator
*/

//Imports
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App'
import Home from "./components/Home.vue"
import OneVarStats from "./components/OneVarStats.vue"
import ZScoreCalculator from "./components/ZScoreCalculator";
import TScoreCalculator from "./components/TScoreCalculator";
import DistOfSampleMeans from "./components/DistOfSampleMeans";
import Binomial from "./components/Binomial";
import Poisson from "./components/Poisson";
import Geometric from "./components/Geometric";

window.Vue = Vue;

//using VueRouter
Vue.use(VueRouter)

Vue.config.productionTip = false

//router paths and components
const router = new VueRouter({
  mode: 'history',
  base: '/statistics-calculator',
  routes: [
    { path: '/', component: Home },
    { path: '/one-var-stats', component: OneVarStats },
    { path: '/z-score-calc', component: ZScoreCalculator},
    { path: '/dist-of-sample-means', component: DistOfSampleMeans},
    { path: '/t-score-calc', component: TScoreCalculator},
    { path: '/binomial', component: Binomial},
    { path: '/poisson', component: Poisson},
    { path: '/geometric', component: Geometric}
  ]
})

//using the hash mode
new Vue({
  mode: 'hash',
  router,
  render: h => h(App)
}).$mount('#app')
