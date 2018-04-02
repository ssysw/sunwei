package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Order;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderAndLinesServiceImpl;

@WebServlet("/user/showLines")
public class ShowAllLinesByOrderIdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderAndLinesServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         long orderid =Long.parseLong(req.getParameter("orderid"));
         String path="/user/orderinfo.jsp";
         Order order=null;
         String showLinsMsg="";
	     try {
			 order = orderService.findById(orderid);
		} catch (OrderException e) {
			showLinsMsg=e.getMessage();
			e.printStackTrace();
		}
	     req.getSession().setAttribute("order", order);
	     req.getSession().setAttribute("showLinsMsg", showLinsMsg);
	     req.getRequestDispatcher(path).forward(req, resp);
	}

}
