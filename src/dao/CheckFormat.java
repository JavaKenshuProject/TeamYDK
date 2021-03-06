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
 * TeamB-YDK CheckFormat.java
 *
 * Copyright(C) 2017 TeamB-YDK All Rights Reserved.
 *
 * データがデータベースに適合する形であるかチェックし、変換もしくはExceptionをスローします。
 *
 * @author KIKUCHI
 * @version 1.30
 */
public class CheckFormat {

	/**
	 * Date型の定数です
	 */
	static public final String DATE_PATTERN = "yyyy-MM-dd";
	// String DATE_PATTERN ="yyyy/MM/dd HH:mm:ss";

	public static final String KATAKANA = "^[\\u30A0-\\u30FF]+$";
	public static final String ALPHA_NUMBER = "^[0-9a-zA-Z]+$";

	public static final String TEMP = "が不正です<br>";
	public static final String VOID_C = "が入力可能文字数を超えています<br>";
	public static final String ZERO_C = "を入力してください<br>";
	public static final String KANA = "をカタカナ(全角)で入力してください<br>";

	/**
	 * 指定された文字エンコーディングでのバイト数をintで返します
	 *
	 * @param string
	 *            バイト数を得たい文字列
	 * @param charset
	 *            文字列の文字円コーディングの種類
	 * @return int バイト数
	 */
	public static int getByteLength(String string, Charset charset) {
		return string.getBytes(charset).length;
	}

	/**
	 * 指定された文字エンコーディングでのバイト数をカウントします。 バイト数が引数sizeを超えていた場合、falseを返します。
	 *
	 * @param string
	 *            バイト数を得たい文字列
	 * @param charset
	 *            文字列の文字円コーディングの種類
	 * @param size
	 *            このサイズ以下か
	 * @return boolean 照合結果
	 */
	public static boolean isCheckSize(String string, Charset charset, int size) {
		boolean hasFlag;
		if (getByteLength(string, charset) <= size) {
			hasFlag = true;
		} else {
			hasFlag = false;
		}
		return hasFlag;
	}

	/**
	 * Windows-31Jでのバイト数をカウントします。 バイト数が引数sizeを超えていた場合、falseを返します。
	 *
	 * @param string
	 *            バイト数を得たい文字列
	 * @param size
	 *            このサイズ以下か
	 * @return boolean 照合結果
	 */
	public static boolean isCheckSize31J(String string, int size) {
		boolean hasFlag;
		Charset charset = Charset.forName("Windows-31J");
		if (getByteLength(string, charset) <= size) {
			hasFlag = true;
		} else {
			hasFlag = false;
		}
		return hasFlag;
	}

	/**
	 * Windows-31Jでのバイト数をカウントします。 バイト数が0バイトの場合、falseを返します。
	 *
	 * @param string
	 *            バイト数を得たい文字列
	 * @return boolean 照合結果
	 */
	public static boolean isCheckZero31J(String string) {
		boolean hasFlag;
		Charset charset = Charset.forName("Windows-31J");
		if (getByteLength(string, charset) != 0) {
			hasFlag = true;
		} else {
			hasFlag = false;
		}
		return hasFlag;
	}

