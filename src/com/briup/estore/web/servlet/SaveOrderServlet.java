package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.Line;
import com.briup.estore.bean.Order;
import com.briup.estore.bean.ShoppingCar;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.service.IOrderService;
import com.briup.estore.service.impl.OrderAndLinesServiceImpl;
@WebServlet("/user/saveOrderAndLines")
public class SaveOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L; 
	private IOrderService orderService = new OrderAndLinesServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 ShoppingCar sc = (ShoppingCar) req.getSession().getAttribute("shoppingCar");
    	 Customer customer = (Customer) req.getSession().getAttribute("customer");
    	 Collection<Line> lines = sc.getLines().values();//获取订单的所有订单项
         Order order = new Order();
         order.setCost(sc.getCost());
         order.setOrderDate(new Date());
         order.setCustomer(customer);
         order.setLines(lines);
         String path="";
         String dinnDanMsg="";
         try {
			orderService.saveOrderAndLines(order);
			path="/user/showAllOrder";
			dinnDanMsg="订单提交成功";
			
		} catch (OrderException e) {
			e.printStackTrace();
			path="/user/showAllOrder";
			dinnDanMsg="订单提交失败"+e.getMessage();
		}
         req.getSession().setAttribute("dinnDanMsg", dinnDanMsg);
        req.getRequestDispatcher(path).forward(req, resp);;
    }
}
