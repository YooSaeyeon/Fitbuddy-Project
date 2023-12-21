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
