package com.briup.estore.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.briup.estore.bean.ShoppingCar;
@WebListener
public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		  ShoppingCar sc = new ShoppingCar();
		  session .setAttribute("shoppingCar", sc);
		  System.out.println("session创建了"+se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	System.out.println("销毁session了"+se.getSession());
	}

}
