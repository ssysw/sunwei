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
@WebServlet("/user/updateCustomer")
public class UpdateCustomerInfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ICustomerService customerService = new CustomerServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String id = req.getParameter("id");
    String name = req.getParameter("name");
    String password= req.getParameter("password");
    String address = req.getParameter("address");
    String zip = req.getParameter("zip");
    String telephone = req.getParameter("telephone");
    String email = req.getParameter("email");
    
    Customer customer = new Customer();
    customer.setId(Long.parseLong(id));
    customer.setName(name);
    customer.setPassword(password);
    customer.setAddress(address);
    customer.setZip(zip);
    customer.setTelephone(telephone);
    customer.setEmail(email);
    
    String path="/user/userinfo.jsp";
    String updatCustomereMsg="";
    
    try {
		customerService.updateCustomer(customer);
		req.getSession().setAttribute("customer", customer);
		updatCustomereMsg="更新成功";
	} catch (CustomerException e) {
		updatCustomereMsg="更新失败"+e.getMessage();
	}
	  req.getSession().setAttribute("updatCustomereMsg", updatCustomereMsg);
	  resp.sendRedirect(req.getContextPath()+path);
	}

}
