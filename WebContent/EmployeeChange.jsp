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
		<h2 class="page_title">メニュー</h2>
		<p>変更したい情報を入力してください。</p>
		<!------------------------------------従業員情報変更フォーム-------------------------------->
		<h2 class="form_title">従業員情報変更フォーム</h2>
		<%
			ArrayList<SectionBean> sectionList = (ArrayList<SectionBean>) request.getAttribute("sectionList");
			EmployeeBean empB = (EmployeeBean) request.getAttribute("empB");
			int num = Integer.parseInt(request.getParameter("employee"));
		%>
		<form action="ChangeServlet" method="post">
			<table class="employee_add">
				<tr>
					<th>従業員コード</th>
					<td><%=empB.getEmp_code()%></td>
				<tr>
					<th>氏名(漢字)</th>
					<td>氏<input type="text" name="l_name" class="textbox">
						名<input type="text" name="f_name" class="textbox"></td>
				</tr>
				<tr>
					<th>氏名(カナ)</th>
					<td>氏<input type="text" name="l_kana_name">
						名<input type="text" name="f_kana_name"></td>
				</tr>
				<tr>
					<th>性別</th>
					<td><input type="radio" name="sex" value="0">男 <input
						type="radio" name="sex" value="1">女</td>
				</tr>
				<tr>
					<th>所属部署</th>
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
			<input type="submit"  class="botan_get"  value="変更確定" name="page">
		</form>
	</div>
</body>
</html>