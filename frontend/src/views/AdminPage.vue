<template>
    <v-container class="workbook">
        <v-row style="height: 100%">
            <v-btn @click="change" v-if="!now">문제 추가</v-btn>
            <v-btn @click="change" v-if="now">목록 보기</v-btn>
            <v-col cols="12">
                <v-container v-if="now">
                    <v-form ref="formProblem">
                        <v-layout id="problem">
                            <v-flex class="mb-10">
                                <h5>Problem</h5>
                                <v-divider></v-divider>
                                <v-textarea
                                v-model="title"
                                outlined
                                label="Problem">
                                </v-textarea>
                            </v-flex>
                        </v-layout>
                        <v-layout id="answer">
                            <v-flex class="mb-10">
                                <h5>Solution</h5>
                                <v-textarea
                                v-model="answer"
                                outlined
                                label="Solution">
                                </v-textarea>
                                <b-button pill variant="success" @click.prevent="add">추가</b-button>
                                <b-button pill variant="danger" @click.prevent="reset">리셋</b-button>
                            </v-flex>
                        </v-layout>
                    </v-form>
                </v-container>
                <v-container v-if="!now" pa-0 fluid grid-list-md>
                    <v-layout align wrap>
                        <v-flex v-for="problem in problems" v-bind:key="problem.key" xs12 md4 sm6>
                            <v-card class="card-area">
                                <v-card-title style="font-size:13px;">{{problem.title}}</v-card-title>
                                <v-card-actions>
                                    <v-btn text @click="deleteProblem(problem.key)">DELETE</v-btn>
                                    <v-btn text @click="openModal(problem.title, problem.answer, problem.key)">UPDATE</v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-col>
        </v-row>
        <div v-show="mod">
            <v-dialog v-model="mod">
                <v-card>
                    <v-card-title
                    class="headline grey lighten-2"
                    primary-title
                    >
                    {{pTitle}}
                    </v-card-title>
                    <br><br>
                    <v-card-text>
                        <v-textarea rows="10" v-model="pAnswer" label="Solution"></v-textarea>
                    </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="error"
                        text
                        @click="mod = false"
                    >
                        취소
                    </v-btn>
                    <v-btn
                        color="primary"
                        text
                        @click="modifySolution"
                    >
                        수정 완료
                    </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div>
    </v-container>
</template>

<script>
import AccountService from '../services/AccountService';

export default {
    components: {
    },
    data() {
        return{
            now: false,
            uploadImgByFile: true,
            title: "",
            answer: "",
            problems: [],
            mod: false,
            content: '',
            pTitle: "",
            pAnswer: "",
            pKey: 0,
        }
    },
    mounted() {
        this.getAllProblem()
    },
    methods: {
        parents(dialog) {
            this.dialog = dialog;
        },
        change() {
            this.now = !this.now;
        },
        reset() {
            this.$refs.formProblem.reset();
        },
        add() {
            if (
                this.title == "" ||
                this.answer == ""
            ) {
                alert("문제와 정답을 입력해주세요");
            }
            else {
                const problemForm = {
                    title: this.title,
                    answer: this.answer,
                    difficulty: 0,
                    type: 0,
                    image: "",
                }
                const response = AccountService.addProblem(problemForm);
                response.then(res => {
                if (res.success == true) {
                    alert("등록되었습니다!");
                    this.getAllProblem()
                } else {
                    alert("등록에 실패했습니다");
                }
                })
                this.change();
                this.reset();
            }
        },
        getAllProblem() {
            const response = AccountService.getAllProblem();
            response.then(res => {
                this.problems = res
            })
        },
        modifySolution() {
            this.mod = false
            const contents = {
                key: this.pKey,
                content: this.pAnswer
            }
            const response = AccountService.updateProblem(contents);
            response.then(res => {
                if (res.success == true) {
                    alert("수정 완료!")
                    this.getAllProblem()
                }
                else {
                    alert("수정하지 못했습니다")
                }
            })
        },
        deleteProblem(key){
            const response = AccountService.deleteProblem(key);
            response.then(res=> {
                //console.log("res : " + res.success)
                if (res.success == true) {
                    alert("삭제 완료!")
                    this.getAllProblem()
                }
            })
        },
        openModal(pTitle, pAnswer, pKey) {
            this.mod = true
            this.pTitle = pTitle
            this.pAnswer = pAnswer
            this.pKey = pKey
        },
        closeModal() {
            this.mod = false
            this.pTitle = ""
            this.pAnswer = ""
            this.pKey = 0
        }
    }
}
</script>

<style scoped>
.card-area{
    margin: 7px;
    height: 100%;
}

.sidebar{
    background: blueviolet;
    width: 100%;
    height: 100%;
}

.content{
    background: violet;
    height: auto;
}

</style>