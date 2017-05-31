package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//エンコーディング指定
		 request.setCharacterEncoding("Windows-31J");
	     response.setCharacterEncoding("Windows-31J");

	     // formからの値を取得
	     String move = request.getParameter("move");
	     String page = request.getParameter("page");

	     // 移譲する先のjspを格納する変数url
	     String url = "Logout.jsp";

	   //セッション管理
			HttpSession session =request.getSession() ;

	     if(page.equals("ログアウト")){
	    	// セッション破棄
	            session.invalidate();
	            url = "Logout.jsp";
	     }

	     if(move.equals("ログイン画面へ")){
	    	 url = "Login.jsp";
	     }

	     RequestDispatcher rd = request.getRequestDispatcher(url);
	        rd.forward(request, response);
	}

}
