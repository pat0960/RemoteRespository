package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerService;
import model.dao.CustomerDAOJdbc;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		Map<String,String> errorMsgMap=new HashMap<String,String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String requestURI=(String) session.getAttribute("requestURI");
		if(name==null||name.trim().length()==0){
			errorMsgMap.put("AccountEmptyError","姓名必須輸入");	
		}
		if(password==null||password.trim().length()==0){
			errorMsgMap.put("PasswordEmptyError","密碼必須輸入");
		}
		if(!errorMsgMap.isEmpty()){
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
		CustomerService logindb= new CustomerService();	
		CustomerBean mb=logindb.login(name, password);
		if(mb !=null){
			session.setAttribute("LoginOk",mb);			
		}else{
			errorMsgMap.put("LoginError","該帳號不存在或密碼錯誤");			
		}
		if(errorMsgMap.isEmpty()){
		RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		return;
			}		
		else{
			RequestDispatcher rd=request.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
		}
			
		
		
		
			}
				

	

}
