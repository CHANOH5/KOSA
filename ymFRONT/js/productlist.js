$(()=> {
    function ajaxHandler(cp) {

        $.ajax({
            url : 'http://192.168.1.22:8888/backspringconfiguration/productlistjson',
            method : 'get',
            data: `currentPage=${cp}`,
            success : (responseJSONObj) => {
                const totalCnt = responseJSONObj.totalCnt
                const list = responseJSONObj.list
                console.log("총상품수 : ", totalCnt)
                console.log("현재페이지의 상품목록 : ", list)
                //원복 product 객체 찾기
                const $originProduct = $('div.productlist>div.product').first()

                // $originProduct.parent().children().not($originProduct)
                $originProduct.siblings().remove()  // productlist영역 초기화
                $originProduct.show()

                // 첫번째 요소가 index, 두번째 요소가 product 
                $(list).each((index, p)=>{
                    // 복제본 product 객체 생성
                    let $copyProduct = $originProduct.clone()
                    // 이 객체를 원본인 .productlist의 자식으로 추가
                    $('div.productlist').append( $copyProduct)
                    let prodNo = p.prodNo // prodNo를 받아와서 src속성값으로 주기위함
                    let prodName = p.prodName // prodName을 받아와서 주기위함
                    $copyProduct.find("ul>li>img").attr('src', '../images/' + prodNo + '.jpg').attr("alt", prodName)
                    $copyProduct.find("ul>li>span").html(prodName).attr("alt", prodName)
                })

                // 원본객체 숨기기
                $originProduct.hide()

                //페이지 그룹
                const $divPageGroup = $('div.pagegroup')

                $divPageGroup.empty() // 페이지 그룹영역 초기화
                // 브모입장에서 하위 객체들을 지우려면 empty함수를 쓰고
                // 자신의 객체 자체를 제거하려면 remove함수를 쓰는것 -> 25라인
                const startPage = responseJSONObj.startPage // 시작페이지
                const endPage = responseJSONObj.endPage     // 끝페이지

                if(responseJSONObj.startPage>1) {
                    let page = `[<span class="pg${startPage-1}">PREV</span>&nbsp;&nbsp;&nbsp;`
                    $divPageGroup.html($divPageGroup.html() + page)
                }

                for(let i = startPage; i <= endPage; i++) {
                    let page = `[<span class="pg${i}">${i}</span>]&nbsp;&nbsp;&nbsp;`
                    $divPageGroup.html($divPageGroup.html() + page)
                } // for

                if(responseJSONObj.endPage != responseJSONObj.totalPage) {
                    let page =  `[<span class="pg${endPage+1}">NEXT</span>]`
                    $divPageGroup.html($divPageGroup.html() + page)
                }

            },
            error : () => {

            }
        })

    } // ajaxHandler

    ajaxHandler(1)

    // 위의 ajax가 먼저 실행되어 span태그가 생성될지 안될지 모르는게 비동기이니까 이렇게 하면 안댐
    // $('div.productlist>.pagegroup>span').click(()=> {
    //         alert("클릭됨")
    // })

    // span객체가 존재할지 안할지 모르니 현재 돔트리에 존재하는 곳 까지만가서 찾고
    // on함수를 사용해서 span태그가 생성된다면 실행되도록
        $('div.pagegroup').on('click', 'span', (e) => {
        // alert($(e.target).html() + ": " + $(e.target).attr('class') + "페이지가 클릭되었습니다")
        const pg = $(e.target).attr('class')
        const currentPage = pg.substr(2)
        ajaxHandler(currentPage)

        $('div.productlist').on('click', 'div.product', (e) => {
            // alert('클릭되었습니다')
            //클릭된 이미지 객체를 찾고, 이미지의 속성중에 src속성을 찾아가기
            // .이라는 위치값의 index -> 15 확인
            // alert($(e.target).attr("src").lastIndexOf("."))

            // 상품번호만 추출해오기
            const src = $(e.target).attr("src")
            const prodNo = src.substring(src.lastIndexOf('/')+1, src.lastIndexOf('.'))
            // alert(prodNo)
            location.href = `./product.html?prodno=${prodNo}`
        })
    })

})