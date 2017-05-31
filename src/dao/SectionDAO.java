package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.SectionBean;

/**
 *
 * @author KIKUCHI
 * @version 1.00
 */
public class SectionDAO {
	/**
	 * 所属情報の全レコードを取得 ---返り値:ArrayList(SectionBean)
	 */
	public ArrayList<SectionBean> SectionAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<SectionBean> SectionList = new ArrayList<SectionBean>();

		String section_sql = "SELECT * FROM m_section";
		try (Connection con = cm.getConnection();
				PreparedStatement section_pstmt = con.prepareStatement(section_sql);
				ResultSet section_res = section_pstmt.executeQuery();) {

			while (section_res.next()) {
				SectionBean section = new SectionBean();

				// m_employee
				section.setSection_code(section_res.getString("section_code"));
				section.setSection_name(section_res.getString("section_name"));

				SectionList.add(section);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-sectionAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return SectionList;
	}

}
