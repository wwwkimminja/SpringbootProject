package com.example.rcp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rcp.model.Members;


@Repository
public interface MembersRepository extends JpaRepository<Members,Integer>{
	
	
}
