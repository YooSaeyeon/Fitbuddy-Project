<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>todopost</title>
<style>
#container {
	width: 390px;
	min-height: 789px;
	margin: 0px auto;
	text-align: center;
	background: black; /* 검정색 배경 추가 */
	position: relative;
	overflow: auto;
	border: 1px solid black;
}

/* 로그아웃 */
#logout {
	position: absolute;
	width: 48px;
	height: 16px;
	left: 308.25px;
	top: 77px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 12px;
	line-height: 16px;
	color: #FFFFFF;
}

.todobox {
	top: 180px;
	height: 500px;
	width: 380px;
	background: black;
	position: relative;
	overflow: auto;
}

li.comment-item {
    margin-bottom: 15px; /* 각 댓글 사이의 간격을 조절할 수 있습니다. */
}

/* TO DO 작성 */
#writing {
	position: absolute;
	width: 230px;
	height: 28px;
	left: 50%;
	transform: translateX(-50%);
	top: 149px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 18px;
	line-height: 24px;
	color: #FFFFFF;
}

#todo {
	position: absolute;
	width: 230px;
	height: 28px;
	left: 50%;
	transform: translateX(-50%);
	top: 190px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 18px;
	line-height: 24px;
	color: #FFFFFF;
}

#todoboxplus {
	/* + */
	position: absolute;
	width: 14px;
	height: 20px;
	left: 338px;
	top: 236px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 20px;
	line-height: 24px;
	color: #FFFFFF;
	content: '+';
	z-index: 2;
	cursor: pointer;
	text-align: center; /* 텍스트 중앙 정렬 추가 */
	border: none; /* 외곽선 제거 */
	background: transparent; /* 배경 투명으로 설정 */
}

.todoboxplus1 {
	/* + */
	position: absolute;
	width: 14px;
	height: 20px;
	left: 338px;
	/* top 수정*/
	/*top: 285px;*/
	font-family : 'Inter'; font-style : normal; font-weight :
	600; font-size : 20px; line-height : 24px; color : #FFFFFF; content :
	'+'; z-index : 2; cursor : pointer; text-align : center;
	/* 텍스트 중앙 정렬 추가 */
	border: none; /* 외곽선 제거 */
	background: transparent;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 20px;
	line-height: 24px;
	color: #FFFFFF;
	content: '+';
	z-index: 2;
	cursor: pointer;
	text-align: center; /* 배경 투명으로 설정 */
}

#todofirst {
	/* Rectangle 103 */
	position: absolute;
	width: 25px;
	height: 25px;
	left: 25px;
	top: 240px;
	background: #D3D3D3;
	border-radius: 8px;
	cursor: pointer;
}

.todofirst1 {
	/* Rectangle 103 */
	position: absolute;
	width: 25px;
	height: 25px;
	left: 25px;
	/*top 수정*/
	/*top: 290px;*/
	background: #D3D3D3;
	border-radius: 8px;
	cursor: pointer;
}

#todosecond {
	/* Rectangle 102 */
	position: absolute;
	width: 273px;
	height: 25px;
	left: 65px;
	top: 240px;
	background: #FFFFFF;
	border-radius: 8px;
}

#todotext {
	border: 2px solid #FFFFFF; /* 테두리 스타일 추가 */
	border-radius: 8px; /* 둥근 테두리 추가 */
	outline: none;
	position: absolute;
	width: 230px; /* 너비 조절 */
	height: 23px; /* 높이 조절 */
	left: 71px;
	top: 238px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 13px;
	line-height: 16px;
	color: #000000;
	padding: 0 8px; /* 텍스트와 테두리 간격 조절 */
}

.todotext1 {
	border: 2px solid #FFFFFF; /* 테두리 스타일 추가 */
	border-radius: 8px; /* 둥근 테두리 추가 */
	outline: none;
	position: absolute;
	width: 230px; /* 너비 조절 */
	height: 23px; /* 높이 조절 */
	left: 71px;
	/* top 수정*/
	/*top: 287px;*/
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 13px;
	line-height: 16px;
	color: #000000;
	padding: 0 8px; /* 텍스트와 테두리 간격 조절 */
	background: #FFFFFF;
}

#todocheck {
	/* ✓ */
	position: absolute;
	width: 14px;
	height: 18px;
	left: 31px;
	top: 243px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 15px;
	line-height: 18px;
	color: #000000;
}

.todocheck1 {
	/* ✓ */
	position: absolute;
	width: 14px;
	height: 18px;
	left: 31px;
	/* top 수정*/
	/*top: 293px;*/
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 15px;
	line-height: 18px;
	color: #000000;
}

#logo {
	margin-top: -37px;
}

#save {
	position: absolute;
	width: 230px;
	height: 28px;
	left: 62%;
	transform: translateX(-50%);
	top: 700px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 18px;
	line-height: 24px;
	color: #FFFFFF;
}
</style>
</head>
<body>
	<div id="container">
		<div>
			<img class="logo"
				src="${pageContext.request.contextPath}/images/image1.png"
				onclick="redirectMainPage()" alt="로고">
		</div>
		<div id="logout">로그아웃</div>
		<!-- createdAt 불러오는 거 수정해야됨 -->
		<div id="writing" pattern="yyyy년 MM월 dd일">${sessionScope.createdAt}</div>
		
		<div id="todo">TO DO</div>


		<form action="<c:url value='/todo/todolist/comm/comm' />"
			method="post">
			<input type="hidden" name="userId" value="${loggedInUser.userId}" />
			<input type="hidden" name="todopostId" value="${todo.todopostId}" />

			<div id="todoList">
				<div id="todofirst" onclick="changeColor(this)"></div>
				<div id="todocheck">✓</div>
				<textarea type="text" name="content" id="todotext"></textarea>
				<button type="submit" id="todoboxplus">+</button>
			</div>

		</form>

		<!-- 댓글을 반복하여 표시 -->
		<div class="todobox">
			<ul>
				<c:forEach var="comment" items="${commentList}">
					<li class="comment-item" >
						<div class="todofirst1" onclick="changeColor(this)" ></div>
						<div class="todocheck1">✓</div>
						<div class="todotext1">${comment.content}</div> <!-- 다른 댓글 정보가 필요한 경우 표시 -->
						<button class="todoboxplus1">+</button>
					</li>
				</c:forEach>
			</ul>
		</div>


		<script>
			var currentColor = "#D3D3D3"; // 초기 색상
			function changeColor(element) {
				if (currentColor === "#D3D3D3") {
					currentColor = "#C2AC18"; // 색상 변경
				} else {
					currentColor = "#D3D3D3"; // 원래 색상으로 변경
				}
				element.style.background = currentColor;
			}

			// main페이지 이동
			function redirectMainPage() {
				window.location.href = "${pageContext.request.contextPath}/";
			}
		</script>
</body>
</html>
