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
import entity.EmployeeBean;

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

		/* エンコーディング*/
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/*web操作による有無*/
		//String licenseGet = request.getParameter("submit");

		/* EmployeeDAOの生成 */
		EmployeeDAO emp = new EmployeeDAO();
		ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();

		/* セットする */
		request.setAttribute("employeeList",employeeList);

		/* 移動先の設定 */
		url = "LicenseGet.jsp";

		/* 転送先*/
		RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

	}

}
