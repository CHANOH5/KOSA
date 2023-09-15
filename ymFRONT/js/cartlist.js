$(() => {
    $.ajax({
            // ----- 쿠키정보가 전달되도록 CORS 문제 해결 -----
        xhrFields: {
            withCredentials: true
        },
        url: 'http://192.168.1.22:8888/back/cartlist',
        method : 'get',
        // data : , 요청 전달데이터가 있으면 작성
        success:(responseJSONObj)=>{

            // const originCartlist = $('div.cartlist>table>tbody').first()
            const originCartlist = $('div.cartlist>table>thead>tr') // 원본
            const tbodyObj = $('div.cartlist>table>tbody') // tbody 객체

            
            console.log(responseJSONObj);
            
            responseJSONObj.forEach(element => {
                // JSON형태로 보내도 매개변수로 responseJSONObj 받으면
                // 자동 자바스크립트객체 형태로 응답을 받음 (배열로 이해)
                const $copyCartlist = originCartlist.clone()
                
                // const p = responseJSONObj[0].product // 상품
                // const q = responseJSONObj[0].quantity // 수량
                const p = element.product // 상품
                const q = element.quantity // 수량
                console.log(p, q);

                // let prodNo = p.prodNo
                // // let prodName = p.prodName
                // // let prodPrice = p.prodPrice
                // let quantity = q

                // console.log(prodNo, prodName, prodPrice, quantity);

                // $copyCartlist.find('tr>th.prodNo').html(prodNo)
                // $copyCartlist.find('tr>th.prodName').html(prodName)
                // $copyCartlist.find('tr>th.prodPrice').html(prodPrice)
                // $copyCartlist.find('tr>th.quantity').html(quantity)

                const $prodNoTdObj = $('.prodNo')
                $prodNoTdObj.addClass('prodNo')
                $prodNoTdObj.append(p.prodNo)
                $copyCartlist.append($copyCartlist)

                const $prodNameTdObj = $('.prodName')
                $prodNoTdObj.addClass('prodName')
                $prodNoTdObj.append(p.prodName)
                $copyCartlist.append($prodNameTdObj)

                const $prodPriceTdObj = $('.prodPrice')
                $prodNoTdObj.addClass('prodPrice')
                $prodNoTdObj.append(p.prodPrice)
                $copyCartlist.append($prodPriceTdObj)

                const $prodQuantityTdObj = $('.quantity')
                $prodNoTdObj.addClass('quantity')
                $prodNoTdObj.append(q)
                $copyCartlist.append($prodQuantityTdObj)

            });
        }
    }) // $.ajax
})