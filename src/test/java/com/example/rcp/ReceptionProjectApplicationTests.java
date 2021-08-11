package com.example.rcp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.rcp.domain.Members;
import com.example.rcp.mapper.MembersMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReceptionProjectApplicationTests {
	@Autowired
	private DataSource ds;

	@Autowired
	private MembersMapper mapper;

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

	@Test
	public void testMemberMapper() throws Exception {


		Members member = mapper.getLoginInfo("naruse@abc.ne.jp", "11111111");
		System.out.println("Member >>" + member);
		assertEquals("成瀬 太一", member.getName());

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

}
