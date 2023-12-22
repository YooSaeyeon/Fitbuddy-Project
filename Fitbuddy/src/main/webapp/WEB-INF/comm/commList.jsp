<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/commList.css' />" type="text/css">
    <title>ì»¤ë®¤ëí°-ëª©ë¡</title>
</head>
<body>
    <div id="container">
        <div id="back" onClick="location.href='main_logout.html'">
            <img src="<c:url value='/images/back.png' />" />
        </div>
        <div id="logo">
            <img src="<c:url value='/images/commlogo.png' />" style="width: 110px" />
        </div>
        <div id="logout">
            <img src="<c:url value='/images/comm_logout.png' />" />
        </div>
               <div id="search">
          
        </div>
        
        <div id="input" onClick="location.href='create'">
            <img src="<c:url value='/images/search.svg' />" />
        </div>
        <div id="font">
            <img src="<c:url value='/images/commmain_font.svg' />" />
        </div>
        <table style="width: 100%">
            <tr>
                <td width="20"></td>
                <td>
                    <br>
                    <div id="commboxes">
                        <c:forEach var="community" items="${commList}">
                            <div id="commbox" class="commbox" data-cmpostid="${community.cmPostId}" onclick="redirectToPost(${community.cmPostId})">
                               
                        
					                <div class="communityProfileImage" style="width: 50px; height: 50px; border-radius: 50%; overflow: hidden; margin-right: 10px;">
					    <c:choose>
					        <c:when test="${empty community.userProfile}">
					            <img src="<c:url value='/images/communityProfile.png' />" alt="Profile Image" style="width: 100%; height: 100%; object-fit: cover;">
					        </c:when>
					        <c:otherwise>
					            <img src="<c:url value='/uploads/${community.userProfile}' />" alt="Profile Image" style="width: 100%; height: 100%; object-fit: cover;">
					        </c:otherwise>
					    </c:choose>
					</div>
                               
                               
                                <div id="name" style="color:white">${community.userName}</div>
                                <div id="date" style="color:white">${community.commDate}</div>
                                <div id="detail" style="color:white">${community.content}</div>                
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                </td>
            </tr>
        </table>
    </div>

    <script>
        function redirectToPost(cmPostId) {
            try {
                window.location.href = '/Fitbuddy/community/view/' + cmPostId;
            } catch (error) {
                console.error('Error while redirecting to post:', error);
            }
        }
    </script>
</body>
</html>
