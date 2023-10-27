const url = `http://localhost:8889/board`

$(() =>{
    $.ajax({
        xhrFields: {
            withCredentials: true
        },
        url: url+'/list',
        method: 'get',
        success: (data) => {
            console.log(data);
            
            const originTrObj = $('div.boardlist>table>thead>tr') //원본
            const tbodyObj = $('div.boardlist>table>tbody') //tbody객체
            
            data.forEach(element => {
                const copyTrObj = originTrObj.clone(); // 복제본 테이블 행
                copyTrObj.empty();

                const boardNo = element.boardNo;
                const boardTitle = element.boardTitle;
                const boardId = element.boardId;

                const boardNoTdObj = $('<td>'); // td 객체 생성
                boardNoTdObj.addClass('boardNo');
                boardNoTdObj.text(boardNo); // 텍스트 설정
                copyTrObj.append(boardNoTdObj); // 복제본에 td 객체 추가

                const boardTitleTdObj = $('<td>');
                boardTitleTdObj.addClass('boardTitle');
                boardTitleTdObj.text(boardTitle);
                copyTrObj.append(boardTitleTdObj);

                const boardIdTdObj = $('<td>');
                boardIdTdObj.addClass('boardId');
                boardIdTdObj.text(boardId);
                copyTrObj.append(boardIdTdObj);

                tbodyObj.append(copyTrObj); // tbody 객체에 복제본 행 추가
            }); // forEach
        }, //success
    }) // ajax

    //  작성 버튼 클릭시 할 일
    
    $('div.boardlist').on('click', 'div.write', (e) => {

        location.href = `./boardwrite.html`
    })
})