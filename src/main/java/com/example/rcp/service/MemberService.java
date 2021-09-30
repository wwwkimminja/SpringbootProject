package com.example.rcp.service;

import com.example.rcp.domain.Members;

public interface MemberService {
	
	Members getInfo(Integer memberId) throws Exception;

	

}
