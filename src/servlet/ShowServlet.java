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

import dao.EmployeeDAO;
import entity.EmployeeBean;

/**
 * TeamB-YDK ShowServlet.java
 *
 * Copyright(C) 2017 TeamB-YDK All Righta Reserved.
 *
 */

/**
 * 従業員一覧を表示するクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */

@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

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

			// エンコーディング指定
			request.setCharacterEncoding("Windows-31J");
			response.setCharacterEncoding("Windows-31J");

			// formからの値を取得
			String page = request.getParameter("page");

			/* DAOの生成 */
			EmployeeDAO emp = new EmployeeDAO();

			/* 従業員一覧を押下したとき */
			if (page.equals("従業員一覧")) {
				ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();

				// セットする
				request.setAttribute("employeeList", employeeList);

				// 移動先の設定
				url = "ShowEmployee.jsp";
			} else {
				/* DO NOTHING */
			}

			/* 削除を押下したとき */
			if (page.equals("削除")) {
				url = "DeleteServlet";
			} else {
				/* DO NOTHING */
			}

			/* 変更を押下したとき */
			if (page.equals("変更")) {
				url = "ChangeServlet";
				request.setAttribute("page", page);
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
