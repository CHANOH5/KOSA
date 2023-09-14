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
        const data = `id=${idValue}&pwd=${pwdValue}`
        alert(data);

        alert("ajax-4")
        $.ajax({
            url : 'http://localhost:8888/back/login',
            method : 'post',
            success : (responseText)=>{ // 요청이 완료되고 응답이 성공적으로 됐을때 할 일
                alert(responseText)
            },
            error : (jqXHR, textStatus) => {
                alert(jqXHR.readyState)

            }
        })

        alert("ajax-5")
        e.preventDefault()
    })
    //----form객체에서 submit이벤트가 발생했을 때 할 일 END----
})