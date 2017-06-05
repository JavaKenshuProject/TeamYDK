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
 * @version 1.30
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
	 * Windows-31Jでのバイト数をカウントします。 バイト数が0バイトの場合、falseを返します。
	 *
	 * @param string
	 * @param size
	 * @return boolean
	 */
	public static boolean checkZero31J(String string) {
		boolean flag;
		Charset charset = Charset.forName("Windows-31J");
		if (getByteLength(string, charset) != 0) {
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
	 *
	 * @param employee
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static void checkEmployeeBean(EmployeeBean employee, boolean flag, String call)
			throws ServletServiceException {
		String katakana = "^[\\u30A0-\\u30FF]+$";
		String alphaNumber = "^[0-9a-zA-Z]+$";
		java.util.Date uNow = new java.util.Date();
		java.sql.Date now = new java.sql.Date(uNow.getTime());

		// String call = "";
		String temp = "が不正です<br>";
		String voidC = "が入力可能文字数を超えています<br>";
		String zeroC = "を入力してください<br>";
		String kana = "をカタカナで記入してください<br>";

		if (employee.getEmpCode() == null) {
			call = call + "従業員コード" + temp;
		} else if (checkSize31J(employee.getEmpCode(), 4) == false) {
			call = call + "従業員コード" + voidC;
		} else if (checkZero31J(employee.getEmpCode()) == false) {
			call = call + "従業員コード" + zeroC;
		} else if ((employee.getEmpCode().matches(alphaNumber) == false)) {
			call = call + "従業員コードは半角英数字のみで入力してください<br>";
		}

		if (employee.getLName() == null) {
			call = call + "氏" + temp;
		} else if (checkSize31J(employee.getLName(), 16) == false) {
			call = call + "氏" + voidC;
		} else if (checkZero31J(employee.getLName()) == false) {
			call = call + "氏" + zeroC;
		}

		if (employee.getFName() == null) {
			call = call + "名" + temp;
		} else if (checkSize31J(employee.getFName(), 16) == false) {
			call = call + "名" + voidC;
		} else if (checkZero31J(employee.getFName()) == false) {
			call = call + "名" + zeroC;
		}

		if (employee.getLKanaName() == null) {
			call = call + "氏(カナ)" + temp;
		} else if (checkSize31J(employee.getLKanaName(), 24) == false) {
			call = call + "氏(カナ)" + voidC;
		} else if (checkZero31J(employee.getLKanaName()) == false) {
			call = call + "氏(カナ)" + zeroC;
		} else if (employee.getLKanaName().matches(katakana) == false) {
			call = call + "氏(カナ)" + kana;
		}

		if (employee.getFKanaName() == null) {
			call = call + "名(カナ)" + temp;
		} else if (checkSize31J(employee.getFKanaName(), 24) == false) {
			call = call + "名(カナ)" + voidC;
		} else if (checkZero31J(employee.getFKanaName()) == false) {
			call = call + "名(カナ)" + zeroC;
		} else if (employee.getFKanaName().matches(katakana) == false) {
			call = call + "名(カナ)" + kana;
		}

		if (!(employee.getSex() == 0 || employee.getSex() == 1)) {
			call = call + "性別" + temp;
		}

		if ((employee.getBirthDay() == null) || (checkDate(employee.getBirthDay()) == false)) {
			call = call + "生年月日" + temp;
		} else if (now.compareTo(convertString2Date(employee.getBirthDay())) < 0) {
			call = call + "生年月日-あなたはまだ生まれていません・・・！<br>";
		}

		if (employee.getSectionCode() == null) {
			call = call + "所属コード" + temp;
		} else if (checkSize31J(employee.getSectionCode(), 2) == false) {
			call = call + "所属コード" + voidC;
		} else if (checkZero31J(employee.getSectionCode()) == false) {
			call = call + "所属コード" + zeroC;
		}

		if ((employee.getEmpDate() == null) || (checkDate(employee.getEmpDate()) == false)) {
			call = call + "入社日" + temp;
		} else if (convertString2Date(employee.getEmpDate())
				.compareTo(convertString2Date(employee.getBirthDay())) < 0) {
			call = call + "入社日が生年月日より昔です<br>";
		}

		if (flag) {
			call = call + checkTGetLicense(employee);
		}

		if (!(call.equals(""))) {
			throw new ServletServiceException(call);
		}
	}

	/**
	 * EmployeeBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param employee
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static String checkTGetLicense(EmployeeBean employee) {
		String alphaNumber = "^[0-9a-zA-Z]+$";
		java.util.Date uNow = new java.util.Date();
		java.sql.Date now = new java.sql.Date(uNow.getTime());

		String call = "";
		String temp = "が不正です<br>";
		String voidC = "が入力可能文字数を超えています<br>";
		String zeroC = "を入力してください<br>";

		if (employee.getLicenseCdSQLinsert() == null) {
			call = call + "資格コード" + temp;
		} else if (checkSize31J(employee.getLicenseCdSQLinsert(), 5) == false) {
			call = call + "資格コード" + voidC;
		} else if (checkZero31J(employee.getLicenseCdSQLinsert()) == false) {
			call = call + "資格コード" + zeroC;
		} else if ((employee.getLicenseCdSQLinsert().matches(alphaNumber) == false)) {
			call = call + "資格コードは半角英数字のみで入力してください<br>";
		}

		if ((employee.getGetLicenseDateSQLinsert() == null)
				|| (checkDate(employee.getGetLicenseDateSQLinsert()) == false)) {
			call = call + "取得日" + temp;
		} else if (now.compareTo(convertString2Date(employee.getGetLicenseDateSQLinsert())) < 0) {
			call = call + "取得日が未来を指定しています<br>";
		} else if (convertString2Date(employee.getGetLicenseDateSQLinsert())
				.compareTo(convertString2Date(employee.getBirthDay())) < 0) {
			call = call + "取得日が生年月日より昔です<br>";
		}

		return call;
	}

	/**
	 * UserBeanの中身がDBのテーブル内と適合しているかチェックします
	 *
	 * @param user
	 * @return void or throw
	 * @throws ServletServiceException
	 */
	public static void checkUserBean(UserBean user, String call) throws ServletServiceException {
		String alphaNumber = "^[0-9a-zA-Z]+$";
		String temp = "が不正です\n";
		String voidC = "が入力可能文字数を超えています<br>";
		String zeroC = "を入力してください<br>";

		if (checkSize31J(user.getUserId(), 24) == false) {
			call = call + "ユーザID" + temp;
		} else if (checkSize31J(user.getUserId(), 24) == false) {
			call = call + "ユーザID" + voidC;
		} else if (checkZero31J(user.getUserId()) == false) {
			call = call + "ユーザID" + zeroC;
		} else if ((user.getUserId().matches(alphaNumber) == false)) {
			call = call + "ユーザIDは半角英数字のみで入力してください<br>";
		}

		if (user.getPassword() == null) {
			call = call + "パスワード" + temp;
		} else if (checkSize31J(user.getPassword(), 32) == false) {
			call = call + "パスワード" + voidC;
		} else if (checkZero31J(user.getPassword()) == false) {
			call = call + "パスワード" + zeroC;
		} else if ((user.getPassword().matches(alphaNumber) == false)) {
			call = call + "パスワードは半角英数字のみで入力してください<br>";
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
	public static void checkLicenseBean(LicenseBean license, String call) throws ServletServiceException {
		String alphaNumber = "^[0-9a-zA-Z]+$";
		String temp = "が不正です\n";
		String voidC = "が入力可能文字数を超えています<br>";
		String zeroC = "を入力してください<br>";

		if (license.getLicenseCd() == null) {
			call = call + "資格コード" + temp;
		} else if (checkSize31J(license.getLicenseCd(), 5) == false) {
			call = call + "資格コード" + voidC;
		} else if (checkZero31J(license.getLicenseCd()) == false) {
			call = call + "資格コード" + zeroC;
		} else if ((license.getLicenseCd().matches(alphaNumber) == false)) {
			call = call + "資格コードは半角英数字のみで入力してください<br>";
		}

		if (license.getLicenseName() == null) {
			call = call + "資格名" + temp;
		} else if (checkSize31J(license.getLicenseName(), 100) == false) {
			call = call + "資格名" + voidC;
		} else if (checkZero31J(license.getLicenseName()) == false) {
			call = call + "資格名" + zeroC;
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
	public static boolean checkPkEmpCode(EmployeeBean employee, ArrayList<EmployeeBean> emp_list)
			throws ServletServiceException {
		boolean flag = true;
		if (employee.getEmpCode() == null) {
			throw new ServletServiceException("従業員コードが不正です");
		}
		for (EmployeeBean all_emp : emp_list) {
			if (all_emp.getEmpCode().equals(employee.getEmpCode())) {
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
	public static boolean checkPkTGetLicense(EmployeeBean employee, ArrayList<EmployeeBean> emp_list) {
		boolean flag = true;
		for (EmployeeBean all_emp : emp_list) {
			for (String string : all_emp.getLicenseCd()) {
				if (string.equals(employee.getLicenseCdSQLinsert())
						&& all_emp.getEmpCode().equals(employee.getEmpCode())) {
					flag = false;
					// throw new ServletServiceException("すでに登録された資格です");
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
	public static boolean checkPkLicense(LicenseBean license, ArrayList<LicenseBean> license_list)
			throws ServletServiceException {
		boolean flag = true;
		if (license.getLicenseCd() == null) {
			throw new ServletServiceException("資格コードが不正です");
		}
		for (LicenseBean all_license : license_list) {
			if (all_license.getLicenseCd().equals(license.getLicenseCd())) {
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
	public static boolean checkPkUser(UserBean user, ArrayList<UserBean> user_list) throws ServletServiceException {
		boolean flag = true;
		if (user.getUserId() == null) {
			throw new ServletServiceException("ユーザIDが不正です");
		}
		for (UserBean all_user : user_list) {
			if (all_user.getUserId().equals(user.getUserId())) {
				flag = false;
				// throw new ServletServiceException("ユーザIDが重複しています");
			}
		}
		return flag;
	}

}
