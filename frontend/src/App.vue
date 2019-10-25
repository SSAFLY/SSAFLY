<template>
<v-app>
  <div id="head">
    <Header/>
  </div>
  <v-content class="content">
    <router-view />
  </v-content>
  <div id="footer">
    <Footer />
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
import Header from './components/Header'
import Footer from './components/Footer'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  data() {
    return {
    }
  }
}
</script>
