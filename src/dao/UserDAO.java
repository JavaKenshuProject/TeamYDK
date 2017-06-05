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
 * @version 1.10
 */
public class UserDAO {
	/**
	 * ログインにかかわるユーザー情報の全レコードを取得 ---返り値:ArrayList(UserBean)
	 */
	public ArrayList<UserBean> userAllGet() {

		ConnectionManager cm = ConnectionManager.getInstance();

		ArrayList<UserBean> userList = new ArrayList<UserBean>();

		String userSql = "SELECT * FROM m_user";
		try (Connection con = cm.getConnection();
				PreparedStatement userPstmt = con.prepareStatement(userSql);
				ResultSet userRes = userPstmt.executeQuery();) {

			while (userRes.next()) {
				UserBean user = new UserBean();

				// m_employee
				user.setUserId(userRes.getString("user_id"));
				user.setPassword(userRes.getString("password"));
				user.setUpdateDate(userRes.getTimestamp("update_date"));

				userList.add(user);
			}

		} catch (SQLException e) {
			System.out.println("error:UserDAO-userAllGet");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}
		return userList;
	}

	/**
	 * ユーザ情報の登録 ---UserBean内の情報を元に登録します
	 *
	 * @param user
	 */
	public void userInsert(UserBean user) throws ServletServiceException {

		String call = "";
		ArrayList<UserBean> userAllList = new UserDAO().userAllGet();
		if (!(CheckFormat.checkPkUser(user, userAllList))) {
			call = call + "ユーザIDが重複しています";
		}

		CheckFormat.checkUserBean(user, call);

		ConnectionManager cm = ConnectionManager.getInstance();

		String userSql = "INSERT INTO m_user (user_id,password) VALUES (?,?)";

		try (Connection con = cm.getConnection(); PreparedStatement userPstmt = con.prepareStatement(userSql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				userPstmt.setString(1, user.getUserId());
				userPstmt.setString(2, user.getPassword());

				userPstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:UserDAO-userRegist-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:UserDAO-userRegist");
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

		ArrayList<UserBean> userAllList = new UserDAO().userAllGet();
		if (CheckFormat.checkPkUser(user, userAllList)) {
			throw new ServletServiceException("ユーザIDが存在しません");
		}

		ConnectionManager cm = ConnectionManager.getInstance();

		String userSql = "DELETE FROM m_user WHERE user_id = ?";

		try (Connection con = cm.getConnection(); PreparedStatement user_pstmt = con.prepareStatement(userSql);) {
			try {
				con.setAutoCommit(false);

				// m_employee
				user_pstmt.setString(1, user.getUserId());

				user_pstmt.executeUpdate();

				con.commit();
			} catch (SQLException e) {
				System.out.println("error:UserDAO-userDelete-commit:error");
				e.printStackTrace();
				con.rollback();
				throw e;
			} finally {
				con.setAutoCommit(true);
			}

		} catch (SQLException e) {
			System.out.println("error:UserDAO-userDelete");
			e.printStackTrace();
		} finally {
			cm.closeConnection();
		}

	}

}
