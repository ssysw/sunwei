package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;
@WebServlet("/registerCustomer")
public class ReisterCustomerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ICustomerService customerService = new CustomerServiceImpl();
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String name = req.getParameter("username");
	    String password= req.getParameter("password");
	    String address = req.getParameter("address");
	    String zip = req.getParameter("zip");
	    String telephone = req.getParameter("telephone");
	    String email = req.getParameter("email");
	    
	    Customer customer = new Customer();
	    customer.setName(name);
	    customer.setPassword(password);
	    customer.setAddress(address);
	    customer.setZip(zip);
	    customer.setTelephone(telephone);
	    customer.setEmail(email);
	    
	    String path="";
	    String registerCustomereMsg="";
	    
	    try {
			customerService.register(customer);
			req.getSession().setAttribute("customer", customer);
			registerCustomereMsg="注册成功";
			path="/index.jsp";
		} catch (CustomerException e) {
			registerCustomereMsg="注册失败"+e.getMessage();
			path = "/register.jsp";
		}
		  req.getSession().setAttribute("registerCustomereMsg", registerCustomereMsg);
		  resp.sendRedirect(req.getContextPath()+path);
		}}

