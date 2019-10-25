import Vue from "vue";
import router from "@/router";

export default {
    signUserUp({ commit }, payload) {
        // 로컬 회원가입
        commit("setLoading", true);
        commit("clearError");
    },
    signUserIn({ commit }, payload) {
    // 로컬 로그인
        commit("setLoading", true);
        commit("clearError");
    },    
}