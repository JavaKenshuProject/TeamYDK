<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList"%>
<%@ page import="entity.EmployeeBean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<link rel="stylesheet" href="websystem.css" type="text/css">
<%@ include file = "header.jsp" %>
<title>従業員管理システム</title>
</head>
<body>
<!-------------------------------------------------------------------------------->
     <div class="content">
      <h2 class="page_title">資格取得</h2>

 <!--------------------------------従業員一覧の表-------------------------------->
      <p>保有資格を追加する従業員を選んでください。</p>
      <%
      ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>)request.getAttribute("employeeList");
      %>
      <form action="/LicenseServlet" method="post">
        <table class="employee_table">
          <tr class="employee_title">
           <th></th>
           <th>従業員コード</th>
           <th>氏名</th>
           <th>氏名(フリガナ)</th>
           <th>性別</th>
           <th>生年月日</th>
           <th>所属部署</th>
           <th>入社日</th>
           <th>保有資格</th>
          </tr>
          <% if(employeeList != null ){
        	  for(int i=0;i<employeeList.size();i++){
        		  EmployeeBean employee = employeeList.get(i);
        		  String color;
        		  if(i%2 == 0){
        			  color = "#dbffed";
        		  }else{
        			  color = "#fdfdfd";
        		  }
          %>
          <tr style="background-color:<%= color %>;">
           <td><input type="radio" name="radio"></td>
           <td><%= employee.getEmp_code() %></td>
           <td><%= employee.getL_name() %><%= employee.getF_name() %></td>
           <td><%= employee.getL_kana_name() %><%= employee.getF_kana_name() %></td>
           <td><%= employee.getSex() %></td>
           <td><%= employee.getBirth_day() %></td>
           <td><%= employee.getSection_name() %></td>
           <td><%= employee.getEmp_date() %></td>
           <td>基本情報</td>
          </tr>
          <% } %>
          <% } %>
       </table>
<!--------------------------------資格一覧の表-------------------------------->
      <p>保有資格に追加する資格を選んでください。</p>
      <table>
       <tr class="license_title">
        <th></th>
        <th>資格名</th>
       </tr>
       <tr style="background-color:#eeeeee;">
        <td><input type="radio" name="license"></td>
        <td>ITパスポート</td>
       </tr>
       <tr style="background-color:#fdfdfd;">
        <td><input type="radio" name="license"></td>
        <td>基本情報</td>
       </tr>
      </table>
      <input type="submit" value="取得">
      </form>
     </div>
</body>
</html>