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
import dao.SectionDAO;
import entity.EmployeeBean;
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
			SectionDAO sec = new SectionDAO();

			/* Beanのインスタンス化 */
			EmployeeBean empB = new EmployeeBean();

			if (page.equals("変更")) {

				String cEmp = request.getParameter("employee");
				if (cEmp == null) {
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

				String cEmp = request.getParameter("sex");
				if (cEmp == null) {
					throw new ServletServiceException("性別にチェックを入れてください");
				}

				int num = Integer.parseInt(request.getParameter("hidden"));
				String lName = request.getParameter("l_name");
				String fName = request.getParameter("f_name");
				String lKanaName = request.getParameter("l_kana_name");
				String fKanaName = request.getParameter("f_kana_name");
				byte sex = (byte) Integer.parseInt(request.getParameter("sex"));
				String sectionCode = request.getParameter("job");

				ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();
				empB = employeeList.get(num);
				empB.setLName(lName);
				empB.setFName(fName);
				empB.setLKanaName(lKanaName);
				empB.setFKanaName(fKanaName);
				empB.setSex(sex);
				empB.setSectionCode(sectionCode);
				emp.employeeUpdate(empB);

				url = "ChangeSuccess.jsp";
			}
		}
		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
