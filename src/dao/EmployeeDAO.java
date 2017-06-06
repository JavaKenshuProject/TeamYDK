package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.EmployeeBean;
import exception.ServletServiceException;

/**
 * TeamB-YDK EmployeeDAO.java
 *
 * Copyright(C) 2017 TeamB-YDK All Rights Reserved.
 *
 * 従業員情報にかかわるデータベースとのやり取りを行うクラスです
 *
 * @author KIKUCHI
 * @version 1.35
 */
public class EmployeeDAO {

	/**
	 * employeeAllGetから呼ばれるメソッドです
	 *
	 * @param con
	 *            データベースのConnnection
	 * @param employee
	 *            setしたいEmployeeBean
	 * @return EmployeeBean setしたEmployeeBean
	 * @throws SQLException
	 */
	private EmployeeBean mSectionGet(Connection con, EmployeeBean employee) throws SQLException {
		String sectionSql = "SELECT * FROM m_section WHERE section_code = ?";

		try (PreparedStatement sectionPstmt = con.prepareStatement(sectionSql);) {

			sectionPstmt.setString(1, employee.getSectionCode());
			try (ResultSet section_res = sectionPstmt.executeQuery();) {
				if (section_res.next()) {
					// m_section
					employee.setSectionName(section_res.getString("section_name"));
					employee.setSectionUpdateDate(section_res.getTimestamp("update_date"));
				} else {
					/* DO NOTHING */
				}
			} catch (SQLException e) {
				throw e;
			}
		} catch (SQLException e) {
			throw e;
		}
		return employee;

	}

	/**
	 * employeeAllGetから呼ばれるメソッドです
	 *
	 * @param con
	 *            データベースのConnnection
	 * @param employee
	 *            setしたいEmployeeBean
	 * @return EmployeeBean setしたEmployeeBean
	 * @throws SQLException
	 */
	private EmployeeBean tGetLicenseGet(Connection con, EmployeeBean employee) throws SQLException {
		String getLicenseSql = "SELECT * FROM t_get_license WHERE emp_code = ?";
		try (PreparedStatement getLicensePstmt = con.prepareStatement(getLicenseSql);) {

			getLicensePstmt.setString(1, employee.getEmpCode());
			try (ResultSet getLicenseRes = getLicensePstmt.executeQuery();) {
				ArrayList<String> licenseCd = new ArrayList<String>();
				ArrayList<String> licenseDate = new ArrayList<String>();

				while (getLicenseRes.next()) {
					licenseCd.add(getLicenseRes.getString("license_cd"));
					licenseDate.add(CheckFormat.convertDate2String(getLicenseRes.getDate("get_license_date")));
				}

				// t_get_license
				employee.setLicenseCd(licenseCd);
				employee.setGetLicenseDate(licenseDate);
			} catch (SQLException e) {
				throw e;
			}
		} catch (SQLException e) {
			throw e;
		}
		return employee;
	}

	/**
	 * employeeAllGetから呼ばれるメソッドです
	 *
	 * @param con
	 *            データベースのConnnection
	 * @param employee
	 *            setしたいEmployeeBean
	 * @return EmployeeBean setしたEmployeeBean
	 * @throws SQLException
	 */
	private EmployeeBean mLicenseGet(Connection con, EmployeeBean employee) throws SQLException {

		String licenseSql = "SELECT * FROM m_license WHERE license_cd = ?";
		ArrayList<String> licenseName = new ArrayList<String>();
		try (PreparedStatement licensePstmt = con.prepareStatement(licenseSql);) {
			for (int i = 0; employee.getLicenseCd().size() > i; i++) {
				String license_ = employee.getLicenseCd().get(i);
				licensePstmt.setString(1, license_);
				try (ResultSet licenseRes = licensePstmt.executeQuery();) {

					if (licenseRes.next()) {
						// m_license
						licenseName.add(licenseRes.getString("license_name"));
					} else {
						/* DO NOTHING */
					}
				} catch (SQLException e) {
					throw e;
				}
			}
			employee.setLicenseName(licenseName);
		} catch (SQLException e) {
			throw e;
		}
		return employee;
	}

	/**
	 * 従業員情報にかかわる全レコードの取得 ---返り値:ArrayList(EmployeeBean)
	 *
	 * @return ArrayList<EmployeeBean> 全従業員情報のArrayList
	 */
	public ArrayList<EmployeeBean> employeeAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();

