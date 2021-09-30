package com.example.rcp.service;

import java.util.List;

import com.example.rcp.domain.Members;

public interface AccountService {

	public Members save(Members member);

	public List<Members> save(List<Members> members);

}
