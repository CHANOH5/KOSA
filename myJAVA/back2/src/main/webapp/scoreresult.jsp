<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
선택한 별점은 <%= request.getParameter("star") %>점
총점은 <%=request.getAttribute("totalScore") %>점
참여인원은 <%=request.getAttribute("totalCnt") %>명
평점은 <%=request.getAttribute("avgScore") %>점

</body>
</html>