	/**
	 * 文字列型のDate型としての妥当性を精査します
	 *
	 * @param date
	 *            妥当性の検査をしたいデータ型
	 * @return boolean 照合結果
	 */
	public static boolean isCheckDate(String date) {
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
	 *            変換したいデータ型
	 * @return String 変換後の文字列型
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
	 *            変換したい文字列型
	 * @return Date 変換後のデータ型
	 */
	public static java.sql.Date convertString2Date(String source) {
		if ((source != null) && (isCheckDate(source))) {
			Date date = Date.valueOf(source);
			return date;
		} else {
			return null;
		}
	}

	/**
	 * EmployeeBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param employee,hasFlag,call
	 *            照合したい従業員情報, 資格情報も含めたチェックをするか,スロー文に含めたい文字列
	 * @return void
	 * @throws ServletServiceException
	 */
	public static void checkEmployeeBean(EmployeeBean employee, boolean hasFlag, String call)
			throws ServletServiceException {
		java.util.Date uNow = new java.util.Date();
		java.sql.Date now = new java.sql.Date(uNow.getTime());

		if (employee.getEmpCode() == null) {
			call = call + "従業員コード" + TEMP;
		} else if (isCheckSize31J(employee.getEmpCode(), 4) == false) {
			call = call + "従業員コード" + VOID_C;
		} else if (isCheckZero31J(employee.getEmpCode()) == false) {
			call = call + "従業員コード" + ZERO_C;
		} else if ((employee.getEmpCode().matches(ALPHA_NUMBER) == false)) {
			call = call + "従業員コードは半角英数字のみで入力してください<br>";
		} else {
			/* DO NOTHING */
		}

		if (employee.getLName() == null) {
			call = call + "氏" + TEMP;
		} else if (isCheckSize31J(employee.getLName(), 16) == false) {
			call = call + "氏" + VOID_C;
		} else if (isCheckZero31J(employee.getLName()) == false) {
			call = call + "氏" + ZERO_C;
		} else {
			/* DO NOTHING */
		}

		if (employee.getFName() == null) {
			call = call + "名" + TEMP;
		} else if (isCheckSize31J(employee.getFName(), 16) == false) {
			call = call + "名" + VOID_C;
		} else if (isCheckZero31J(employee.getFName()) == false) {
			call = call + "名" + ZERO_C;
		} else {
			/* DO NOTHING */
		}

		if (employee.getLKanaName() == null) {
			call = call + "氏(フリガナ)" + TEMP;
		} else if (isCheckSize31J(employee.getLKanaName(), 24) == false) {
			call = call + "氏(フリガナ)" + VOID_C;
		} else if (isCheckZero31J(employee.getLKanaName()) == false) {
			call = call + "氏(フリガナ)" + ZERO_C;
		} else if (employee.getLKanaName().matches(KATAKANA) == false) {
			call = call + "氏(フリガナ)" + KANA;
		} else {
			/* DO NOTHING */
		}

		if (employee.getFKanaName() == null) {
			call = call + "名(フリガナ)" + TEMP;
		} else if (isCheckSize31J(employee.getFKanaName(), 24) == false) {
			call = call + "名(フリガナ)" + VOID_C;
		} else if (isCheckZero31J(employee.getFKanaName()) == false) {
			call = call + "名(フリガナ)" + ZERO_C;
		} else if (employee.getFKanaName().matches(KATAKANA) == false) {
			call = call + "名(フリガナ)" + KANA;
		} else {
			/* DO NOTHING */
		}

		if (!(employee.getSex() == 0 || employee.getSex() == 1)) {
			call = call + "性別" + TEMP;
		} else {
			/* DO NOTHING */
		}

		if (employee.getBirthDay() == null) {
			call = call + "生年月日" + TEMP;
		} else if (convertString2Date(employee.getBirthDay()) == null) {
			call = call + "生年月日が存在しない日時です<br>";
		} else if (now.compareTo(convertString2Date(employee.getBirthDay())) < 0) {
			call = call + "生年月日-あなたはまだ生まれていません・・・！<br>";
		} else {
			/* DO NOTHING */
		}

		if (employee.getSectionCode() == null) {
			call = call + "所属コード" + TEMP;
		} else if (isCheckSize31J(employee.getSectionCode(), 2) == false) {
			call = call + "所属コード" + VOID_C;
		} else if (isCheckZero31J(employee.getSectionCode()) == false) {
			call = call + "所属コード" + ZERO_C;
		} else {
			/* DO NOTHING */
		}

		if (employee.getEmpDate() == null) {
			call = call + "入社日" + TEMP;
		} else if (convertString2Date(employee.getEmpDate()) == null) {
			call = call + "入社日が存在しない日時です<br>";
		} else if ((convertString2Date(employee.getBirthDay()) != null) && (convertString2Date(employee.getEmpDate())
				.compareTo(convertString2Date(employee.getBirthDay()))) < 0) {
			call = call + "入社日が生年月日より昔です<br>";
		} else {
			/* DO NOTHING */
		}

		if (hasFlag) {
			call = call + checkTGetLicense(employee);
		} else {
			/* DO NOTHING */
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		} else {
			/* DO NOTHING */
		}
	}

	/**
	 * EmployeeBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param employee
	 *            照合したい従業員情報
	 * @return String エラーが発生した場合にエラー文を返す
	 */
	public static String checkTGetLicense(EmployeeBean employee) {
		java.util.Date uNow = new java.util.Date();
		java.sql.Date now = new java.sql.Date(uNow.getTime());

		String call = "";

		if (employee.getLicenseCdSQLinsert() == null) {
			call = call + "資格コード" + TEMP;
		} else if (isCheckSize31J(employee.getLicenseCdSQLinsert(), 5) == false) {
			call = call + "資格コード" + VOID_C;
		} else if (isCheckZero31J(employee.getLicenseCdSQLinsert()) == false) {
			call = call + "資格コード" + ZERO_C;
		} else if ((employee.getLicenseCdSQLinsert().matches(ALPHA_NUMBER) == false)) {
			call = call + "資格コードは半角英数字のみで入力してください<br>";
		} else {
			/* DO NOTHING */
		}

		if (employee.getGetLicenseDateSQLinsert() == null) {
			call = call + "取得日" + TEMP;
		} else if (convertString2Date(employee.getGetLicenseDateSQLinsert()) == null) {
			call = call + "取得日が存在しない日時です<br>";
		} else if (now.compareTo(convertString2Date(employee.getGetLicenseDateSQLinsert())) < 0) {
			call = call + "取得日が未来を指定しています<br>";
		} else if ((convertString2Date(employee.getBirthDay()) != null)
				&& (convertString2Date(employee.getGetLicenseDateSQLinsert())
						.compareTo(convertString2Date(employee.getBirthDay())) < 0)) {
			call = call + "取得日が生年月日より昔です<br>";
		} else {
			/* DO NOTHING */
		}

		return call;
	}

	/**
	 * UserBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param user
	 *            照合したいユーザー情報
	 * @return void
	 * @throws ServletServiceException
	 */
	public static void checkUserBean(UserBean user, String call) throws ServletServiceException {

		if (user.getUserId() == null) {
			call = call + "ユーザID" + TEMP;
		} else if (isCheckSize31J(user.getUserId(), 24) == false) {
			call = call + "ユーザID" + VOID_C;
		} else if (isCheckZero31J(user.getUserId()) == false) {
			call = call + "ユーザID" + ZERO_C;
		} else if ((user.getUserId().matches(ALPHA_NUMBER) == false)) {
			call = call + "ユーザIDは半角英数字のみで入力してください<br>";
		} else {
			/* DO NOTHING */
		}

		if (user.getPassword() == null) {
			call = call + "パスワード" + TEMP;
		} else if (isCheckSize31J(user.getPassword(), 32) == false) {
			call = call + "パスワード" + VOID_C;
		} else if (isCheckZero31J(user.getPassword()) == false) {
			call = call + "パスワード" + ZERO_C;
		} else if ((user.getPassword().matches(ALPHA_NUMBER) == false)) {
			call = call + "パスワードは半角英数字のみで入力してください<br>";
		} else {
			/* DO NOTHING */
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		} else {
			/* DO NOTHING */
		}
	}

	/**
	 * LicenseBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param license
	 *            照合したい資格情報
	 * @return void
	 * @throws ServletServiceException
	 */
	public static void checkLicenseBean(LicenseBean license, String call) throws ServletServiceException {

		if (license.getLicenseCd() == null) {
			call = call + "資格コード" + TEMP;
		} else if (isCheckSize31J(license.getLicenseCd(), 5) == false) {
			call = call + "資格コード" + VOID_C;
		} else if (isCheckZero31J(license.getLicenseCd()) == false) {
			call = call + "資格コード" + ZERO_C;
		} else if ((license.getLicenseCd().matches(ALPHA_NUMBER) == false)) {
			call = call + "資格コードは半角英数字のみで入力してください<br>";
		} else {
			/* DO NOTHING */
		}

		if (license.getLicenseName() == null) {
			call = call + "資格名" + TEMP;
		} else if (isCheckSize31J(license.getLicenseName(), 100) == false) {
			call = call + "資格名" + VOID_C;
		} else if (isCheckZero31J(license.getLicenseName()) == false) {
			call = call + "資格名" + ZERO_C;
		} else {
			/* DO NOTHING */
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		} else {
			/* DO NOTHING */
		}
	}

	/**
	 * emp_Codeの重複を検査します (employee(登録する従業員情報),ArrayList(全従業員情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param employee,emp_list
	 *            主キーをチェックしたい従業員情報,全従業員情報のArrayList
	 * @return boolean 主キーの重複の有無
	 * @throws ServletServiceException
	 */
	public static boolean isCheckPkEmpCode(EmployeeBean employee, ArrayList<EmployeeBean> emp_list)
			throws ServletServiceException {
		boolean hasFlag = true;
		if (employee.getEmpCode() == null) {
			throw new ServletServiceException("従業員コードが不正です");
		} else {
			/* DO NOTHING */
		}
		for (EmployeeBean all_emp : emp_list) {
			if (all_emp.getEmpCode().equals(employee.getEmpCode())) {
				hasFlag = false;
				// throw new ServletServiceException("従業員コードが重複しています");
			} else {
				/* DO NOTHING */
			}
		}
		return hasFlag;
	}

	/**
	 * emp_Codeとlicense_cdの重複を検査します (employee(登録する従業員情報),ArrayList(全従業員情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param employee,emp_list
	 *            主キーをチェックしたい従業員情報,全従業員情報のArrayList
	 * @return boolean 主キーの重複の有無
	 * @throws ServletServiceException
	 */
	public static boolean isCheckPkTGetLicense(EmployeeBean employee, ArrayList<EmployeeBean> emp_list) {
		boolean hasFlag = true;
		for (EmployeeBean all_emp : emp_list) {
			for (String string : all_emp.getLicenseCd()) {
				if (string.equals(employee.getLicenseCdSQLinsert())
						&& all_emp.getEmpCode().equals(employee.getEmpCode())) {
					hasFlag = false;
					// throw new ServletServiceException("すでに登録された資格です");
				} else {
					/* DO NOTHING */
				}
			}

		}
		return hasFlag;
	}

	/**
	 * license_cdの重複を検査します (license(登録する資格情報),ArrayList(全資格情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param license,license_list
	 *            主キーをチェックしたい資格情報,全資格情報のArrayList
	 * @return boolean 主キーの重複の有無
	 * @throws ServletServiceException
	 */
	public static boolean isCheckPkLicense(LicenseBean license, ArrayList<LicenseBean> license_list)
			throws ServletServiceException {
		boolean hasFlag = true;
		if (license.getLicenseCd() == null) {
			throw new ServletServiceException("資格コードが不正です");
		} else {
			/* DO NOTHING */
		}
		for (LicenseBean all_license : license_list) {
			if (all_license.getLicenseCd().equals(license.getLicenseCd())) {
				hasFlag = false;
				// throw new ServletServiceException("資格コードが重複しています");
			} else {
				/* DO NOTHING */
			}
		}
		return hasFlag;
	}

	/**
	 * user_idの重複を検査します (user(登録するユーザー情報),ArrayList(全ユーザー情報))
	 * 重複していた場合はfalseを返します
	 *
	 * @param user,user_list
	 *            主キーをチェックしたいユーザー情報,全ユーザー情報のArrayList
	 * @return boolean 主キーの重複の有無
	 * @throws ServletServiceException
	 */
	public static boolean isCheckPkUser(UserBean user, ArrayList<UserBean> user_list) throws ServletServiceException {
		boolean hasFlag = true;
		if (user.getUserId() == null) {
			throw new ServletServiceException("ユーザIDが不正です");
		} else {
			/* DO NOTHING */
		}
		for (UserBean all_user : user_list) {
			if (all_user.getUserId().equals(user.getUserId())) {
				hasFlag = false;
				// throw new ServletServiceException("ユーザIDが重複しています");
			} else {
				/* DO NOTHING */
			}
		}
		return hasFlag;
	}

}
