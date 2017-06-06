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
 * 従業員情報を追加するクラス
 *
 * @author TeamB-YDK
 * @version 1.00
 */
@WebServlet("/EmployeeInsertServlet")
public class EmployeeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		// ↑ここまでデフォルト ↓ここから編集したやつ

		// 移譲する先のjspを格納する変数url
		String url = null;
		HttpSession session = request.getSession(true);

		if ((String) session.getAttribute("login") == null) {
			url = "Login.jsp";
		} else {

			// エンコーディング指定
			request.setCharacterEncoding("Windows-31J");
			response.setCharacterEncoding("Windows-31J");

			/* web操作による有無 */
			String page = request.getParameter("page");

			/* DAOのインスタンス化 */
			EmployeeDAO emp = new EmployeeDAO();
			LicenseDAO lic = new LicenseDAO();

			/* Beanのインスタンス化 */
			EmployeeBean empB = new EmployeeBean();
			LicenseBean licB = new LicenseBean();

			// 従業員登録画面には、資格一覧も表示しなくてはいけない
			if ((page != null) && (page.equals("従業員登録"))) {
				ArrayList<LicenseBean> licenseList = lic.licenseAllGet();
				// セットする
				request.setAttribute("licenseList", licenseList);
				url = "EmployeeInsert.jsp";
			} else {
				/* DO NOTHING */
			}

			/*
			 * test 登録ボタンが押されたとき 以下の emp 情報を に セットして、 Emp.Ins.Successへ遷移
			 */
			if ((page != null) && (page.equals("登録"))) {
				if (request.getParameter("sex") == null) {
					throw new ServletServiceException("性別にチェックを入れてください<br>");
				} else {
					/* DO NOTHING */
				}
				// formの取得
				String empCd = request.getParameter("emp_cd"); // 従業員コード
				String lName = request.getParameter("l_name"); // 氏
				String fName = request.getParameter("f_name"); // 名
				String lKanaName = request.getParameter("l_kana_name"); // 氏(カナ)
				String fKanaName = request.getParameter("f_kana_name"); // 名(カナ)
				byte sex = (byte) Integer.parseInt(request.getParameter("sex")); // 性別
				String birthYear = request.getParameter("birth_year"); // 生年
				String birthMonth = request.getParameter("birth_month"); // 月
				String birthday = request.getParameter("birth_day"); // 日
				String birthDay = birthYear + "-" + birthMonth + "-" + birthday;
				// String section_code = request.getParameter("section_code");
				// //
				// 所属コード
				String startYear = request.getParameter("start_year"); // 入社年
				String startMonth = request.getParameter("start_month"); // 月
				String startDay = request.getParameter("start_day"); // 日
				String empDate = startYear + "-" + startMonth + "-" + startDay;
				// String section_code = request.getParameter("section_code");
				// // 日
				SectionDAO section = new SectionDAO();
				ArrayList<SectionBean> secList = section.SectionAllGet();
				String sectionCode = null;
				String sectionName = request.getParameter("section_name");

				for (SectionBean sec : secList) {
					if ((sectionName != null) && (sec.getSectionName().equals(sectionName))) {
						sectionCode = sec.getSectionCode();
					} else {
						/* DO NOTHING */
					}
				}
				String[] license = request.getParameterValues("license"); // 資格チェック
				String[] getYear = request.getParameterValues("get_year"); // 取得年
				String[] getMonth = request.getParameterValues("get_month"); // 月
				String[] getDay = request.getParameterValues("get_day"); // 日
				ArrayList<String> getdate = new ArrayList<String>();

				empB.setEmpCode(empCd);
				empB.setLName(lName);
				empB.setFName(fName);
				empB.setLKanaName(lKanaName);
				empB.setFKanaName(fKanaName);
				empB.setSex(sex);
				empB.setBirthDay(birthDay);
				empB.setSectionCode(sectionCode);
				empB.setEmpDate(empDate);

				if (license != null) {
					licB = lic.licenseAllGet().get(Integer.parseInt(license[0]));
					empB.setLicenseCdSQLinsert(licB.getLicenseCd());
					getdate.add(getYear[Integer.parseInt(license[0])] + "-" + getMonth[Integer.parseInt(license[0])]
							+ "-" + getDay[Integer.parseInt(license[0])]);
					empB.setGetLicenseDateSQLinsert(getdate.get(0));
				} else {
					/* DO NOTHING */
				}

				emp.employeeInsert(empB);

				if (license != null) {
					for (int i = 1; i < license.length; i++) {
						licB = lic.licenseAllGet().get(Integer.parseInt(license[i]));
						empB.setLicenseCdSQLinsert(licB.getLicenseCd());
						getdate.add(getYear[Integer.parseInt(license[i])] + "-" + getMonth[Integer.parseInt(license[i])]
								+ "-" + getDay[Integer.parseInt(license[i])]);
						empB.setGetLicenseDateSQLinsert(getdate.get(i));
						emp.employeeUpdate(empB);
					}
				} else {
					/* DO NOTHING */
				}

				url = "EmployeeInsertSuccess.jsp";
			} else {
				/* DO NOTHING */
			}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
}