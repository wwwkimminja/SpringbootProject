package com.example.rcp.domain;



import javax.validation.constraints.Pattern;

import lombok.Data;


@Data
public class SearchOption {

	
	@Pattern(regexp ="All|Name|Part")
	private String searchItem;
	
	private String searchText;
	
	

}
 