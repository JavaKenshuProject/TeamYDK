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
import dao.LicenseDAO;
import entity.EmployeeBean;
import entity.LicenseBean;
import exception.ServletServiceException;

/**
 * TeamB-YDK LicenseServlett.java
 *
 * Copyright(C) 2017 TeamB-YDK All Righta Reserved.
 *
 */

/**
 * 資格を追加するクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */
@WebServlet("/LicenseServlet")
public class LicenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LicenseServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		/* url宣言 */
		String url = null;
		HttpSession session = request.getSession(true);

		if ((String) session.getAttribute("login") == null) {
			url = "Login.jsp";
		} else {

			/* エンコーディング */
			request.setCharacterEncoding("Windows-31J");
			response.setCharacterEncoding("Windows-31J");

			/* formの取得 */
			String employee = request.getParameter("employee"); // 従業員一覧の上から順（0~）
			String license = request.getParameter("license"); // 資格一覧の上から順（0~）
			String[] getLicenseDay = request.getParameterValues("getLicenseDay");
			String page = request.getParameter("page");

			/* DAOのインスタンス化 */
			EmployeeDAO emp = new EmployeeDAO();
			LicenseDAO lic = new LicenseDAO();

			/* Beanのインスタンス化 */
			EmployeeBean empB = new EmployeeBean();
			LicenseBean licB = new LicenseBean();

			if (page.equals("資格取得")) {
				ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();
				ArrayList<LicenseBean> licenseList = lic.licenseAllGet();

				/* セットする */
				request.setAttribute("employeeList", employeeList);
				request.setAttribute("licenseList", licenseList);

				/* 移動先の設定 */
				url = "LicenseGet.jsp";
			} else {
				/* DO NOTHING */
			}

			if (page.equals("取得")) {
				String call = "";
				if (employee == null) {
					call = call + "保有資格を追加する従業員にチェックを入れてください<br>";
				} else {
					/* DO NOTHING */
				}
				if (license == null) {
					call = call + "保有資格に追加する資格へチェックを入れてください<br>";
				} else {
					/* DO NOTHING */
				}

				if (!(call.equals(""))) {
					throw new ServletServiceException(call);
				} else {
					/* DO NOTHING */
				}

				String licenseDay = getLicenseDay[0] + "-" + getLicenseDay[1] + "-" + getLicenseDay[2];
				empB = emp.employeeAllGet().get(Integer.parseInt(employee));
				licB = lic.licenseAllGet().get(Integer.parseInt(license));
				empB.setGetLicenseDateSQLinsert(licenseDay);
				empB.setLicenseCdSQLinsert(licB.getLicenseCd());
				emp.employeeUpdate(empB);

				url = "GetSuccess.jsp";
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
