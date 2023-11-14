<template lang="">
    생명주기용 메서드 호출
</template>
<script>
import axios from 'axios';
export default {
    name:'Lifecycle',
    data() {
    },
    methods: {

    },
    // Lifecycle에 관련된 메서드
    // DOM트리 완성전 DOM접근 불가
    beforeCreate() {
        console.log("하위 컴포넌트 beforeCreate");
    },
    // DOM트리 완성 후
    created() {
        console.log("하위 컴포넌트 created");
        const url = "http://192.168.1.22:8888/back2/productlistjson?currentPage=1"
        axios.get(url).then((response)=>{
            console.log(response.data);
        })
    },
    // template태그 실행 후에 호출됨 ( 렌더링 전 )
    beforeMount() {
        console.log("하위 컴포넌트 beforeMount");
    },
    // 랜더링 후 호출됨
    mounted() {
        console.log("하위 컴포넌트 mounted");
    },
    // 데이터가 ( data옵션, props옵션 )변경되고 렌더링 전
    beforeUpdate() {
        console.log("하위 컴포넌트 beforeUpdate");
    },
    // 데이터 변경되고 렌더링 후
    updated() {
        console.log("하위 컴포넌트 updated");
        // 함수 내부에서 data를 변경하면 무한루프에 빠질 수 있음
    },
    beforeUnmount() {
        console.log("하위 컴포넌트 beforeUnmount");
    },
    unmounted() {
        console.log("하위 컴포넌트 unmounted");
    },

    props:["p"]
}
</script>
<style lang="">
    
</style>