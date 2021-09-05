package com.example.rcp.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		String requestURI = request.getRequestURI();
		
		log.info("lonincheck interceptor action {}",requestURI);
		
		HttpSession session = request.getSession();
		
		if(session == null || session.getAttribute("loginMember")==null) {
			log.info("Unauthenticated user");
			
			//login redirect
			response.sendRedirect("/login?redirectURL=" + requestURI);
			return false;
			
		}
		
		
		return true;
	}
	
	
	

}
