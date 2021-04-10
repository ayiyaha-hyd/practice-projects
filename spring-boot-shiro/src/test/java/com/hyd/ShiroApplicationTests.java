package com.hyd;

import com.hyd.entity.User;
import com.hyd.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		String userName = "admin";
		User user = userService.findByUsername(userName);
		System.out.println(user);
	}

}
