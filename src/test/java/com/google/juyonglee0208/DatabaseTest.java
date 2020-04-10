package com.google.juyonglee0208;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.juyonglee0208.config.RootConfig;
import com.mysql.cj.xdevapi.DatabaseObject;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class DatabaseTest {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFatory;
	
	@Test
	public void mysqlConnectionTest() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "0000");
			log.info(connection);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void connectionTest() {
		try(SqlSession session = sqlSessionFatory.openSession();
				Connection con = session.getConnection();) {
			log.info("Connection 성공!");
		} catch (Exception e) {
			fail("Connection 실패!");
		}
		
	}

}
