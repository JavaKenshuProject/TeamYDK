<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE>
  <html>
   <head>
    <link rel="stylesheet" href="websystem.css" type="text/css">
    <meta charset="Shift-JIS">
    <title>従業員管理システム</title>
   </head>
   <body>

 <!------------------------------------------ヘッダー------------------------------>
  <%@ include file ="header.jsp" %>
 <!-------------------------------------------------------------------------------->

     <div class="content" align="center">
     <h2 class="message">従業員情報を新規登録しました</h2>
     <form action="EmployeeInsertServlet" method="post">
     <input type="submit" value="メニューに戻る" name="page" >
     </form>
     </div>
   </body>
  </html>