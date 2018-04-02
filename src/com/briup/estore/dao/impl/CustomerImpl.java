package com.briup.estore.dao.impl;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.dao.ICustomerDao;

public class CustomerImpl implements ICustomerDao{
	private SqlSession session;
	private ICustomerDao i;
  public CustomerImpl() {
	  session = MyBatisSqlSessionFactory.openSession();  
	  i = session.getMapper(ICustomerDao.class);
}
	@Override
	public Customer findByName(String name) {
		// i = session.getMapper(ICustomerDao.class);
		Customer cu=null;
		try{
			 cu=i.findByName(name);
		}catch(Exception ce){
			ce.printStackTrace();
		}finally{
			session.close();
			
		}
		return cu;
	}

	@Override
	public void saveCustomer(Customer customer) {
		//session = MyBatisSqlSessionFactory.openSession();
		// i = session.getMapper(ICustomerDao.class);
		try{
			i.saveCustomer(customer);
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		//session = MyBatisSqlSessionFactory.openSession();
		// i = session.getMapper(ICustomerDao.class);
		try{
			i.updateCustomer(customer);
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
		
	}

}
