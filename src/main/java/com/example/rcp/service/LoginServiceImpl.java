package com.example.rcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;
import com.example.rcp.mapper.MembersMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	MembersMapper membersMapper;

	/**
	 * @return nullの場合、ログイン失敗
	 * @throws Exception 
	 */

		@Override
		public LoginMember login(String email, String password) throws Exception {
			
	
			LoginMember member = membersMapper.findByEmail(email);
			if(member == null) {
				return null;
			}else if (member.getMemberPassword().equals(password)) {
				return member;
			}else {
				return null;
			}
		
		
		}

}
