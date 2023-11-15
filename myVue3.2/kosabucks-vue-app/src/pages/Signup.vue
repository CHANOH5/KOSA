<template>
    <form class="signup" @submit.prevent="signupFormSubmitHandler">
        <label for="i">아이디 : </label> 
        <input type="text" name="id" id="i" required
                 v-model="c.id" @focus="idFocusJHandler">
        <button type="button" @click="btDupchkClickHandler">중복확인</button>
        <br>

        <label for="p">비밀번호 :</label>
         <input type="password" name="pwd" id="p" required
                 v-model="c.pwd" ref="p1">
        <br>
        <label for="p1">비밀번호1 :</label>
         <input type="password" id="p1"
                required v-model="pwd1" ref="p1">
        <br>

        <label for="n">이름 :</label>
         <input type="text" name="name" id="n" required
          v-model="c.name">
        <br>

        <label for="f1">프로필 :</label> <input type="file" name="f1" id="f1">
        <div>
            <img class="profile">
        </div>
        <br>
        <label for="f2">자기소개서 :</label> <input type="file" name="f2" id="f2">

        <button type="submit" :class="[isBtSignup?'btSignupSHow':'btSignupHide']">가입하기</button>
    </form>
</template>
<script>

import axios from 'axios';

export default {
    name:'Signup',
    data() {
        return {
            // id: '',
            // pwd: '',
            pwd1: '',
            // name: '',
            isBtSignup: false, // 가입버튼 보여주기 여부
            c: {
                id:'',
                pwd:'',
                name:'',
            }
        }
    },
    methods: {

        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 START----
        idFocusJHandler() {
            this.isBtSignup = false
        },
        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 End----

        //---- 중복확인 버튼 이벤트 발생했을 때 할 일 START ----
        btDupchkClickHandler() {
            const url = `${this.backURL}/iddupchk?id=${this.c.id}`
            // const data = `id={$this.id}`
            axios.get(url)
            .then((response)=>{
                console.log('확인');
                console.log(response.data)
                if(response.data.status == 0) {
                    console.log('중복');
                } else if ( response.data.status == 1) {
                    console.log('성공');
                    this.isBtSignup = true
                }
            })
            .catch(error => {
                console.log('실패:', error)
            })
        },
        //---- 중복확인 버튼 이벤트 발생했을 때 할 일 END ----   
        //----폼객체에서 submit이벤트 발생했을때 할 일 START----
        signupFormSubmitHandler() {
            if(this.c.pwd != this.pwd1) {
                alert('비밀번호를 다시 입력하세요')
                const pwdObj = this.$refs.p // 비번\
                pwdObj.focus()
                pwdObj.select()
            } else {
                const url = `${this.backURL}/signup`
                const data = this.c

                axios.post(url, data)
                .then(response=>{
                    alert("결과:" + response.data.msg)
                    if(response.data.status == 1) {
                        location.href = '/'
                    }
                })
                .catch(error=>{
                    alert(error.message)
                })
            }
        }


        //----폼객체에서 submit이벤트 발생했을때 할 일 END----
    },
}
</script>
<style scoped>
    /*공통 속성*/
form {
    border: 2px solid;
    width: 500px;
    height: 400px;
    padding: 15px;
    background-color:beige;
}
input{
    height: 20px;
    border-radius: 5px;
    margin: 10px;
}
#button{
    height: 25px;
}

tr>td{
    text-align: right;
}
/* #signup_bt {
    display: none;
} */
.btSignupShow {
    display:block;
}
.btSignupHide {
    display:none;
}
</style>