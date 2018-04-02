package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.service.ICustomerService;
import com.briup.estore.service.impl.CustomerServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICustomerService customerService = new CustomerServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String path="";
        String loginMsg="";
        HttpSession session = req.getSession();
        try {
			Customer customer = customerService.login(name, password);
			path="/index.jsp";
			session.setAttribute("customer", customer);
		} catch (CustomerException e) {
		    path="/login.jsp";
		    loginMsg=e.getMessage();
			e.printStackTrace();
		}
	     resp.sendRedirect(req.getContextPath()+path);
	}

}
