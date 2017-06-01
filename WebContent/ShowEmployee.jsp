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
	<!-- �w�b�_�[ -->
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->

	<h2 class="page_title">�]�ƈ��ꗗ</h2>

	<!--------------------------------�]�ƈ��ꗗ�̕\-------------------------------->



	<%
		ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList");
		String color;
	%>
	<form action="ShowServlet" method="post">
		<table class="employee_table">
			<tr class="employee_title">
				<th></th>
				<th>�]�ƈ��R�[�h</th>
				<th>����</th>
				<th>����(�t���K�i)</th>
				<th>����</th>
				<th>���N����</th>
				<th>��������</th>
				<th>���Г�</th>
				<th>�ۗL���i</th>
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
								sex = "�j";
								break;
							case 1:
								sex = "��";
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
		<input type="submit" value="�폜" name="page">
		<input type="submit" value="�ύX" name="page">
	</form>

</body>
</html>