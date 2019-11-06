<template>
    <div id="test">
        <v-content style="height:150vh;">
            <v-container class="vueBox survey" v-if="survey">
                <b-form @submit="onSubmit" @reset="onReset">
                    <h3>Survey</h3>
                    <br>
                    <i>모의시험은 기본 CS 지식과 함께 Spring Framework, Vue.js에 대한 문제가 나옵니다.</i><br>
                    <v-divider></v-divider>
                    <i>푼 문제는 자동으로 학습장에 저장되어, 모의시험이 끝난 후 재학습이 가능합니다.</i><br>
                    <i>준비되었다면, GO 버튼을 눌러주세요.</i><br>
                    <br>
                    <b-button type="submit" variant="primary">GO</b-button>
                </b-form>
            </v-container>
            <v-container class="vueBox" v-if="!survey">
                <div v-for="problem in problems" v-bind:key="problem.num">
                    <div v-if="problem.num == nowPage">
                        <v-layout id="problem">
                            <v-flex class="mb-10">
                                <h5>Problem {{nowPage}}</h5>
                                <v-divider></v-divider>
                                <div class="inner">
                                    <p>{{problem.title}}</p>
                                    <p v-if="problem.content !== 0">
                                        <img :src="problem.content" height="60%" width="60%"/>
                                    </p>
                                </div>
                            </v-flex>
                        </v-layout>
                        <v-layout id="answer">
                            <v-flex class="mb-10">
                                <h5>Answer</h5>
                                <v-textarea
                                outlined
                                label="Answer" v-model="userAnswer">
                                </v-textarea>
                                <b-row>
                                    <b-col cols="5"><b-button pill variant="primary" v-on:click="showing = !showing">Solution</b-button></b-col>
                                    <b-col cols="1"></b-col>
                                    <b-col cols="4">
                                    <b-button pill @click="sub" v-visible="!empty" :style="{visibility: empty ? 'visible' : 'hidden'}">prev</b-button>
                                    <span> {{nowPage}}/10 </span>
                                    <b-button pill variant="success" @click="add" v-show="full">next</b-button>
                                    </b-col>
                                    <b-col cols="2"><b-button pill variant="danger" @click="onExit">Exit</b-button></b-col>
                                </b-row>
                            </v-flex>
                        </v-layout>

                        <v-layout id="solution">
                            <transition name="fade">
                                <v-flex class="mb-10" v-if="showing">
                                    <h5>Solution</h5>
                                    <v-divider></v-divider>
                                    <div v-html=change(problem.answer)></div>
                                </v-flex>
                            </transition>
                        </v-layout>
                        </div>
                    </div>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import Vue from 'vue';
import AccountService from '../services/AccountService';

Vue.directive('visible', function(el, binding) {
el.style.visibility = !!binding.value ? 'visible' : 'hidden';
});

export default {
    data() {
        return{
            nowPage: 1,
            showing: false,
            empty: true,
            full: true,
            survey: true,
            row: null,
            checked: [],
            languages: [
                { text: 'Java', value: 'java' },
                { text: 'Python', value: 'python' }
            ],
            domain: [
                { text: 'Web', value: 'web' },
                { text: 'Spring Framework', value: 'spring' },
                { text: 'Vue.js Framework', value: 'vue' },
                { text: 'Django Framework', value: 'django' }
            ],
            problems: [],
            userAnswer: ""
        }
    },
    methods: {
        onSubmit(evt) {
            evt.preventDefault()
            this.getProblem(sessionStorage.getItem("user"))
            this.survey = false
        },
        onReset(evt) {
            evt.preventDefault()
            this.checked = []
        },
        onExit() {
            this.nowPage = 1,
            this.survey = true,
            this.checked = []
        },
        add() {
            console.log(this.problems[this.nowPage])
            this.sendAnswer(this.problems[this.nowPage -1].key, this.userAnswer)
            this.problems[this.nowPage -1].userAnswer = this.userAnswer;
            this.scrollToTop();
            this.nowPage++;
            this.showing = false;
            if(this.nowPage === 10) {
                this.full = false;
            }
            else {
                this.empty = false;
            }
            if(this.nowPage<10){
                var ua =  this.problems[this.nowPage -1].userAnswer;
                if(ua != null)
                    this.userAnswer=ua;
                else
                    this.userAnswer="";
            }
        },
        sub() {
            var ten = false;
            if(this.nowPage==10) {
                this.nowPage=9;
                ten = true;
            }

            this.sendAnswer(this.problems[this.nowPage-1].key, this.userAnswer)

            this.problems[this.nowPage-1].userAnswer = this.userAnswer;
            this.scrollToTop();
            if(!ten){
                this.nowPage--;
            }
            this.showing = false;
            if(this.nowPage === 1) {
                this.empty = true;
            }
            else {
                this.full = true;
            }
            var ua =  this.problems[this.nowPage-1].userAnswer;
            if(ua != null)
                this.userAnswer=ua;
            else
                this.userAnswer="";
        },
        change(message) {
            return message.split('\n').join('<br>');
        },
        scrollToTop() {
            window.scrollTo(0,0);
        },
        getProblem(id) {
            const response = AccountService.getProblem(id);
            response.then(res => {
                this.problems = res;
            })
        },
        sendAnswer(key, ans){
            const data = {
                problemKey: key,
                answer: ans
            }
            //console.log(data)
            const response = AccountService.sendAnswer(data);
        }
    }
}
</script>

<style scoped>
.btn {
  border-radius: 0;
  margin-right: .5em;
  color: #fff !important;
  width: 100px;
}

i {
    color: gray;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

.vueBox {
  background-color: white;
  border: 2px solid black;
  border-radius: 2px;
  padding: 40px;
  margin-top: 50px;
  margin-bottom: 20px;
}

.vueBox.survey{
    padding-bottom: 90px;
}

.surveyTitle {
    font-weight: bold;
    font-size: 1.2em;
}

</style>