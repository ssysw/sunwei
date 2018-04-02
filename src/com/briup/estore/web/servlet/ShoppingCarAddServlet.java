package com.briup.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Book;
import com.briup.estore.bean.Line;
import com.briup.estore.bean.ShoppingCar;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImpl;

@WebServlet("/user/shoppingCarAdd")
public class ShoppingCarAddServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  Long bookid = Long.parseLong(req.getParameter("bookid"));
	  String path="/index.jsp";
	  String addShoppingMsg="";
	  try {
		Book book = bookService.findById(bookid);
		ShoppingCar sc = (ShoppingCar) req.getSession().getAttribute("shoppingCar");
		Line line = new Line();
	    line.setBook(book);
	    sc.add(line);
	    addShoppingMsg="添加成功";
	  
	  } catch (BookException e) {
		  addShoppingMsg="添加失败";
		e.printStackTrace();
	}
	  req.getSession().setAttribute("addShoppingMsg", addShoppingMsg);
	  resp.sendRedirect(req.getContextPath()+path);
	}

}