		String emp_sql = "SELECT * FROM m_employee";
		try (Connection con = cm.getConnection();
				PreparedStatement emp_pstmt = con.prepareStatement(emp_sql);
				ResultSet emp_res = emp_pstmt.executeQuery();) {

			while (emp_res.next()) {
				EmployeeBean employee = new EmployeeBean();

				// m_employee
				employee.setEmpCode(emp_res.getString("emp_code"));
				employee.setLName(emp_res.getString("l_name"));
				employee.setFName(emp_res.getString("f_name"));
				employee.setLKanaName(emp_res.getString("l_kana_name"));
				employee.setFKanaName(emp_res.getString("f_kana_name"));
				employee.setSex(emp_res.getByte("sex"));
				employee.setBirthDay(CheckFormat.convertDate2String(emp_res.getDate("birth_day")));
				employee.setSectionCode(emp_res.getString("section_code"));
				employee.setEmpDate(CheckFormat.convertDate2String(emp_res.getDate("emp_date")));
				employee.setUpdateDate(emp_res.getTimestamp("update_date"));

				employee = mSectionGet(con, employee);
				employee = tGetLicenseGet(con, employee);
				employee = mLicenseGet(con, employee);

				employeeList.add(employee);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-employeeAllGet_Prepared");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return employeeList;
	}

	/**
	 * 従業員情報の登録 ---Bean内のm_employeeとt_get_licenseの情報のみ登録されます
	 *
	 * @param employee
	 *            処理したい資格情報
	 */
	public void employeeInsert(EmployeeBean employee) throws ServletServiceException {

		boolean hasFlag = false;
		String call = "";

		ArrayList<EmployeeBean> empAllList = new EmployeeDAO().employeeAllGet();
		if (!(CheckFormat.isCheckPkEmpCode(employee, empAllList))) {
			call = call + "従業員コードが重複しています<br>";
		} else {
			/* DO NOTHING */
		}
		if ((employee.getEmpCode() != null) && (employee.getLicenseCdSQLinsert() != null)
				&& (employee.getGetLicenseDateSQLinsert() != null)) {
			hasFlag = true;
		} else {
			/* DO NOTHING */
		}

		CheckFormat.checkEmployeeBean(employee, hasFlag, call);

		ConnectionManager cm = ConnectionManager.getInstance();

		String mEmpSql = "INSERT INTO m_employee (emp_code,l_name,f_name,l_kana_name,f_kana_name,sex,birth_day,section_code,emp_date) VALUES (?,?,?,?,?,?,?,?,?)";
		String tGetLicenseSql = "INSERT INTO t_get_license (emp_code,license_cd,get_license_date) VALUES (?,?,?)";

		try (Connection con = cm.getConnection(); PreparedStatement emp_pstmt = con.prepareStatement(mEmpSql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				emp_pstmt.setString(1, employee.getEmpCode());
				emp_pstmt.setString(2, employee.getLName());
				emp_pstmt.setString(3, employee.getFName());
				emp_pstmt.setString(4, employee.getLKanaName());
				emp_pstmt.setString(5, employee.getFKanaName());
				emp_pstmt.setByte(6, employee.getSex());
				emp_pstmt.setDate(7, CheckFormat.convertString2Date(employee.getBirthDay()));
				emp_pstmt.setString(8, employee.getSectionCode());
				emp_pstmt.setDate(9, CheckFormat.convertString2Date(employee.getEmpDate()));

				emp_pstmt.executeUpdate();

				if ((employee.getEmpCode() != null) && (employee.getLicenseCdSQLinsert() != null)
						&& (employee.getGetLicenseDateSQLinsert() != null)) {

					try (PreparedStatement get_license_pstmt = con.prepareStatement(tGetLicenseSql);) {
						// t_get_license
						get_license_pstmt.setString(1, employee.getEmpCode());
						get_license_pstmt.setString(2, employee.getLicenseCdSQLinsert());
						get_license_pstmt.setDate(3,
								CheckFormat.convertString2Date(employee.getGetLicenseDateSQLinsert()));

						get_license_pstmt.executeUpdate();
					} catch (SQLException e) {
						throw e;
					}
				} else {
					/* DO NOTHING */
				}

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:EmployeeDAO-employeeRegist-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-employeeRegist");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

	/**
	 * 従業員情報の更新及び、資格保有情報の追加
	 * ---Bean内のm_employeeとt_get_licenseの、emp_code以外の情報のみ更新します
	 * 保有資格を追加するためには、一覧から取得したEmployeeBeanの_SQLinsertにデータを格納してこのメソッドに渡してください
	 *
	 * @param employee
	 *            処理したい資格情報
	 */
	public void employeeUpdate(EmployeeBean employee) throws ServletServiceException {

		boolean hasFlag = false;
		String call = "";

		ArrayList<EmployeeBean> empAllList = new EmployeeDAO().employeeAllGet();
		if (CheckFormat.isCheckPkEmpCode(employee, empAllList)) {
			call = call + "従業員コードが存在しません<br>";
		} else {
			/* DO NOTHING */
		}

		if ((employee.getEmpCode() != null) && (employee.getLicenseCdSQLinsert() != null)
				&& (employee.getGetLicenseDateSQLinsert() != null)) {
			if (CheckFormat.isCheckPkTGetLicense(employee, empAllList)) {
				hasFlag = true;
			} else {
				call = call + "すでに取得済みの資格です<br>";
			}

		} else {
			/* DO NOTHING */
		}

		CheckFormat.checkEmployeeBean(employee, hasFlag, call);

		ConnectionManager cm = ConnectionManager.getInstance();

		String mEmpSql = "UPDATE m_employee SET l_name = ?, f_name = ?, l_kana_name = ?, f_kana_name = ?, sex = ?, birth_day = ?, section_code = ?, emp_date = ? WHERE emp_code = ?";
		String tGetLicenseSql = "INSERT INTO t_get_license (emp_code,license_cd,get_license_date) VALUES (?,?,?)";
		try (Connection con = cm.getConnection(); PreparedStatement emp_pstmt = con.prepareStatement(mEmpSql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				emp_pstmt.setString(1, employee.getLName());
				emp_pstmt.setString(2, employee.getFName());
				emp_pstmt.setString(3, employee.getLKanaName());
				emp_pstmt.setString(4, employee.getFKanaName());
				emp_pstmt.setByte(5, employee.getSex());
				emp_pstmt.setDate(6, CheckFormat.convertString2Date(employee.getBirthDay()));
				emp_pstmt.setString(7, employee.getSectionCode());
				emp_pstmt.setDate(8, CheckFormat.convertString2Date(employee.getEmpDate()));
				emp_pstmt.setString(9, employee.getEmpCode());

				emp_pstmt.executeUpdate();

				// t_get_license
				if ((employee.getEmpCode() != null) && (employee.getLicenseCdSQLinsert() != null)
						&& (employee.getGetLicenseDateSQLinsert() != null)
						&& (CheckFormat.isCheckPkTGetLicense(employee, empAllList))) {
					try (PreparedStatement get_license_pstmt = con.prepareStatement(tGetLicenseSql);) {
						get_license_pstmt.setString(1, employee.getEmpCode());
						get_license_pstmt.setString(2, employee.getLicenseCdSQLinsert());
						get_license_pstmt.setDate(3,
								CheckFormat.convertString2Date(employee.getGetLicenseDateSQLinsert()));
						get_license_pstmt.executeUpdate();
					} catch (SQLException e) {
						throw e;
					}
				} else {
					/* DO NOTHING */
				}

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:EmployeeDAO-employeeRegist-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-employeeRegist");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

	/**
	 * 従業員情報の削除 ---m_employeeとt_get_licenseから、Bean内のemp_idを元に情報を削除します
	 *
	 * @param employee
	 *            処理したい資格情報
	 */
	public void employeeDelete(EmployeeBean employee) throws ServletServiceException {

		ArrayList<EmployeeBean> empAllList = new EmployeeDAO().employeeAllGet();
		if (CheckFormat.isCheckPkEmpCode(employee, empAllList)) {
			throw new ServletServiceException("従業員コードが存在しません<br>");
		} else {
			/* DO NOTHING */
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String mEmpSql = "DELETE FROM m_employee WHERE emp_code = ?";
		String tGetLicenseSql = "DELETE FROM t_get_license WHERE emp_code = ? ";

		try (Connection con = cm.getConnection();) {
			try {
				con.setAutoCommit(false);

				try (Statement stmt = con.createStatement();) {
					stmt.execute("SET FOREIGN_KEY_CHECKS=0");
				} catch (SQLException e) {
					throw e;
				}

				// t_get_license
				try (PreparedStatement get_license_pstmt = con.prepareStatement(tGetLicenseSql);) {
					// t_get_license
					get_license_pstmt.setString(1, employee.getEmpCode());
					get_license_pstmt.executeUpdate();
				} catch (SQLException e) {
					throw e;
				}

				try (PreparedStatement emp_pstmt = con.prepareStatement(mEmpSql);) {
					// m_employee
					emp_pstmt.setString(1, employee.getEmpCode());
					emp_pstmt.executeUpdate();
				} catch (SQLException e) {
					throw e;
				}

				try (Statement stmt = con.createStatement();) {
					stmt.execute("SET FOREIGN_KEY_CHECKS=1");
				} catch (SQLException e) {
					throw e;
				}

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:EmployeeDAO-employeeDelete-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-employeeDelete");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

}
