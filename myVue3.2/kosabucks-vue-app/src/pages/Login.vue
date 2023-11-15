<template>

<!-- 기본이벤트처리 막기위해 prevent, submit이벤트가 발생한 이후에는 기본 이벤트처리 하려고 하기 때문 -->
<form @submit.prevent="loginFormSubmitHandler">

    <label for="i">아이디</label>
    <input type="text" name="id" id="i" v-model="id"><br>

    <label for="p">비밀번호</label>
    <input type="password" name="pwd" id="p" v-model="pwd"><br>
    <input type="checkbox" v-model="checked"> 아이디저장

    <button>로그인</button>
    <!-- @click.prevent="login" -->
</form>

</template>
<script>
import axios from 'axios';
export default {
    name: 'Login',
    data() {
        return {
            id : '',
            pwd: '',
            checked: true,
        }
    },
    methods: {
        loginFormSubmitHandler() {

            if(this.checked){
                localStorage.setItem('savedId', this.id)
            }else{
                localStorage.removeItem('savedId')
            }

            console.log('form클릭')

            // 로그인 Back 요청 - axios라이브러리 이용
            const url = `${this.backURL}/login`
            const data = `id=${this.id}&pwd=${this.pwd}`

            axios.post(url, data, {withCredentials: true})
            .then((responseJSONObj)=>{
                console.log(data);
                    if(responseJSONObj.data.status == 0) {
                        alert(responseJSONObj.data.msg) 
                
                    } else if(responseJSONObj.data.status == 1) {
                        console.log('성공')
                        // // Controller에서 1을 받아와서 로그인 성공인 경우
                        localStorage.setItem("loginedId", this.id)
                        location.href= '/'
                    }
                }
            )
            .catch(error => {
                console.log('실패:', error)
            })
        }
        // 로그인
    },
    created() {

        const savedId = localStorage.getItem('savedId')

        if(savedId != null){
            this.id = savedId
        }

    },

}

</script>
<style>
    
</style>