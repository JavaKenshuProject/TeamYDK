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

   <h3 class="menu_title">■従業員情報を編集する</h3>
    <ul class="botans">
      <li>
        <form action="ShowServlet" method="post">
         <input type="submit" value="従業員一覧" name="page" class="menu_botan">
        </form>
      </li>
      <li>
      <form action="MenuServlet" method="post">
       <input type="submit" value="従業員登録" name="page" class="menu_botan">
      </form>
      </li>
    </ul>
   <h3 class="menu_title">■資格情報を編集する</h3>
      <form action="LicenseServlet" method="post">
       <input type="submit" value="資格取得" name="page" class="menu_botan">
      </form>
      <form action="MenuServlet" method="post">
       <input type="submit" value="資格追加" name="page" class="menu_botan">
      </form>

   <h3 class="menu_title">■システム利用者情報を編集する</h3>
      <form action="MenuServlet" method="post">
       <input type="submit" value="ユーザ登録" name="page" class="menu_botan">
      </form>

   <p>
    <form action="Logoutservlet" method="post">
     <input type="submit" value="ログアウト" class="menu_botan">
     </form>
    </p>
 </div>
</body>
</html>