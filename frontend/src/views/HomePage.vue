<template>
  <div>
    <h1>헤더~</h1>
    <v-container>
      <!-- About Me -->
      <v-layout>
        <v-flex>
          <h2 class="headline my-5 text-xs-center">나는 기문이</h2>
        
        </v-flex>
      </v-layout>

      <v-layout>
        <v-flex>
          <h2 class="headline my-5 text-xs-center">모의평가</h2>
        
        </v-flex>
      </v-layout>

      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">학습장</h2>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<style>
.center {
  text-align: center;
}
</style>

<script>
export default {
  name: 'HomePage',
  components: {
  },
  methods: {
  },
}
</script>
