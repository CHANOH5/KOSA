<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%! 
		int totalScore; 
		int totalCnt = 0;
	%>
	<% String star = request.getParameter("star");%>
	
	선택한 별점은 <%= star %>점입니다.
	<hr>
	<%
	totalScore += Integer.parseInt(star);
	totalCnt++;
	%>
	<%! // int score[]; %>
	<%// 파라미터는 값의 타입이 반드시 String 타입이기 때문에 int값으로 못 받아옴 %>
	<hr>
	총점은 <%= totalScore %>점 입니다.
	참여인원은 <%=totalCnt %>명 입니다.
	<hr>
	평점은 <%= (float)totalScore/totalCnt %>점 입니다.
</body>
</html>