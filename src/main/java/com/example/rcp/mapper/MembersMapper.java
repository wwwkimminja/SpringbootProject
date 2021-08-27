package com.example.rcp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.example.rcp.domain.Members;

@Mapper
public interface MembersMapper {
	
	

	public Members findByEmail(@Param("email") String email) throws Exception;
	


}
