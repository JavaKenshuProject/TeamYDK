<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�]�ƈ��Ǘ��V�X�e��</title>
 <link rel="stylesheet" href="websystem.css" type="text/css">
</head>
<body>

   <!------------------------------------------�w�b�_�[------------------------------>
     <%@include file="header.jsp"%>
 <!-------------------------------------------------------------------------------->
 <div class="content">
   <h2 class="page_title">���j���[</h2>

   <h3 class="menu_title">���]�ƈ�����ҏW����</h3>
    <table class="botan_box">
     <tr>
      <td>
        <form action="ShowServlet" method="post">
         <input type="submit" value="�]�ƈ��ꗗ" name="page" class="menu_botan">
        </form>
       </td>
       <td>
       <form action="EmployeeInsertServlet" method="post">
        <input type="submit" value="�]�ƈ��o�^" name="page" class="menu_botan">
       </form>
      </td>
     </tr>
    </table>
   <h3 class="menu_title">�����i����ҏW����</h3>
    <table class="botan_box">
      <tr>
       <td>
        <form action="LicenseServlet" method="post">
         <input type="submit" value="���i�擾" name="page" class="menu_botan">
        </form>
       </td>
       <td>
        <form action="LicenseInsertServlet" method="post">
         <input type="submit" value="���i�ǉ�" name="page" class="menu_botan">
        </form>
       </td>
      </tr>
    </table>

   <h3 class="menu_title">���V�X�e�����p�ҏ���ҏW����</h3>
      <form action="UserInsertServlet" method="post">
       <input type="submit" value="���[�U�o�^" name="page" class="menu_botan">
      </form>

    <form action="LogoutServlet" method="post">
     <input type="submit" value="���O�A�E�g" name="page" class="menu_botan" id="logout">
     </form>
 </div>
</body>
</html>