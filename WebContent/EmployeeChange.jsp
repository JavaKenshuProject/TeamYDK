<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="websystem.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=Windows-31J">
<title>�]�ƈ��Ǘ��V�X�e��</title>
</head>
<body>
 <!------------------------------------------�w�b�_�[------------------------------>
 <%@include file="header.jsp" %>
 <!-------------------------------------------------------------------------------->

 	<div class="content">
       <h2 class="page_title">���j���[</h2>
       <p>�ύX������������͂��Ă��������B</p>

  <!------------------------------------�]�ƈ����ύX�t�H�[��-------------------------------->
      <h2 class="form_title">�]�ƈ����ύX�t�H�[��</h2>
      <form action="/ChangeServlet" method="post">
        <table class="employee_add">
         <tr>
           <th>����(����)</th>
           <td>��<input type="text" name="I_name" class="textbox">�@��<input type="text" name="f_name" class="textbox"></td>
         </tr>
         <tr>
           <th>����(�J�i)</th>
           <td>��<input type="text" name="I_kana_name">�@��<input type="text" name="f_kana_name"></td>
         </tr>
         <tr>
           <th>����</th>
           <td><input type="radio" name="sex" value="man">�j�@<input type="radio" name="sex" value="lady">��</td>
         </tr>
         <tr>
           <th>��������</th>
           <td><select name="job">
                <option>�c�ƕ�</option>
                <option>�f�U�C����</option>
               </select>
           </td>
         </tr>
        </table>
        <input type="submit" value="�ύX">
      </form>
      </div>

</body>
</html>