<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.SectionBean"%>
<%@ page import="entity.EmployeeBean"%>
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�]�ƈ��Ǘ��V�X�e��</title>

</head>
<body>
	<!------------------------------------------�w�b�_�[------------------------------>
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->

	<div class="content">
		<h2 class="page_title">���j���[</h2>
		<p>�ύX������������͂��Ă��������B</p>
		<!------------------------------------�]�ƈ����ύX�t�H�[��-------------------------------->
		<h2 class="form_title">�]�ƈ����ύX�t�H�[��</h2>
		<%
			ArrayList<SectionBean> sectionList = (ArrayList<SectionBean>) request.getAttribute("sectionList");
			EmployeeBean empB = (EmployeeBean) request.getAttribute("empB");
			int num = Integer.parseInt(request.getParameter("employee"));
		%>
		<form action="ChangeServlet" method="post">
			<table class="employee_add">
				<tr>
					<th>�]�ƈ��R�[�h</th>
					<td><%=empB.getEmp_code()%></td>
				<tr>
					<th>����(����)</th>
					<td>��<input type="text" name="l_name" class="textbox">
						��<input type="text" name="f_name" class="textbox"></td>
				</tr>
				<tr>
					<th>����(�J�i)</th>
					<td>��<input type="text" name="l_kana_name">
						��<input type="text" name="f_kana_name"></td>
				</tr>
				<tr>
					<th>����</th>
					<td><input type="radio" name="sex" value="0">�j <input
						type="radio" name="sex" value="1">��</td>
				</tr>
				<tr>
					<th>��������</th>
					<td><select name="job">
							<%
								if (sectionList != null) {
									for (int i = 0; i < sectionList.size(); i++) {
										SectionBean section = sectionList.get(i);
							%>
							<option><%=section.getSection_name()%></option>
							<%
								}
							%>
							<%
								}
							%>
					</select></td>
				</tr>
			</table>
			<input type="hidden" name="hidden" value="<%= num %>">
			<input type="submit"  class="botan_get"  value="�ύX�m��" name="page">
		</form>
	</div>
</body>
</html>