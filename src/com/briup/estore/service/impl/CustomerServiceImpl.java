 package com.briup.estore.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.briup.estore.bean.Customer;
import com.briup.estore.common.MyBatisSqlSessionFactory;
import com.briup.estore.common.exception.CustomerException;
import com.briup.estore.dao.ICustomerDao;
import com.briup.estore.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService {
	private ICustomerDao customerDao;
	

	@Override
	public void register(Customer customer)throws CustomerException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		try{
		customerDao.saveCustomer(customer);
		session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		}
	}

	@Override
	public Customer login(String name, String password) throws CustomerException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		if (name == null || password == null || "".equals(name.trim()) || "".equals(password.trim())) {

		}

		Customer customer = customerDao.findByName(name);

		if (customer == null || !password.equals(customer.getPassword())) {
			throw new CustomerException("用户名或密码不正确");
		}
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer)throws CustomerException  {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		
		String password = customer.getPassword();
		if(password==null||"".equals(password.trim())){
			throw new CustomerException("密码不能为空");
		}
		try{
			customerDao.updateCustomer(customer);
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
			throw new CustomerException(e.getMessage());

		}
		
	}

	@Override
	public Customer checkCustomerName(String name) throws CustomerException {
		SqlSession session = MyBatisSqlSessionFactory.openSession();
		customerDao = session.getMapper(ICustomerDao.class);
		if("".equals(name.trim())){
			throw new CustomerException("请输入用户名");
		}
		Customer customer = customerDao.findByName(name);
		return customer;
	}

}
