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
			ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList"); //EmployeeBean�̃��X�g��錾
			String color; /* color�̊i�[�ϐ�*/
		%>
		<form action="ShowServlet" method="post">
			<table class="employee_table" style="text-align: center;">
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
						/* employeeList��null�łȂ��Ƃ� */
						if (employeeList != null) {
							for (int i = 0; i < employeeList.size(); i++) {
								EmployeeBean employee = employeeList.get(i);
								/* �F�̐ݒ� */
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
			<input type="submit" value="�폜" name="page" class="botan_get">
			<input type="submit" value="�ύX" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>