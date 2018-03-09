package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserService service=new UserService();
		User user=null;
		try {
			user=service.login(username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null){
			//登录成功
			String autoLogin=req.getParameter("autoLogin");
			System.out.println(autoLogin);
			if(autoLogin!=null){
				Cookie cookie_username=new Cookie("cookie_username",user.getUsername());
				Cookie cookie_password=new Cookie("cookie_password",user.getPassword());
				//设置cookie的持久化时间
				cookie_username.setMaxAge(60*60);
				cookie_password.setMaxAge(60*60);
				//设置cookie的携带路径
				cookie_username.setPath(req.getContextPath());
				cookie_password.setPath(req.getContextPath());
				//发送cookie
				res.addCookie(cookie_username);
				System.out.println(cookie_username);
				res.addCookie(cookie_password);
			}
			
			//将登录成功的user对象存到session中
			
			session.setAttribute("user", user);
			//重定向到首页
			res.sendRedirect(req.getContextPath());
		}else{
			//失败转发到登录页面，给出提示信息
			req.setAttribute("loginInfo", "用户名或密码错误");
			req.getRequestDispatcher("/login.jsp").forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}