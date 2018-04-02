package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCar;
@WebServlet("/clearShoppingCart")
public class ClearShoppingCarServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ShoppingCar sc = (ShoppingCar) req.getSession().getAttribute("shoppingCar");
      sc.clear();
      req.getRequestDispatcher("/user/shopcart.jsp").forward(req, resp);
	}

}
