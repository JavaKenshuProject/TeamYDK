<%@ page language="java" contentType="text/html; charset=Windows-31J"
	pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>従業員管理システム</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<!-------------------------------------------------------------------------------->
	<div class="content">
		<h2 class="page_title">資格追加</h2>
		<p>新しい資格の情報を入力してください。</p>

		<!------------------------------------資格追加登録フォーム-------------------------------->
		<form action="LicenseInsertServlet" method="post">
			<table class="license_add">
				<tr>
					<th>資格コード</th>
					<td><input type="text" name="license_cd"
						placeholder="半角英数字5文字"></td>
				</tr>
				<tr>
					<th>資格名</th>
					<td><input type="text" name="license_name"
						placeholder="例) ITパスポート"></td>
				</tr>
			</table>
			<input type="submit" value="追加" name="page" class="botan_get">
		</form>
	</div>
</body>
</html>