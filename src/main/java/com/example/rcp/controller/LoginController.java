package com.example.rcp.controller;




import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.rcp.domain.Members;
import com.example.rcp.mapper.MembersMapper;
import com.example.rcp.service.LoginService;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	MembersMapper membersMapper;
	
	
	
	@GetMapping("/")
	public String homeLogin(@CookieValue(name="memberId",required= false) Integer memberId, Model model) throws Exception {
		if(memberId == null) {
			return "home";
			
		}
		
		//login
		Members loginMember = membersMapper.findById(memberId);
		if(loginMember == null) {
			return "home";
		}
		
		model.addAttribute("member",loginMember);
		log.info("memberName={}",loginMember.getMemberName());
	
		
		return "loginHome";
	}

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("member") Members member) {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("member") Members member,BindingResult bindingResult, HttpServletResponse response) throws Exception {
		
		log.info("memberEmail={}", member.getMemberEmail());
		
		Members loginMember = loginService.login(member.getMemberEmail(),member.getMemberPassword());
		
		//ログイン失敗
		if(loginMember == null) {
			bindingResult.reject("loginFail");

			return "loginForm";
		}
		
		//ログイン成功TODO 			
		Cookie idCooki = new Cookie("memberId",String.valueOf(loginMember.getMemberId()));
		response.addCookie(idCooki);
		
		
		return "redirect:/";
		
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		
		Cookie cookie= new Cookie("memberId",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	

	

}
