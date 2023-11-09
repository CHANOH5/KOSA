window.addEventListener('load', function(){

    const model = {message: 'HELLO'}
    // 객체 찾기
    const demo1 = document.querySelector("#demo1")
    const demo2 = document.querySelector("#demo2")
    const inputObj = document.querySelector('input')

    // proxy객체 사용(mdoel을 첫번째인자값, 두번째인자{get, set})
    // 첫번째 인자로 사용된 model이 target에 대입됨
    const data = new Proxy(model, {
        get: function(target, key){
            alert('get')
            if(!target[key]) throw new Error(`존재하지 않는 속성${key}입니다`)
            return target[key]
        },
        set : function(target, key, value){
            // alert('set')
            // target[key] -> dynamic(동적)key를사용 [] 
            // .이 아닌 대괄호 사용
            // target객체의 key값에 value를 대입
            target[key] = value  
            demo1.innerHTML = value
            demo2.innerHTML += '<br>' +value
        }
    })

    // data객체의 실제 객체는 model이므로 model객체의 대리자인 proxy객체의 get메서드를 호출
    demo1.innerHTML = data.message //Proxy의 get함수가 자동호출됨
    demo2.innerHTML = data.message 

    // input영역에서 key가 떼어졌을때 (keyup) 처리 될 이벤트
    inputObj.addEventListener('keyup', function(e){         
        // input영역의 문자열 값을 data.message에 대입 -> set함수를 호출        
        data.message =  e.target.value      //Proxy의 set함수가 자동호출됨  
    })

})