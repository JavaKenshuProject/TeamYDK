<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE>
  <html>
   <head>
    <link rel="stylesheet" href="websystem.css" type="text/css">
    <meta charset="Shift-JIS">
    <title>�]�ƈ��Ǘ��V�X�e��</title>
   </head>
   <body>
 <!------------------------------------------�w�b�_�[------------------------------>
  <%@ include file ="header.jsp" %>>
 <!-------------------------------------------------------------------------------->

     <div class="content">
      <h2 class="page_title">�]�ƈ��o�^</h2></div>
      <p>�o�^�������]�ƈ��̃f�[�^����͂��Ă��������B</p>
      <form>
        <table class="employee_add">
         <tr>
           <th>�]�ƈ��R�[�h</th>
           <td>�@<input type="text" name="emp_cd"></td>
         </tr>
         <tr>
           <th>����(����)</th>
           <td>��<input type="text" name="l_name">�@��<input type="text" name="f_name"></td>
         </tr>
         <tr>
           <th>����(�J�i)</th>
           <td>��<input type="text" name="l_kana_name">�@��<input type="text" name="f_kana_name"></td>
         </tr>
         <tr>
           <th>����</th>
           <td><input type="radio" name="sex" value="man">�j�@<input type="radio" name="sex" value="lady">��</td>
         </tr>
         <tr>
           <th>���N����</th>
           <td><select name="birth_year">
                <option>1994</option>
                <option>1993</option>
               </select>�N�@
               <select name="birth_month">
                <option>4</option>
                <option>3</option>
               </select>���@
               <select name="birth_day">
                <option>4</option>
                <option>3</option>
               </select>���@
            </td>
         </tr>
         <tr>
           <th>��������</th>
           <td><select name="job">
                <option>�c�ƕ�</option>
                <option>�f�U�C����</option>
               </select>
           </td>
         </tr>
         <tr>
           <th>���Г�</th>
           <td><select name="start_year">
                <option>1994</option>
                <option>1993</option>
               </select>�N�@
               <select name="start_month">
                <option>4</option>
                <option>3</option>
               </select>���@
               <select name="start_day">
                <option>4</option>
                <option>3</option>
               </select>���@
            </td>
         </tr>
         <tr>
           <th>�ۗL���i</th>
           <td><input type="checkbox" name="license">��{���<br>
               <input type="checkbox" name="license">IT�p�X�|�[�g
            </td>
         </tr>
        </table>
        <input type="submit" value="�o�^">
      </form>
   </body>
  </html>