<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�]�ƈ��Ǘ��V�X�e��</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">���[�U���o�^</h2>
		<p>�V�������[�U�̏�����͂��Ă��������B</p>

		<!------------------------------------���[�U���o�^�t�H�[��-------------------------------->
		<form action="UserInsertServlet" method="post">
			<table class="user_add">
				<tr>
					<th>���[�UID</th>
					<td><input type="text" name="userID"></td>
				</tr>
				<tr>
					<th>�p�X���[�h</th>
					<td><input type="text" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="�ǉ�" name="page">
		</form>
	</div>
</body>
</html>