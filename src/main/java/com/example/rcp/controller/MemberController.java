package com.example.rcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;
import com.example.rcp.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	@GetMapping("/profile/{memberId}")
	 public String getProfile(@PathVariable Integer memberId,@SessionAttribute LoginMember loginMember, Model model) throws Exception {


		Members member = memberService.getInfo(memberId);

		model.addAttribute("member", member);
		model.addAttribute("loginMember", loginMember);

		log.info("member{}", member);
		return "member/profile";
	}

}
