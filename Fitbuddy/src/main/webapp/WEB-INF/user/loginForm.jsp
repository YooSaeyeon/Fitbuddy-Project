<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인 폼</title>

<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
}

.container {
	width: 390px;
	height: 789px;
	position: relative;
	background: black;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.header {
	width: 100%;
	padding: 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.icons {
	width: 30%;
	display: flex;
	justify-content: space-between;
}

.icons img {
	width: 18px;
	height: 12px;
}

.icon-container {
	position: relative;
	width: 24px;
	height: 12px;
}

.logo {
	width: 145px;
	height: 81px;
	margin-top: 50px;
	margin-bottom: 80px;
}

.input-container {
	width: 312px;
	height: 52px;
	margin-top: 10px;
	border-radius: 6px;
	border: 1px #EDEDED solid;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	padding: 10px 16px;
}

.input-box {
	color: #787878;
	font-size: 16px;
	font-family: 'Abyssinica SIL', sans-serif;
	font-weight: 400;
	letter-spacing: 0.32px;
}

.input-container input {
	border: none;
	outline: none;
	background-color: transparent;
	color: #fff; /* 텍스트 색상 추가 */
	font-size: 16px;
	margin-top: 15px; /* 추가한 마진 */
}

/* 추가한 부분 */
.input-container input::placeholder {
	color: #787878; /* placeholder 텍스트 색상을 흰색으로 지정 */
	font-size: 16px;
}

.login-text {
	position: absolute;
	width: 76px;
	height: 30px;
	left: 161px;
	top: 200px;
	line-height: 30px;
	font-family: 'Inter';
	color: white; /* 텍스트 색상을 흰색으로 지정 */
	font-size: 25px; /* 원하는 글자 크기로 조절 */
	font-weight: 600; /* 글자 굵기를 조절 */
	margin-bottom: 10px; /* 추가한 마진 */
}

.login-button {
	width: 281px;
	height: 44px;
	background: #C2AC18;
	border-radius: 20px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: black;
	font-size: 20px;
	font-family: 'Inter', sans-serif;
	font-weight: 500;
	margin: 50px auto;
}

.signup-button {
	width: 281px;
	height: 44px;
	background: #007AFF;
	/* 회원가입 버튼 배경색 변경 */
	border-radius: 20px;
	margin-top: 10px;
	display: flex;
	justify-content: center;
	align-items: center;
	color: white;
	/* 텍스트 색상 변경 */
	font-size: 20px;
	font-family: 'Inter', sans-serif;
	font-weight: 500;
}

.links {
	margin-top: 20px;
	display: flex;
	justify-content: space-between;
	width: 100%;
}

.link-item {
	width: 75px;
	height: 75px;
	background: rgba(249, 249, 249, 0.78);
	border-top: 1px solid #C6C6C8;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 5px;
}

/* .link-icon {
            color: #007AFF;
            font-size: 24px;
            font-family: 'SF Pro Text', sans-serif;
            font-weight: 400;
        } */
.separator {
	width: 100%;
	height: 20px;
}
</style>
</head>

<body>
	<div class="container">
		<div class="header">
			<!-- 헤더 내용을 추가하세요 -->
		</div>
		<img class="logo"
			src="${pageContext.request.contextPath}/images/image1.png"
			onclick="redirectMainPage()" alt="로고">
		<form action="<c:url value='/user/login' />" method="POST">
			<!-- <form action="<c:url value='/' />" method="POST"> -->
			<div>
				<div class="login-text">로그인</div>
				<!-- 이름 입력란 -->
				<div class="input-container">
					<input type="text" name="nickname" required
						placeholder="이름을 입력해주세요">
				</div>

				<!-- 비밀번호 입력란 -->
				<div class="input-container">
					<input type="password" name="password" required
						placeholder="비밀번호를 입력해주세요">
				</div>

				<!-- 로그인 버튼 -->
				<button type="submit" class="login-button" onClick="login()">로그인</button>
			</div>
		</form>

		<div class="separator"></div>

	</div>


	<script>
		// main페이지 이동
		function redirectMainPage() {
			window.location.href = "${pageContext.request.contextPath}/";
		}
	</script>
</body>

</html>
