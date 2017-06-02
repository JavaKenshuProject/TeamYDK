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
 *
 * @author KIKUCHI
 * @version 1.20
 */
public class EmployeeDAO {

	/**
	 * employeeAllGetから呼ばれるメソッドです
	 *
	 * @param con
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	private EmployeeBean m_sectionGet(Connection con, EmployeeBean employee) throws SQLException {
		String section_sql = "SELECT * FROM m_section WHERE section_code = ?";

		try (PreparedStatement section_pstmt = con.prepareStatement(section_sql);) {

			section_pstmt.setString(1, employee.getSection_code());
			try (ResultSet section_res = section_pstmt.executeQuery();) {
				if (section_res.next()) {
					// m_section
					employee.setSection_name(section_res.getString("section_name"));
					employee.setSection_update_date(section_res.getTimestamp("update_date"));
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
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	private EmployeeBean t_get_licenseGet(Connection con, EmployeeBean employee) throws SQLException {
		String get_license_sql = "SELECT * FROM t_get_license WHERE emp_code = ?";
		try (PreparedStatement get_license_pstmt = con.prepareStatement(get_license_sql);) {

			get_license_pstmt.setString(1, employee.getEmp_code());
			try (ResultSet get_license_res = get_license_pstmt.executeQuery();) {
				ArrayList<String> license_cd = new ArrayList<String>();
				ArrayList<String> license_date = new ArrayList<String>();

				while (get_license_res.next()) {
					license_cd.add(get_license_res.getString("license_cd"));
					license_date.add(CheckFormat.convertDate2String(get_license_res.getDate("get_license_date")));
				}

				// t_get_license
				employee.setLicense_cd(license_cd);
				employee.setGet_license_date(license_date);
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
	 * @param employee
	 * @return
	 * @throws SQLException
	 */
	private EmployeeBean m_licenseGet(Connection con, EmployeeBean employee) throws SQLException {

		String license_sql = "SELECT * FROM m_license WHERE license_cd = ?";
		ArrayList<String> license_name = new ArrayList<String>();
		try (PreparedStatement license_pstmt = con.prepareStatement(license_sql);) {
			for (int i = 0; employee.getLicense_cd().size() > i; i++) {
				String license_ = employee.getLicense_cd().get(i);
				license_pstmt.setString(1, license_);
				try (ResultSet license_res = license_pstmt.executeQuery();) {

					if (license_res.next()) {
						// m_license
						license_name.add(license_res.getString("license_name"));
					}
				} catch (SQLException e) {
					throw e;
				}
			}
			employee.setLicense_name(license_name);
		} catch (SQLException e) {
			throw e;
		}
		return employee;
	}

