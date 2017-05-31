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
<title>�]�ƈ��Ǘ��V�X�e��</title>
</head>
<body>
<!-------------------------------------------------------------------------------->
     <div class="content">
      <h2 class="page_title">���i�擾</h2>

 <!--------------------------------�]�ƈ��ꗗ�̕\-------------------------------->
      <p>�ۗL���i��ǉ�����]�ƈ���I��ł��������B</p>
      <%
      ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>)request.getAttribute("employeeList");
      %>
      <form action="/LicenseServlet" method="post">
        <table class="employee_table">
          <tr class="employee_title">
           <th></th>
           <th>�]�ƈ��R�[�h</th>
           <th>����</th>
           <th>����(�t���K�i)</th>
           <th>����</th>
           <th>���N����</th>
           <th>��������</th>
           <th>���Г�</th>
           <th>�ۗL���i</th>
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
           <td>��{���</td>
          </tr>
          <% } %>
          <% } %>
       </table>
<!--------------------------------���i�ꗗ�̕\-------------------------------->
      <p>�ۗL���i�ɒǉ����鎑�i��I��ł��������B</p>
      <table>
       <tr class="license_title">
        <th></th>
        <th>���i��</th>
       </tr>
       <tr style="background-color:#eeeeee;">
        <td><input type="radio" name="license"></td>
        <td>IT�p�X�|�[�g</td>
       </tr>
       <tr style="background-color:#fdfdfd;">
        <td><input type="radio" name="license"></td>
        <td>��{���</td>
       </tr>
      </table>
      <input type="submit" value="�擾">
      </form>
     </div>
</body>
</html>