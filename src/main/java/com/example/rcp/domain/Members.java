package com.example.rcp.domain;

import lombok.*;

@Data
@ToString(exclude = {"password"})
public class Members {
	
	/** 社員番号 */
	private int id;
	
	/** 名前 */
	private String name;
	
	/** 名前（ひらがな） */
	private String nameHiragana;
	
	/** 部署名 */
	private String part;
	
	/** 部署名（ひらがな） */
	private String partHiragana;
	
	/** 内線番号 */
	private String tel;
	
	/** emailアドレス*/
	private String email;
	
	/** パスワード */
	private String password;
	
	/** 権限 */
	private Role auth;
	
	/** 登録日 */
	private String regDate;
	
	/** 修正日 */
	private String modDate;
	

}
