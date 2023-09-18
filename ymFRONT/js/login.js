alert('in login start')

$(() =>{
    const savedId = $('savedId')

    if(savedId != null){
        // $('input[name=id]').val() = $savedId
        $('input[name=id]').val(savedId)
    }

    $('form').submit((e)=>{

        alert("ajax-0")

        if($('input[type=checkbox]').prop('checked')){
            localStorage.setItem('savedId', $('input[name=id]').val())
        }else{
            localStorage.removeItem('savedId')
        }
        alert("ajax-1")
        const idValue = $('input[name=id]').val()
        alert("ajax-2")
        const pwdValue = $('input[name=pwd]').val()

        alert("ajax-3")
        const idpwddata = `id=${gidValue}&pwd=${pwdValue}`
        alert(idpwddata);

        alert("ajax-4")
        $.ajax({
            xhrFields: {
                withCredentials: true
            },

            url : 'http://192.168.1.22:8888/back/login',
            method : 'post',
            data : idpwddata, 
            success : (responseJSONObj)=>{ // 요청이 완료되고 응답이 성공적으로 됐을때 할 일
                // success로 응답받은 내용이 로그인 성공일 수도 있고 실패일 수도 있는 것임
                // alert(responseText)
                if(responseJSONObj.status == 0) {
                    // Controller에서 0을 받아와서 로그인이 실패인 경우
                    alert(responseJSONObj.msg) // Controller에서 받은 msg를 alert창으로 띄움
                
                } else if(responseJSONObj.status == 1) {
                    // Controller에서 1을 받아와서 로그인 성공인 경우
                    localStorage.setItem("loginedId", idValue)
                    location.href = "./main.html"
                }
            },
            error : (jqXHR, textStatus) => {
                // error가 로그인이 실패가 아니라 요청 응답에 문제가 있다는 것
                alert(jqXHR.readyState + ":" + jqXHR.status + ":" + jqXHR.statusText)
            }
        })

        alert("ajax-5")
        e.preventDefault()
    })
    //----form객체에서 submit이벤트가 발생했을 때 할 일 END----
})