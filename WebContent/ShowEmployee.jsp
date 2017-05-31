<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; Windows-31J">
<title>Insert title here</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@include file="header.jsp" %>
 <!-------------------------------------------------------------------------------->

		<h2 class="page_title">従業員一覧</h2>

   <!--------------------------------従業員一覧の表-------------------------------->
   <form action="/servlet" method="post">
   <input type="submit" value="変更">
   <input type="submit" value="削除">
   <table>
    <tr class="employee_title">
     <th></th>
     <th>従業員コード</th>
     <th>氏名</th>
     <th>氏名(フリガナ)</th>
     <th>性別</th>
     <th>生年月日</th>
     <th>所属部署</th>
     <th>入社日</th>
     <th>保有資格</th>
    </tr>
    <tr style="background-color:#dbffed">
     <td><input type="radio" name="radio"></td>
     <td>00</td>
     <td>田中一郎</td>
     <td>タナカイチロウ</td>
     <td>男</td>
     <td>1994-08-05</td>
     <td>営業部</td>
     <td>1994-08-05</td>
     <td>基本情報</td>
    </tr>
    <tr>
     <td><input type="radio" name="radio"></td>
     <td>00</td>
     <td>田中一郎</td>
     <td>タナカイチロウ</td>
     <td>男</td>
     <td>1994-08-05</td>
     <td>営業部</td>
     <td>1994-08-05</td>
     <td>基本情報</td>
    </tr>
    </table>
    </form>

</body>
</html>