<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.EmployeeBean"%>
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; Windows-31J">
<title>Insert title here</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->

	<h2 class="page_title">従業員一覧</h2>

	<!--------------------------------従業員一覧の表-------------------------------->



	<%
		ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList");
		String color;
	%>
	<form action="ShowServlet" method="post">
		<table class="employee_table">
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
			<%
					if (employeeList != null) {
						for (int i = 0; i < employeeList.size(); i++) {
							EmployeeBean employee = employeeList.get(i);
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
				<td><input type="radio" name="employee" value="<%=i%>"></td>
				<td><%=employee.getEmp_code()%></td>
				<td><%=employee.getL_name()%><%=employee.getF_name()%></td>
				<td><%=employee.getL_kana_name()%><%=employee.getF_kana_name()%></td>
				<td><%=sex%></td>
				<td><%=employee.getBirth_day()%></td>
				<td><%=employee.getSection_name()%></td>
				<td><%=employee.getEmp_date()%></td>
				<td>
						<%
						if (employee.getLicense_name() != null){
							for (int j = 0; j < employee.getLicense_name().size(); j++) {
						%> <%=employee.getLicense_name().get(j)%>
						<% if(j!=employee.getLicense_name().size()-1){ %>
							<br>
						<% } %>
						<% } %>
						<% } %>
					</td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
		</table>
		<input type="submit" value="削除" name="page">
		<input type="submit" value="変更" name="page">
	</form>

</body>
</html>