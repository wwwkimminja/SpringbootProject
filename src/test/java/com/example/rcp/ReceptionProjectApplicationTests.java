package com.example.rcp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.rcp.domain.LoginMember;

import com.example.rcp.mapper.MembersMapper;
import com.example.rcp.model.Members;
import com.example.rcp.repository.MembersRepository;
import com.example.rcp.service.AccountService;
import com.example.rcp.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReceptionProjectApplicationTests {
	@Autowired
	private DataSource ds;

	@Autowired
	private MembersMapper mapper;
	
	
	@Autowired
	private LoginService service;
	
	
	@Autowired
	private AccountService accountService;
	


	/*
	@Ignore
	@Test
	public void testSelect()throws Exception{
		
		List<Members> result = mapper.selectByCount(10);
		System.out.println(result.get(0).getMemberName());
		assertEquals(10,result.size());
		
	}
	
	@Ignore
	@Test
	public void testInsert() throws Exception{
		
		Members member = new Members();
	

		member.setMemberName("永井");
		member.setMemberNameHiragana("ながい");
		member.setMemberPart("営業");
		member.setMemberPartHiragana("えいぎょう");
		member.setMemberTel("1265");
		member.setMemberEmail("nagi@abc.ne.jp");
		member.setMemberPassword("12345678");
		member.setMemberAuth((short) 1);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		member.setMemberRegDate(timestamp);
		
		int result = mapper.insert(member);
		System.out.println(result);
		
		assertEquals(1,result);
		
	}
	@Ignore
	@Test
	public void testLogin() throws Exception{
		
		LoginMember member =  service.login("naruse@abc.ne.jp", "11111111");
		System.out.println("member>>"+member);
		assertEquals(1,member.getMemberId());

		
	}

	@Ignore
	@Test
	public void testDataSource() throws Exception {
		System.out.println("DS=" + ds);

		try (Connection conn = ds.getConnection()) {
			System.out.println("Connection=" + conn);
			assertThat(conn).isInstanceOf(Connection.class);

			assertEquals(4, getLong(conn, "select count(*) from members"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Ignore
	@Test
	public void testMemberMapper() throws Exception {


		Members member = mapper.findById(1);
		System.out.println("Member >>" + member);
		assertEquals("成瀬 太一", member.getMemberName());

	}

	private long getLong(Connection conn, String sql) {

		long result = 0;

		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
*/
}
