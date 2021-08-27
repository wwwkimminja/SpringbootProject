package com.example.rcp.service;

import com.example.rcp.domain.Members;

public interface LoginService {

	Members login(String memberEmail, String memberPassword) throws Exception;



}
