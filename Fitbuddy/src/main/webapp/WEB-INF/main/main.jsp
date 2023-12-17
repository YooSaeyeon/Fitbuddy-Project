<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면-로그인 버전</title>
<style>

		body {
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    height: 100vh;
		    margin: 0;
		    
		}
		
        #container {
          width: 375px;
          min-height: 667px;
          margin: 0px auto;
          text-align: center;
          background: black;
          position: relative;
          overflow: auto;
          border: 1px solid black;
        }
        #logo {
          margin-top: 40px;
        }
        #box {
          margin-top: 15px;
        }
        #box2 {
          position: relative;
          width: 270px;
          height: 87px;
          left: 14%;
          right: 51.79%;
          margin-top: 15%;
          bottom: 47.79%;
          background: #ffffff;
          box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.25);
          border-radius: 10px;
          color: #000;
          text-align: center;
          font-family: Inter;
          font-size: 18px;
          font-style: normal;
          font-weight: 600;
          line-height: normal;
          padding-top: 53px;
        }
        #box3 {
          position: relative;
          width: 130px;
          height: 87px;
          left: 13.5%;
          right: 51.79%;
          margin-top: 3%;
          bottom: 47.79%;
          background: #ffffff;
          box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.25);
          border-radius: 10px;
          color: #000;
          text-align: center;
          font-family: Inter;
          font-size: 18px;
          font-style: normal;
          font-weight: 600;
          line-height: normal;
          padding-top: 53px;
        }
        #box4 {
          position: relative;
          width: 130px;
          height: 87px;
          left: 53%;
          right: 51.79%;
          margin-top: -37.5%;
          bottom: 47.79%;
          background: #ffffff;
          box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.25);
          border-radius: 10px;
          color: #000;
          text-align: center;
          font-family: Inter;
          font-size: 18px;
          font-style: normal;
          font-weight: 600;
          line-height: normal;
          padding-top: 53px;
        }
        #footer_box {
          width: 375px;
          height: 97px;
          box-sizing: border-box;
          position: absolute;
          background: #ffffff;
          border: 1px solid #d9d9d9;
          box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
          border-top-left-radius: 10px;
          border-top-right-radius: 10px;
          padding-top: 10px;
          top: 570px;
        }
    </style>

</head>
<body>
    <div id="container">
        <div id="logo">
            <img src="${pageContext.request.contextPath}/images/image1.png" alt="logo" />
        </div>
        <div>
           <!-- login 페이지로 이동시키도록 수정 -->
           <a href="${pageContext.request.contextPath}/user/loginform">
            <img src="${pageContext.request.contextPath}/images/logout.png" 
            onclick="login()"
            style="margin-top: 20px; margin-left: 7px" />
            </a>
        </div>
        <div id="box">
           <!-- community 페이지로 이동시키도록 수정 -->
            <div id="box2" onclick="redirectToCommPage()">커뮤니티</div>
            
            <!-- todo 페이지로 이동시키도록 수정 -->
            <div id="box3" onclick="todo()">운동 기록</div>
            
            <!-- mypage로 이동시키도록 수정 -->
            <div id="box4" onclick="redirectToMypagePage()">마이페이지</div>
        </div>
    </div>

    <script>
         // 이 부분 url만 수정
         // community 페이지 이동 
        function redirectToCommPage() {
            window.location.href = "${pageContext.request.contextPath}/comm/comm";
        }
         
         // todo 페이지 이동 
        function redirectToTodoPage() {
            window.location.href = "${pageContext.request.contextPath}/comm/comm";
        }
         
         // mypage 페이지 이동 
        function redirectToMypagePage() {
            window.location.href = "${pageContext.request.contextPath}/comm/comm";
        }
         
         // login 페이지 이동 
        function redirectToLoginPage() {
            window.location.href = "${pageContext.request.contextPath}/comm/comm";
        }
    </script>
</body>
</html>