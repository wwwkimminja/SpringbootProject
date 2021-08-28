package com.example.rcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rcp.domain.Members;
import com.example.rcp.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	

	@GetMapping("/{memberId}")
	public String getInfo(@PathVariable int memberId, Model model) throws Exception {
		
		Members member=memberService.getInfo(memberId);
		
		model.addAttribute("member", member);
		
		return "memebers/profile";
	}
}
