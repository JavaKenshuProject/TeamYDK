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
			ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeeList"); // EmployeeBean�̃��X�g��錾 */
			ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList"); // LicenseBean�̃��X�g��錾 */
			String color; // color�̊i�[�ϐ�
		%>
		<form action="LicenseServlet" method="post">
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
								/* ���ʂ̐ݒ� */
								String sex = "";
								switch (employee.getSex()) {
								case 0:
									sex = "�j";
									break;
								case 1:
									sex = "��";
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
			<!--------------------------------���i�ꗗ�̕\-------------------------------->
			<p>�ۗL���i�ɒǉ����鎑�i��I��ł��������B</p>
			<table style="text-align: center;">
				<thead class="scrollHead">
					<tr class="license_title">
						<th class="radio_license"></th>
						<th class="license_name">���i��</th>
					</tr>
				</thead>
				<tbody class="datebody">
					<%
						/* licenseList��null�łȂ��Ƃ� */
						if (licenseList != null) {
							for (int i = 0; i < licenseList.size(); i++) {
								LicenseBean license = licenseList.get(i);
								/* �F�̐ݒ� */
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
			<p>���i���擾�������t����͂��Ă�������</p>
			<table>
				<tr>
					<td><select name="getLicenseDay">
							<%
								/* ���t�̐ݒ�*/
								Calendar cal = Calendar.getInstance(); /* �J�����_�[�I�u�W�F�N�g���擾 */
								int year = cal.get(Calendar.YEAR);
								for (int j = year; j >= 1900; j--) {
							%>
							<option>
								<%=j%>
							</option>
							<%
								}
							%>
					</select>�N</td>
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
					</select>��</td>
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
					</select>��</td>
				</tr>
			</table>
			<input type="submit" value="�擾" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>