	/**
	 * 従業員情報にかかわる全レコードの取得 ---返り値:ArrayList(EmployeeBean)
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
				employee.setEmp_code(emp_res.getString("emp_code"));
				employee.setL_name(emp_res.getString("l_name"));
				employee.setF_name(emp_res.getString("f_name"));
				employee.setL_kana_name(emp_res.getString("l_kana_name"));
				employee.setF_kana_name(emp_res.getString("f_kana_name"));
				employee.setSex(emp_res.getByte("sex"));
				employee.setBirth_day(CheckFormat.convertDate2String(emp_res.getDate("birth_day")));
				employee.setSection_code(emp_res.getString("section_code"));
				employee.setEmp_date(CheckFormat.convertDate2String(emp_res.getDate("emp_date")));
				employee.setUpdate_date(emp_res.getTimestamp("update_date"));

				employee = m_sectionGet(con, employee);
				employee = t_get_licenseGet(con, employee);
				employee = m_licenseGet(con, employee);

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
	 */
	public void employeeInsert(EmployeeBean employee) throws ServletServiceException {

		CheckFormat.checkEmployeeBean(employee);

		ArrayList<EmployeeBean> emp_all_list = new EmployeeDAO().employeeAllGet();
		if (!(CheckFormat.checkPK_empCode(employee, emp_all_list))) {
			throw new ServletServiceException("従業員コードが重複しています");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String m_emp_sql = "INSERT INTO m_employee (emp_code,l_name,f_name,l_kana_name,f_kana_name,sex,birth_day,section_code,emp_date) VALUES (?,?,?,?,?,?,?,?,?)";
		String t_get_license_sql = "INSERT INTO t_get_license (emp_code,license_cd,get_license_date) VALUES (?,?,?)";

		try (Connection con = cm.getConnection(); PreparedStatement emp_pstmt = con.prepareStatement(m_emp_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				emp_pstmt.setString(1, employee.getEmp_code());
				emp_pstmt.setString(2, employee.getL_name());
				emp_pstmt.setString(3, employee.getF_name());
				emp_pstmt.setString(4, employee.getL_kana_name());
				emp_pstmt.setString(5, employee.getF_kana_name());
				emp_pstmt.setByte(6, employee.getSex());
				emp_pstmt.setDate(7, CheckFormat.convertString2Date(employee.getBirth_day()));
				emp_pstmt.setString(8, employee.getSection_code());
				emp_pstmt.setDate(9, CheckFormat.convertString2Date(employee.getEmp_date()));

				emp_pstmt.executeUpdate();

				if ((employee.getEmp_code() != null) && (employee.getLicense_cd_SQLinsert() != null)
						&& (employee.getGet_license_date_SQLinsert() != null)) {
					CheckFormat.checkTGetLicense(employee);

					try (PreparedStatement get_license_pstmt = con.prepareStatement(t_get_license_sql);) {
						// t_get_license
						get_license_pstmt.setString(1, employee.getEmp_code());
						get_license_pstmt.setString(2, employee.getLicense_cd_SQLinsert());
						get_license_pstmt.setDate(3,
								CheckFormat.convertString2Date(employee.getGet_license_date_SQLinsert()));

						get_license_pstmt.executeUpdate();
					} catch (SQLException e) {
						throw e;
					}
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
	 */
	public void employeeUpdate(EmployeeBean employee) throws ServletServiceException {

		CheckFormat.checkEmployeeBean(employee);

		ArrayList<EmployeeBean> emp_all_list = new EmployeeDAO().employeeAllGet();
		if (CheckFormat.checkPK_empCode(employee, emp_all_list)) {
			throw new ServletServiceException("従業員コードが存在しません");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String m_emp_sql = "UPDATE m_employee SET l_name = ?, f_name = ?, l_kana_name = ?, f_kana_name = ?, sex = ?, birth_day = ?, section_code = ?, emp_date = ? WHERE emp_code = ?";
		String t_get_license_sql = "INSERT INTO t_get_license (emp_code,license_cd,get_license_date) VALUES (?,?,?)";
		try (Connection con = cm.getConnection(); PreparedStatement emp_pstmt = con.prepareStatement(m_emp_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				emp_pstmt.setString(1, employee.getL_name());
				emp_pstmt.setString(2, employee.getF_name());
				emp_pstmt.setString(3, employee.getL_kana_name());
				emp_pstmt.setString(4, employee.getF_kana_name());
				emp_pstmt.setByte(5, employee.getSex());
				emp_pstmt.setDate(6, CheckFormat.convertString2Date(employee.getBirth_day()));
				emp_pstmt.setString(7, employee.getSection_code());
				emp_pstmt.setDate(8, CheckFormat.convertString2Date(employee.getEmp_date()));
				emp_pstmt.setString(9, employee.getEmp_code());

				emp_pstmt.executeUpdate();

				// t_get_license
				if ((employee.getEmp_code() != null) && (employee.getLicense_cd_SQLinsert() != null)
						&& (employee.getGet_license_date_SQLinsert() != null)
						&& (CheckFormat.checkPK_t_get_license(employee, emp_all_list))) {
					CheckFormat.checkTGetLicense(employee);
					try (PreparedStatement get_license_pstmt = con.prepareStatement(t_get_license_sql);) {
						get_license_pstmt.setString(1, employee.getEmp_code());
						get_license_pstmt.setString(2, employee.getLicense_cd_SQLinsert());
						get_license_pstmt.setDate(3,
								CheckFormat.convertString2Date(employee.getGet_license_date_SQLinsert()));
						get_license_pstmt.executeUpdate();
					} catch (SQLException e) {
						throw e;
					}
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
	 */
	public void employeeDelete(EmployeeBean employee) throws ServletServiceException {

		ArrayList<EmployeeBean> emp_all_list = new EmployeeDAO().employeeAllGet();
		if (CheckFormat.checkPK_empCode(employee, emp_all_list)) {
			throw new ServletServiceException("従業員コードが存在しません");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String m_emp_sql = "DELETE FROM m_employee WHERE emp_code = ?";
		String t_get_license_sql = "DELETE FROM t_get_license WHERE emp_code = ? ";

		try (Connection con = cm.getConnection();) {
			try {
				con.setAutoCommit(false);

				try (Statement stmt = con.createStatement();) {
					stmt.execute("SET FOREIGN_KEY_CHECKS=0");
				} catch (SQLException e) {
					throw e;
				}

				// t_get_license
				try (PreparedStatement get_license_pstmt = con.prepareStatement(t_get_license_sql);) {
					// t_get_license
					get_license_pstmt.setString(1, employee.getEmp_code());
					get_license_pstmt.executeUpdate();
				} catch (SQLException e) {
					throw e;
				}

				try (PreparedStatement emp_pstmt = con.prepareStatement(m_emp_sql);) {
					// m_employee
					emp_pstmt.setString(1, employee.getEmp_code());
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
