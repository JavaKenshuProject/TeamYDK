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
 * Servlet implementation class EmployeeInsertServlet
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ↑ここまでデフォルト ↓ここから編集したやつ

		// 移譲する先のjspを格納する変数url
		String url = null;
		HttpSession session = request.getSession(true);

		if((String)session.getAttribute("login") == null){
			url = "Login.jsp";
		}else{

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
		}

		/*
		 * test 登録ボタンが押されたとき 以下の emp 情報を に セットして、 Emp.Ins.Successへ遷移
		 */
		if ((page != null) && (page.equals("登録"))) {
			if(request.getParameter("sex") == null){
				throw new ServletServiceException("性別にチェックを入れてください<br>");
			}
			// formの取得
			String emp_cd = request.getParameter("emp_cd"); // 従業員コード
			String l_name = request.getParameter("l_name"); // 氏
			String f_name = request.getParameter("f_name"); // 名
			String l_kana_name = request.getParameter("l_kana_name"); // 氏(カナ)
			String f_kana_name = request.getParameter("f_kana_name"); // 名(カナ)
			byte sex = (byte) Integer.parseInt(request.getParameter("sex")); // 性別
			String birth_year = request.getParameter("birth_year"); // 生年
			String birth_month = request.getParameter("birth_month"); // 月
			String birthday = request.getParameter("birth_day"); // 日
			String birth_day = birth_year + "-" + birth_month + "-" + birthday;
			// String section_code = request.getParameter("section_code"); //
			// 所属コード
			String start_year = request.getParameter("start_year"); // 入社年
			String start_month = request.getParameter("start_month"); // 月
			String start_day = request.getParameter("start_day"); // 日
			String emp_date = start_year + "-" + start_month + "-" + start_day;
			// String section_code = request.getParameter("section_code"); // 日
			SectionDAO section = new SectionDAO();
			ArrayList<SectionBean> sec_list = section.SectionAllGet();
			String section_code = null;
			String section_name = request.getParameter("section_name");

			for (SectionBean sec : sec_list) {
				if ((section_name != null) && (sec.getSection_name().equals(section_name))) {
					section_code = sec.getSection_code();
				}
			}
			String[] license = request.getParameterValues("license"); // 資格チェック
			String[] get_year = request.getParameterValues("get_year"); // 取得年
			String[] get_month = request.getParameterValues("get_month"); // 月
			String[]  get_day = request.getParameterValues("get_day"); // 日
			ArrayList<String> getdate = new ArrayList<String>();

			empB.setEmp_code(emp_cd);
			empB.setL_name(l_name);
			empB.setF_name(f_name);
			empB.setL_kana_name(l_kana_name);
			empB.setF_kana_name(f_kana_name);
			empB.setSex(sex);
			empB.setBirth_day(birth_day);
			empB.setSection_code(section_code);
			empB.setEmp_date(emp_date);

			if (license != null) {
				licB = lic.licenseAllGet().get(Integer.parseInt(license[0]));
				empB.setLicense_cd_SQLinsert(licB.getLicense_cd());
				getdate.add(get_year[Integer.parseInt(license[0])] + "-" + get_month[Integer.parseInt(license[0])] + "-" + get_day[Integer.parseInt(license[0])]);
				empB.setGet_license_date_SQLinsert(getdate.get(0));
			}

			emp.employeeInsert(empB);

			if (license != null) {
				for (int i = 1; i < license.length; i++) {
					licB = lic.licenseAllGet().get(Integer.parseInt(license[i]));
					empB.setLicense_cd_SQLinsert(licB.getLicense_cd());
					getdate.add(get_year[Integer.parseInt(license[i])] + "-" + get_month[Integer.parseInt(license[i])] + "-" + get_day[Integer.parseInt(license[i])]);
					empB.setGet_license_date_SQLinsert(getdate.get(i));
					emp.employeeUpdate(empB);
				}
			}

			url = "EmployeeInsertSuccess.jsp";
		}
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}
}