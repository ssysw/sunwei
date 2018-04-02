package com.briup.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Book;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.service.IBookService;
import com.briup.estore.service.impl.BookServiceImpl;

/**
 * Servlet implementation class showCart
 */
@WebServlet("/showCart")
public class showCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBookService bookService = new BookServiceImpl();
	  @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map<Long,Integer> map =(Map<Long, Integer>) req.getSession().getAttribute("cart");
		Double sumPrice =0.0;
		Book book =null;
		Map<Book,Integer>books = new HashMap<Book,Integer>();
		if(map!=null){
			Set<Long> keys =map.keySet();
			 for(Long id:keys){
				 try {
					book = bookService.findById(id);
					books.put(book,map.get(id));
					sumPrice+=(book.getPrice() * map.get(id));
				} catch (BookException e) {
					e.printStackTrace();
				}
			 }
			 Map<String,Integer>li = new HashMap<String,Integer>();
			
			 req.getSession().setAttribute("booksShow", books);
			 req.getSession().setAttribute("sumPrice", sumPrice);
			 resp.sendRedirect(req.getContextPath()+"/user/shopcart.jsp");
		}else{
			req.getSession().setAttribute("msgBook", "您目前没添加购物车");
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}
	}

}
