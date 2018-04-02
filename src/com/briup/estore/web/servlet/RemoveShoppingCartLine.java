package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.ShoppingCar;

@WebServlet("/user/removerShoppingLine")
public class RemoveShoppingCartLine extends HttpServlet{
	private static final long serialVersionUID = 1L;
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	long bookid=Long.parseLong(req.getParameter("bookid"));
	ShoppingCar sc= (ShoppingCar) req.getSession().getAttribute("shoppingCar");
	sc.delete(bookid);
	String path = "/user/shopcart.jsp";
	req.getRequestDispatcher(path).forward(req, resp);
}
}
