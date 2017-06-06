<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="entity.LicenseBean" import="entity.SectionBean"
	import="dao.SectionDAO"%>

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
	<%
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
	%>
	<div class="content">
		<h2 class="page_title">従業員登録</h2>
		<p>登録したい従業員のデータを入力してください。</p>
		<form action="EmployeeInsertServlet" method="post">
			<table class="employee_add">
				<tr>
					<th>従業員コード</th>
					<td><input type="text" name="emp_cd" placeholder="半角英数字4文字以内"></td>
				</tr>
				<tr>
					<th>氏名(漢字)</th>
					<td>氏&nbsp;<input type="text" name="l_name"
						placeholder="例) 田中"> 名&nbsp;<input type="text"
						name="f_name" placeholder="例) 一郎"></td>
				</tr>
				<tr>
					<th>氏名(フリガナ)</th>
					<td>氏&nbsp;<input type="text" name="l_kana_name"
						placeholder="例) タナカ"> 名&nbsp;<input type="text"
						name="f_kana_name" placeholder="例) イチロウ"></td>
				</tr>
				<tr>
					<th>性別</th>
					<td><input type="radio" name="sex" value="0" checked="checked">男
						<input type="radio" name="sex" value="1">女</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td><select name="birth_year">
							<%
								for (int BY = year; BY >= 1900; BY--) {
							%>
							<option><%=BY%></option>
							<%
								}
							%>
					</select>年 <select name="birth_month">
							<%
								for (int BM = 1; BM <= 12; BM++) {
							%>
							<option><%=BM%></option>
							<%
								}
							%>
					</select>月 <select name="birth_day">
							<%
								for (int BD = 1; BD <= 31; BD++) {
							%>
							<option><%=BD%></option>
							<%
								}
							%>
					</select>日</td>
				</tr>
				<tr>
					<th>所属部署</th>
					<td><select name="section_name">
							<%
								SectionDAO section = new SectionDAO();
								ArrayList<SectionBean> sec_list = section.SectionAllGet();

								for (SectionBean sec : sec_list) {
							%>
							<option><%=sec.getSectionName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>入社日</th>
					<td><select name="start_year">
							<%
								for (int SY = year; SY >= 1900; SY--) {
							%>
							<option><%=SY%></option>
							<%
								}
							%>
					</select>年 <select name="start_month">
							<%
								for (int SM = 1; SM <= 12; SM++) {
							%>
							<option><%=SM%></option>
							<%
								}
							%>
					</select>月 <select name="start_day">
							<%
								for (int SD = 1; SD <= 31; SD++) {
							%>
							<option><%=SD%></option>
							<%
								}
							%>
					</select>日</td>
				</tr>
			</table>
			<div class="licensediv">
				<table class="license_insert">
					<tr>
						<%
							ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList");
						%>
						<th class="license_insert_th" style="vertical-align: middle;">保有資格</th>
					</tr>
				</table>

				<table class="scroll">

					<%
						if (licenseList != null) {
							for (int i = 0; i < licenseList.size(); i++) {
								LicenseBean license = licenseList.get(i);
					%>
					<td class="check"><input type="checkbox" name="license"
						value="<%=i%>"></td>
					<td class="license_insert_name"><%=license.getLicenseName()%></td>

					<td class="license_day"><select name="get_year">
							<%
								for (int GY = year; GY >= 1900; GY--) {
							%>
							<option><%=GY%></option>
							<%
								}
							%>
					</select>年 <select name="get_month">
							<%
								for (int GM = 1; GM <= 12; GM++) {
							%>
							<option><%=GM%></option>
							<%
								}
							%>
					</select>月 <select name="get_day">
							<%
								for (int GD = 1; GD <= 31; GD++) {
							%>
							<option><%=GD%></option>
							<%
								}
							%>
					</select>日</td>
					</tr>
					<%
						}
						}else{
							/* DO NOTHING */
						}
					%>

				</table>
			</div>
			<input type="submit" name="page" value="登録" class="botan">
		</form>
	</div>
</body>
</html>