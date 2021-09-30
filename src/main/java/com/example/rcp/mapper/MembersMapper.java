package com.example.rcp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;

@Mapper
public interface MembersMapper {
	
	

	public LoginMember findByEmail(@Param("email") String email) throws Exception;

	public Members findById(@Param("id")int memberId) throws Exception;
	
	public int insert(@Param("member") Members member) throws Exception;
	


}
