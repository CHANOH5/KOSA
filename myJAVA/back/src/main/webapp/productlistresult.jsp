<%@page import="com.my.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>productlistresult.jsp</title>
		
		    <style>
		        body{
		            /* 백그라운드 컬러 설정 */
		            background-color:bisque;
		            /* 백그라운드 컬러를 수평으로 반복하겠다? */
		            background-repeat: repeat-x;
		        }
		        div {
		            display: inline-block;
		        }
		        div.product{
		            width: 200px;
		        }
		        div.product>ul{
		            /* product명을 가진 div의 하위 ul 태그들 */
		            /* ul요소에 동그라미점 삭제 */
		            list-style-type: none;
		            padding-left: 0px;
		        }
		        div.product>ul>li>img{
		            /* 이미지 태그 크기 조절 */
		            width: 90%;
		        }
		        div.product>ul>li>span{
		            width: 90%;
		            display:inline-block;
		            text-align: center;
		            color: white;
		            background-color: seagreen;
		        }
		        .productlist>h3 {
		            width: 200px;
		            margin: 10px auto;
		            text-align: center;
		            background-color: yellow;
		        }
		    </style>
	</head>
		<body>
		<div class="productlist">
			<h3>상품목록</h3>
			<% List<Product> list = (List)request.getAttribute("list"); %>
			<% for(Product p : list) {
			%>
			<div class="product">
				<ul>
					<li><img src="./images/<%=p.getProdNo()%>.jpg"></li>
					<li><%= p.getProdName() %></li>
				</ul>
			</div>
			<% }
			%>
		</div>
	
	</body>
</html>