// window.addEventListener('load', ()=> {
    $(() => {
        //DOM트리에서 가입하기버튼객체찾기
        // const btSignupObj = document.querySelector('form.signup>button[type=submit]')
        const $btSignupObj = $('form.signup>button[type=submit]')
        //DOM트리에서 id입력란객체찾기
        // document.getElementById('i')
        // document.querySelector('#i')
        // const idObj = document.querySelector('form.signup>input[name=id]')
        const $idObj = $('form.signup>input[name=id]')
        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 START----
        // idObj.addEventListener('focus', ()=>{
        //     btSignupObj.style.display = 'none'
        // })
        $idObj.focus(() => {
            $btSignupObj.hide()
        })
        //----id입력란객체에서 focus이벤트 발생했을 때 할 일 END----
    
    
        //DOM트리에서 중복확인버튼객체찾기
        // const btDupchkObj = document.querySelector('form.signup>button[type=button]')
        const $btDupchkObj = $('form.signup>button[type=button]')
    
        //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 START----
        // btDupchkObj.addEventListener('click', ()=>{
        $btDupchkObj.click(() => {
            // if('admin' == idObj.value){
            if ('admin' == $idObj.val()) {
                alert('이미 사용중인 아이디입니다')
            } else {
                alert('사용가능한 아이디입니다')
                // btSignupObj.style.display = 'inline-block'
                $btSignupObj.show()
            } // if-else
        }) // .click()
        //----중복확인버튼객체에서 클릭이벤트 발생했을때 할 일 END----
    
    
        // const formObj = document.querySelector('form')
        const $formObj = $('form.signup')

    // ----- submit 버튼 클릭이벤트 발생했을 때 할 일 START -----
        formObj.addEventListener('submit', (e) => {

            const $pwdArr = document.querySelectorAll('input[type=password]') // 비밀번호 입력란 객체
            const $nameObj = $('form.signup>input[name=name]') // 이름 입력란 객체

            if($pwdArr.eq(0).val() != $pwdArr.eq(1).val()) {
                alert('비밀번호를 다시 입력하세요')
                // 비밀번호 다시 입력하세요 -> 경고창이 뜬 후 -> 비밀번호 입력칸으로 자동focus잡히게
                $pwdArr[0].focus()
                // 입력된 항목이 선택되도록하는 함수
                $pwdArr[0].select() 
            } else {
                $.ajax({
                    url:'http://192.168.1.22:8888/back/signup',
                    method : 'post',
                    data : //`id=${$idObj.val()}&pwd=${$pwdArr.eq(0).val()}&name=${$nameObj.val()}`,
                        // {
                        // id:$idObj.val(),
                        // pwd:$pwdArr.eq(0).val(),
                        // name:$nameObj.val()
                        // }
                        $('form').serialize(), // post방식일때만 serialize()로 데이터 보내기 가능
                    success : (responseJSONObj) => {

                    },
                    error: (jqxhr) => {
                        this.alert(jqxhr.status)
                    }
                })
            }
            
            // e.preventDefault()
            return false // 기본 이벤트 헨들러를 막는 역할과 같음
        }) // formObj.addEventListener
    // ----- submit 버튼 클릭이벤트 발생했을 때 할 일 END -----

    
    })