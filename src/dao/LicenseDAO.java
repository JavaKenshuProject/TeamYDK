package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.LicenseBean;
import exception.ServletServiceException;

/**
 * TeamB-YDK LicenseDAO.java
 *
 * Copyright(C) 2017 TeamB-YDK All Rights Reserved.
 *
 * 資格情報にかかわるデータベースとのやり取りを行うクラスです
 *
 * @author KIKUCHI
 * @version 1.10
 */
public class LicenseDAO {
	/**
	 * 資格情報の全レコードを取得 ---返り値:ArrayList(LicenseBean)
	 *
	 * @return ArrayList<LicenseBean> 全資格情報
	 */
	public ArrayList<LicenseBean> licenseAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<LicenseBean> licenseList = new ArrayList<LicenseBean>();

		String licenseSql = "SELECT * FROM m_license";
		try (Connection con = cm.getConnection();
				PreparedStatement licensePstmt = con.prepareStatement(licenseSql);
				ResultSet licenseRes = licensePstmt.executeQuery();) {

			while (licenseRes.next()) {
				LicenseBean license = new LicenseBean();

				// m_employee
				license.setLicenseCd(licenseRes.getString("license_cd"));
				license.setLicenseName(licenseRes.getString("license_name"));

				licenseList.add(license);
			}

		} catch (SQLException e) {
			System.out.println("error:LicenseDAO-licenseAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return licenseList;
	}

	/**
	 * 資格情報の登録 ---LicenseBean内の情報を元に登録します
	 *
	 * @param license
	 *            処理したい資格情報
	 */
	public void licenseInsert(LicenseBean license) throws ServletServiceException {

		ArrayList<LicenseBean> licenseAllList = new LicenseDAO().licenseAllGet();
		String call = "";

		if (!(CheckFormat.isCheckPkLicense(license, licenseAllList))) {
			call = call + "資格コードが重複しています<br>";
		} else {
			/* DO NOTHING */
		}

		for (LicenseBean licenseLoop : licenseAllList) {
			if (license.getLicenseName().equals(licenseLoop.getLicenseName())) {
				call = call + "資格名が重複しています<br>";
			} else {
				/* DO NOTHING */
			}
		}

		CheckFormat.checkLicenseBean(license, call);

		ConnectionManager cm = ConnectionManager.getInstance();

		String license_sql = "INSERT INTO m_license (license_cd,license_name) VALUES (?,?)";

		try (Connection con = cm.getConnection(); PreparedStatement licensePstmt = con.prepareStatement(license_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				licensePstmt.setString(1, license.getLicenseCd());
				licensePstmt.setString(2, license.getLicenseName());

				licensePstmt.executeUpdate();

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
	 *            処理したい資格情報
	 */
	public void licenseDelete(LicenseBean license) throws ServletServiceException {

		ArrayList<LicenseBean> licenseAllList = new LicenseDAO().licenseAllGet();
		if (CheckFormat.isCheckPkLicense(license, licenseAllList)) {
			throw new ServletServiceException("資格コードが存在しません<br>");
		} else {
			/* DO NOTHING */
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String licenseSql = "DELETE FROM m_license WHERE license_cd = ?";

		try (Connection con = cm.getConnection(); PreparedStatement licensePstmt = con.prepareStatement(licenseSql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				licensePstmt.setString(1, license.getLicenseCd());

				licensePstmt.executeUpdate();

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
