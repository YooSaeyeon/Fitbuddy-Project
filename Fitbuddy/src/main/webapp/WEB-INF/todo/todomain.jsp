<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>todomain</title>
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

/* image 1 */
#logo {
	margin-top: -37px;
}

/* 로그아웃 */
#logout {
	margin-left: 280px;
	margin-top: -45px;
}

#div1 {
	top: 170px;
}

.submit-button {
	position: absolute;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: transparent;
	color: #ffffff;
	border: none;
	padding: 10px 20px;
	font-size: 40px;
	font-weight: 600;
	cursor: pointer;
	border-radius: 5px;
}

.list-button {
	position: absolute;
	top: 60%;
	background: transparent;
	border: none;
	color: #ffffff;
	font-size: 40px;
	font-weight: 600;
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


		<div>
			<!-- TO DO 작성+ 버튼 -->
			<form action="/Fitbuddy/todo" method="post">
				<input type="hidden" name="userId" value="${loggedInUser.userId}" />
				<input type="hidden" name="todopostId" value="${todopostId}" /> <input
					type="hidden" name="createdAt" value="${created_at}" />
				<button type="submit" name="submit_button" value="submit_todo"
					class="submit-button" style="width: 300px;">TO DO 작성+</button>
			</form>
		</div>

		<div>
			<form action="/Fitbuddy/todo/todolist"  method="get">
			<input type="hidden" name="userId" value="${loggedInUser.userId}" />
				
				<button type="submit" class="list-button"
					style="width: 300px; margin-left: -145px; margin-top: -90px;">
					TO DO 조회</button>
			</form>
		</div>
	</div>


	<!-- TODO 목록 정렬을 위한 스크립트 추가 -->
	<script>
		document.addEventListener('DOMContentLoaded', function() {
			console.log("페이지가 정상적으로 로드되었습니다.");

			var userId = "${userId}";
			console.log("사용자 ID:", userId);
			// 필요한 경우 여기에 더 많은 디버깅 코드를 추가할 수 있습니다.

			// TODO: 사용자의 날짜 데이터 확인을 위한 디버깅 코드 추가
			var todoList = $
			{
				sessionScope.todoList
			}
			;
			console.log("전체 TODO 목록:", todoList);

			// 사용자 ID에 해당하는 TODO 목록 필터링
			var userTodoList = todoList.filter(function(todo) {
				return todo.userId === userId;
			});

			console.log("사용자의 TODO 목록:", userTodoList);
		});

		// 페이지 로딩 시 TODO 목록 정렬
		window.onload = function() {
			sortTodoList();
		};

		// main페이지 이동
		function redirectMainPage() {
			window.location.href = "${pageContext.request.contextPath}/";
		}
	</script>
</body>
</html>
