<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>    

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
/* main */
section{
    height: 700px;
    background-color:wheat;
    margin-top: 10px;
}
</style>

<script>

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

    // DOM트리에서 section객체 찾기
    const sectionObj = document.querySelector('section')
    const $sectionObj = $('section')

    console.log("--- 자바스크립트 sectionObj 객체 ---");
    console.log(sectionObj)
    console.log("--- JQuery sectionObj 객체 ---");
    console.log($sectionObj);
    console.log(sectionObj === $sectionObj);
    console.log(sectionObj === $sectionObj.get(0));

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
                break;
            case 'productlist':

                // ajaxHandler('GET', "./productlist.html", $sectionObj)
				ajaxHandler('GET', "./productlist", $sectionObj)
                break;
            case 'cartlist':
                break;
            case 'orderlist':
                break;
            default:
                break;
        }
        e.preventDefault();
    })

    // ----- 메뉴객체에서 클릭이벤트가 발생했을 때 할 일 END -----

}) // window.addEventListener, $(()=>
</script>

<header>
	<img src="./images/logo/png" alt="로고">
</header>
    <nav id="nav"> <!-- 메뉴들 -->
        <ul id="nav_ul">
            <li><a href="#" class="login">로그인</a></li>
            <li><a href="#" class="signup">가입</a></li>
            <li><a href="#" class="logout">로그아웃</a></li>
            <li><a href="#" class="productlist">상품목록</a></li>
            <li><a href="#" class="cartlist">장바구니목록</a></li>
            <li><a href="#" class="orderlist">주문목록</a></li>
        </ul>
    </nav>