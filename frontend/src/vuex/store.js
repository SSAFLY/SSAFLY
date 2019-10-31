import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
  isLoggedIn: false,
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
  getters: {
    user: state => state.user,
    loading: state => state.loading,
    error: state => state.error,
    loginSuccess: state => state.loginSuccess
  },
  mutations: {
    setLogin(state, login) {
      state.isLoggedIn = login
    },
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
    }
  }
})
