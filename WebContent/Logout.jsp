<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
</head>
<body>
<!-- ヘッダー -->
	<%@include file="header.jsp" %>
	 <div class="content" align="center">
     <h2 class="message">ログアウトしました。<br>お疲れ様でした。</h2>
     <form action="LogoutServlet" method="post">
     <input type="submit" value="ログイン画面へ" name="move" class="menu_return_botan">
     </form>
     </div>
</body>
</html>