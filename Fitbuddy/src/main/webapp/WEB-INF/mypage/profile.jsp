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
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;   
            color: white; /* 텍스트 색상을 하얀색으로 지정 */
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
        
       #scroll {
         width: 390px;
         height: 190px;
         margin: 0px auto;
         position: relative;
         border: 1px solid black;
         overflow-y: scroll;
         overflow-x: hidden;
         &::-webkit-scrollbar {
           /* WebKit 브라우저의 스크롤바를 숨김 */
           width: 0;
           background: transparent;
         }
       }  
        #logo {
          margin-top: 20px;
        }
        #logout {
         margin-left: 280px;
         margin-top: -35px;
       }

        /* 추가된 스타일 */
        .profile h1,
        .my-posts h1,
        #commboxes .commbox #name,
        #commboxes .commbox #date,
        #commboxes .commbox #detail {
            color: white; /* 텍스트 색상을 하얀색으로 지정 */
        }
        
        #imgBox {
         z-index: 1;
         margin-top: 25px;
         width: 100px;
         height: 100px;
         border-radius: 10px;
         border: 2px solid #fff;
         background: #000;
         left: 100px;
         margin-right: auto;
       }
   
       #imgBox p {
         margin-top: 60px;
         z-index: 0;
         color: #fff;
         font-family: Inter;
         font-size: 15px;
         font-style: normal;
         font-weight: 600;
         line-height: normal;
         text-align: center; /* 텍스트를 가운데 정렬 */
       } 
       
       .my-posts{
          margin-top: 50px;
       }
        
        #upload{
         position: absolute;
         width: 159px;
         height: 29px;
         left: 115px;
         top: 322px;
         
         background: #C2AC18;
         border-radius: 20px;   
        }
        
    #commboxes {
      overflow-y: auto;
      overflow-x: hidden;
      margin-top: -27px;
    }
    #commboxes::-webkit-scrollbar {
      width: 2px; /* 스크롤바 너비 조절 */
    }

    #commbox {
      box-sizing: border-box;
      position: relative;
      margin-top: 10px;
      height: 90px;
      width: 330px;
      left: 5px;
      right: 23px;
      background: #000000;
      border: 2px solid #ffffff;
      border-radius: 10px;
    }
    #name {
      color: #fff;
      font-family: Inter;
      font-size: 18px;
      font-style: normal;
      font-weight: 600;
      line-height: normal;
      margin-left: -70px;
      margin-top: 10px;
    }
    #profile img {
      margin-left: -220px;
      width: 50px;
      height: 50px;
      margin-top: -25px;
      border-radius: 25px;
    }
    #date {
      position: relative;
      font-family: "Inter";
      font-style: normal;
      font-size: 15px;
      line-height: 18px;
      color: #d3d3d3;
      margin-top: -27px;
      margin-left: 200px;
    }
    #detail {
      position: relative;
      font-family: "Inter";
      font-style: normal;
      font-size: 16px;
      line-height: 24px;
      color: #ffffff;
      margin-top: 20px;
      margin-left: 100px;
      text-align: left;
    }
       #scroll2 {
         width: 390px;
         height: 190px;
         margin: 0px auto;
         position: relative;
         border: 1px solid black;
         overflow-y: scroll;
         overflow-x: hidden;
         &::-webkit-scrollbar {
           /* WebKit 브라우저의 스크롤바를 숨김 */
           width: 0;
           background: transparent;
         }
       } 
        
    </style>
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
        	<c:when test="${loggedInUser.photo ne null}">
            	<img class="profileImg" src="${loggedInUser.photo}" />
        	</c:when>
        	<c:otherwise>
        	    <!-- 기본 이미지를 여기에 넣으세요. -->
            	<img class="profileImg" src="나중에 넣기" />
            	<!-- 또는 텍스트나 다른 대체 콘텐츠를 표시할 수 있습니다. -->
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
                                    사진 저장 경로 : ${dir} <br/>
									사진 파일 이름 : ${filename} <br/>
                                    <img src="<c:url value='/uploads/${filename}'/>" />
                                </div>
                                <!-- 닉네임, 날짜, 내용 -->
                                <div id="name">ㅇ${community.userName}</div>
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
                                        <!-- 여기에 댓글의 프로필 이미지를 표시하는 코드를 추가하세요 -->
                                        <!-- 예시: -->
                                        <!-- <img class="profileImg" src="${comment.userProfile}" /> -->
                                    </div>
                                    <!-- 유저 이름, 날짜, 내용 -->
                                    <div id="name">${comment.userName}</div>
                                    <div id="date">${comment.date}</div>
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