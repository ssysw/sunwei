package com.briup.estore.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Book;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.dao.IBookDao;

public class BookImpl implements IBookDao{
	private SqlSession session;
	private IBookDao i;
	  public BookImpl() {
		  session = MyBatisSqlSessionFactory.openSession();  
		  i = session.getMapper(IBookDao.class);
	}
	@Override
	public List<Book> queryAll() {
		return i.queryAll();
	}

	@Override
	public Book queryById(Long id) {
		// TODO Auto-generated method stub
		return i.queryById(id);
	}

}
