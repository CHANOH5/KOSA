<template>
<div class="header">
<h1 class="headerText">동적컴포넌트</h1>
<nav>
<ul class="nav nav-tabs nav-fill">
    <li v-for="tab in tabs" :key="tab.id" class="nav-item">
    <a style="cursor:pointer;" class="nav-link" 
        :class="{ active : tab.id === currentTab }"
        @click="changeTab(tab.id)">{{tab.label}}</a> 
        <!-- a태그가 클릭 되었을 때, chnageTab함수 호출,
           현재 사용되는 tab의 아이디 값을가지고 currentTab값을 바꿈 -->
    </li>
</ul>
</nav>
</div>
<div class="container">
<!-- is특성에 바인딩하는 값은 등록한 컴포넌트의 이름입니다.
<keep-alive>
  동적컴포넌트는 기존컴포넌트는 마운트해제하고 클릭된 컴포넌트를 매번 새롭게 마운트됩니다. 
  마운트해제된 컴포넌트가 선택되면 컴포넌트인스턴스가 새로 만들어집니다.
  만일 동적 자식컴포넌트가 갖고있는 값을 유지하거나 정적 컨텐트라면 매번 인스턴스를 만드는것은 효율적이지 않습니다.    
  이런 경우 <keep-alive>요소로 감싸서 캐싱하여 남겨두면 마운트해제가 되지 않습니다.    
  만일 특정 컴포넌트만 캐싱하고 싶으면 include특성으로 컴포넌트들의 이름(name옵션)을 콤마로 구분합니다.
  include에 포함되지 않는 컴포넌트들은 캐싱되지 않아 매번 마운트 해제됩니다.
  라이프사이클용 메서드(created, mounted, unmounted)활용해서 마운트해제여부를 확인해보세요
-->        
<!--1. 동적컴포넌트 캐싱없이 사용해보기 -->
<!-- <component :is="currentTab"></component>    -->

 <!--2. 모든 동적컴포넌트  캐싱하기-->
 <!-- <keep-alive > 
    <component :is="currentTab"></component>        
</keep-alive> -->

<!-- 3. 특정 동적컴포넌트만 캐싱하기 -->
<keep-alive :include="['Tab1', 'Tab2', ]">
    <component :is="currentTab"></component>        
</keep-alive>
</div>

</template>
  <script>
  import Tab1 from './components/DynamicComponent6_1.vue'
  import Tab2 from './components/DynamicComponent6_2.vue'
  import Tab3 from './components/DynamicComponent6_3.vue'
  
  export default {
    name: 'App',
    components : { Tab1, Tab2, Tab3 },
    data() {
      return { 

        currentTab : 'Tab1', // 기본탭
        tabs : [ 
          { id:"Tab1", label:"탭1" }, 
          { id:"Tab2", label:"탭2" }, 
          { id:"Tab3", label:"탭3" }
        ] 
      }

    },
    methods : {
      changeTab(tab) {
        // 현재 currentTab으로 클릭된 tab을 변경
        this.currentTab = tab;
      },

    }
  }
  </script>
  <!-- <style>
  .header { padding: 20px 0px 0px 20px; }
  .headerText { padding: 0px 20px 40px 20px; } 
  .tab { padding: 30px }
  </style> -->