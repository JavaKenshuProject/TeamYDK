package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.SectionBean;

/**
 * TeamB-YDK UserDAO.java
 *
 * Copyright(C) 2017 TeamB-YDK All Rights Reserved.
 *
 * 所属情報にかかわるデータベースとのやり取りを行うクラスです
 *
 * @author KIKUCHI
 * @version 1.00
 */
public class SectionDAO {
	/**
	 * 所属情報の全レコードを取得 ---返り値:ArrayList(SectionBean)
	 *
	 * @return ArrayList<SectionBean> 全所属情報
	 */
	public ArrayList<SectionBean> SectionAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<SectionBean> sectionList = new ArrayList<SectionBean>();

		String sectionSql = "SELECT * FROM m_section";
		try (Connection con = cm.getConnection();
				PreparedStatement sectionPstmt = con.prepareStatement(sectionSql);
				ResultSet sectionRes = sectionPstmt.executeQuery();) {

			while (sectionRes.next()) {
				SectionBean section = new SectionBean();

				// m_employee
				section.setSectionCode(sectionRes.getString("section_code"));
				section.setSectionName(sectionRes.getString("section_name"));

				sectionList.add(section);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-sectionAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return sectionList;
	}

}
