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
	<!-- �w�b�_�[ -->
	<%@include file="header.jsp" %>
 <!-------------------------------------------------------------------------------->
	<div class="content">

 <!--------------------------------���O�C���t�H�[��-------------------------------->
        <div class="loginform">
         <div class="loginheader">
          <h2 class="logintitle">���O�C��</h2>
         </div>
          <form action="/LoginServlet" method="post">
            <table align="center">
              <tr>
               <td>
                <input type="text" value="���[�UID����͂��Ă�������" name="userID" class="dateform">
               </td>
              </tr>
              <tr>
               <td>
                <input type="password" value="�p�X���[�h����͂��Ă�������" name="password" class="dateform">
               </td>
              </tr>
               <tr>
                <td>
                <input type="submit" value="���O�C��" name="login" class="botan">
                </td>
               </tr>
            </table>
           </form>
         </div>
   <!-------------------------------------------------------------------------------->
     </div>
</body>
</html>