package com.onlinePharmacySystem.controller.Admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.Impl.ProductDaoImpl;
import com.onlinePharmacySystem.dao.Impl.UserDaoImpl;
import com.onlinePharmacySystem.model.Product;
import com.onlinePharmacySystem.model.User;

/**
 * Servlet implementation class ManageUsersServlet
 */
@WebServlet("/ManageUsersServlet")
public class ManageUsersServlet extends HttpServlet {
	@Resource(name ="jdbc/connection")
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		   System.out.println(action);
		   if (Objects.isNull(action)) {
			   action = "load-users";
		   }
		   
		   switch (action) {
		         
		        	 
		         case "remove-user":
			                    removeUser(request,response);
								       break;
		     
		         case "load-users":
							try {
								loadUsers(request,response);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						        	   break;
						        	 
				 case "load-user":
			try {
				loadUsers(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					           break;
				 case "edit-role":
					           editRole(request, response);
					           break;
		         default:
							
							 try {
								loadUsers(request,response);
							} catch (SQLException | IOException | ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			
		
		    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

	    if ("add-user".equals(action)) {
	        try {
	            addUser(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().write("Error while adding product: " + e.getMessage());
	        }
	    } else if ("edit-user".equals(action)) {
	    	 editUser(request,response);
		}
	    
	    else {
	        response.sendRedirect(request.getContextPath() + "/jsp/admin/manage-users.jsp");
	    }
	}
	public void loadUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
		
		List<User> users = userDaoImpl.loadUsers();
		System.out.println(users);
		HttpSession session = request.getSession();
		session.setAttribute("Users", users);
		response.sendRedirect(request.getContextPath() + "/jsp/admin/manage-users.jsp");
		
		
	}
	public void editUser(HttpServletRequest request, HttpServletResponse response) {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
   	    String email = request.getParameter("email");
   	    String password = request.getParameter("password");
   	  User user = new User();
   	  user.setEMAIL(email);
   	  user.setPASSWORD(password);
   	  user.setFULL_NAME(name);
   	  UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
      boolean updateUser = userDaoImpl.updateUser(user);
      System.out.println(updateUser);
      if(updateUser) {
    	    try {
				loadUsers(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
    	
		
	}
	public void removeUser(HttpServletRequest request, HttpServletResponse response)  {
	    	UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			boolean removeUser = userDaoImpl.removeUser(id);
			System.out.println(removeUser);
			if(removeUser) {
				System.out.println("Item Removed");
				try {
					loadUsers(request, response);
				} catch (SQLException | IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	public void loadProduct(HttpServletRequest request, HttpServletResponse response) {
		   int id = Integer.parseInt(request.getParameter("id"));
		   UserDaoImpl userDaoImpl  = new UserDaoImpl(dataSource);
		   User user = userDaoImpl.loadUser(id);
		   HttpSession session = request.getSession();
		   session.setAttribute("User", user);
		   try {
	       response.sendRedirect(request.getContextPath()+"/jsp/admin/edit-user.jsp");
			
		} catch ( IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		   
		
		}
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		String name = request.getParameter("name");
   	    String email = request.getParameter("email");
   	    String password = request.getParameter("password");
   	 
       
   	  User user = new User();
  	  user.setEMAIL(email);
  	  user.setPASSWORD(password);
  	  user.setFULL_NAME(name);
  	  UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
     boolean addUser = userDaoImpl.addUser(user);
		
		if(addUser) {
			loadUsers(request, response);
		}
   	  
   	  
		
	}
	public void  editRole(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String role = request.getParameter("role");
    	System.out.println(id +"   "+role);
    	UserDaoImpl userDaoImpl = new UserDaoImpl(dataSource);
    	
		try {
			boolean editRole = userDaoImpl.updateRole(id,role);
			HttpSession session = request.getSession();
	    	if (editRole) {
	    		
	    		session.setAttribute("Message", "The process went Succesfully");
	    		loadUsers(request, response);
	    	}else {
				session.setAttribute("Message", "The process went Wrong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	
    	
    }
	
	
}
