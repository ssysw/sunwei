package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCar;

@WebServlet("/user/updateShoppingCartNum")
public class UpdateShoppingCartNum extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     long id = Long.parseLong(req.getParameter("bookid"));
	 ShoppingCar sc = (ShoppingCar) req.getSession().getAttribute("shoppingCar");
	 int num = Integer.parseInt(req.getParameter("num"));
	 sc.update(id, num);
	 req.getRequestDispatcher("/user/shopcart.jsp").forward(req, resp);
	}

}
