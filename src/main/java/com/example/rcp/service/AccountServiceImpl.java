package com.example.rcp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rcp.domain.Members;
import com.example.rcp.domain.SearchOption;
import com.example.rcp.mapper.MembersMapper;
import com.example.rcp.repository.MembersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private MembersMapper membersMapper;

	@Autowired
	private MembersRepository membersRepository;

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

		if (accountList.size() == 0) {
			return null;
		} else {

			int result = membersMapper.bulkInsert(accountList);
			log.info("insert={}件成功", result);

			// members = membersMapper.selectByCount(result);
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

	@Override
	public Page<com.example.rcp.model.Members> selectMembers(Pageable pageable, SearchOption searchOption) {

		String searchItem = searchOption.getSearchItem();
		String searchKeyword = searchOption.getSearchText();
		Page<com.example.rcp.model.Members> result = null;

			switch (searchItem) {
			case "all":
				result = membersRepository.findByMemberNameOrMemberPartContainig(searchKeyword, searchKeyword,
						pageable);
				break;

			case "name":
				result = membersRepository.findByMemberNameContainig(searchKeyword, pageable);
				log.info("{}", result.getSize());
				break;

			case "part":
				result = membersRepository.findByMemberPartContainig(searchKeyword, pageable);
				break;

			}
		

		return result;

	}

}
