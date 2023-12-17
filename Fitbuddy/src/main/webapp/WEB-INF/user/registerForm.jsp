<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="ko-KR">
<head>
<title>사용자 관리 - 회원 가입</title>
<script>
        function displayErrorMessageAndRedirect(errorMessage) {
            document.getElementById('errorMessage').innerText = errorMessage;
            const myModal = new bootstrap.Modal(document.getElementById('errorMessageModal'));
            myModal.show();
            setTimeout(() => {
                window.location.href = "/Fitbuddy/user/register"; // 다시 등록 페이지로 리다이렉션
            }, 3000);
        }
    </script>
<style>
/* 가입 버튼 스타일 */
.join-button {
	width: 280px;
	height: 44px;
	background: #c2ac18;
	border-radius: 20px;
	text-align: center;
	line-height: 44px;
	cursor: pointer;
	color: black;
	font-size: 20px;
	font-weight: 500;
	margin-left: 20px;
	margin-top: -15px;
}
/* 회원가입 폼 스타일 */
.registration-form {
	width: 375px;
	padding: 50px 25px;
	position: absolute;
	color: white;
	font-size: 25px;
	font-weight: 600;
}

body {
	margin: 0;
	padding: 0;
	background: white;
	font-family: Inter, sans-serif;
	position: relative;
}

#container {
	width: 390px;
	min-height: 789px;
	margin: 0px auto;
	text-align: center;
	background: black;
	position: relative;
	border: 1px solid black;
}

/* 상단 바 스타일 */
.top-bar {
	width: 375px;
	padding: 13.46px 25px 11.54px 25.96px;
	left: 7px;
	top: 0;
	position: absolute;
	justify-content: space-between;
	align-items: center;
	display: flex;
}

/* 로고 스타일 */
.logo {
	margin-top: 2px;
	margin-left: 23px;
	width: 200px;
	/* 원하는 크기로 조정 */
	height: 90px;
	/* 원하는 크기로 조정 */
	background: black;
	/* 배경색을 흰색으로 설정 */
}
/* 입력 필드 스타일 */
.input-field {
	width: 320px;
	height: 32px;
	padding: 7px 16px 6px;
	border-radius: 6px;
	border: 1px #ededed solid;
	color: #787878;
	font-size: 16px;
	font-weight: 400;
}
/* 입력 필드와 라벨을 한 줄에 붙이는 스타일 */
.registration-form label, .registration-form input {
	display: block; /* 기존 display: inline-block;을 block으로 변경합니다. */
	margin-bottom: 10px;
}

/* 기존 스타일은 그대로 두고, 라디오 버튼에 대한 스타일만 추가 */
.gender-radio {
	display: flex;
	flex-direction: column;
}

.gender-radio label {
	display: flex;
	align-items: center;
	cursor: pointer;
	font-size: 16px;
	margin-bottom: 10px;
}

.gender-radio input[type="radio"] {
	display: none;
}

.gender-radio input[type="radio"]+label:before {
	content: '';
	display: inline-block;
	width: 20px;
	height: 20px;
	margin-right: 10px;
	border-radius: 50%;
	border: 2px solid #ccc;
}

.gender-radio input[type="radio"]:checked+label:before {
	background-color: #c2ac18;
}
</style>

</head>
<body>
	<div id="container">
		<!-- 모달 영역 -->
		<div class="modal" id="errorMessageModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">에러 메시지</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p id="errorMessage"></p>
					</div>
				</div>
			</div>
		</div>

		<h2>회원 가입</h2>
		<div class="top-bar">
			<!-- 로고 -->
			<div class="logo">
				<img class="logo"
					src="${pageContext.request.contextPath}/images/image1.png" onclick="redirectMainPage()" alt="로고">
			</div>
		</div>
		<!-- <form method="POST" action="<c:url value=/'user/register'/>"> -->
		<form class="registration-form" method="POST"
			action="/Fitbuddy/user/register">
			<script>
    		window.onload = function() {
        		var passwordMismatch = <%=request.getAttribute("passwordMismatch")%>;
        		var existingUserMessage = '<%=request.getAttribute("existingUserMessage")%>';

        	if (passwordMismatch) {
            	alert("비밀번호가 일치하지 않습니다.");
        	} else if (existingUserMessage && existingUserMessage !== 'null' && existingUserMessage !== '') {
            	alert(existingUserMessage);
        		}
    		}
</script>
			<label for="name"
				style="font-size: 18px; margin-left: -88%; margin-top: -16%;">이름:</label>
			<input type="text" id="name" name="nickname" class="input-field"
				placeholder="이름을 입력해주세요." required><br>
			<br> <label for="password"
				style="margin-top: -10%; font-size: 18px; margin-left: -80%;">비밀번호:</label>
			<input type="password" id="password" class="input-field"
				name="password" placeholder="숫자,영문 조합 최소 8자" required><br>
			<br> <label for="confirmPassword"
				style="margin-top: -10%; font-size: 18px; margin-left: -69%;">비밀번호
				확인:</label> <input type="password" id="confirmPassword" class="input-field"
				name="confirmPassword" placeholder="비밀번호 재입력"><br>
			<br>
			<!--  <label for="gender">성별</label>
	        <select id="gender" name="gender">
	          <option value="male">남성</option>
	          <option value="female">여성</option>
	      </select> -->
			<div class="gender-radio" style="margin-top: -10%;">
				<input type="radio" id="male" name="gender" value="male" checked>
				<label for="male">남성</label> <input type="radio" id="female"
					name="gender" value="female"> <label for="female">여성</label>
			</div>
			<br>
			<br> <input type="submit" value="회원 가입" class="join-button">
		</form>
	</div>


	<script>
 // main페이지 이동
    function redirectMainPage() {
        window.location.href = "${pageContext.request.contextPath}/";
    }
    </script>
</body>
</html>