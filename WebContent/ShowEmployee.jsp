<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.EmployeeBean"%>
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; Windows-31J">
<title>�]�ƈ��Ǘ��V�X�e��</title>
</head>
<body>
	<!-- �w�b�_�[ -->
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->
  <div class="content">
	<h2 class="page_title">�]�ƈ��ꗗ</h2>

	<!--------------------------------�]�ƈ��ꗗ�̕\-------------------------------->



	<%
		ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList");
		String color;
	%>
	<form action="ShowServlet" method="post">
		<table class="employee_table" style="text-align:center;">
		<thead class="scrollHead">
			<tr class="employee_title">
				<th class="radio"></th>
				<th class="no">�]�ƈ��R�[�h</th>
				<th class="name">����</th>
				<th class="name_kana">����(�t���K�i)</th>
				<th class="sex">����</th>
				<th class="birth">���N����</th>
				<th class="section">��������</th>
				<th class="startday">���Г�</th>
				<th class="license">�ۗL���i</th>
			</tr>
          </thead>
          <tbody class="datebody">
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
				<td class="radiotd"><input type="radio" name="employee" value="<%=i%>"></td>
				<td class="notd"><%=employee.getEmp_code()%></td>
				<td class="nametd"><%=employee.getL_name()%><%=employee.getF_name()%></td>
				<td class="name_kanatd"><%=employee.getL_kana_name()%><%=employee.getF_kana_name()%></td>
				<td class="sextd"><%=sex%></td>
				<td class="birthtd"><%=employee.getBirth_day()%></td>
				<td class="sectiontd"><%=employee.getSection_name()%></td>
				<td class="startdaytd"><%=employee.getEmp_date()%></td>
				<td class="licensetd">
						<%
						if (employee.getLicense_name() != null){//test
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
				</tbody>
		</table>
		<input type="submit" value="�폜" name="page" class="botan_get">
		<input type="submit" value="�ύX" name="page" class="botan_get">
	</form>
	</div>
</body>
</html>