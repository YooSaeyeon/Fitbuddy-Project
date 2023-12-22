<%-- <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>프로필</title>
</head>
<body>
<div id="profile" class="profile">
	<div class="img">
		<c:if test="${profile ne null}">
			<img class="profileImg" src="${profile}" onerror="this.style.display='none'"/></img>
		</c:if>
	</div>
	<div class="info">
		<div class="name">${uname}</div>
		<c:if test="${userId eq param.uid}">
			<div class="edit"><iconify-icon icon="ri:edit-2-fill"></iconify-icon> 수정하기</div>
		</c:if>
	</div>
</body>
</html>
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 프로필 정보를 보여주는 코드 -->
<div class="profile-info">
    <!-- 여기에 프로필 정보 표시 -->
    <p>프로필 정보를 여기에 표시합니다.</p>
</div>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 프로필</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            color: white;
            background-color: #1f1f1f;
            font-family: Arial, sans-serif;
        }
        
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        
        .profile {
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: #333;
            border: 1px solid #000;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
        }
        
        .profile .img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            overflow: hidden;
            margin-bottom: 20px;
        }
        
        .profile .img img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        .profile .info {
            text-align: center;
        }
        
        .my-posts, .user-comments {
            width: 100%;
            max-width: 400px;
            margin-bottom: 20px;
        }
        
        .my-posts .commbox, .user-comments .commentBox {
            border: 1px solid #000;
            background-color: #222;
            padding: 10px;
            margin-bottom: 10px;
        }
        
        .my-posts .commbox .profile, .user-comments .commentBox .profile {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            overflow: hidden;
            margin-right: 10px;
        }
        
        .my-posts .commbox .profile img, .user-comments .commentBox .profile img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
        
        /* Scrollbar style */
        ::-webkit-scrollbar {
            width: 8px;
        }
        
        ::-webkit-scrollbar-track {
            background: #333;
        }
        
        ::-webkit-scrollbar-thumb {
            background: #555;
            border-radius: 10px;
        }
        
        ::-webkit-scrollbar-thumb:hover {
            background: #777;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="profile">
            <div class="img">
                <c:choose>
                    <c:when test="${not empty userProfile.photo}">
                        <img src="<c:url value='/uploads/${userProfile.photo}'/>" />
                    </c:when>
                    <c:otherwise>
                        <img class="profileImg" src="나중에 넣을 것" />
                        <span>프로필 이미지가 없습니다.</span>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="info">
                <h1>${loggedInUser.nickname}</h1>
                <form action="<c:url value='/user/update'/>" method="post" enctype="multipart/form-data">
                    <input type="file" name="profilePhoto" size="11" multiple="multiple" />
                    <input type="submit" value="사진 업로드">
                </form>
            </div>
        </div>

        <div class="my-posts">
            <h1>게시글 목록</h1>
            <div id="scroll">
                <c:forEach var="community" items="${userCommList}">
                    <div class="commbox" data-cmpostid="${community.cmPostId}" onclick="redirectToPost(${community.cmPostId})">
                        <div class="profile">
                            <c:choose>
                              <%--   <c:when test="${not empty community.userProfile.photo}">
                                    <img src="<c:url value='/uploads/${community.userProfile.photo}'/>" />
                                </c:when> --%>
                                 <c:when test="${not empty userProfile.photo}">
                        		<img src="<c:url value='/uploads/${userProfile.photo}'/>" />
                    			</c:when>
                                <c:otherwise>
                                    <img class="profileImg" src="나중에 넣을 것" />
                                    <span>프로필 이미지가 없습니다.</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="name">${community.userName}</div>
                        <div class="date">${community.commDate}</div>
                        <div class="detail">${community.content}</div>                
                    </div>
                </c:forEach>
            </div>
        </div>
        
        <div class="user-comments">
            <h1>댓글 목록</h1>
            <div id="scroll2">
                <c:choose>
                    <c:when test="${empty userCommentList}">
                        <p>댓글이 없습니다.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="comment" items="${userCommentList}">
                            <div class="commentBox" data-cmcommentid="${comment.cmCommentId}">
                                <div class="profile">
                                    <c:choose>
                                        <%-- <c:when test="${not empty comment.userProfile}">
                                            <img src="<c:url value='/uploads/${comment.userProfile}'/>" />
                                        </c:when> --%>
                                         <c:when test="${not empty userProfile.photo}">
                        				<img src="<c:url value='/uploads/${userProfile.photo}'/>" />
                    					</c:when>
                                        <c:otherwise>
                                            <img class="profileImg" src="나중에 넣을 것" />
                                            <span>프로필 이미지가 없습니다.</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="name">${comment.userName}</div>
                                <div class="content">${comment.content}</div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>