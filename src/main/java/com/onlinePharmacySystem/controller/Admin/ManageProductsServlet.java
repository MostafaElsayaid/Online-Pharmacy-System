package com.onlinePharmacySystem.controller.Admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.websocket.Session;

import com.onlinePharmacySystem.dao.ProductDao;
import com.onlinePharmacySystem.dao.Impl.ProductDaoImpl;
import com.onlinePharmacySystem.model.Product;

/**
 * Servlet implementation class ManageProductsServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
                 maxFileSize = 5 * 1024 * 1024,     // 5MB
                 maxRequestSize = 10 * 1024 * 1024) // 10MB
@WebServlet("/ManageProductsServlet")
public class ManageProductsServlet extends HttpServlet {
	@Resource(name ="jdbc/connection")
	private DataSource dataSource;
	
	private static final long serialVersionUID = 1L;

	private static final List<Product> HttpSession = null;
       
    
    public ManageProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	public void init() throws ServletException {
		  
	}
        
   // action = {add-item, update-item, remove-item, load-item, load-items}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		   System.out.println(action);
		   if (Objects.isNull(action)) {
			   action = "load-products";
		   }
		   
		   switch (action) {
		         
		        	 
		         case "remove-product":
			                    removeProduct(request,response);
								       break;
		     
		         case "load-products":
							try {
								loadProducts(request,response);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						        	   break;
						        	 
				 case "load-product":
					           loadProduct(request, response);
					           break;
				 case "add-description":
					           addDescription(request, response);
					           break;
		         default:
							
							 try {
								loadProducts(request,response);
							} catch (SQLException | IOException | ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			
		
		    }
		       
		}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");

		    if ("add-product".equals(action)) {
		        try {
		            addProduct(request, response);
		        } catch (Exception e) {
		            e.printStackTrace();
		            response.getWriter().write("Error while adding product: " + e.getMessage());
		        }
		    } else if ("edit-product".equals(action)) {
		    	 editProduct(request,response);
			}
		    
		    else {
		        response.sendRedirect(request.getContextPath() + "/jsp/admin/manage-products.jsp");
		    }
		    
		
	}
	
	public void loadProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		ProductDaoImpl ProductDaoImpl = new ProductDaoImpl(dataSource);
		
		List<Product> products = ProductDaoImpl.loadProducts();
		System.out.println(products);
		HttpSession session = request.getSession();
		session.setAttribute("Products", products);
		response.sendRedirect(request.getContextPath() + "/jsp/admin/manage-products.jsp");
		
		
	}
    public void editProduct(HttpServletRequest request, HttpServletResponse response) {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
   	   double price = Double.parseDouble(request.getParameter("price"));
   	   String description = request.getParameter("description");
   	   int quantity = Integer.parseInt(request.getParameter("quantity")) ;
   	  String image = request.getParameter("image");
   	  Product product = new Product();
   	  product.setProductId(id);
   	  product.setName(name);
   	  product.setPrice(price);
   	  product.setDescreption(description);
   	  product.setQuantity(quantity);
   	  product.setImagePath(image);
   	  ProductDaoImpl ProductDaoImpl = new ProductDaoImpl(dataSource);
      boolean updateProduct = ProductDaoImpl.updateProduct(product);
      System.out.println(updateProduct);
      if(updateProduct) {
    	    try {
				loadProducts(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      }
    	
		
	}
    public void removeProduct(HttpServletRequest request, HttpServletResponse response)  {
    	ProductDaoImpl ProductDaoImpl = new ProductDaoImpl(dataSource);
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		boolean removeProduct = ProductDaoImpl.removeProduct(id);
		System.out.println(removeProduct);
		if(removeProduct) {
			System.out.println("Item Removed");
			try {
				loadProducts(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
    public void loadProduct(HttpServletRequest request, HttpServletResponse response) {
	   int id = Integer.parseInt(request.getParameter("id"));
	   ProductDaoImpl productDaoImpl  = new ProductDaoImpl(dataSource);
	   Product product = productDaoImpl.loadProduct(id);
	   HttpSession session = request.getSession();
	   session.setAttribute("Product", product);
	   try {
       response.sendRedirect(request.getContextPath()+"/jsp/admin/edit-product.jsp");
		
	} catch ( IOException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	   
	
	}
    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
    	 String name = request.getParameter("name");
    	 double price = Double.parseDouble(request.getParameter("price"));
    	 String description = request.getParameter("description");
    	 int quantity = Integer.parseInt(request.getParameter("quantity")) ;
    	
         String image = request.getParameter("image");
        
    	  Product product = new Product();
    	  product.setName(name);
    	  product.setPrice(price);
    	  product.setDescreption(description);
    	  product.setQuantity(quantity);
    	  product.setImagePath(image);
    	  ProductDaoImpl ProductDaoImpl = new ProductDaoImpl(dataSource);
    	  boolean addProduct = ProductDaoImpl.addProduct(product);
		
		if(addProduct) {
			loadProducts(request, response);
		}
    	  
    	  
		
	}
    public void addDescription(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String description = request.getParameter("description");
    	System.out.println(id +"   "+description);
    	ProductDaoImpl productDaoImpl = new ProductDaoImpl(dataSource);
    	
		try {
			boolean addDescription = productDaoImpl.addDescription(id,description);
			HttpSession session = request.getSession();
	    	if (addDescription) {
	    		
	    		session.setAttribute("Message", "The process went Succesfully");
	    		loadProducts(request, response);
	    	}else {
				session.setAttribute("Message", "The process went Wrong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	
    	
    }

}
