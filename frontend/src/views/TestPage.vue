<template>
    <div id="test">
        <v-content style="height:150vh;">
            <v-container class="vueBox survey" v-if="survey">
                <b-form @submit="onSubmit" @reset="onReset">
                    <h3>Survey</h3>
                    <i>모의평가를 진행할 전공 카테고리 및 기술 스택을 선택해주세요</i><br>
                    <i>(기본 CS 지식은 공통으로, 필수 출제 유형입니다)</i>
                    <v-divider></v-divider>
                    <br>
                    <br>
                    <label class="surveyTitle">※ Domain</label>
                    <b-form-group>
                        <b-form-checkbox-group
                            id="checkbox-group-2"
                            v-model="checked"
                            :options="domain"
                            name="domain"
                        ></b-form-checkbox-group>
                    </b-form-group>
                    <br>
                    <label class="surveyTitle">※ Language</label>
                    <b-form-group>
                        <b-form-checkbox-group
                            id="checkbox-group-1"
                            v-model="checked"
                            :options="languages"
                            name="language"
                        ></b-form-checkbox-group>
                    </b-form-group>
                    <br>
                    <b-button type="reset" variant="danger">Reset</b-button>
                    <b-button type="submit" variant="primary">Submit</b-button>
                </b-form>
            </v-container>
            <v-container class="vueBox" v-if="!survey">
                <div v-for="problem in problems" v-bind:key="problem.num">
                    <div v-if="problem.num === nowPage">
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
                                label="Answer">
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
            problems: [
                {
                    num: 1,
                    title: "1) REST API의 메소드 4가지를 작성하고, 2) 이 중에서 Idempotent가 성립되지 않는 메소드를 작성하세요.",
                    content: 0,
                    answer: "1) GET, POST, PUT, DELETE\n2) POST",
                    difficulty: 0
                },
                {
                    num: 2,
                    title: "다음은 이분탐색의 수도코드입니다. 빈칸에 들어갈 코드를 순서대로 작성하세요.",
                    content: "https://github.com/SSAFLY/ssafly/blob/master/resource/problem.png?raw=true",
                    answer: "mid - 1 / mid + 1",
                    difficulty: 0
                },
                {
                    num: 3,
                    title: "접근 지정자 4가지에 대해 설명해주세요.",
                    content: 0,
                    answer: "public : 모든 접근 허용\nprotected : 상속받은 클래스 or 같은 패키지만 접근 허용. \ndefault : 기본 제한자. 자신 클래스 내부 or 같은 패키지만 접근 허용\nprivate : 외부 접근 불가능. 같은 클래스 내에서만 가능.",
                    difficulty: 0
                },
                {
                    num: 4,
                    title: "오버로딩과 오버라이딩 차이에 대해 설명해보세요.",
                    content: 0,
                    answer: "오버로딩 : 메소드의 이름은 같고, 매개변수를 다르게 함으로써 여러 메소드를 만드는 것입니다.\n오버라이딩 : 부모클래스로부터 상속받은 메소드를 재정의하는 것입니다. 자식 객체에서 오버라이딩한 메소드는 호출시 오버라이딩한 메소드가 우선시 되어 호출됩니다. (동일한 리턴타입, 메소드 이름, 매개변수를 가져야합니다)",
                    difficulty: 0
                },
                {
                    num: 5,
                    title: "외부 단편화와 내부 단편화에 대해 설명해주세요.",
                    content: 0,
                    answer: "외부 단편화 : 작업보다 많은 공간이 있더라도 실제로 그 작업을 받아들일 수 없는 경우 (메모리 배치에 따라 발생하는 문제)\n내부 단편화 : 작업에 필요한 공간보다 많은 공간을 할당받음으로써 발생하는 내부의 사용 불가능한 공간",
                    difficulty: 0
                },
                {
                    num: 6,
                    title: "Dispatcher-Servlet에 대해 설명해주세요.",
                    content: 0,
                    answer: "서블릿 컨테이너에서 HTTP 프로토콜을 통해 들어오는 모든 요청을 제일 앞에서 처리해주는 프론트 컨트롤러를 말합니다.\n따라서 서버가 받기 전에, 공통처리 작업을 디스패처 서블릿이 처리해주고 적절한 세부 컨트롤러로 작업을 위임해줍니다.\n디스패처 서블릿이 처리하는 url 패턴을 지정해줘야 하는데, 일반적으로는 .mvc와 같은 패턴으로 처리하라고 미리 지정해줍니다.\n디스패처 서블릿으로 인해 web.xml이 가진 역할이 상당히 축소되었습니다.\n기존에는 모든 서블릿을 url 매핑 활용을 위해 모두 web.xml에 등록해 주었지만, 디스패처 서블릿은 그 전에 모든 요청을 핸들링해주면서 작업을 편리하게 할 수 있도록 도와줍니다.\n또한 이 서블릿을 통해 MVC를 사용할 수 있기 때문에 웹 개발 시 큰 장점을 가져다 줍니다.",
                    difficulty: 0
                },
                {
                    num: 7,
                    title: "MyBatis에 대해 설명해주세요.",
                    content: 0,
                    answer: "객체, 데이터베이스, Mapper 자체를 독립적으로 작성하고, DTO에 해당하는 부분과 SQL 실행결과를 매핑해서 사용할 수 있도록 지원합니다.\n기존에는 DAO에 모두 SQL문이 자바 소스상에 위치했으나, MyBatis를 통해 SQL은 XML 설정 파일로 관리합니다.\n설정파일로 분리하면, 수정할 때 설정파일만 건드리면 되므로 유지보수에 매우 좋습니다. 또한 매개변수나 리턴 타입으로 매핑되는 모든 DTO에 관련된 부분도 모두 설정파일에서 작업할 수 있는 장점이 있습니다.",
                    difficulty: 0
                },
                {
                    num: 8,
                    title: "Serialization이 뭔지 설명해보세요.",
                    content: 0,
                    answer: "직렬화로, 객체의 상태 혹은 데이터 구조를 기록할 수 있는 포맷으로 변환해줍니다.\n또한, 나중에 재구성 할 수 있게 자바 객체를 JSON으로 변환해주거나 JSON을 자바 객체로 변환해주는 라이브러리입니다.",
                    difficulty: 0
                },
                {
                    num: 9,
                    title: "포인터를 이해하기 쉽도록 설명해주세요.",
                    content: 0,
                    answer: "포인터는 메모리 주소를 저장하는 변수입니다.\n주소를 지칭하고 있는 곳인데, 예를 들면 엘리베이터에서 포인터는 해당 층을 표시하는 버튼이라고 할 수 있습니다. 10층을 누르면 10층으로 이동하듯, 해당 위치를 가리키고 있는 변수입니다.\n포인터를 사용할 때 주의할 점은, 어떤 주소를 가리키고 있어야만 사용이 가능한 것입니다.",
                    difficulty: 0
                },
                {
                    num: 10,
                    title: "교착상태(DeadLock)가 무엇이며, 4가지 조건에 대해 설명해보세요.",
                    content: 0,
                    answer: "프로세스가 자원을 얻지 못해 다음 처리를 하지 못하는 상태를 말합니다. 시스템적으로 한정된 자원을 여러 곳에서 사용하려고 할 때 발생하는 문제입니다.\n상호배제 : 프로세스들이 필요로 하는 자원에 대해 배타적 통제권을 요구함\n점유대기 : 프로세스가 할당된 자원을 가진 상태에서 다른 자원 기다림\n비선점 : 프로세스가 어떤 자원의 사용을 끝날 때까지 그 자원을 뺏을 수 없음\n순환대기 : 각 프로세스는 순환적으로 다음 프로세스가 요구하는 자원을 갖고 있음",
                    difficulty: 0
                }
            ]
        }
    },
    methods: {
        onSubmit(evt) {
            evt.preventDefault()
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
            this.scrollToTop();
            this.nowPage++;
            this.showing = false;
            if(this.nowPage === 10) {
                this.full = false;
            }
            else {
                this.empty = false;
            }
        },
        sub() {
            this.scrollToTop();
            this.nowPage--;
            this.showing = false;
             if(this.nowPage === 1) {
                this.empty = true;
            }
            else {
                this.full = true;
            }
        },
        change(message) {
            return message.split('\n').join('<br>');
        },
        scrollToTop() {
            window.scrollTo(0,0);
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