<template>

    <header>
        <img src="../../public/images/logo.png" alt="로고" @click="logoClickHandler"/>
    </header>

    <nav id="nav"> <!-- 메뉴들 -->
        <ul id="nav_ul">
            <!-- 로그인이 안된 경우 -->
            <li v-if="loginedId === ''">
                <router-link class="nav-link" to="/login">로그인</router-link>
            </li>
            <li v-if="loginedId === ''">
                <router-link class="nav-link" to="/signup">가입</router-link>
            </li>
            <!-- 로그인이 된 경우 -->
            <li v-if="loginedId !== ''">
                <a href="#" @click="logoutClickHandler()">{{loginedId}}님 로그아웃</a>
            </li>
        </ul>
    </nav>
</template>
<script>
import axios from 'axios';
export default {
    name: 'Haeder',
    data() {
        return {
            loginedId: '' // 빈 문자열로 초기화
        }
    },
    created() {
        const loginedId = localStorage.getItem('loginedId')
        if(loginedId != null) {
            this.loginedId = loginedId
        }
    },
    methods: {

        // -- 로고 객채에서 클릭 이벤트가 발생했을 때 할 일 START --
        logoClickHandler() {
            location.href= '/'
        },
        // -- 로고 객채에서 클릭 이벤트가 발생했을 때 할 일 END -- 

        logoutClickHandler() {
            // const loginedId = localStorage.getItem('loginedId')

            // 로그인 Back 요청 - axios라이브러리 이용
            const url = `${this.backURL}/logout`

            axios.get(url, {withCredentials: true})
            .then(()=>{
                localStorage.removeItem('loginedId')
                location.href='/'
                }
            )
            .catch(error => {
                console.log('실패:', error)
            })
        },
    },


}
</script>
<style scoped>

/* 다른 css 파일 사용하기 */
/* @import url('base.css'); */

/* header */
nav{
    width: 100%;
    display: inline;
    text-align: center;
    left: 100px;
}
#nav_ul{
    width: 50%;
    display: inline-block;
    list-style-type: none; /* ul태그 점 삭제 */
    margin-left: 380px;
}
#nav_ul>li {
    float: left;
    margin: 10px;
    margin-left: 80px;
}
nav>ul>li>a:hover{ /* hover */
    background-color:coral;
    color: white;
    text-decoration: underline; /* 마우스 올라갔을 때 밑줄 */
}

</style>