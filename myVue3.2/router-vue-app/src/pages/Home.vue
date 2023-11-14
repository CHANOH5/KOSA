<template lang="">
    HOME
    <p>전체경로: {{ $route.fullpath}} </p>
    <p>URI경로 : {{ $route.path }}</p>
    <p>요청 전달 데이터: {{ $route.params }}</p>
    <p>요청 쿼리스트링 : {{ $route.query }}</p>
</template>
<script>
export default {
    name: "Home"
}
</script>
<style lang="">
    
</style>