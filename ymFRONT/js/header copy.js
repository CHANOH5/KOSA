const backURL = 'http://192.168.1.84:8888/back'
const frontURL = 'http://192.168.1.84:5500/html'
function ajaxHandler(method, u, target) {
    console.log(u)
    // $.ajax({
    //     url:u,
    //     method: method,
    //     success: (responseText)=>{
    //         target.html(responseText) 
    //     },
    //     error: ()=>{
    //         alert('응답실패')
    //     }
    // })
    if(method == 'GET'){
        target.load(u,  function( response, status, xhr ) {
            if ( status == "error" ) {
                alert(xhr.status + xhr.statusText)
            }
        })
    }

}

$(() => {
    const loginedId = localStorage.getItem("loginedId") 
    if(loginedId == null){ //로그인 안된 경우
        //로그인, 가입메뉴 보여주기/ 로그아웃메뉴 안보여주기
        $('nav>ul>li>a.login').parent().show()
        $('nav>ul>li>a.signup').parent().show()
        $('nav>ul>li>a.logout').parent().hide()
        
    }else{ //로그인 된 경우
        //로그아웃메뉴 보여주기/ 로그인, 가입메뉴안보여주기
        $('nav>ul>li>a.login').parent().hide()
        $('nav>ul>li>a.signup').parent().hide()
        $('nav>ul>li>a.logout').parent().show()
    }
    //DOM트리에서 section객체찾기
    const $sectionObj = $('section')
    //DOM트리에서 nav>ul>li>a객체들 찾기
    const $menus = $('nav>ul>li>a')

    //----메뉴객체에서 클릭이벤트가 발생했을 때 할 일 START----      
    $menus.click((e) => {
        // alert('메뉴클릭됨')
        console.log(e.target.className)
        //menu
        switch (e.target.className) {
            case 'login':
                // location.href = 'http://www.naver.com'
                ajaxHandler('GET', "./login.html", $sectionObj)
                break
            case 'signup':
                ajaxHandler('GET', "./signup.html", $sectionObj)
                break
            case 'logout': 
                $.ajax({
                    xhrFields: {
                        withCredentials: true
                    },
                    url: 'http://192.168.1.84:8888/back/logout',
                    success: ()=>{
                        localStorage.removeItem('loginedId')
                        location.href='./main.html'
                    }
                })
                break
            case 'productlist':
                ajaxHandler('GET', "./productlist.html", $sectionObj)
                break
            case 'cartlist': 
                ajaxHandler('GET', "./cartlist.html", $sectionObj)
                break            
                
            case 'orderlist': 
                ajaxHandler('GET', "./orderlist.html", $sectionObj)
                break
        }
        e.preventDefault()
    })
    //----메뉴객체에서 클릭이벤트가 발생했을 때 할 일 END----

    //--로고img객체에서 클릭이벤트가 발생했을 때 할 일 START----
    //main.html로 URL이동
    $('header>img').click(()=>{
        location.href='./main.html'
    })
    //--로고img객체에서 클릭이벤트가 발생했을 때 할 일 END----
})