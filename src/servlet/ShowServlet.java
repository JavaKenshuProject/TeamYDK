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
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//移譲する先のjspを格納する変数url
		String url = null;

		//エンコーディング指定

		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		//formからの値を取得
	    String employee = request.getParameter("employee");
	    String page = request.getParameter("page");


		/* DAOの生成 */
		EmployeeDAO emp = new EmployeeDAO();

		if (page.equals("従業員一覧")){
		ArrayList<EmployeeBean> employeeList = emp.employeeAllGet();

		//セットする
		request.setAttribute("employeeList", employeeList);

		//移動先の設定
		url = "ShowEmployee.jsp";

		}if (page.equals("削除")) {
		url = "EmployeeDelete.jsp";
		}

	if(page.equals("変更")){
		url = "ChangeServlet.jsp";
	}
	/* 転送先 */
	RequestDispatcher rd = request.getRequestDispatcher(url);
	rd.forward(request, response);


	}


	}




