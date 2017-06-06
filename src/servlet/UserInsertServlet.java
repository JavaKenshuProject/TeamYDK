package servlet;

import java.io.IOException;

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
 * TeamB-YDK UserInsertServlet.java
 *
 * Copyright(C) 2017 TeamB-YDK All Righta Reserved.
 *
 */

/**
 * システムユーザを新規登録するクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInsertServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		throw new ServletServiceException("最初からやり直してください");
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

		String url = null; // 移譲する先のjspを格納する変数url

		HttpSession session = request.getSession(true); // セッションの取得

		/* ログイン状況の確認 */
		if ((String) session.getAttribute("login") == null) {
			url = "Login.jsp";
		} else {

			/* エンコーディング */
			request.setCharacterEncoding("Windows-31J");
			response.setCharacterEncoding("Windows-31J");

			/* formの取得 */
			String page = request.getParameter("page");
			String userId = request.getParameter("user_id");
			String password = request.getParameter("password");

			/* DAOのインスタンス化 */
			UserDAO userD = new UserDAO();

			/* Beanのインスタンス化 */
			UserBean user = new UserBean();

			/* ユーザ登録を押下したとき */
			if (page.equals("ユーザ登録")) {
				url = "UserInsert.jsp";
			} else {
				/* DO NOTHING */
			}

			/* 登録を押下したとき */
			if (page.equals("登録")) {
				user.setUserId(userId);
				user.setPassword(password);
				userD.userInsert(user);

				url = "UserInsertSuccess.jsp";
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
