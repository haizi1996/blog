package com.hailin;

import com.hailin.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public  class BlogApplicationTests {


	protected static Logger logger;

	@Before
	public void start(){
		logger.info("test is begin");
	}


	@Test
	public void contextLoads() {
	}


	@After
	public void end(){
		logger.info("test is end");
	}

}
