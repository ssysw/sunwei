package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Book;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.BookException;
import com.briup.estore.dao.IBookDao;
import com.briup.estore.dao.impl.BookImpl;
import com.briup.estore.service.IBookService;

public class BookServiceImpl implements IBookService{
	private IBookDao bookDao;
   public BookServiceImpl() {
	   bookDao = new BookImpl();
}
	@Override
	public List<Book> listAllBooks() throws BookException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		bookDao = session.getMapper(IBookDao.class);
		return bookDao.queryAll();
	}

	@Override
	public Book findById(Long id) throws BookException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		bookDao = session.getMapper(IBookDao.class);
		Book book =  bookDao.queryById(id);
		if(book==null){
			throw new BookException("该书已经售完");
		}
		return book;
	}

}
