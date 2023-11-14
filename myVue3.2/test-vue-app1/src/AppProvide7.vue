<template>
    <div>
      <h2>상품목록</h2>
      <ProdideList :products="products" />
    </div>
  </template>
  
  <script>
  import { computed } from 'vue';
  import ProdideList from './components/ProvideList7.vue'
  
  export default {
    name : "App",
    components : { ProdideList },
    data() {
      return {
          products : [{"prodNo":"C0001","prodName":"아메84","prodPrice":1100, "checked":true},
                      {"prodNo":"C0002","prodName":"아이스아메리카노","prodPrice":1000, "checked":false},
                      {"prodNo":"C0005","prodName":"카푸치노","prodPrice":1500, "checked":true}]
      }
    },
    provide() {
      return { 
        //:css에서 사용될 객체입니다
        // ⭐ 객체 형태로 제공
        icons : { 
          checked : "far fa-check-circle",
          unchecked : "far fa-circle",
        },
        //가격이 1500원 이상인 상품들의 개수를 반환하는 함수로써 computed옵션으로 작성합니다 
        // ⭐ 함수 형태로 제공 -> 함수형태는 반드시 computed옵션으로 작성해야한다 -> return값 필요
        priceCount : computed(()=> { 
          return this.products.filter((p)=>p.prodPrice>=1500).length
        })  
      }
    },
  }
  </script>
  <style>
  @import url("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css");
  </style>