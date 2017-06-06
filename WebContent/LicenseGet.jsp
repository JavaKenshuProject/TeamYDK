<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="entity.EmployeeBean"%>
<%@ page import="entity.LicenseBean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="websystem.css" type="text/css">
<%@ include file="header.jsp"%>
<title>従業員管理システム</title>
</head>
<body>
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">資格取得</h2>

		<!--------------------------------従業員一覧の表-------------------------------->
		<p>保有資格を追加する従業員を選んでください。</p>
		<%
			ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList"); // EmployeeBeanのリストを宣言 */
			ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList"); // LicenseBeanのリストを宣言 */
			String color; // colorの格納変数
		%>
		<form action="LicenseServlet" method="post">
			<table class="employee_table" style="text-align: center;">
				<thead class="scrollHead">
					<tr class="employee_title">
						<th class="radio"></th>
						<th class="no">従業員コード</th>
						<th class="name">氏名</th>
						<th class="name_kana">氏名(フリガナ)</th>
						<th class="sex">性別</th>
						<th class="birth">生年月日</th>
						<th class="section">所属部署</th>
						<th class="startday">入社日</th>
						<th class="license">保有資格</th>
					</tr>
				</thead>
				<tbody class="datebody">
					<%
						/* employeeListがnullでないとき */
						if (employeeList != null) {
							for (int i = 0; i < employeeList.size(); i++) {
								EmployeeBean employee = employeeList.get(i);
								/* 色の設定 */
								if (i % 2 == 0) {
									color = "#dbffed";
								} else {
									color = "#fdfdfd";
								}
								/* 性別の設定 */
								String sex = "";
								switch (employee.getSex()) {
								case 0:
									sex = "男";
									break;
								case 1:
									sex = "女";
									break;
								default:
									/* DO NOTHING */
									break;
								}
					%>
					<tr style="background-color:<%=color%>;">
						<td class="radiotd"><input type="radio" name="employee"
							value="<%=i%>"></td>
						<td class="notd"><%=employee.getEmpCode()%></td>
						<td class="nametd"><%=employee.getLName()%><%=employee.getFName()%></td>
						<td class="name_kanatd"><%=employee.getLKanaName()%><%=employee.getFKanaName()%></td>
						<td class="sextd"><%=sex%></td>
						<td class="birthtd"><%=employee.getBirthDay()%></td>
						<td class="sectiontd"><%=employee.getSectionName()%></td>
						<td class="startdaytd"><%=employee.getEmpDate()%></td>
						<td class="licensetd">
							<%
								if (employee.getLicenseName() != null) {
											for (int j = 0; j < employee.getLicenseName().size(); j++) {
							%> <%=employee.getLicenseName().get(j)%> <%
 	if (j != employee.getLicenseName().size() - 1) {
 %> <br> <%
 	} else {
 						/* DO NOTHING */
 					}
 %> <%
 	}
 %> <%
 	} else {
 				/* DO NOTHING */
 			}
 %>
						</td>
					</tr>
					<%
						}
					%>
					<%
						} else {
							/* DO NOTHING */
						}
					%>
				</tbody>
			</table>
			<!--------------------------------資格一覧の表-------------------------------->
			<p>保有資格に追加する資格を選んでください。</p>
			<table style="text-align: center;">
				<thead class="scrollHead">
					<tr class="license_title">
						<th class="radio_license"></th>
						<th class="license_name">資格名</th>
					</tr>
				</thead>
				<tbody class="datebody">
					<%
						/* licenseListがnullでないとき */
						if (licenseList != null) {
							for (int i = 0; i < licenseList.size(); i++) {
								LicenseBean license = licenseList.get(i);
								/* 色の設定 */
								if (i % 2 == 0) {
									color = "#eeeeee";
								} else {
									color = "#fdfdfd";
								}
					%>
					<tr style="background-color:<%=color%>;">
						<td class="radiotd"><input type="radio" name="license"
							value="<%=i%>"></td>
						<td class="license_nametd"><%=license.getLicenseName()%></td>
					</tr>
					<%
						}
					%>
					<%
						} else {
							/* DO NOTHING */
						}
					%>
				</tbody>
			</table>
			<p>資格を取得した日付を入力してください</p>
			<table>
				<tr>
					<td><select name="getLicenseDay">
							<%
								/* 日付の設定*/
								Calendar cal = Calendar.getInstance(); /* カレンダーオブジェクトを取得 */
								int year = cal.get(Calendar.YEAR);
								for (int j = year; j >= 1900; j--) {
							%>
							<option>
								<%=j%>
							</option>
							<%
								}
							%>
					</select>年</td>
					<td><select name="getLicenseDay">
							<%
								for (int month = 1; month <= 12; month++) {
							%>
							<option>
								<%=month%>
							</option>
							<%
								}
							%>
					</select>月</td>
					<td><select name="getLicenseDay">
							<%
								for (int day = 1; day <= 31; day++) {
							%>
							<option>
								<%=day%>
							</option>
							<%
								}
							%>
					</select>日</td>
				</tr>
			</table>
			<input type="submit" value="取得" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>