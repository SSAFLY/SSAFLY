<template>
  <div>
    <div class="dialog-outer" v-show="showLogin">
      <div class="d-title text">로그인</div>
      <div class="d-sub text">
        or
        <span @click="showLogin = false">회원가입하기</span>
      </div>
      <v-form ref="formLogin" v-model="valid" lazy-validation>
        <v-text-field
          v-model="id"
          :rules="idRules"
          :disabled="loading"
          :loading="loading"
          label="ID"
          required
          class="mb-2"
          @keydown.enter="signUp"
        ></v-text-field>
        <v-text-field
          v-model="password"
          :rules="passwordRules"
          :disabled="loading"
          :loading="loading"
          label="Password"
          :type="'password'"
          required
          @keydown.enter="login"
        ></v-text-field>
        <div class="btn-box">
          <div class="mb-1">
            <b-button variant="danger" :loading="loading" @click.prevent="reset">Reset</b-button>
            <b-button
              variant="primary"
              :disabled="!valid"
              :loading="loading"
              :checkDialog="checkDialog"
              @click.prevent="login"
            >Login</b-button>
          </div>
        </div>
      </v-form>
      <div class="cancel-btn" @click="closePopup">
        <i class="material-icons">close</i>
      </div>
    </div>

    <div class="dialog-outer" v-show="!showLogin">
      <div class="d-title text">회원가입</div>
      <div class="d-sub text">
        or
        <span @click="showLogin = true" class="text">로그인하기</span>
      </div>
      <v-form ref="formSignup" v-model="valid" lazy-validation>
        <v-text-field
          v-model="id"
          :rules="idRules"
          :disabled="loading"
          :loading="loading"
          label="ID"
          required
          class="mb-2"
          @keydown.enter="signUp"
        ></v-text-field>
       <v-text-field
          v-model="name"
          :rules="nameRules"
          :disabled="loading"
          :loading="loading"
          v-if="!showLogin"
          label="Name"
          required
          class="mb-2"
          @keydown.enter="signUp"
        ></v-text-field>
        <v-text-field
          v-model="password"
          :rules="passwordRules"
          :disabled="loading"
          :loading="loading"
          label="Password"
          :type="'password'"
          class="mb-2"
          required
          @keydown.enter="signUp"
        ></v-text-field>
        <v-text-field
          v-model="passwordConfirm"
          :rules="passwordConfirmRules"
          :disabled="loading"
          :loading="loading"
          label="Password Confirm"
          :type="'password'"
          required
          @keydown.enter="signUp"
        ></v-text-field>
        <div class="btn-box">
          <b-button variant="danger" :loading="loading" @click.prevent="reset">Reset</b-button>
          <b-button
            variant="primary"
            :disabled="!valid"
            :loading="loading"
            @click.prevent="signUp"
          >Sign up</b-button>
        </div>
      </v-form>
      <div class="cancel-btn" @click="closePopup">
        <i class="material-icons">close</i>
      </div>
    </div>
  </div>
</template>


<script>
import Vue from "vue";
import AccountService from '../services/AccountService'
import { mapActions, mapGetters } from "vuex";

