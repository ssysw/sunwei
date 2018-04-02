package com.briup.estore.dao;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.bean.Order;
import com.briup.estore.common.MyBatisSqlSessionFactory;

public class Test {
	public void testCustomer(){
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		ICustomerDao i = session.getMapper(ICustomerDao.class);
		Customer  c = new Customer( "lisi", "45612", "456", null, "1234666789", "qq.com");
		  try{
			 i.updateCustomer(c);
			  System.out.println("成功");
			  session.commit();
		  }catch(Exception e){
			  System.out.println("失败");
			  session.rollback();
			  e.printStackTrace();
		  }finally{
			  session.close();
		  }
	}
  public static void main(String[] args) {
	  SqlSession session = MyBatisSqlSessionFactory.openSession();
	IOrderDao   orderDao = session.getMapper(IOrderDao.class);
	 Order order = orderDao.findOrderById(18L);
	 System.out.println(order);
	//orderDao.deleteOrderById(18L);
	session.commit();
	  /* List<Order> orders = null;
		orders=orderDao.findOrderByCustomerId(11L);
		for(Order order :orders){
			System.out.println(order);
		}*/
	
}
}
