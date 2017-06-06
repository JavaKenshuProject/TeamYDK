<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.EmployeeBean"%>
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; Windows-31J">
<title>従業員管理システム</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">従業員一覧</h2>

		<!--------------------------------従業員一覧の表-------------------------------->



		<%
			ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList"); //EmployeeBeanのリストを宣言
			String color; /* colorの格納変数*/
		%>
		<form action="ShowServlet" method="post">
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

								String sex = "";
								switch (employee.getSex()) {
								case 0:
									sex = "男";
									break;
								case 1:
									sex = "女";
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
								if (employee.getLicenseName() != null) {//test
											for (int j = 0; j < employee.getLicenseName().size(); j++) {
							%> <%=employee.getLicenseName().get(j)%> <%
 	if (j != employee.getLicenseName().size() - 1) {
 %> <br> <%
 	}
 %> <%
 	}
 %> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
					%>
					<%
						}
					%>
				</tbody>
			</table>
			<input type="submit" value="削除" name="page" class="botan_get">
			<input type="submit" value="変更" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>