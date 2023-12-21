<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>todoList</title>
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

/* TO DO 작성 + */
#writing {
	position: absolute;
	width: 126px;
	height: 28px;
	left: 132px;
	top: 149px;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 600;
	font-size: 20px;
	line-height: 24px;
	color: #FFFFFF;
}

/*날짜 추가(글 박스)*/
#box {
	box-sizing: border-box;
	position: absolute;
	height: 41px;
	left: 23px;
	right: 22px;
	top: 250px; /* 수정된 부분 */
	background: #000000;
	border: 2px solid #FFFFFF;
	border-radius: 10px;
}

/* 2023년 9월 25일 월요일 - 박스안 글씨*/
#boxtitle {
	position: absolute;
	width: 100%;
	height: 100%;
	/* width: 215px;
    height: 23px; */
	left: 0;
	top: 0;
	/* left: 88px;
    top: 204px; */
	font-family: 'Inter';
	font-style: normal;
	font-weight: 400;
	font-size: 18px;
	line-height: 41px;
	/* line-height: 24px; */
	color: #FFFFFF;
	text-align: center;
	display: flex; /* 수직 가운데 정렬을 위해 flex 사용 */
	justify-content: center; /* 수직 가운데 정렬을 위해 flex 사용 */
	align-items: center; /* 수직 가운데 정렬을 위해 flex 사용 */
}

#div1 {
	top: 170px;
}

.todobox {
	height: 600px;
	width: 350px;
	background: #D3D3D3;
	background: black;
	position: relative;
	overflow: auto;
}
/* 새로운 TO DO 항목 스타일 */
.todo {
	box-sizing: border-box;
	height: 41px;
	width: calc(100% - 50px);
	margin: 0 auto;
	background: #000000;
	border: 2px solid #FFFFFF;
	border-radius: 10px;
	margin-bottom: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: #FFFFFF;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 400;
	font-size: 18px;
	line-height: 41px;
	text-align: center;
}

.todo-item {
	box-sizing: border-box;
	height: 41px;
	width: calc(100% - 50px);
	margin: 0 auto;
	background: #000000;
	border: 2px solid #FFFFFF;
	border-radius: 10px;
	margin-bottom: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: #FFFFFF;
	font-family: 'Inter';
	font-style: normal;
	font-weight: 400;
	font-size: 18px;
	line-height: 41px;
	text-align: center;
	white-space: nowrap; /* 텍스트가 길 경우 줄바꿈 방지 */
	overflow: hidden; /* 넘치는 부분 감추기 */
	text-overflow: ellipsis; /* 넘치면 ...으로 표시 */
}

.submit-button {
	background: transparent;
	color: #ffffff;
	border: none;
	padding: 10px 20px; /* 원하는 패딩 값으로 조절할 수 있습니다. */
	font-size: 25px;
	font-weight: 600;
	cursor: pointer;
	border-radius: 5px;
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




		<!-- TODO 목록 표시 부분 수정 -->
		<div class="todobox">
			<ul>
				<c:forEach var="todo" items="${todoList}">
					<li class="todo-item" onclick="redirectToPost(${todo.todopostId}, ${todo.userId})"><fmt:formatDate
							value="${todo.createdAt}" pattern="yyyy년 MM월 dd일" /></li>
				</c:forEach>
			</ul>
		</div>


	</div>


	<!-- TODO 목록 정렬을 위한 스크립트 추가 -->
	<script>
	function redirectToPost(postId, userId) {
        try {
        	console.log('userId: ', userId);
        	console.log('todopostId: ', postId);
        	
        	window.location.href = '/Fitbuddy/todo/todolist/comm?userId=' + userId + '&postId=' + postId;
        } catch (error) {
            console.error('Error while redirecting to post:', error);
        }
    }
	
		// main페이지 이동
		function redirectMainPage() {
			window.location.href = "${pageContext.request.contextPath}/";
		}
	</script>
</body>
</html>
