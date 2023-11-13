<template lang="">
    <div>
        <h1>App3.vue 컴포넌트 입니다</h1>
        <!-- 부모가자식에게 데이터 전달 -> v-bind -->
        <!-- <InputNamePropsEmit3 v-bind:word="parentWord"/> -->
        <!-- <InputNamePropsEmit3 :word="parentWord"/> -->

        <!-- 양방향이면 부모가 데이터를 보내면 그 데이터를 다시 자식이 부모에게 전달함 -->
        <!-- v-bind는 단방향이기 때문에 여기서 쓸 수 없음 -->
        <InputNamePropsEmit3 v-model:word="parentWord"/>
        자식 컴포넌트가 보내준 데이터 {{parentWord}}
    </div>
</template>

<script>
// checkbox 만든것 import해오기
import InputNamePropsEmit3 from "./components/InputNamePropsEmit3.vue";

export default {
    name: 'App1',
    // c 컴포넌트를 사용하겠다라는 components를 사용해주어야 함 
    components: {InputNamePropsEmit3},
    data() {
        return {
            parentWord: 'hello' // 빈 문자열로 초기화
        }
    },
    methods:{

    },
}
</script>

<style lang="">
    
</style>