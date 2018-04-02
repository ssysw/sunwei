package com.briup.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Line;
import com.briup.estore.bean.Order;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.OrderException;
import com.briup.estore.dao.ILineDao;
import com.briup.estore.dao.IOrderDao;
import com.briup.estore.service.IOrderService;

public class OrderAndLinesServiceImpl implements IOrderService{
   private IOrderDao orderDao;
   private ILineDao lineDao;
   /*
    *保存订单和订单明细
    */
   @Override
   public void saveOrderAndLines(Order order) throws OrderException {
	   SqlSession session = MyBatisSqlSessionFactory.openSession();
	   orderDao = session.getMapper(IOrderDao.class);
	   lineDao = session.getMapper(ILineDao.class);
	   try {
		orderDao.saveOrder(order);
		for(Line line:order.getLines()){
			line.setOrder(order);
			lineDao.savaLine(line);
		}
		session.commit();
	} catch (Exception e) {
		session.rollback();
		e.printStackTrace();
		throw new OrderException("提交失败");
	}
	}
@Override
public void deleteOrder(Long id) throws OrderException {
	 SqlSession session = MyBatisSqlSessionFactory.openSession();
	   orderDao = session.getMapper(IOrderDao.class);
	   lineDao = session.getMapper(ILineDao.class);
	   
	   try {
		   lineDao.deleteLineByOrderId(id);
		   orderDao.deleteOrderById(id);
		session.commit();
	} catch (Exception e) {
		session.rollback();
		throw new OrderException("删除失败");
	}
	
}
@Override
public Order findById(Long id) throws OrderException {
	 SqlSession session = MyBatisSqlSessionFactory.openSession();
	   orderDao = session.getMapper(IOrderDao.class);
	 //  lineDao = session.getMapper(ILineDao.class);
	   Order order = null;
	   try {
		order = orderDao.findOrderById(id);
	} catch (Exception e) {
		e.printStackTrace();
		throw new OrderException("查找失败");
		
	}
	   
	return order;
}
@Override
public List<Order> findByCustomerId(Long id) throws OrderException {
	  SqlSession session = MyBatisSqlSessionFactory.openSession();
	   orderDao = session.getMapper(IOrderDao.class);
	   List<Order> orders = null;
	   try {
		orders=orderDao.findOrderByCustomerId(id);
	} catch (Exception e) {
		throw new OrderException("有错误");
	}
	return orders;
}


}
