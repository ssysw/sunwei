package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderAndLinesServiceImpl;

@WebServlet("/user/deleteOrder")
public class deleteOrderServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderAndLinesServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long orderId = Long.parseLong(req.getParameter("orderid"));
		String path="/user/showAllOrder";
		String deleteOderMsg="";
		try {
			orderService.deleteOrder(orderId);
			deleteOderMsg="删除成功";
		} catch (OrderException e) {
			deleteOderMsg=e.getMessage();
		}
		req.getSession().setAttribute("deleteOderMsg", deleteOderMsg);
		req.getRequestDispatcher(path).forward(req, resp);
	}

}
