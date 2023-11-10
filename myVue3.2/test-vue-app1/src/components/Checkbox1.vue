<template lang="">
    <div>
        <!-- <li><input type="checkbox" v-bind:checked="chk"/>{{name}}</li> -->
        <li><input type="checkbox" v-bind:checked="p.checked"/>{{p.prodName}}</li>
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