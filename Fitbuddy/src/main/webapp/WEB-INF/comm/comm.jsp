<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>커뮤니티 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/comm.css' />" type="text/css">
<script>
function commCreate() {
    if (commForm.content.value == "") {
        alert("내용을 입력하십시오.");
        commForm.content.focus();
        return false;
    } 
    commForm.submit();
}

function commList(targetUri) {
    commForm.action = targetUri;
    commForm.submit();
}
</script>
</head>
<body>
<br>
	<!-- registration form  -->
	<form name="commForm" method="POST" action="<c:url value='/community/create' />">
	    <div id="container">
	        <div id="logo">
	            <img src="<c:url value='/images/commlogo.png' />" onclick="redirectMainPage()" style="width: 110px" />
	        </div>
	        <div id="logout">
	            <img src="<c:url value='/images/comm_logout.png' />" />
	        </div>
	
	        <hr style="height: 2px; margin-top: 45px; background-color: white" />
	
	        <div id="scroll">
	            <div id="whiteBox">
	         
	                <!-- 커뮤니티 생성이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	                <c:if test="${creationFailed}">
	                    <font color="red">
	                    	<c:out value="${exception.getMessage()}" />
	                    </font>
	                </c:if>
	                <table class="commTable">
	                    <tr height="40">
	                        <td class="commCell">
	                            <%-- java.util.Date를 사용하여 현재 날짜를 가져옴 --%>
	                            <fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy년 MM월 dd일" var="commDate" />
	                            <span id="date" style="margin-left: 90px;">${commDate}</span>
	                        </td>
	                    </tr>
	
                        <tr>
                            <td class="commCell">
                                <input type="text" style="width: 300; height: 350px; margin-left: 18px;" name="content" 
                                    <c:if test="${creationFailed}">value="${comm.content}"</c:if>>
                            </td>
                        </tr>   
    
	                </table>
	            </div>
	        </div>
	
	            <table style="width: 100%">
	                <tr>
	                    <td align="left">
	                        <input style="   
	                        position: absolute;
	                  width: 281px;
	                  height: 44px;
	                  left: 54px;
	                  margin-top: 50px;
	                  
	                  background: #C2AC18;
	                  border-radius: 20px;" 
	                  type="button" value="올리기" onClick="commCreate()"> &nbsp;
	                    </td>
	                </tr>
	            </table>
	
	    </div>
	</form>

    <script>
        // JavaScript function to update the selected image preview
        document.querySelector('input[name="imgFile"]').addEventListener('change', function(event) {
            const selectedImage = document.getElementById('selectedImage');
            selectedImage.src = URL.createObjectURL(event.target.files[0]);
        });
        
     // main페이지 이동
        function redirectMainPage() {
            window.location.href = "${pageContext.request.contextPath}/";
        }
    </script>

</body>
</html>