import Vue from 'vue'
import vuetify from './plugin/vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'font-awesome/css/font-awesome.min.css'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import './registerServiceWorker'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import VueSweetalert2 from 'vue-sweetalert2';

Vue.config.productionTip = false

Vue.use(BootstrapVue);
Vue.use(VueSweetalert2);

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
