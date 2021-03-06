package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LicenseDAO;
import entity.LicenseBean;
import exception.ServletServiceException;

/**
 * 資格を取得するクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */
@WebServlet("/LicenseInsertServlet")
public class LicenseInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LicenseInsertServlet() {
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
			String page = request.getParameter("page");
			String licenseName = request.getParameter("license_name");
			String licenseCd = request.getParameter("license_cd");

			/* DAOのインスタンス化 */
			LicenseDAO lic = new LicenseDAO();

			/* Beanのインスタンス化 */
			LicenseBean licB = new LicenseBean();

			if (page.equals("資格追加")) {
				url = "LicenseInsert.jsp";
			} else {
				/* DO NOTHING */
			}

			if (page.equals("追加")) {
				licB.setLicenseCd(licenseCd);
				licB.setLicenseName(licenseName);
				lic.licenseInsert(licB);

				url = "LicenseInsertSuccess.jsp";
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
