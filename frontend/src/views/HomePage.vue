<template>
<div>
 <v-layout class="banner">
     <v-flex xs12 class="banner-flex">
       <h2 class="bannerText">SSAFLY</h2>
       <h2 class="bannerText domain">Fly up to the world</h2>
     </v-flex>
 </v-layout>
 <v-container pa-0>
   <!-- About Me -->
   <v-layout class="introduction">
     <v-flex xs12 class="introduciton-box">
       <span class="number">
         01
       </span>
       <h4 class="headlineText">
         SSAFLY란?
       </h4>
       <p class="description">
         SSAFY 교육생들을 위한 주관식과 서술형으로 이루어진
         IT 모의시험 웹 서비스로, 필기시험과 면접에 도움이
         되는 서비스를 제공합니다.
       </p>
     </v-flex>
     <v-flex xs12 class="introduciton-box">
       <span class="number">
         02
       </span>
       <h4 class="headlineText">
         내가 원하는 분야 시험보기
       </h4>
       <p class="description">
         기초 IT 지식, 알고리즘, 자료구조를 기본으로 자기만의 시험지를 만듭니다. 특정 도메인을 선택하여 시험을 볼 수 도 있습니다.
       </p>
     </v-flex>
      <v-flex xs12 class="introduciton-box">
       <span class="number" >
         03
       </span>
       <h4 class="headlineText">
         나만의 학습장 만들기
       </h4>
       <p class="description">
         모의시험을 본 후, 자신만의 학습장을 만들어보세요.
         학습장을 통해 IT 필기시험과 기술 면접을
         손쉽게 정리할 수 있습니다.
       </p>
     </v-flex>
   </v-layout>
 </v-container>
  <v-layout class="service-description">
    <img src="../assets/service-description.png" style="width:100vw;">
  </v-layout>
  <v-layout class="end-area">
    <v-flex xs12>
      <img src="../assets/ssafly-logo.png">
      <h2 class="end-text">SSAFLY. Fly up to the world</h2>
      <p class="end-text-des">SSAFY 교육생들의 미래를 진심으로 응원합니다.</p>
      <p class="end-text-des"> #취업 성공으로 가는 지름길 #SSAFLY</p>
    </v-flex>
  </v-layout>
</div>
</template>
<style scoped>
@import url('https://fonts.googleapis.com/css?family=Caveat&display=swap');
.banner{
 background-image: url('../assets/banner-image.png');
 background-size: 100%;
 padding-top: 10%;
 padding-left: 10%;
 height: 70vh;
 width: 100vw;
}
.center {
 text-align: center;
}

.banner-flex{
  height: 50%;

}

.bannerText{
 line-height: 1.2em;
 font-size :4.5vw;
 font-family: 'Caveat', cursive;
 color:white;
 margin-bottom: 15px;
}
.bannerText.domain{
 line-height: 1.2em;
 font-size :3.0vw;
 font-weight: bold;
 color:white;
}
.introduction{
  padding: 40px 0 40px 0;
}
.introduciton-box{
 padding: 30px;
}
.number{
 font-family: fantasy;
 font-size: 50px;
}

.headlineText{
 font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
 margin-top: 15px;
 margin-bottom: 15px;
}

.test-detail{
 background-color: aliceblue;
 height: 70vh;
}

.description{
 font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
 margin-bottom: 15px;
 line-height:250%;
}

.service-description{
 height:auto;
}


.end-area{
  padding: 70px;
  text-align: center;


}

.end-text{
  line-height: 2.5em;
  font-size :2.5vw;
  font-family: 'Caveat', cursive;
  color:black;
  margin-bottom: 15px;
}

.end-text-des{
  line-height: 1.0em;
  font-size :1.2vw;

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