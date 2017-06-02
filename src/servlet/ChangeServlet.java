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
import dao.SectionDAO;
import entity.EmployeeBean;
import entity.LicenseBean;
import entity.SectionBean;
import exception.ServletServiceException;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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


			/* pageの取得 */
			String page = request.getParameter("page");
			if (page == null) {
				page = (String) request.getAttribute("page");
			}

			/* DAOのインスタンス化 */
			EmployeeDAO emp = new EmployeeDAO();
			LicenseDAO lic = new LicenseDAO();
			SectionDAO sec = new SectionDAO();

			/* Beanのインスタンス化 */
			EmployeeBean empB = new EmployeeBean();
			LicenseBean licB = new LicenseBean();

			if (page.equals("変更")) {

				String c_emp = request.getParameter("employee");
				if (c_emp == null) {
					throw new ServletServiceException("チェックボックスにチェックを入れてください");
				}

				ArrayList<SectionBean> sectionList = sec.SectionAllGet();
				ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();

				int num = Integer.parseInt(request.getParameter("employee"));
				empB = employeeList.get(num);
				/* セットする */
				request.setAttribute("sectionList", sectionList);
				request.setAttribute("empB", empB);

				url = "EmployeeChange.jsp";
			}

			if (page.equals("変更確定")) {
				/* formの入力 */


				String c_emp = request.getParameter("sex");
				if (c_emp == null) {
					throw new ServletServiceException("性別にチェックを入れてください");
				}

				int num = Integer.parseInt(request.getParameter("hidden"));
				String l_name = request.getParameter("l_name");
				String f_name = request.getParameter("f_name");
				String l_kana_name = request.getParameter("l_kana_name");
				String f_kana_name = request.getParameter("f_kana_name");
				int sex_num = Integer.parseInt(request.getParameter("sex"));
				byte sex = (byte) sex_num;
				String job = request.getParameter("job");

				ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();
				empB = employeeList.get(num);
				empB.setL_name(l_name);
				empB.setF_name(f_name);
				empB.setL_kana_name(l_kana_name);
				empB.setF_kana_name(f_kana_name);
				empB.setSex(sex);
				empB.setSection_name(job);
				emp.employeeUpdate(empB);

				url = "ChangeSuccess.jsp";
			}
		}
		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
