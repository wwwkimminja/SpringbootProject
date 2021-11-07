package com.example.rcp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.rcp.model.Members;


public interface MembersRepository extends JpaRepository<Members,Integer>{


	@Query(value = "SELECT * FROM members WHERE member_name LIKE %?1% OR member_name_hiragana LIKE %?1%",nativeQuery = true)
	Page<Members> findByMemberNameContainig(String memberName, Pageable pageable);
	
	@Query(value = "SELECT * FROM members WHERE member_part LIKE %?1% OR member_part_hiragana LIKE %?1%",nativeQuery = true)
	Page<Members> findByMemberPartContainig(String memberPart, Pageable pageable);
	
	@Query(value = "SELECT * FROM members WHERE member_name LIKE %?1% OR member_name_hiragana LIKE %?1% OR member_part LIKE %?2% OR member_part_hiragana LIKE %?2%",nativeQuery = true)
	Page<Members> findByMemberNameOrMemberPartContainig(String memberName,String memberPart, Pageable pageable);


}
