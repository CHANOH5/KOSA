<template lang="">
    <div>
        <!-- <li><input type="checkbox" v-bind:checked="chk"/>{{name}}</li> -->
        <li><input type="checkbox" v-bind:checked="p.checked"/>{{p.prodName}}</li>
        <!-- 객체 자료형이 전달될 때에는 양방향으로 전달 가능하다 -->
        <!-- 메모리 주소를 바꾸려는 시도가 아니라 객체 값을 변경하려는 시도이기 때문 -->
        <!-- 하지만 이렇게 하지마라!! -->
        <!-- <input type="text" v-model="p.prodName"> -->
    </div>
</template>
<script>
export default {
    name:'Checkbox1',
    data() {
        checked : false
    },
    // 부모쪽으로 데이터(값)을 전달하기 위함
    // props:["chk", "name"]
    props:["p"]
}
</script>
<style lang="">
    
</style>