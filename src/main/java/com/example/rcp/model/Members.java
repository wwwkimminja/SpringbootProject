package com.example.rcp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "members")
@Data
@ToString
public class Members {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "member_id")
  	private Integer memberId;
	
	/** 名前 */
    @Column(name = "member_name")
	private String memberName;
	
	/** 名前（ひらがな） */
    @Column(name = "member_name_hiragana")
	private String memberNameHiragana;
	
	/** 部署名 */
    @Column(name = "member_part")
	private String memberPart;
	
	/** 部署名（ひらがな） */
    @Column(name = "member_part_hiragana")
	private String memberPartHiragana;
	
	/** 内線番号 */
    @Column(name = "member_tel")
	private String memberTel;
	
	/** emailアドレス*/
    @Column(name = "member_email")
	private String memberEmail;
	
	/** パスワード */
    @Column(name = "member_password")
	private String memberPassword;
	
	/** 権限 */
    @Column(name = "member_auth")
	private short memberAuth;
	
	/** 登録日 */
    @Column(name = "member_reg_date")
	private Timestamp memberRegDate;
	
	/** 修正日 */
    @Column(name = "member_mod_date")
	private Timestamp memberModDate;
	


}
