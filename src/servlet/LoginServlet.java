package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.UserBean;
import exception.ServletServiceException;

/**
 * TeamB-YDK LoginServlet.java
 *
 * Copyright(C) 2017 TeamB-YDK All Righta Reserved.
 *
 */

/**
 * ログインするクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * ポストされたときに用いるメソッド
	 *
	 * @param request
	 *            response
	 * @return
	 * @throws ServletException
	 *             IOException
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* エンコーディング指定 */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		String login = request.getParameter("login"); // formからの値を取得

		String url = "/Login.jsp"; // 移譲する先のjspを格納する変数url

		/* ログインを押下したとき */
		if (login.equals("ログイン")) {

			/* パラメーター取得 */
			String userID = request.getParameter("userID");
			String password = request.getParameter("password");

			/* DAO,Beanをインスタンス化 */
			ArrayList<UserBean> userList = new ArrayList<UserBean>();
			UserDAO dao = new UserDAO();

			HttpSession session = request.getSession(); // セッション管理

			/* DAOからのreturnをBeanに格納 */
			try {
				userList = dao.userAllGet();
			} catch (Exception e) {
			}

			boolean hasFlag = false; // データがあるかの有無を確認する変数

			/* ユーザ情報の詮索 */
			for (UserBean user : userList) {
				if ((user.getUserId().equals(userID)) && (user.getPassword().equals(password))) {
					hasFlag = true;
					session.setAttribute("login", (String) "OK");
				} else {
					/* DO NOTHING */
				}
			}

			/* hasFlagがtrueのとき */
			if (hasFlag) {
				url = "/Menu.jsp";
			} else {
				throw new ServletServiceException("ログインに失敗しました");
			}
		} else {
			/* DO NOTHING */
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
