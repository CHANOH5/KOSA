$(() => {
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        url: `http://192.168.1.22:8888/back/cartlist`,//'http://192.168.1.84:8888/back/cartlist',
        method: 'get',
        // data:,
        success: (responseJSONObj) => {
            if(responseJSONObj.msg != undefined){
                alert('장바구니 없습니다')
                return
            }
            // const p = responseJSONObj[0].product        // const q = responseJSONObj[0].quantity
            // console.log(p, q)
            const $originTrObj = $('div.cartlist>table>thead>tr') //원본
            const $tbodyObj = $('div.cartlist>table>tbody') //tbody객체
            let totalPrice = 0; //총가격
            responseJSONObj.forEach(element => {
                const $copyTrObj = $originTrObj.clone() //복제본
                $copyTrObj.empty()
                const p = element.product //상품
                const q = element.quantity //수량
                
                // const tds =  '<td>'+p.prodNo+'</td><td>'+p.prodNo+'</td><td>'+p.prodNo+'</td><td>'+p.prodNo+'</td>'
                // $copyTrObj.html(tds)
                // $tbodyObj.append($copyTrObj)  
                const $prodNoTdObj = $('<td>') //td객체생성 $('td')는 객체찾기 $('<td>')는 객체생성
                $prodNoTdObj.addClass('prodNo')
                $prodNoTdObj.append(p.prodNo) //td객체의 하위노드추가
                $copyTrObj.append($prodNoTdObj)  //복제본에 td객체를 추가

                const $prodNameTdObj = $('<td>')
                $prodNameTdObj.addClass('prodName')
                $prodNameTdObj.append(p.prodName)                
                $copyTrObj.append($prodNameTdObj)  

                const $prodPriceTdObj = $('<td>')
                $prodPriceTdObj.addClass('prodPrice')
                $prodPriceTdObj.append(p.prodPrice)
                $copyTrObj.append($prodPriceTdObj) 
                totalPrice += p.prodPrice

                const $quantityTdObj = $('<td>')
                $quantityTdObj.addClass('quantity')
                $quantityTdObj.append(q)
                $copyTrObj.append($quantityTdObj)  
                
                $tbodyObj.append($copyTrObj) //tbody객체에 복제본객체 추가
            });//end for
            const $copyTrObj = $originTrObj.clone() //복제본
            $copyTrObj.empty()
            const $totalTdObj = $('<td>')
            // $totalTdObj.attr('colspan', 4)
            // $totalTdObj.css('text-align', 'right')
            // $totalTdObj.attr('colspan', 4).css('text-align', 'right').css('font-weight', 'bold')
            $totalTdObj.attr('colspan', 4).css({"text-align":"right", 'font-weight' : 'bold'})
            $totalTdObj.append('총 상품목록 : '+ responseJSONObj.length + "개, 총 가격:" + totalPrice +"원") 
            $copyTrObj.append($totalTdObj)
            $tbodyObj.append($copyTrObj) //tbody객체에 복제본객체 추가

        }
    })
     //----주문하기 버튼객체에서 클릭이벤트가 발생했을 때 할 일 START----
     $('div.cartlist>button').click(()=>{
        $.ajax({
            xhrFields: {
                withCredentials: true
            },
            url: 'http://192.168.1.84:8888/back/addorder',
            method : 'get',
            success: (responseJSONObj)=>{
                if(responseJSONObj.status == 0){ //주문실패
                    alert(responseJSONObj.msg)
                }else{
                    $('nav>ul>li>a.orderlist').click()
                }
            }
        })
    })
    //----주문하기 버튼객체에서 클릭이벤트가 발생했을 때 할 일 END----
})