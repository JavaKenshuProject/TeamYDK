package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.LicenseBean;
import exception.ServletServiceException;

/**
 *
 * @author KIKUCHI
 * @version 1.10
 */
public class LicenseDAO {
	/**
	 * 資格情報の全レコードを取得 ---返り値:ArrayList(LicenseBean)
	 */
	public ArrayList<LicenseBean> licenseAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<LicenseBean> LicenseList = new ArrayList<LicenseBean>();

		String license_sql = "SELECT * FROM m_license";
		try (Connection con = cm.getConnection();
				PreparedStatement license_pstmt = con.prepareStatement(license_sql);
				ResultSet license_res = license_pstmt.executeQuery();) {

			while (license_res.next()) {
				LicenseBean license = new LicenseBean();

				// m_employee
				license.setLicense_cd(license_res.getString("license_cd"));
				license.setLicense_name(license_res.getString("license_name"));

				LicenseList.add(license);
			}

		} catch (SQLException e) {
			System.out.println("error:LicenseDAO-licenseAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return LicenseList;
	}

	/**
	 * 資格情報の登録 ---LicenseBean内の情報を元に登録します
	 *
	 * @param license
	 */
	public void licenseInsert(LicenseBean license) throws ServletServiceException {

		ArrayList<LicenseBean> license_all_list = new LicenseDAO().licenseAllGet();
		CheckFormat.checkLicenseBean(license,license_all_list);
		if (!(CheckFormat.checkPK_license(license, license_all_list))) {
			throw new ServletServiceException("資格コードが重複しています");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String license_sql = "INSERT INTO m_license (license_cd,license_name) VALUES (?,?)";

		try (Connection con = cm.getConnection();
				PreparedStatement license_pstmt = con.prepareStatement(license_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				license_pstmt.setString(1, license.getLicense_cd());
				license_pstmt.setString(2, license.getLicense_name());

				license_pstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:LicenseDAO-licenseRegist-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:LicenseDAO-licenseRegist");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

	/**
	 * 資格情報の削除 ---LicenseBean内の情報を元に資格情報を削除します
	 *
	 * @param license
	 */
	public void licenseDelete(LicenseBean license) throws ServletServiceException {

		ArrayList<LicenseBean> license_all_list = new LicenseDAO().licenseAllGet();
		if (CheckFormat.checkPK_license(license, license_all_list)) {
			throw new ServletServiceException("資格コードが存在しません");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String license_sql = "DELETE FROM m_license WHERE license_cd = ?";

		try (Connection con = cm.getConnection();
				PreparedStatement license_pstmt = con.prepareStatement(license_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				license_pstmt.setString(1, license.getLicense_cd());

				license_pstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:LicenseDAO-licenseDelete-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:LicenseDAO-licenseDelete");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}
}
