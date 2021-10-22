package com.dbbl.mrt.recharge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dbbl.mrt.recharge.entity.MPUser;
import com.dbbl.mrt.recharge.util.JwtUtil;

@SpringBootTest
class RechargeApplicationTests {

	@Autowired
	JwtUtil jwtUtil;

	@Test
	void contextLoads() {
	}

	@Test
	void Password() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode("Dbbl@123");
		System.out.println("Password Hashed:" + pass);
		assertThat(encoder.matches("Dbbl@123", pass)).isEqualTo(true);
	}

	@Test
	void validateToken() {

		MPUser mpUser = new MPUser();
		mpUser.setUserName("admin");
		String token = jwtUtil.generateToken(mpUser);
		String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6IkRCQkwiLCJleHAiOjE2MzQ4ODM0MzksImlhdCI6MTYzNDg4MzEzOX0.2ullm6Lvr5C_4ddcE-HjAdbtL0cS28pb8BUgvDMlnew";
		assertThat(jwtUtil.validateToken(token, mpUser)).isEqualTo(true);
		assertThat(jwtUtil.validateToken(expiredToken, mpUser)).isEqualTo(true);
		assertThat(jwtUtil.validateToken("radom", mpUser)).isEqualTo(true);
	}
}
