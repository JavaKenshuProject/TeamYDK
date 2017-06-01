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
 * Servlet implementation class EmployeeInsertServlet
 */
@WebServlet("/EmployeeInsertServlet")
public class EmployeeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmployeeInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//↑ここまでデフォルト　↓ここから編集したやつ

	    // 移譲する先のjspを格納する変数url
		String url = null;

		//エンコーディング指定
		request.setCharacterEncoding("Windows-31J");
	    response.setCharacterEncoding("Windows-31J");

		/* web操作による有無 */
		String page = request.getParameter("page");

		/* DAOのインスタンス化 */
		EmployeeDAO emp = new EmployeeDAO();
		LicenseDAO lic = new LicenseDAO();

		/* Beanのインスタンス化 */
    	EmployeeBean empB=new EmployeeBean();
    	LicenseBean licB=new LicenseBean();

    	//従業員登録画面には、資格一覧も表示しなくてはいけない
		if ((page!=null)&&(page.equals("従業員登録"))) {
			ArrayList<LicenseBean> licenseList=lic.licenseAllGet();
			//セットする
			request.setAttribute("licenseList",licenseList);
			url="EmployeeInsert.jsp";
		}

		//formの取得
			String emp_code = request.getParameter("emp_code");			//従業員コード
	        String l_name = request.getParameter("l_name");				//氏
	        String f_name = request.getParameter("f_name");				//名
	        String l_kana_name = request.getParameter("l_kana_name");		//氏(カナ)
	        String f_kana_name = request.getParameter("f_kana_name");		//名(カナ)
	        byte[] sex = request.getParameter("sex").getBytes();							//性別
	        String birth_year = request.getParameter("birth_year");		//生年
	        String birth_month = request.getParameter("birth_month");	//　　月
	        String birth_day = request.getParameter("birth_day");		//　　日
	        String birth = birth_year + "-" + birth_month + "-" + birth_day;
	        String section_code = request.getParameter("section_code");		//所属コード
	        String start_year = request.getParameter("start_year");		//入社年
	        String start_month = request.getParameter("start_month");	//　　月
	        String start_day = request.getParameter("start_day");		//　　日
	        String start = start_year + "-" + start_month + "-" + start_day;
	        String license[] =request.getParameterValues("license");		//資格チェック

	        /* 移動先の設定 */
		if (page.equals("登録")){

			empB.setEmp_code(emp_code);
	        empB.setL_name(l_name);
	        empB.setF_name(f_name);
	        empB.setL_kana_name(l_kana_name);
	        empB.setF_kana_name(f_kana_name);
	        empB.setSex(sex[0]);
	        empB.setBirth_day(birth);
	        empB.setSection_code(section_code);
	        empB.setEmp_date(start);
	        empB.setLicense_cd_SQLinsert(licB.getLicense_cd());
//licenseのbeanのインスタンスか

			url = "EmployeeInsertSuccess.jsp";
		}

		/* 転送先*/
		RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

		}
}