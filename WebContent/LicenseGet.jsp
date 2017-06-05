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
<title>�]�ƈ��Ǘ��V�X�e��</title>
</head>
<body>
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">���i�擾</h2>

		<!--------------------------------�]�ƈ��ꗗ�̕\-------------------------------->
		<p>�ۗL���i��ǉ�����]�ƈ���I��ł��������B</p>
		<%
			ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList");
			ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList");
			String color;
		%>
		<form action="LicenseServlet" method="post">
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
			</tbody>
			</table>
			<!--------------------------------���i�ꗗ�̕\-------------------------------->
			<p>�ۗL���i�ɒǉ����鎑�i��I��ł��������B</p>
			<table style="text-align:center;">
				<thead class="scrollHead">
				<tr class="license_title">
					<th class="radio_license"></th>
					<th class="license_name">���i��</th>
				</tr>
				</thead>
				<tbody class="datebody">
				<%
					if (licenseList != null) {
						for (int i = 0; i < licenseList.size(); i++) {
							LicenseBean license = licenseList.get(i);
							if (i % 2 == 0) {
								color = "#eeeeee";
							} else {
								color = "#fdfdfd";
							}
				%>
				<tr style="background-color:<%=color%>;">
					<td class="radiotd"><input type="radio" name="license" value="<%=i%>"></td>
					<td class="license_nametd"><%=license.getLicense_name()%></td>
				</tr>
				<%
					}
				%>
				<%
					}
				%>
				</tbody>
			</table>
			<p>���i���擾�������t����͂��Ă�������</p>
			<table>
			 <tr>
			  <td>
			   <select name="getLicenseDay">
			     <% Calendar cal = Calendar.getInstance(); //�J�����_�[�I�u�W�F�N�g���擾
                    int year = cal.get(Calendar.YEAR);
			        for(int j = year; j>=1900; j--){ %>
			       <option>
			          <%=j %>
			       </option>
			   <%
			}%>
			  </select>�N
			 </td>
			 <td>
			  <select name="getLicenseDay">
			   <%
			     for(int month= 1; month<=12; month++){
			   %>
			    <option>
			      <%=month%>
			    </option>
		          <%
			        }
			       %>
			  </select>��
			 </td>
			 <td>
		      <select name="getLicenseDay">
			    <%
			     for(int day= 1; day<=31; day++){
			    %>
			    <option>
			     <%=day%>
			    </option>
		         <%
			     }
			 %>
			 </select>��
			</td>
		   </tr>
		  </table>
			<input type="submit" value="�擾" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>