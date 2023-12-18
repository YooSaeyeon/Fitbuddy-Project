<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 프로필</title>
    <style>
        /* 여기에 CSS 스타일을 추가하세요. */
    </style>
</head>
<body>
    <div id="profile" class="profile">
        <div class="img">
            <c:if test="${loggedInUser.photo ne null}">
                <img class="profileImg" src="${loggedInUser.photo}" onerror="this.style.display='none'"/>
            </c:if>
        </div>
        <div class="info">
            <h1>Welcome, ${loggedInUser.nickname}!</h1>

            <form action="mypage/profile" method="post" enctype="multipart/form-data">
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
                                    <c:if test="${community.userProfile.photo ne null}">
                                        <img class="profileImg" src="${community.userProfile.photo}" onerror="this.style.display='none'"/>
                                    </c:if>
                                </div>
                                <!-- 닉네임, 날짜, 내용 -->
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
</body>
</html>