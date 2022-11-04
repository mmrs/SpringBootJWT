package com.mmrs.mrt.recharge;

import beans.AppBean;
import com.mmrs.mrt.recharge.entity.MPUser;
import com.mmrs.mrt.recharge.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppBean.class})
class RechargeApplicationTests {

	@Resource
	JwtUtil jwtUtil;

	@BeforeEach
	void setUp(){
		System.out.println("= Setting Up=");
	}

	@Test
	void checkPasswordHashingWorks() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode("Dbbl@123");
		System.out.println("Password Hashed:" + pass);
		assertThat(encoder.matches("Dbbl@123", pass)).isEqualTo(true);
	}

	@Test
	void ShouldPass_When_Token_Valid() {
		MPUser mpUser = new MPUser();
		mpUser.setUserName("admin");
		String token = jwtUtil.generateToken(mpUser);
		assertThat(jwtUtil.validateToken(token, mpUser)).isEqualTo(true);
	}

	@Test
	void ShouldFail_When_Token_Expired(){
		MPUser mpUser = new MPUser();
		mpUser.setUserName("admin");
		String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6IkRCQkwiLCJleHAiOjE2MzQ4ODM0MzksImlhdCI6MTYzNDg4MzEzOX0.2ullm6Lvr5C_4ddcE-HjAdbtL0cS28pb8BUgvDMlnew";
		assertThat(jwtUtil.validateToken(expiredToken, mpUser)).isEqualTo(false);
	}
}
