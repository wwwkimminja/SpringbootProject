package com.example.rcp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.rcp.domain.Members;
import com.example.rcp.domain.SearchOption;

public interface AccountService {

	public List<Members> save(List<Members> members) throws Exception;

	public List<Members> unavailabledEmail(List<Members> members) throws Exception;

	public Page<com.example.rcp.model.Members> selectMembers(Pageable pageable, SearchOption searchOption);

	
	

}
