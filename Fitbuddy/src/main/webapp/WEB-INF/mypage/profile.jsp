<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>사용자프로필</title>
</head>
<body>
<div id="profile" class="profile">
	<div class="img">
		<c:if test="${profile ne null}">
			<img class="profileImg" src="${profile}" onerror="this.style.display='none'"/></img>
		</c:if>
	</div>
	<div class="info">
		 <h1>Welcome, ${loggedInUser.nickname}!</h1>
			<div class="edit"><iconify-icon icon="ri:edit-2-fill"></iconify-icon> �����ϱ�</div>
	</div>
</body>
</html>