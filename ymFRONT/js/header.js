
function ajaxHandler(method, u, target) {
    console.log(u)
    // JQuery에서 XMLHttpRequest()를 처리하는 방법
    // $.ajax({
    //     url: u,// property이름 : 매개변수
    //     method: method,
    //     success: (responseText)=>{ // 요청이 완료되고 응답이 성공적으로 됐을때 할 일
    //         target.html(responseText)
    //     },
    //     error: () => {
    //         alert('응답실패')
    //     }
    // }) // $.ajax

    // $.ajax메서드와 똑같은 .load메서드 사용방식

    if (method == 'GET') {
        target.load(u, function (response, status, xhr){
            if (status == "error") {
                alert(xhr.status + xhr.statusText)
            } // inner-if
        }) 
    } //if

} // ajaxHandler

// window.addEventListener('load', () => {
// $(document).ready()
$(() => {
    const loginedId = localStorage.getItem("loginedId")
    if (loginedId == null ){ // 로그인 안된 경우
        // 로그인, 가입메뉴 보여주기 / 로그아웃 메뉴 안보여주기
        $('nav>ul>li>a.login').show();
        $('nav>ul>li>a.signup').show();
        $('nav>ul>li>a.logout').hide();
    } else { // 로그인이 된 경우
        // 로그아웃 메뉴만 보여주기 
        $('nav>ul>li>a.login').hide();
        $('nav>ul>li>a.signup').hide();
        $('nav>ul>li>a.logout').show();
    }
    // DOM트리에서 section객체 찾기
    const sectionObj = document.querySelector('section')
    const $sectionObj = $('section')

    const menus = document.querySelectorAll('nav>ul>li>a')
    const $menus = $('nav>ul>li>a')

    // ----- 메뉴객체에서 클릭이벤트가 발생했을 때 할 일 START -----
    $menus.click((e) => {
        // alert('메뉴클릭됨')
        // console.log(e.target.class);
        // 화살표함수 내에서 this는 window객체 이므로 사용x
        switch (e.target.className) {
            case 'login':
                //location.href = "http://www.naver.com"
                // const xhttp = new XMLHttpRequest(); // 객체 생성
                // xhttp.addEventListener('load', function(){
                //     // 응답되었을 때 응답 내용을 section영역에 보여줌 -> section객체를 찾아놓자 미리
                //     sectionObj.innerHTML = this.responseText
                // })
                ajaxHandler('GET', "./login.html", $sectionObj)

                break;
            case 'signup':

                ajaxHandler('GET', "./signup.html", $sectionObj)

                break;
            case 'logout':
                $.ajax({
                    xhrFields: {
                        withCredentials: true
                    },
                    url : 'http://192.168.1.22:8888/back/logout',
                    success: () => {
                        localStorage.removeItem('loginedId')
                        location.href='./main.html'
                    }
                })
                break;
            case 'productlist':

                ajaxHandler('GET', "./productlist.html", $sectionObj)

                break;
            case 'cartlist':

                ajaxHandler('GET', './cartlist.html', $sectionObj)
                
                break;
            case 'orderlist':
                break;
            default:
                break;
        }
        e.preventDefault();
    })

// ----- 메뉴객체에서 클릭이벤트가 발생했을 때 할 일 END -----

// ----- 로고 img객체에서 클릭이벤트가 발생했을 때 할 일 START -----
const imgObj = document.querySelector('header>img')

$(imgObj).click(() => {
    // alert('클릭')
    location.href='./main.html'
})
// ----- 로고 img객체에서 클릭이벤트가 발생했을 때 할 일 END -----
}) // window.addEventListener, $(()=>