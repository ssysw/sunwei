package com.briup.estore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.estore.bean.Customer;
@WebFilter("/user/*")
public class loginFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
    HttpServletRequest request =	(HttpServletRequest) srequest;
    HttpServletResponse response = (HttpServletResponse) sresponse;
    String path="";
    Customer customer = (Customer) request.getSession().getAttribute("customer");
     if(customer==null){
    	 path="/login.jsp";
    	 response.sendRedirect(request.getContextPath()+path);
    	
     }else{
    	 chain.doFilter(srequest, sresponse);
     }
     
     
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
