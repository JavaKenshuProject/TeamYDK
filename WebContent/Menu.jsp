<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
 <link rel="stylesheet" href="websystem.css" type="text/css">
</head>
<body>

   <!------------------------------------------ヘッダー------------------------------>
     <%@include file="header.jsp"%>
 <!-------------------------------------------------------------------------------->
 <div class="content">
   <h2 class="page_title">メニュー</h2>
   <form action="/Loginservlet" method="post">
   <h3 class="menu_title">■従業員情報を編集する</h3>
   <p>
    <input type="submit" value="従業員一覧" class="menu_botan">
    <input type="submit" value="従業員登録" class="menu_botan">
   </p>
   <h3 class="menu_title">■資格情報を編集する</h3>
   <p>
    <input type="submit" value="資格取得" class="menu_botan">
    <input type="submit" value="資格追加" class="menu_botan">
   </p>
   <h3 class="menu_title">■システム利用者情報を編集する</h3>
    <p>
     <input type="submit" value="ユーザ登録" class="menu_botan">
    </p>
    <p>
     <input type="submit" value="ログアウト" class="menu_botan">
    </p>
   </form>
 </div>
</body>
</html>