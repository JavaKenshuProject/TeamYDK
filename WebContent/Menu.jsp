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
    <ul class="botans">
      <li>
        <form action="ShowServlet" method="post">
         <input type="submit" value="�]�ƈ��ꗗ" name="page" class="menu_botan">
        </form>
      </li>
      <li>
      <form action="MenuServlet" method="post">
       <input type="submit" value="�]�ƈ��o�^" name="page" class="menu_botan">
      </form>
      </li>
    </ul>
   <h3 class="menu_title">�����i����ҏW����</h3>
      <form action="LicenseServlet" method="post">
       <input type="submit" value="���i�擾" name="page" class="menu_botan">
      </form>
      <form action="MenuServlet" method="post">
       <input type="submit" value="���i�ǉ�" name="page" class="menu_botan">
      </form>

   <h3 class="menu_title">���V�X�e�����p�ҏ���ҏW����</h3>
      <form action="MenuServlet" method="post">
       <input type="submit" value="���[�U�o�^" name="page" class="menu_botan">
      </form>

   <p>
    <form action="Logoutservlet" method="post">
     <input type="submit" value="���O�A�E�g" class="menu_botan">
     </form>
    </p>
 </div>
</body>
</html>