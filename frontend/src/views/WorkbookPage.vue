<template>
    <v-container class="workbook">
        <v-row style="height: 100%">
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
                        <v-flex v-for="problem in problems" v-bind:key="problem.problemKey" xs12 md4 sm6>
                            <v-card class="card-area">
                                <v-card-title style="font-size:13px;">{{problem.problem}}</v-card-title>
                                <v-card-actions>
                                    <v-btn text @click="deleteWorkbook(problem.problemKey)">DELETE</v-btn>
                                    <v-btn text @click="openModal(problem.problem, problem.solution, problem.answer, problem.problemKey)">UPDATE</v-btn>
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
                        <v-textarea rows="10" v-model="pAnswer" label="Answer"></v-textarea>
                    </v-card-text>
                    <br><br>
                    <v-card-text>
                        <v-textarea rows="10" v-model="pSolution" label="Solution" readonly="readonly" disabled></v-textarea>
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
                        @click="updateWorkbook"
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
import { mapActions, mapState } from "vuex";
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
            pSolution: "",
            pAnswer: "",
            pKey: 0,
        }
    },
    computed: {
        ...mapState(["user"])
    },
    mounted() {
        this.getAllWorkbookByID(this.user)
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
        getAllWorkbookByID(id) {
            const response = AccountService.getAllWorkbookByID(id);
            response.then(res => {
                this.problems = res
            })
        },
        updateWorkbook() {
            this.mod = false
            const contents = {
                key: this.pKey,
                content: this.pAnswer
            }
            const response = AccountService.updateWorkbook(this.user, contents);
            response.then(res => {
                alert("수정 완료!")
                this.getAllWorkbookByID(this.user)
            })
        },
        deleteWorkbook(pKey){
            const response = AccountService.deleteWorkbook(this.user,pKey);
            response.then(res=> {
                alert("삭제 완료!")
                this.getAllWorkbookByID(this.user)
            })
        },
        openModal(pTitle, pSolution,pAnswer, pKey) {
            this.mod = true
            this.pTitle = pTitle
            this.pSolution = pSolution
            this.pAnswer = pAnswer
            this.pKey = pKey
        },
        closeModal() {
            this.mod = false
            this.pTitle = ""
            this.pSolution = ""
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