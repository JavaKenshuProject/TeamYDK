<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.LicenseBean"%>

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
<div class="content">
	<h2 class="page_title">�]�ƈ��o�^</h2>
	<p>�o�^�������]�ƈ��̃f�[�^����͂��Ă��������B</p>
	<form action= "EmployeeInsertServlet" method="post">
		<table class="employee_add">
			<tr>
				<th>�]�ƈ��R�[�h</th>
				<td><input type="text" name="emp_cd"></td>
			</tr>
			<tr>
				<th>����(����)</th>
				<td>��<input type="text" name="l_name"> ��<input type="text" name="f_name"></td>
				</tr>
				<tr>
				<th>����(�J�i)</th>
				<td>��<input type="text" name="l_kana_name"> ��<input type="text" name="f_kana_name"></td>
			</tr>
			<tr>
				<th>����</th>
				<td><input type="radio" name="sex" value="man">�j <input type="radio" name="sex" value="lady">��</td>
			</tr>
			<tr>
				<th>���N����</th>
				<td>  <select name="birth_year">
						<option>1994</option>	<option>1993</option>
					  </select>�N
					  <select name="birth_month">
							<option>1</option>	<option>2</option>	<option>3</option>	<option>4</option>
							<option>5</option>	<option>6</option>	<option>7</option>	<option>8</option>	<option>9</option>	<option>10</option>
							<option>11</option>	<option>12</option>	</select>��
					   <select name="birth_day">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
						</select>��</td>
				</tr>
				<tr>
					<th>��������</th>
					<td><select name="job">
							<option>�c�ƕ�</option>
							<option>�f�U�C����</option>
					</select></td>
				</tr>
				<tr>
					<th>���Г�</th>
					<td><select name="start_year">
							<option>1994</option>
							<option>1993</option>
					</select>�N <select name="start_month">
							<option>1</option>	<option>2</option>	<option>3</option>	<option>4</option>
							<option>5</option>	<option>6</option>	<option>7</option>	<option>8</option>	<option>9</option>	<option>10</option>
							<option>11</option>	<option>12</option>
					</select>�� <select name="start_day">
							<option>4</option>
							<option>3</option>
					</select>��</td>
				</tr>
			</table>
			<table>
				<%
					ArrayList<LicenseBean> licenseList = (ArrayList<LicenseBean>) request.getAttribute("licenseList");
				%>
				<tr>
					<th></th>
					<th>�ۗL���i</th>

					<%
						if (licenseList != null) {
							for (int i = 0; i < licenseList.size(); i++) {
								LicenseBean license = licenseList.get(i);
					%>

				</tr>
				<td><input type="checkbox" name="license" value="<%=i%>"></td>
				<td><%=license.getLicense_name()%></td>

				<%
					}
				%>
				<%
					}
				%>
				</tr>
			</table>
			<input type="submit" name="page"  value="�o�^" class="botan">
		</form>
	</div>
</body>
</html>