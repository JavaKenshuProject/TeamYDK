package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.UserBean;
import exception.ServletServiceException;

/**
 *
 * @author KIKUCHI
 * @version 1.00
 */
public class UserDAO {
	/**
	 * ログインにかかわるユーザー情報の全レコードを取得 ---返り値:ArrayList(UserBean)
	 */
	public ArrayList<UserBean> userAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<UserBean> UserList = new ArrayList<UserBean>();

		String user_sql = "SELECT * FROM m_user";
		try (Connection con = cm.getConnection();
				PreparedStatement user_pstmt = con.prepareStatement(user_sql);
				ResultSet user_res = user_pstmt.executeQuery();) {

			while (user_res.next()) {
				UserBean user = new UserBean();

				// m_employee
				user.setUser_id(user_res.getString("user_id"));
				user.setPassword(user_res.getString("password"));
				user.setUpdate_date(user_res.getTimestamp("update_date"));

				UserList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-userAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return UserList;
	}

	/**
	 * ユーザ情報の登録 ---UserBean内の情報を元に登録します
	 *
	 * @param user
	 */
	public void userInsert(UserBean user) throws ServletServiceException {

		ConnectionManager cm = ConnectionManager.getInstance();

		String user_sql = "INSERT INTO m_user (user_id,password) VALUES (?,?)";

		try (Connection con = cm.getConnection(); PreparedStatement user_pstmt = con.prepareStatement(user_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				user_pstmt.setString(1, user.getUser_id());
				user_pstmt.setString(2, user.getPassword());

				user_pstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:EmployeeDAO-userRegist-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-userRegist");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

	/**
	 * ユーザ情報の削除 ---UserBean内の情報を元にユーザ情報を削除します
	 *
	 * @param user
	 */
	public void userDelete(UserBean user) throws ServletServiceException {

		ConnectionManager cm = ConnectionManager.getInstance();

		String user_sql = "DELETE FROM m_user WHERE user_id = ?";

		try (Connection con = cm.getConnection(); PreparedStatement user_pstmt = con.prepareStatement(user_sql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				user_pstmt.setString(1, user.getUser_id());

				user_pstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:EmployeeDAO-userDelete-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:EmployeeDAO-userDelete");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

}
