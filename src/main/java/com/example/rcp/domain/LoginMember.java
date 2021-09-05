package com.example.rcp.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude= {"memberPassword"})
public class LoginMember {

	private Integer memberId;
	
	private String memberName;
	
	private String memberEmail;
	
	private String memberPassword;
	
	private short memberAuth;
	
	
	
}
