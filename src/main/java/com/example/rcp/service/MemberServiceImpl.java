package com.example.rcp.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rcp.domain.Members;
import com.example.rcp.mapper.MembersMapper;

import lombok.extern.slf4j.Slf4j;
import net.osdn.util.phonetic.Phonetic;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	MembersMapper membersMapper;

	@Override
	public Members getInfo(Integer memberId) throws Exception {
		Members member = membersMapper.findById(memberId);
		return member;
	}

	@Override
	public Members edit(Integer memberId, Members member) throws Exception {
		String partHiragana = Phonetic.getPhonetic(member.getMemberPart());
		member.setMemberPartHiragana(partHiragana);
		Timestamp modDate = new Timestamp(System.currentTimeMillis());
		member.setMemberModDate(modDate);
		
		int result = membersMapper.update(member);

		if (result == 1) {
			
		member = membersMapper.findById(memberId);
		
		}
		
		return member;
	}

}
