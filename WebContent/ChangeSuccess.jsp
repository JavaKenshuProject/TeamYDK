<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
</head>
<body>
	<!------------------------------------------ヘッダー------------------------------>
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->
	<div class="content" align="center">
		<h2 class="message">従業員情報を変更しました。</h2>
		<form action="MenuServlet" method="post">
			<input type="submit" value="メニューに戻る" name="page"
				class="menu_return_botan">
		</form>
	</div>
</body>
</html>