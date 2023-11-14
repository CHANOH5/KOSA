<template>
    <ul>
        <ProvideListItem v-for="p in products" :key="p.prodNo" :p="p" />
    </ul>
    <div>1500원보다 비싼 상품 수 : {{priceCount}}</div>
</template>  
<script>
    import ProvideListItem from './ProvideListItem7.vue'
    export default {
        name: "ProvideList",
        components: {
            ProvideListItem
        },
        props: ["products"],
        inject : ["priceCount"],
    }
</script>
 