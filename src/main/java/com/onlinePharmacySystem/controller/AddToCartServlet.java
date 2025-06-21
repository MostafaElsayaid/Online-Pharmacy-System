package com.onlinePharmacySystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.Impl.CartDaoImpl;
import com.onlinePharmacySystem.model.CartDetails;
import com.onlinePharmacySystem.model.CartItem;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	@Resource(name = "jdbc/connection")
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String action = request.getParameter("action");
		  if ("showCartDetails".equals(action)) {
		        try {
		            showCartDetails(request, response);
		        } catch (Exception e) {
		            e.printStackTrace();
		            response.getWriter().write("Error : " + e.getMessage());
		        }
		  }else if ("updateCart".equals(action)) {
			  try {
				  updateCart(request,response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int productId = Integer.parseInt(request.getParameter("productId"));
		 int userId = Integer.parseInt(request.getParameter("userId"));
		 CartItem cart = new CartItem();
		 cart.setUserId(userId);
		 cart.setProductId(productId);
		 CartDaoImpl cartItemDao = new CartDaoImpl(dataSource);
		 
		 List<CartItem> cartCount = cartItemDao.getCarts(userId);
		 int cartCounter = cartCount.size();
		 System.out.println(cartCount);
		 HttpSession session = request.getSession();
		 session.setAttribute("cartCount", cartCounter);
		 boolean addCart = cartItemDao.addCart(cart);
		 if(addCart) {
			 session.setAttribute("addCart", "Add product to cart success");
			 response.sendRedirect(request.getContextPath()+"/jsp/Home.jsp");
		 }
		 
		 
		
		 
	}
	 public void showCartDetails(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 int userId = Integer.parseInt(request.getParameter("id"));
		 System.out.println("jhjhjh");
		 CartDaoImpl cartDaoImpl = new CartDaoImpl(dataSource);
		 List<CartDetails> cartDetails = cartDaoImpl.cartDetails(userId);
		 
		 HttpSession session = request.getSession();
		 session.setAttribute("cartDetails", cartDetails);
		 System.out.println("==================================");
			System.out.println(session);

		 response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

		 
		
	}
	public void updateCart(HttpServletRequest request,HttpServletResponse response) {
		
		
	}

}
