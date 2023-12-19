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
                
                <hr style="height: 2px; margin-top: 40px; background-color: white" />
                <div id="detail_content">${community.content}</div>
            </div>
         <hr style="height: 2px; margin-top: 40px; background-color: white" />
     <!-- 댓글 부분 추후 추가 -->
    <div id="commentList1">
    <c:forEach var="comment" items="${commentList}">
        <div class="commentContainer">
            <div class="commentProfileImage">
                <c:choose>
                    <c:when test="${empty comment.userProfile}">
                        <img src="<c:url value='/images/commentProfile.png' />">
                    </c:when>
                    <c:otherwise>
                        <img src="<c:url value='${comment.userProfile}' />">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="profileInfo">
                <div class="comName">${empty comment.userName ? '익명' : comment.userName}</div>
                <div class="comContent">${comment.content}</div>
            </div>
        </div>
    </c:forEach>
</div>

   <div id="commentForm1">
    <form action="<c:url value='/community/comment' />" method="post">
        <input type="hidden" name="cmPostId" value="${community.cmPostId}" />
        <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
        <button type="submit"><img src="<c:url value='/images/commentClick.png' />"></button>
    </form>
	</div>
	 </div>
   </div>      
</body>
</html>
