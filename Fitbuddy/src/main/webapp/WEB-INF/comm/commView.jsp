<%@page contentType="text/html; charset=utf-8" %>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel=stylesheet href="<c:url value='/css/commList.css' />" type="text/css">
    <title>커뮤니티-글 상세 조회</title>
</head>
  
  <body>
  <div id="container">
    <div id="back" onClick="">
        <img src="<c:url value='/images/back.png' />" />
    </div>
    <div id="logo">
        <img src="<c:url value='/images/commlogo.png' />" style="width: 110px" />
    </div>
    <div id="logout">
        <img src="<c:url value='/images/comm_logout.png' />" />
     </div> 
      <hr style="height: 2px; margin-top: 40px; background-color: white" />

     <div id="scroll">
            <div id="post_detailView">
                <div id="detail_name">${community.userName}</div>
                <!-- <div id="detail_profile">
                    <img src="<c:url value='${community.userProfile}' />" />
                </div> -->
               
                <div id="detail_date">${community.commDate}</div>
                <div id="detail_content">${community.content}</div>
            </div>
        </div>
     <!-- 댓글 부분 추후 추가 -->
     <c:forEach var="comment" items="${commentList}">
    <p>${comment.content}</p>
	</c:forEach>	
     <!-- 댓글 부분 추가 -->
    <div id="comment_section">
    <!-- 디버그 코드 시작 -->
 
    <c:out value="${community.cmPostId}" />
    <!-- 디버그 코드 끝 -->
    <form action="<c:url value='/community/comment' />" method="post">
       <input type="hidden" name="cmPostId" value="${community.cmPostId}" />
        <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
        <button type="submit">댓글 달기</button>
    </form>
    </div>
    </div>      
</body>
</html>
