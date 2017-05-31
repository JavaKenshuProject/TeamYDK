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
		doGet(request, response);
//↑ここまでデフォルト　↓ここから編集したやつ

	    // 移譲する先のjspを格納する変数url
		String url = null;

		//エンコーディング指定
		request.setCharacterEncoding("Windows-31J");
	    response.setCharacterEncoding("Windows-31J");

		/* EmployeeDAOの生成 */
		EmployeeDAO emp = new EmployeeDAO();
		ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();

		/* セットする */
		request.setAttribute("employeeList",employeeList);

		/* 移動先の設定 */
		url = "EmployeeInsertSuccess.jsp";

		/* 転送先*/
		RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

	    // リクエスト オブジェクトから、　パラメータの取得
    /*
	     if(login.equals("登録")){
     *  String emp_code = request.getParameter("emp_code");			//従業員コード
        String l_name = request.getParameter("l_name");				//氏
        String f_name = request.getParameter("f_name");				//名
        String l_kana_name = request.getParameter("l_kana_name");		//氏(カナ)
        String f_kana_name = request.getParameter("f_kana_name");		//名(カナ)
        byte sex = request.getParameter("sex");						//性別
        String birth_year[] = request.getParameterValues("birth_year");	//生年
        String birth_month[] = request.getParameterValues("birth_month");	//　　月
        String birth_day[] = request.getParameterValues("birth_day");		//　　日
        String section_code = request.getParameter("section_code");	//所属コード
        String start_year[] = request.getParameterValues("start_year");	//入社年
        String start_month[] = request.getParameterValues("start_month");	//　　月
        String start_day[] = request.getParameterValues("start_day");		//　　日
        String license[] =request.getParameterValues("license");		//資格チェック
        String  = request.getParameter("");		*/						//登録

	}



}