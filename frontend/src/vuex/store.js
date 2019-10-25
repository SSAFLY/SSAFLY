import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'

Vue.use(Vuex)

const state = {
  user: null,
  loading: false,
  error: null,
  loginSuccess: false,
  problem: {
    title: "",
    img: "",
    answer: "",
    difficulty: 0,
    type: 0
  }
}

export default new Vuex.Store({
  state,
  getters,
  actions,
  mutations: {
    setUser(state, payload) {
      state.user = payload;
    },
    setLoading(state, payload) {
      state.loading = payload;
    },
    setError(state, payload) {
      state.error = payload;
    },
    clearError(state) {
      state.error = null;
    },
    loginSuccess(state, payload) {
      state.loginSuccess = payload;
    },
    setProblem(state, payload) {
      state.problem = payload;
    }
  }
})
