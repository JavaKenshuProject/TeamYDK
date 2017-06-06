package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TeamB-YDK LogoutServlet.java
 *
 * Copyright(C) 2017 TeamB-YDK All Righta Reserved.
 *
 */

/**
 * ログアウトするクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// エンコーディング指定
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		String url = null; // 移譲する先のjspを格納する変数url

		HttpSession session = request.getSession(true); // セッションの取得

		/* ログイン状況の確認 */
		if ((String) session.getAttribute("login") == null) {
			url = "Login.jsp";
		} else {

			// formからの値を取得
			String move = request.getParameter("move");
			String page = request.getParameter("page");

			// 移譲する先のjspを格納する変数url
			url = "Logout.jsp";

			/* ログアウトを押下したとき */
			if ((page != null) && (page.equals("ログアウト"))) {
				// セッション破棄
				session.invalidate();
				url = "Logout.jsp";
			} else {
				/* DO NOTHING */
			}

			/* ログイン画面を押下したとき */
			if ((move != null) && (move.equals("ログイン画面へ"))) {
				url = "Login.jsp";
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
