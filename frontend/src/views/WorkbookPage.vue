<template>
    <v-container class="workbook">
        <v-row style="height: 100%">
            <v-col cols="12">
                <v-container pa-0 fluid grid-list-md>
                    <v-layout align wrap>
                        <v-flex v-for="i in 15" :key="`4${i}`" xs12 md4 sm6>
                            <v-card dark color="primary" class="card-area">
                                <v-card-title>오버로딩과 오버라이딩의 차이에 대해 설명하시오.</v-card-title>
                                <v-card-actions>
                                    <v-btn text>DELETE</v-btn>
                                    <v-btn text>UPDATE</v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-flex>
                    </v-layout>
                </v-container>
            </v-col>
        
        </v-row>

    </v-container>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.card-area{
    margin: 7px;
}

.workbook{

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