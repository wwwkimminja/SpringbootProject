package com.example.rcp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rcp.domain.LoginMember;
import com.example.rcp.domain.Members;

@Mapper
public interface MembersMapper {
	
	

	public LoginMember findByEmail(@Param("email") String email) throws Exception;

	public Members findById(@Param("id")int memberId) throws Exception;
	
	public int insert(@Param("member") Members member) throws Exception;
	
	public int bulkInsert(@Param("memberList") List<Members> members) throws Exception;

	public List<Members> selectByCount(@Param("count")int count) throws Exception;

	public List<Members> selectAllMembers() throws Exception;
	
	public Page<Members> selectAll(Pageable pageable);
	


}
