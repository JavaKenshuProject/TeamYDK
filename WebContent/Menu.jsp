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
   <form action="/Loginservlet" method="post">
   <h3 class="menu_title">���]�ƈ�����ҏW����</h3>
   <p>
    <input type="submit" value="�]�ƈ��ꗗ" class="menu_botan">
    <input type="submit" value="�]�ƈ��o�^" class="menu_botan">
   </p>
   <h3 class="menu_title">�����i����ҏW����</h3>
   <p>
    <input type="submit" value="���i�擾" class="menu_botan">
    <input type="submit" value="���i�ǉ�" class="menu_botan">
   </p>
   <h3 class="menu_title">���V�X�e�����p�ҏ���ҏW����</h3>
    <p>
     <input type="submit" value="���[�U�o�^" class="menu_botan">
    </p>
    <p>
     <input type="submit" value="���O�A�E�g" class="menu_botan">
    </p>
   </form>
 </div>
</body>
</html>