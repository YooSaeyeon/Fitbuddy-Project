<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 프로필</title>
 
</head>
<body>
    <div id="profile" class="profile">
        <%-- <div class="img">
            <c:if test="${loggedInUser.photo ne null}">
                <img class="profileImg" src="${loggedInUser.photo}" onerror="this.style.display='none'"/>
            </c:if>
        </div> --%>
        <div class="img">
    	<c:choose>
    		<c:when test="${not empty userProfile.photo}">
        	<% System.out.println("로그인된 사용자의 사진이 있습니다."); %>
        	<img src="<c:url value='/uploads/${userProfile.photo}'/>" />
        	${userProfile.photo}
    	</c:when>
    	<c:otherwise>
        	<!-- 기본 이미지 -->
        	<img class="profileImg" src="나중에 넣을 것" />
        	<span>프로필 이미지가 없습니다.</span>
    	</c:otherwise>
</c:choose>

</div>
        
        <div class="info">
            <h1>Welcome, ${loggedInUser.nickname}!</h1>

         	<form action="<c:url value='/user/update'/>" method="post" enctype="multipart/form-data">
     <%--      <form action="${pageContext.request.contextPath}/user/update" method="post" enctype="multipart/form-data"> --%>
                <input type="file" name="profilePhoto" size="11" multiple="multiple" />
                <input type="submit" value="사진 업로드">
            </form>
        </div>
    </div>

    <div class="my-posts">
        <h1>게시글 목록</h1>
        <table style="width: 100%">
            <tr>
                <td width="20"></td>
                <td>
                    <br>
                    <div id="commboxes">
                        <c:forEach var="community" items="${userCommList}">
                            <div id="commbox" class="commbox" data-cmpostid="${community.cmPostId}" onclick="redirectToPost(${community.cmPostId})">
                                <!-- 프로필 이미지 -->
                                <div id="profile">
                                   <%--  <c:if test="${community.userProfile.photo ne null}">
                                        <img class="profileImg" src="${community.userProfile.photo}" onerror="this.style.display='none'"/>
                                    </c:if> --%>
                                    <img src="<c:url value='/uploads/${filename}'/>" />
                                </div>
                                <!-- 닉네임, 날짜, 내용 -->
                                <div id="name">${community.userName}</div>
                                <div id="date">${community.commDate}</div>
                                <div id="detail">${community.content}</div>                
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                </td>
            </tr>
        </table>
    </div>
    <div class="user-comments">
    <h1>댓글 목록</h1>
    <table style="width: 100%">
        <tr>
            <td width="20"></td>
            <td>
                <br>
                <div id="commentBoxes">
                    <c:choose>
                        <c:when test="${empty userCommentList}">
                            <p>댓글이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="comment" items="${userCommentList}">
                                <div id="commentBox" class="commentBox" data-cmcommentid="${comment.cmCommentId}">
                                    <!-- 프로필 이미지 -->
                                    <div id="profile">
                                        <img class="profileImg" src="${comment.userProfile}" />
                                    </div>
                                    <!-- 유저 이름,내용 -->
                                    <div id="name">${comment.userName}</div>     
                                    <div id="content">${comment.content}</div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>
                <br>
            </td>
        </tr>
    </table>
</div>

    
</body>
</html>