package com.onlinePharmacySystem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.Impl.UserDaoImpl;
import com.onlinePharmacySystem.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User user = new User();
		user.setFULL_NAME(name);
		user.setEMAIL(email);
		user.setPASSWORD(password);
		UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
		boolean register = userDaoImpl.register(user);
		if (register) {
			PrintWriter out = response.getWriter();
			out.append("<h1> Register success  </h1>");
			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("registerError", "Error while register");
		}
		
		
	}

}
