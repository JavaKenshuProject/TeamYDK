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
import entity.EmployeeBean;
import exception.ServletServiceException;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		throw new ServletServiceException("最初からやり直してください");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		String url = null;
		HttpSession session = request.getSession(true);

		if((String)session.getAttribute("login") == null){
			url = "Login.jsp";
		}else{

		int val = -1;
		try{
			String cEmp = request.getParameter("employee");
			if(cEmp == null){
				throw new ServletServiceException("チェックボックスにチェックを入れてください");
			}
			val = Integer.parseInt(cEmp);
		}catch(NumberFormatException e){
			throw new ServletException();
		}

		if(val != -1){
			EmployeeDAO dao = new EmployeeDAO();
			ArrayList<EmployeeBean> empList = dao.employeeAllGet();
			dao.employeeDelete(empList.get(val));
			url = "DeleteSuccess.jsp";
		}

		if(url == null){
			throw new ServletServiceException("削除に失敗しました");
		}

		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
