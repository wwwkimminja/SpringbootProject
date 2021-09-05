package com.example.rcp.service;

import com.example.rcp.domain.LoginMember;


public interface LoginService {

	LoginMember login(String Email, String Password) throws Exception;



}
