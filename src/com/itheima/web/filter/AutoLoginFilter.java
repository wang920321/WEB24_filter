package com.itheima.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;

public class AutoLoginFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		//定义cookie_username
		String cookie_username=null;
		//定义cookie_password
		String cookie_password=null;
		//获得cookie中的用户名和密码进行登录操作
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				//获得名字为cookie_username和cookie_password
				if("cookie_username".equals(cookie.getName())){
					cookie_username=cookie.getValue();
					System.out.println(cookie.getValue());
				}
				if("cookie_password".equals(cookie.getName())){
					cookie_password=cookie.getValue();
				}

			}
		}
		
		//判断username和password是否为null
		if(cookie_username!=null&&cookie_password!=null){
			UserService service=new UserService();
			User user=null;
			try {
				user=service.login(cookie_username,cookie_password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将登录成功的user对象存到session中
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
		}
		//放行
		chain.doFilter(req,res );
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
