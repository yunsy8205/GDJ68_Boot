package com.winter.app.connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DBConnectionTest {
	@Autowired
	private DataSource dataSource;
	
	//@Test
	void dbtest() throws Exception{
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
	}
	
	//아침에 오면 미리 db 연결 확인해 보는 게 좋음!

}
