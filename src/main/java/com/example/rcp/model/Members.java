package com.example.rcp.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"memberPassword"})
public class Members {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  	private Integer memberId;
	
	/** 名前 */
	private String memberName;
	
	/** 名前（ひらがな） */
	private String memberNameHiragana;
	
	/** 部署名 */
	private String memberPart;
	
	/** 部署名（ひらがな） */
	private String memberPartHiragana;
	
	/** 内線番号 */
	private String memberTel;
	
	/** emailアドレス*/
	private String memberEmail;
	
	/** パスワード */
	private String memberPassword;
	
	/** 権限 */
	private short memberAuth;
	
	/** 登録日 */
	private Timestamp memberRegDate;
	
	/** 修正日 */
	private Timestamp memberModDate;
	


}
