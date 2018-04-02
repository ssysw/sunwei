package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.Order;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderAndLinesServiceImpl;

@WebServlet("/user/showAllOrder")
public class showAllOrder extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private IOrderService orderService = new OrderAndLinesServiceImpl();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String path="/user/order.jsp";
		   Customer customer = (Customer) req.getSession().getAttribute("customer");
		   long id = customer.getId();
       List<Order> orders =null;
       try {
		orders=orderService.findByCustomerId(id);
	} catch (OrderException e) {
		e.printStackTrace();
	}
     req.setAttribute("orders", orders);
     req.getRequestDispatcher(path).forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
}
