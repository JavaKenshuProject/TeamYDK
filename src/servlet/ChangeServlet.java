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
import dao.SectionDAO;
import entity.EmployeeBean;
import entity.LicenseBean;
import entity.SectionBean;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* url宣言 */
		String url = null;

		/* エンコーディング */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/* formの取得 */
		String l_name = request.getParameter("l_name");
		String f_name = request.getParameter("f_name");
		String l_kana_name = request.getParameter("l_kana_name");
		String f_kana_name = request.getParameter("f_kana_name");
		byte[] sex = request.getParameter("sex").getBytes();
		String job = request.getParameter("job");
		String license = request.getParameter("license");
		String page = request.getParameter("page");

		/* DAOのインスタンス化 */
		EmployeeDAO emp = new EmployeeDAO();
		LicenseDAO lic = new LicenseDAO();
		SectionDAO sec = new SectionDAO();

		/* Beanのインスタンス化*/
		EmployeeBean empB = new EmployeeBean();
		LicenseBean licB = new LicenseBean();

		if(page.equals("変更")){
			ArrayList<SectionBean> sectionList = sec.SectionAllGet();

			/* セットする */
			request.setAttribute("sectionList", sectionList);

			url = "EmployeeChange.jsp";
		}

		if(page.equals("変更完了")){

		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);


	}

}
