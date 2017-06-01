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
	<!-- ヘッダー -->
	<%@include file="header.jsp" %>
 <!-------------------------------------------------------------------------------->
	<div class="content">

 <!--------------------------------ログインフォーム-------------------------------->
        <div class="loginform">
         <div class="loginheader">
          <h2 class="logintitle">ログイン</h2>
         </div>
          <form action="LoginServlet" method="post">
            <table class="login_table">
              <tr>
              <!-- <td>ユーザID</td>-->
               <td>
                <input type="text"  name="userID" placeholder="ユーザIDを入力してください" class="dateform">
               </td>
              </tr>
              <tr>
             <!-- <td>パスワード</td>-->
               <td>
                <input type="password"  name="password" placeholder="パスワードを入力してください"  class="dateform">
               </td>
              </tr>
               <tr>
                <td>
                <input type="submit" value="ログイン" name="login" class="botan">
                </td>
               </tr>
            </table>
           </form>
         </div>
   <!-------------------------------------------------------------------------------->
     </div>
</body>
</html>