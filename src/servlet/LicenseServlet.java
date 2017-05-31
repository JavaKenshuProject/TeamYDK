package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import dao.LicenseDAO;
import entity.EmployeeBean;
import entity.LicenseBean;

/**
 * Servlet implementation class LicenseServlet
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = null;

		/* エンコーディング */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/* web操作による有無 */
		String employee = request.getParameter("employee");
		String license = request.getParameter("license");
		String page = request.getParameter("page");

		/* DAOの生成 */
		EmployeeDAO emp = new EmployeeDAO();
		LicenseDAO lic = new LicenseDAO();

		if (page.equals("資格取得")) {
			ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();
			ArrayList<LicenseBean> licenseList = lic.licenseAllGet();

			/* セットする */
			request.setAttribute("employeeList", employeeList);
			request.setAttribute("licenseList", licenseList);


			/* 移動先の設定 */
			url = "LicenseGet.jsp";
		}

		if (page.equals("取得")) {
			url = "GetSuccess.jsp";
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
