package dao;

import java.nio.charset.Charset;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entity.EmployeeBean;
import entity.LicenseBean;
import entity.UserBean;
import exception.ServletServiceException;

/**
 *
 * @author KIKUCHI
 * @version 1.01
 */
public class CheckFormat {

	/**
	 * 指定された文字エンコーディングでのバイト数をintで返します
	 *
	 * @param string
	 * @param charset
	 * @return int
	 */
	public static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}

	/**
	 * 指定された文字エンコーディングでのバイト数をカウントします。 バイト数が引数sizeを超えていた場合、falseを返します。
	 *
	 * @param string
	 * @param charset
	 * @param size
	 * @return boolean
	 */
	public static boolean checkSize(String string, Charset charset, int size) {
		boolean flag;
		if (getByteLength(string, charset) <= size) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * Windows-31Jでのバイト数をカウントします。 バイト数が引数sizeを超えていた場合、falseを返します。
	 *
	 * @param string
	 * @param size
	 * @return boolean
	 */
	public static boolean checkSize31J(String string, int size) {
		boolean flag;
		Charset charset = Charset.forName("Windows-31J");
		if (getByteLength(string, charset) <= size) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * Date型の定数です
	 */
	static public final String DATE_PATTERN = "yyyy-MM-dd";
	// String DATE_PATTERN ="yyyy/MM/dd HH:mm:ss";

	/**
	 * 文字列型のDate型としての妥当性を精査します
	 *
	 * @param date
	 * @return boolean
	 */
	public static boolean checkDate(String date) {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN);
		df.setLenient(false);
		try {
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Date型のオブジェクトをString型に変換します
	 *
	 * @param date
	 * @return
	 */
	public static String convertDate2String(Date date) {
		String str;
		if (date == null) {
			str = null;
		} else {
			str = new SimpleDateFormat(DATE_PATTERN).format(date);
		}
		return str;
	}

	/**
	 * String型のオブジェクトをDate型に変換します---変換前にcheckDateによる妥当性の精査が行われます
	 * ---変換不可能な場合はnullを返します
	 *
	 * @param source
	 * @return Date
	 */
	public static java.sql.Date convertString2Date(String source) {
		if ((source != null) && (checkDate(source))) {
			Date date = Date.valueOf(source);
			return date;
		} else {
			return null;
		}
	}

	/**
	 * EmployeeBeanの中身がDBのテーブル内と適合しているかチェックします
	 * NULLcheck及びDATA型など検証が足りない部分あり!!!要修正!!!
	 *
	 * @param employee
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static void checkEmployeeBean(EmployeeBean employee) throws ServletServiceException {
		String KATAKANA = "^[\\u30A0-\\u30FF]+$";

		String call = "";
		String temp = "が不正です<br>";

		if ((employee.getEmp_code() == null) || (checkSize31J(employee.getEmp_code(), 4) == false)) {
			call = call + "従業員コード" + temp;
		}

		if ((employee.getL_name() == null) || (checkSize31J(employee.getL_name(), 16) == false)) {
			call = call + "氏" + temp;
		}

		if ((employee.getF_name() == null) || (checkSize31J(employee.getF_name(), 16) == false)) {
			call = call + "名" + temp;
		}

		if ((employee.getL_kana_name() == null) || (checkSize31J(employee.getL_kana_name(), 24)) == false
				|| employee.getL_kana_name().matches(KATAKANA) == false) {
			call = call + "氏(カナ)" + temp;
		}

		if ((employee.getF_kana_name() == null) || (checkSize31J(employee.getF_kana_name(), 24)) == false
				|| employee.getF_kana_name().matches(KATAKANA) == false) {
			call = call + "名(カナ)" + temp;
		}

		if (!(employee.getSex() == 0 || employee.getSex() == 1)) {
			call = call + "性別" + temp;
		}

		if ((employee.getBirth_day() == null) || (checkDate(employee.getBirth_day()) == false)) {
			call = call + "生年月日" + temp;
		}

		if ((employee.getSection_code() == null) || (checkSize31J(employee.getSection_code(), 2) == false)) {
			call = call + "所属コード" + temp;
		}

		if ((employee.getEmp_date() == null) || (checkDate(employee.getEmp_date()) == false)) {
			call = call + "入社日" + temp;
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		}
	}

	/**
	 * UserBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param user
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static void checkUserBean(UserBean user) throws ServletServiceException {
		String call = "";
		String temp = "が不正です\n";

		if ((user.getUser_id() == null) || (checkSize31J(user.getUser_id(), 24) == false)) {
			call = call + "ユーザID" + temp;
		}

		if ((user.getPassword() == null) || (checkSize31J(user.getPassword(), 32) == false)) {
			call = call + "パスワード" + temp;
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		}
	}

	/**
	 * LicenseBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param license
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static void checkLicenseBean(LicenseBean license) throws ServletServiceException {
		String call = "";
		String temp = "が不正です\n";

		if ((license.getLicense_cd() == null) || (checkSize31J(license.getLicense_cd(), 5) == false)) {
			call = call + "資格コード" + temp;
		}

		if ((license.getLicense_name() == null) || (checkSize31J(license.getLicense_name(), 100) == false)) {
			call = call + "資格名" + temp;
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		}
	}

	/**
	 * emp_Codeの重複を検査します (employee(登録する従業員情報),ArrayList(全従業員情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param employee
	 * @throws ServletServiceException
	 */
	public static boolean checkPK_empCode(EmployeeBean employee, ArrayList<EmployeeBean> emp_list) throws ServletServiceException {
		boolean flag = true;
		if (employee.getEmp_code() == null){
			throw new ServletServiceException("従業員コードが不正です");
		}
		for (EmployeeBean all_emp : emp_list) {
			if (all_emp.getEmp_code().equals(employee.getEmp_code())) {
				flag = false;
				;
				// throw new ServletServiceException("従業員コードが重複しています");
			}
		}
		return flag;
	}

	/**
	 * emp_Codeとlicense_cdの重複を検査します (employee(登録する従業員情報),ArrayList(全従業員情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param employee
	 * @throws ServletServiceException
	 */
	public static boolean checkPK_t_get_license(EmployeeBean employee, ArrayList<EmployeeBean> emp_list){
		boolean flag = true;
		for (EmployeeBean all_emp : emp_list) {
			for (String string : all_emp.getLicense_cd()) {
				for (String string2 : all_emp.getLicense_name()) {
					if (string.equals(employee.getLicense_cd_SQLinsert())
							&& string2.equals(employee.getLicense_name())) {
						flag = false;
						// throw new ServletServiceException("すでに登録された資格です");
					}
				}
			}

		}
		return flag;
	}

	/**
	 * license_cdの重複を検査します (license(登録する従業員情報),ArrayList(全従業員情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param license
	 * @throws ServletServiceException
	 */
	public static boolean checkPK_license(LicenseBean license, ArrayList<LicenseBean> license_list) throws ServletServiceException {
		boolean flag = true;
		if (license.getLicense_cd() == null){
			throw new ServletServiceException("資格コードが不正です");
		}
		for (LicenseBean all_license : license_list) {
			if (all_license.getLicense_cd().equals(license.getLicense_cd())) {
				flag = false;
				// throw new ServletServiceException("資格コードが重複しています");
			}
		}
		return flag;
	}

	/**
	 * user_idの重複を検査します (user(登録する従業員情報),ArrayList(全従業員情報)) 重複していた場合はfalseを返します
	 *
	 * @param user
	 * @throws ServletServiceException
	 */
	public static boolean checkPK_user(UserBean user, ArrayList<UserBean> user_list) throws ServletServiceException {
		boolean flag = true;
		if (user.getUser_id() == null){
			throw new ServletServiceException("ユーザIDが不正です");
		}
		for (UserBean all_user : user_list) {
			if (all_user.getUser_id().equals(user.getUser_id())) {
				flag = false;
				// throw new ServletServiceException("ユーザIDが重複しています");
			}
		}
		return flag;
	}

}
