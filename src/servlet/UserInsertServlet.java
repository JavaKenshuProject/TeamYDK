package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.UserBean;

/**
 * Servlet implementation class UserInsertServlet
 */
@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if((session == null) || !((boolean)session.getAttribute("login"))){
			     RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			     rd.forward(request, response);
		}

		/* url宣言 */
		String url = null;

		/* エンコーディング */
		request.setCharacterEncoding("Windows-31J");
		response.setCharacterEncoding("Windows-31J");

		/* formの取得 */
		String page = request.getParameter("page");
		String user_id = request.getParameter("user_id");
		String password =request.getParameter("password");

		/* DAOのインスタンス化 */
		UserDAO userD = new UserDAO();

		/* Beanのインスタンス化*/
		UserBean user = new UserBean();

		if(page.equals("ユーザ登録")){
			url = "UserInsert.jsp";
		}

		if(page.equals("登録")){
			user.setUser_id(user_id);
			user.setPassword(password);
			userD.userInsert(user);

			url = "UserInsertSuccess.jsp";
		}

		/* 転送先 */
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}


	}
