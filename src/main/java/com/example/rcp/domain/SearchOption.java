package com.example.rcp.domain;



import javax.validation.constraints.Pattern;

import lombok.Data;


@Data
public class SearchOption {

	
	@Pattern(regexp ="all|name|part")
	private String searchItem;
	
	private String searchText;
	
	

}
 