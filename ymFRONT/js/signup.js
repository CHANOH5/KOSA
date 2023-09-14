window.addEventListener(
    'load', function(){

        // const btObj = document.querySelector('table>tr>td>input[type=button]')
        const btObj = document.querySelector('#id_check')
        const idObj = document.querySelector('input[name=id]')
        const signObj = document.querySelector('input[type=submit]')

    // ----- id입력란 객체에서 focus 이벤트 발생했을 때 할 일 START ----- 
        idObj.addEventListener('focus', () => {
            signObj.style.display = 'none'
        }) // idObj.addEventListener
    // ----- id입력란 객체에서 focus 이벤트 발생했을 때 할 일 END -----

    // ----- 중복확인버튼객체에서 클릭이벤트 발생했을 때 할 일 START -----
        btObj.addEventListener('click', () => {
            if(idObj.value == 'admin') {
                alert('이미 사용중인 아이디입니다.')
            } else {
                alert('사용 가능한 아이디 입니다.')
                signObj.style.display = 'inline-block'
            } // if-else
        }) // btObj.addEventListener
    // ----- 중복확인버튼객체에서 클릭이벤트 발생했을 때 할 일 END -----

        const formObj = document.querySelector('form')

    // ----- submit 버튼 클릭이벤트 발생했을 때 할 일 START -----
        formObj.addEventListener('submit', (e) => {

            const pwdArr = document.querySelectorAll('input[type=password]')

            if(pwdArr[0].value != pwdArr[1].value) {
                alert('비밀번호를 다시 입력하세요')
                // 비밀번호 다시 입력하세요 -> 경고창이 뜬 후 -> 비밀번호 입력칸으로 자동focus잡히게
                pwdArr[0].focus()
                // 입력된 항목이 선택되도록하는 함수
                pwdArr[0].select() 
                e.preventDefault()
            } //if
        }) // formObj.addEventListener
    // ----- submit 버튼 클릭이벤트 발생했을 때 할 일 END -----

    } // function
) // window