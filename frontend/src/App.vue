<template>
<v-app>
  <div id="head">
    <MainHeader />
  </div>
  <v-content>
    <router-view />
  </v-content>
  <div id="foot">
    <MainFooter />
  </div>
</v-app>
</template>

<style>
.hidden-sm-and-down .default {
  width: 80px;
  height: 80px;
  padding: 10px 14px;
  border-radius: 50%;
  font-size: 30px;
  line-height: 53px;
  background-color: #FF7F50;
  font-weight: bold;
}

.hidden-md-and-up .default {
  width: 50px;
  height: 50px;
  padding: 10px 12px;
  border-radius: 50%;
  font-size: 20px;
  line-height: 30px;
  background-color: #FF7F50;
  font-weight: bold;
}
</style>

<script>
import store from './store'
import MainHeader from './components/MainHeader'
import MainFooter from './components/MainFooter'

export default {
  name: 'App',
  store,
  components: {
    MainHeader,
    MainFooter
  },
  data() {
    return {
    }
  }
}
</script>
