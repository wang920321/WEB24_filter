package com.itheima.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class QuickFilter2 implements Filter{

	/* Filter创建的时候执行init方法    服务器启动的时候创建
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//1获得web.xml中filter名称
		System.out.println(filterConfig.getFilterName());
		//2获得当前filter初始化参数
		filterConfig.getInitParameter("aaa");
		System.out.println("init.......");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("quick2 running....");
		chain.doFilter(request, response);
		
	}

	/* Filter对象销毁的时候执行destroy方法   服务器关闭的时候销毁
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("destroy.........");
		
	}

}
