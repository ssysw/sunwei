package com.briup.estore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;
@WebServlet("/CheckUserName")
public class CheckUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ICustomerService customerService = new CustomerServiceImpl();
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   String name = req.getParameter("username");
   PrintWriter out= resp.getWriter();
   try {
 Customer customer= 	customerService.checkCustomerName(name);
 if(customer!=null){
	 out.print(true);
 }
	out.print(false);
} catch (CustomerException e) {
	e.printStackTrace();
}
   }
}
