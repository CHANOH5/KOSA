<template lang="">
    <h1>AppLifecycle4.vue 컴포넌트 입니다</h1>
    <Lifecycle/>
</template>

<script>
// checkbox 만든것 import해오기
import Lifecycle from "./components/Lifecycle.vue";

export default {
    name: 'App1',
    // c 컴포넌트를 사용하겠다라는 components를 사용해주어야 함 
    components: { Lifecycle },
    data() {
        return {
        }
    },
    methods:{

    },
    // Lifecycle에 관련된 메서드

    // DOM트리 완성전 DOM접근 불가
    beforeCreate() {
        console.log("beforeCreate");
    },
    // DOM트리 완성 후
    created() {
        console.log("created");
    },
    // template태그 실행 후에 호출됨 ( 렌더링 전 )
    beforeMount() {
        console.log("beforeMount");
    },
    // 랜더링 후 호출됨
    mounted() {
        console.log("mounted");
    },
    // 데이터가 ( data옵션, props옵션 )변경되고 렌더링 전
    beforeUpdate() {
        console.log("beforeUpdate");
    },
    // 데이터 변경되고 렌더링 후
    updated() {
        console.log("updated");
        // 함수 내부에서 data를 변경하면 무한루프에 빠질 수 있음
    },
    beforeUnmount() {
        console.log("beforeUnmount");
    },
    unmounted() {
        console.log("unmounted");
    },
}
</script>

<style lang="">
    
</style>