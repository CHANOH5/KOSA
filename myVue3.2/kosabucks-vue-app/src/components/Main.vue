<template>

    <div>

        <Header />
        <Section />
        <Footer />

    </div>

</template>
<script>

// 헤더, 섹션, 푸터는 하위 컴포넌트
import Header from "./Header.vue";
import Section from "./Section.vue";
import Footer from "./Footer.vue";

export default {
    name: "Main",
    components:{Header, Section, Footer}
}

</script>

<style>
/* 공통속성 */
body{
    width: 100%;
}
a{
    text-decoration-line: none;
}
img{
    float: left;
}

</style>
