<template lang="">
    <div>
        <h1>App1.vue 컴포넌트 입니다</h1>
        <!-- <Checkbox1/>
        <Checkbox1/>
        <Checkbox1/> -->
            
        <!-- 부모 컴포넌트의 값을 자식 컴포턴트에게 전달하려면 props옵션을 자식쪽에 설정해야함 -->
        <ul>
           <!-- <Checkbox1 v-for="p in products" :chk="p.checked" v-bind:name="p.prodName"/>  -->
           <Checkbox1 v-for="p in products" v-bind:p="p"/> 
        </ul>

    </div>
</template>

<script>
// checkbox 만든것 import해오기
import Checkbox1 from "./components/Checkbox1.vue";

export default {
    name: 'App1',
    data() {
        return {
            products: [
                { prodNo: 'C0001', prodName: '아메리카노', checked: false },
                { prodNo: 'C0002', prodName: '아아', checked: true },
                { prodNo: 'C0003', prodName: '라테', checked: false }
            ]
        }
    },

    // c 컴포넌트를 사용하겠다라는 components를 사용해주어야 함 
    components: { Checkbox1 }
}
</script>

<style lang="">
    
</style>