<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form id="profile-edit" action="/userinfoUpdate" class="mypage" method="post">
	<div class="img">
		<input type="file" id="file" name="profile" accept="image/*"></input>
	</div>
	<div class="info">
		<input type="text" class="name" name="name" placeholder="�̸��� �Է��ϼ���" value="${uname}" required></input>
		<input type="submit" value="���� �Ϸ�"></input>
	</div>
</form>
</body>
</html>



<!-- ������ ������ ���� �ڵ� -->
<!-- <div class="profile-edit-form">
    ���⿡ ������ ���� �� ǥ��
    <form action="������_����_����_URL" method="post">
        ������ ������ ���� �Է� ��
        <input type="text" name="userName" placeholder="�̸�">
        <input type="file" name="profileImage" accept="image/*">
        <button type="submit">������ ����</button>
    </form>
</div> -->
