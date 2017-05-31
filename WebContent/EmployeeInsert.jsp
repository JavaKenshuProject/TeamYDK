<%@ page language="java" contentType="text/html; charset=Windows-31J"
    pageEncoding="Windows-31J"%>
<!DOCTYPE>
  <html>
   <head>
    <link rel="stylesheet" href="websystem.css" type="text/css">
    <meta charset="Shift-JIS">
    <title>従業員管理システム</title>
   </head>
   <body>
 <!------------------------------------------ヘッダー------------------------------>
  <%@ include file ="header.jsp" %>>
 <!-------------------------------------------------------------------------------->

     <div class="content">
      <h2 class="page_title">従業員登録</h2></div>
      <p>登録したい従業員のデータを入力してください。</p>
      <form>
        <table class="employee_add">
         <tr>
           <th>従業員コード</th>
           <td>　<input type="text" name="emp_cd"></td>
         </tr>
         <tr>
           <th>氏名(漢字)</th>
           <td>氏<input type="text" name="l_name">　名<input type="text" name="f_name"></td>
         </tr>
         <tr>
           <th>氏名(カナ)</th>
           <td>氏<input type="text" name="l_kana_name">　名<input type="text" name="f_kana_name"></td>
         </tr>
         <tr>
           <th>性別</th>
           <td><input type="radio" name="sex" value="man">男　<input type="radio" name="sex" value="lady">女</td>
         </tr>
         <tr>
           <th>生年月日</th>
           <td><select name="birth_year">
                <option>1994</option>
                <option>1993</option>
               </select>年　
               <select name="birth_month">
                <option>1</option>	<option>2</option>	<option>3</option>
                <option>4</option>	<option>5</option>	<option>6</option>
                <option>7</option>	<option>8</option>	<option>9</option>
                <option>10</option>	<option>11</option>	<option>12</option>
               </select>月　
               <select name="birth_day">
                <option>4</option>
                <option>3</option>
               </select>日　
            </td>
         </tr>
         <tr>
           <th>所属部署</th>
           <td><select name="job">
                <option>営業部</option>
                <option>デザイン部</option>
               </select>
           </td>
         </tr>
         <tr>
           <th>入社日</th>
           <td><select name="start_year">
                <option>1994</option>
                <option>1993</option>
               </select>年　
               <select name="start_month">
                <option>1</option>	<option>2</option>	<option>3</option>
                <option>4</option>	<option>5</option>	<option>6</option>
                <option>7</option>	<option>8</option>	<option>9</option>
                <option>10</option>	<option>11</option>	<option>12</option>
               </select>月　
               <select name="start_day">
                <option>4</option>
                <option>3</option>
               </select>日　
            </td>
         </tr>
         <tr>
           <th>保有資格</th>
           <td><input type="checkbox" name="license">基本情報<br>
               <input type="checkbox" name="license">ITパスポート
            </td>
         </tr>
        </table>
        <input type="submit" value="登録">
      </form>
   </body>
  </html>