<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="content" align="center">
		<h2 class="message">ユーザ情報を登録しました</h2>
		<form action="MenuServlet" method="post">
			<input type="submit" value="メニューに戻る" name="page"
				class="menu_return_botan">
		</form>
	</div>
</body>
</html>