package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//エンコーディング指定
		 request.setCharacterEncoding("Windows-31J");
	     response.setCharacterEncoding("Windows-31J");

	     // formからの値を取得
	     String login = request.getParameter("login");


	     // 移譲する先のjspを格納する変数url
	     String url = "/Login.jsp";


	     if(login.equals("ログイン")){

	    	 //パラメーター取得
	    	 String userID = request.getParameter("userID");
		     String password = request.getParameter("password");

		     //DAO,Beanをインスタンス化
  			 ArrayList<UserBean> userList = new ArrayList<UserBean>();
		     UserDAO dao = new UserDAO();

		     //DAOからのreturnをBeanに格納
		     try{
		     	userList=dao.userAllGet();
		     }catch(Exception e){
		     }

//		     userList = (ArrayList<UserBean>)request.getAttribute("userList");

		     boolean flag = false;

		     for(UserBean user:userList){
		    	 if((user.getUser_id().equals(userID)) && (user.getPassword().equals(password)) ){
		    		 flag = true;
		    	 }
		     }

		     if(flag){
		    	 url="/Menu.jsp";
		     }else{
		    	 url="/Login.jsp";
		     }



			}

	     RequestDispatcher rd = request.getRequestDispatcher(url);
	        rd.forward(request, response);
	}

}



