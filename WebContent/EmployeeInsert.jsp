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
<title>�]�ƈ��Ǘ��V�X�e��</title>
</head>
<body>
	<!------------------------------------------�w�b�_�[------------------------------>
	<%@ include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->
	<%
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
	%>
	<div class="content">
		<h2 class="page_title">�]�ƈ��o�^</h2>
		<p>�o�^�������]�ƈ��̃f�[�^����͂��Ă��������B</p>
		<form action="EmployeeInsertServlet" method="post">
			<table class="employee_add">
				<tr>
					<th>�]�ƈ��R�[�h</th>
					<td><input type="text" name="emp_cd" placeholder="���p�p����4�����ȓ�"></td>
				</tr>
				<tr>
					<th>����(����)</th>
					<td>��&nbsp;<input type="text" name="l_name"
						placeholder="��) �c��"> ��&nbsp;<input type="text"
						name="f_name" placeholder="��) ��Y"></td>
				</tr>
				<tr>
					<th>����(�t���K�i)</th>
					<td>��&nbsp;<input type="text" name="l_kana_name"
						placeholder="��) �^�i�J"> ��&nbsp;<input type="text"
						name="f_kana_name" placeholder="��) �C�`���E"></td>
				</tr>
				<tr>
					<th>����</th>
					<td><input type="radio" name="sex" value="0" checked="checked">�j
						<input type="radio" name="sex" value="1">��</td>
				</tr>
				<tr>
					<th>���N����</th>
					<td><select name="birth_year">
							<%
								for (int BY = year; BY >= 1900; BY--) {
							%>
							<option><%=BY%></option>
							<%
								}
							%>
					</select>�N <select name="birth_month">
							<%
								for (int BM = 1; BM <= 12; BM++) {
							%>
							<option><%=BM%></option>
							<%
								}
							%>
					</select>�� <select name="birth_day">
							<%
								for (int BD = 1; BD <= 31; BD++) {
							%>
							<option><%=BD%></option>
							<%
								}
							%>
					</select>��</td>
				</tr>
				<tr>
					<th>��������</th>
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
					<th>���Г�</th>
					<td><select name="start_year">
							<%
								for (int SY = year; SY >= 1900; SY--) {
							%>
							<option><%=SY%></option>
							<%
								}
							%>
					</select>�N <select name="start_month">
							<%
								for (int SM = 1; SM <= 12; SM++) {
							%>
							<option><%=SM%></option>
							<%
								}
							%>
					</select>�� <select name="start_day">
							<%
								for (int SD = 1; SD <= 31; SD++) {
							%>
							<option><%=SD%></option>
							<%
								}
							%>
					</select>��</td>
				</tr>
			</table>
			<div class="licensediv">
				<table class="license_insert">
					<tr>
						<%
							ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList");
						%>
						<th class="license_insert_th" style="vertical-align: middle;">�ۗL���i</th>
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
					</select>�N <select name="get_month">
							<%
								for (int GM = 1; GM <= 12; GM++) {
							%>
							<option><%=GM%></option>
							<%
								}
							%>
					</select>�� <select name="get_day">
							<%
								for (int GD = 1; GD <= 31; GD++) {
							%>
							<option><%=GD%></option>
							<%
								}
							%>
					</select>��</td>
					</tr>
					<%
						}
						}else{
							/* DO NOTHING */
						}
					%>

				</table>
			</div>
			<input type="submit" name="page" value="�o�^" class="botan">
		</form>
	</div>
</body>
</html>