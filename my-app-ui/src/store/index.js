import Vue from 'vue'
import Vuex from 'vuex'
import citizen from '@/store/modules/citizen'

Vue.use(Vuex)

// Create a new store instance.
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  },
  modules: {
    app,
    citizen
  }
})

console.log("第一次："+store.state.count)
store.commit('increment')
console.log("第二次："+store.state.count)

export default store
