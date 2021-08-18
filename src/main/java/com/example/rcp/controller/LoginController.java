package com.example.rcp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.rcp.domain.Members;


@Controller
public class LoginController {
	

	@GetMapping("/")
	public String loginForm(Model model) {
		model.addAttribute("member", new Members());
		return "loginForm";
	}

	

}
