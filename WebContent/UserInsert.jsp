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
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">ユーザ情報登録</h2>
		<p>新しいユーザの情報を入力してください。</p>

		<!------------------------------------ユーザ情報登録フォーム-------------------------------->
		<form action="UserInsertServlet" method="post">
			<table class="user_add">
				<tr>
					<th>ユーザID</th>
					<td><input type="text" name="userID"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="text" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="追加" name="page">
		</form>
	</div>
</body>
</html>