<template lang="">
    <div>
        <h1>App2.vue 컴포넌트 입니다</h1>

        <!-- <InputNameEmit2 v-on:nameChnaged=nameChnagedHandler"  /> -->
        <InputNameEmit2 @nameChanged="nameChnagedHandler"/>

        <hr>
        자식 컴포넌트가 전달해준 데이터 : {{parentName}}
        
    </div>
</template>

<script>
// checkbox 만든것 import해오기
import InputNameEmit2 from "./components/InputNameEmit2.vue";

export default {
    name: 'App1',
    // c 컴포넌트를 사용하겠다라는 components를 사용해주어야 함 
    components: {InputNameEmit2},
    data() {
        return {
            parentName: '' // 빈 문자열로 초기화
        }
    },
    methods:{
        nameChnagedHandler(e) {
            this.parentName = e.name
        }
    },
}
</script>

<style lang="">
    
</style>