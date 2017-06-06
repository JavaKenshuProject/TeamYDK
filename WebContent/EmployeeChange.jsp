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
<title>従業員管理システム</title>

</head>
<body>
	<!------------------------------------------ヘッダー------------------------------>
	<%@include file="header.jsp"%>
	<!-------------------------------------------------------------------------------->

	<div class="content">
		<h2 class="page_title">従業員情報変更</h2>
		<p>変更したい情報を入力してください。</p>
		<!------------------------------------従業員情報変更フォーム-------------------------------->
		<%
			ArrayList<SectionBean> sectionList = (ArrayList<SectionBean>) request.getAttribute("sectionList");
			EmployeeBean empB = (EmployeeBean) request.getAttribute("empB");
			int num = Integer.parseInt(request.getParameter("employee"));
		%>
		<form action="ChangeServlet" method="post">
			<table class="employee_add">
				<tr>
					<th>従業員コード</th>
					<td><%=empB.getEmpCode()%></td>
				<tr>
					<th>氏名(漢字)</th>
					<td>氏<input type="text" name="l_name" class="textbox"
						value="<%=empB.getLName()%>"> 名<input type="text"
						name="f_name" class="textbox" value="<%=empB.getFName()%>"></td>
				</tr>
				<tr>
					<th>氏名(フリガナ)</th>
					<td>氏<input type="text" name="l_kana_name"
						value="<%=empB.getLKanaName()%>"> 名<input type="text"
						name="f_kana_name" value="<%=empB.getFKanaName()%>"></td>
				</tr>
				<tr>
					<th>性別</th>
					<td>
						<%
							if (empB.getSex() == 0) {
						%> <input type="radio" name="sex" value="0" checked="checked">男
						<input type="radio" name="sex" value="1">女 <%
 	} else {
 %> <input type="radio" name="sex" value="0">男 <input
						type="radio" name="sex" value="1" checked="checked">女 <%
 	}
 %>
					</td>
				</tr>
				<tr>
					<th>所属部署</th>
					<td><select name="job">
							<%
								if (sectionList != null) {
									for (int i = 0; i < sectionList.size(); i++) {
										SectionBean section = sectionList.get(i);
							%>
							<%
								if (section.getSectionName().equals(empB.getSectionName())) {
							%>
							<option value="<%=section.getSectionCode()%>" selected="selected"><%=section.getSectionName()%></option>
							<%
								} else {
							%>
							<option value="<%=section.getSectionCode()%>"><%=section.getSectionName()%></option>
							<%
								}
							%>
							<%
								}
							%>
							<%
								} else {
									/* DO NOTHING */
								}
							%>
					</select></td>
				</tr>
			</table>
			<input type="hidden" name="hidden" value="<%=num%>"> <input
				type="submit" class="botan_change" value="変更確定" name="page">
		</form>
	</div>
</body>
</html>