<template>
<div id = "header">
  <!-- <modal v-if="showModal" @close="showModal = false"> -->
  <div class="px-50px">
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="text-align-middle">
        <router-link :to="{ name: 'home' }" class="navbar navbar-brand d-inline-flex">
          <div class="ssafly-logo">
            <img class="ssafly-logo-img" src="@/assets/ssafly-logo.png">
            <span class="ssafly-logo-txt">SSAFLY</span>
          </div>
        </router-link>
      </div>
        <button class=" navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      <div class="collapse navbar-collapse " id="navbarNavAltMarkup">
        <div class="nav navbar-nav ml-auto">
          <router-link :to="{ name: 'home' }" class="nav-item nav-link h4"></router-link>
          <router-link :to="{ name: 'test' }" class="nav-item nav-link h4">모의시험</router-link>
          <router-link :to="{ name: 'workbook' }" class="nav-item nav-link h4">학습장</router-link>
          <div v-if="!user" class="nav-item nav-link h4" @click.stop="dialog = true">로그인</div>
          <div v-if="user" v-on:click="signOut" class="nav-item nav-link h4">로그아웃</div>
        </div>
      </div>
    </nav>
  </div>
  <v-dialog v-model="dialog" class="login-dialog" width="300" persistent>
    <LoginDialog @child="parents" :dialog="dialog" />
  </v-dialog>
</div>
</template>
<script>
import LoginDialog from "@/components/LoginDialog";
import { mapActions, mapState } from "vuex";

export default {
name: 'Header',
components: {
  LoginDialog
},
props: {
  text: {
    type: String
  }
},
computed: {
  ...mapState(["user"])
},
methods: {
  scrollToTop() {
    window.scrollTo(0, 0);
  },
  parents(dialog) {
    this.dialog = dialog;
  },
  signOut() {
    alert("로그아웃 되었습니다");
    this.$store.commit("setUser", null);
    this.$store.commit("setLogin", false);
    this.$store.commit("loginSuccess", false);
  }
},
data() {
  return {
    dialog: false
  }
}
}
</script>
<style>
.navbar{
background: steelblue !important;
height: 50px;
padding: 0 5% 0 5%;
}
nav.navbar-light {
background : white;
}
.nav-item.nav-link.h4{
font-size: 15px;
font-weight: bold;
color: white;
margin : 0 0 0 40px;
}
.ssafly-logo{
display: inline;
}
.ssafly-logo-txt{
color:white;
font-family: inherit;
font-weight: bold;
margin-left:10px;
}
.ssafly-logo-img{
height: 30px;
}
a {
cursor: pointer;
}
</style>