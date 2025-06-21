package com.onlinePharmacySystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.websocket.Session;

import com.onlinePharmacySystem.dao.Impl.UserDaoImpl;
import com.onlinePharmacySystem.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String email = request.getParameter("email");
		 String password = request.getParameter("password");
		 System.out.println(email+" "+password);
		 UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
		 try {
			User user = userDaoImpl.login(email, password);
			if (user!=null ) {
				 if(user.getUSER_ROLE().equals("CUSTOMER")) {
					 HttpSession session = request.getSession();
					 session.setAttribute("User", user);
					 response.sendRedirect("HomeServlet");
				 }else if (user.getUSER_ROLE().equals("ADMIN")){
						HttpSession session = request.getSession();
						session.setAttribute("User", user);
						 response.sendRedirect(request.getContextPath()+"/jsp/admin/dashboard.jsp");
					}
			}
				
			else {
				 HttpSession session = request.getSession();
				session.setAttribute("loginError", "Login faild");
				 response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			}	 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				 
		 
		 
		 
	}

}
