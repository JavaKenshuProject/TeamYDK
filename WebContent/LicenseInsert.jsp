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
		<h2 class="page_title">���i�ǉ�</h2>
		<p>�V�������i�̏�����͂��Ă��������B</p>

		<!------------------------------------���i�ǉ��o�^�t�H�[��-------------------------------->
		<form action="LicenseInsertServlet" method="post">
			<table class="license_add">
				<tr>
					<th>���i�R�[�h</th>
					<td><input type="text" name="license_cd"
						placeholder="���p�p����5����"></td>
				</tr>
				<tr>
					<th>���i��</th>
					<td><input type="text" name="license_name"
						placeholder="��) IT�p�X�|�[�g"></td>
				</tr>
			</table>
			<input type="submit" value="�ǉ�" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>