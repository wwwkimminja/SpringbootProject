package com.example.rcp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rcp.domain.Members;
import com.example.rcp.mapper.MembersMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	MembersMapper membersMapper;

	@Override
	public Members save(Members member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional	
	public List<Members> save(List<Members> members) throws Exception {

		List<Members> accountList = new ArrayList<Members>();

		for (int i = 0; i < members.size(); i++) {
			String email = members.get(i).getMemberEmail();

			// Email 重複チェック
			if (membersMapper.findByEmail(email) == null) {

				accountList.add(members.get(i));

			}
		}

		if(accountList.size()==0) {
			return null;
		}else {

			int result = membersMapper.bulkInsert(accountList);
			log.info("insert={}件成功", result);
			//members = membersMapper.selectByCount(result);
			return accountList;
		}
	}
	

	@Override
	public List<Members> unavailabledEmail(List<Members> members) throws Exception {
		List<Members> unavailableList = new ArrayList<Members>();

		for (int i = 0; i < members.size(); i++) {
			String email = members.get(i).getMemberEmail();
			
			if (membersMapper.findByEmail(email) != null) {
	
				unavailableList.add(members.get(i));
			}
		}
		
		return unavailableList;
		
	}
}