export default {
  name: "LoginDialog",
  props: {
    dialog: { type: Boolean, default: false }
  },
  data() {
    return {
      valid: false,
      showLogin: true,
      id: "",
      idRules: [v => !!v || "ID is required"],
      name: "",
      nameRules: [v => !!v || "Name is required"],
      passwordRules: [
        v => !!v || "Password is required",
        v => !v || v.length >= 6 || "Password must be greater than 6 characters"
      ],
      password: "",
      passwordConfirm: "",
    };
  },
  watch: {
    dialog: function() {
      this.reset();
    },
    showLogin: function() {
      this.reset();
    }
  },
  computed: {
    ...mapGetters(["user", "error", "loading", "loginSuccess"]),
    passwordConfirmRules() {
      return [
        () => this.password === this.passwordConfirm || "Password must match",
        v => !!v || "Confirmation password is required"
      ];
    },
    checkDialog() {
      if (this.loginSuccess) {
        return this.$emit("child", false);
      }
      return null;
    }
  },
  methods: {
    reset() {
      this.$refs.formLogin.reset();
      this.$refs.formSignup.reset();
    },
    signUp() {
      this.$store.commit("setLoading", true);
      if (
        typeof this.id === "undefined" ||
        typeof this.name === "undefined" ||
        typeof this.password === "undefined" ||
        typeof this.passwordConfirm === "undefined"
      ) {
        alert("ID and password is required");
      } else if (this.password !== this.passwordConfirm) {
        alert("Password must match");
        this.passwordConfirm = "";
      } else {
        const signupForm = {
          id: this.id,
          name: this.name,
          nickname: "test",
          password: this.password,
        }
        const response = AccountService.signUp(signupForm);
        response
        .then(res => {
          if (res.isSignIn == true) {
            alert("회원가입 완료");
          }
        })
        this.closePopup();
      }
      this.$store.commit("setLoading", false);
    },
    login() {
      this.$store.commit("setLoading", true);
      if (
        typeof this.id === "undefined" ||
        typeof this.password === "undefined"
      ) {
        alert("Id and password is required");
      } else {
        const loginForm = {
          id: this.id,
          password: this.password,
        }
        const response = AccountService.login(loginForm);
        response.then(res => {
          if (res.isLoggedIn == true) {
            this.$store.commit('setLogin', true);
            let user = res.user;
            this.$store.commit('setUser', user);
            console.log("user : " + user);
            this.$store.commit('loginSuccess', true);
            alert(user + "님 반갑습니다!");
          } else {
            alert("Id and password not matched");
          }
        })
        this.reset();
      }
      this.$store.commit("setLoading", false);
    },
    closePopup() {
      this.reset();
      this.showLogin = true;
      return this.$emit("child", false);
    }
  }
};
</script>


<style scoped>
.dialog-outer {
	 width: 100%;
	 height: 450px;
	 background: white;
	 padding: 20px;
	 position: relative;
}
 .dialog-outer .d-title {
	 text-align: center;
	 font-size: 2em;
	 font-weight: bold;
}
 .dialog-outer .d-sub {
	 text-align: center;
}
 .dialog-outer .d-sub span {
	 color: dodgerblue;
	 cursor: pointer;
}
 .btn-box {
	 position: absolute;
	 bottom: 15px;
	 left: 0;
	 margin-left: 60px;
	 height: auto;
}
 .btn-box .btn {
	 margin: 5px 8px;
}
 .btn-box .reset-btn {
	 margin-left: 15px;
}
 .cancel-btn {
	 position: absolute;
	 top: 10px;
	 right: 10px;
	 width: 40px;
	 height: 40px;
	 cursor: pointer;
}
 .cancel-btn i {
	 color: gray;
	 font-size: 2.5em;
}

.vContainer {
	 margin: 0 auto;
	 max-width: 1264px;
}
 @media (max-width: 1264px) {
	 .vContainer {
		 max-width: 960px;
	}
}
 .section-title {
	 text-transform: uppercase;
	 text-align: center;
	 font-size: 3em;
	 font-family: 'Do Hyeon', sans-serif;
	 letter-spacing: 4px;
	 margin: 10px 0 20px;
}
 .section-subtitle {
	 text-transform: uppercase;
	 text-align: left;
	 font-size: 2em;
	 font-family: 'Do Hyeon', sans-serif;
	 letter-spacing: 4px;
	 margin: 10px 0 20px;
}
 .section-btn-box {
	 width: 100%;
	 height: 60px;
	 position: relative;
	 margin-top: 50px;
}
 .load-more-btn {
	 display: inline-block;
	 padding: 5px 10px;
	 cursor: pointer;
	 white-space: nowrap;
	 position: absolute;
	 top: 0;
	 left: 50%;
	 transform: translate(-50%);
	 color: white;
	 background: #00c0ff;
	 border-radius: 3px;
	 box-shadow: 0 5px #00a2ff;
}
 .load-more-btn:hover {
	 box-shadow: 0 3px #00a2ff;
	 top: 2px;
}
 .load-more-btn:active {
	 box-shadow: none;
	 top: 5px;
}
</style>
