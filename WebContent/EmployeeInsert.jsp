<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.LicenseBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
</head>
<body>
<!------------------------------------------ヘッダー------------------------------>
	<%@ include file="header.jsp"%>
<!-------------------------------------------------------------------------------->
<div class="content">
	<h2 class="page_title">従業員登録</h2>
	<p>登録したい従業員のデータを入力してください。</p>
	<form action= "EmployeeInsertServlet" method="post">
		<table class="employee_add">
			<tr>
				<th>従業員コード</th>
				<td><input type="text" name="emp_cd"></td>
			</tr>
			<tr>
				<th>氏名(漢字)</th>
				<td>氏<input type="text" name="l_name"> 名<input type="text" name="f_name"></td>
				</tr>
				<tr>
				<th>氏名(カナ)</th>
				<td>氏<input type="text" name="l_kana_name"> 名<input type="text" name="f_kana_name"></td>
			</tr>
			<tr>
				<th>性別</th>
				<td><input type="radio" name="sex" value="man">男 <input type="radio" name="sex" value="lady">女</td>
			</tr>
			<tr>
				<th>生年月日</th>
				<td>  <select name="birth_year">
						<option>1994</option>	<option>1993</option>
					  </select>年
					  <select name="birth_month">
							<option>1</option>	<option>2</option>	<option>3</option>	<option>4</option>
							<option>5</option>	<option>6</option>	<option>7</option>	<option>8</option>	<option>9</option>	<option>10</option>
							<option>11</option>	<option>12</option>	</select>月
					   <select name="birth_day">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>日</td>
				</tr>
				<tr>
					<th>所属部署</th>
					<td><select name="job">
							<option>営業部</option>
							<option>デザイン部</option>
					</select></td>
				</tr>
				<tr>
					<th>入社日</th>
					<td><select name="start_year">
							<option>1994</option>
							<option>1993</option>
					</select>年 <select name="start_month">
							<option>1</option>	<option>2</option>	<option>3</option>	<option>4</option>
							<option>5</option>	<option>6</option>	<option>7</option>	<option>8</option>	<option>9</option>	<option>10</option>
							<option>11</option>	<option>12</option>
					</select>月 <select name="start_day">
							<option>4</option>
							<option>3</option>
					</select>日</td>
				</tr>
			</table>
			<table>
				<%
					ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList");
				%>
				<tr>
					<th></th>
					<th>保有資格</th>

					<%
						if (licenseList != null) {
							for (int i = 0; i < licenseList.size(); i++) {
								LicenseBean license = licenseList.get(i);
					%>

				</tr>
				<td><input type="checkbox" name="license" value="<%=i%>"></td>
				<td><%=license.getLicense_name()%></td>

				<%
					}
				%>
				<%
					}
				%>
				</tr>
			</table>
			<input type="submit" name="page"  value="登録" class="botan">
		</form>
	</div>
</body>
</html>