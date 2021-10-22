package com.dbbl.mrt.recharge.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dbbl.mrt.recharge.config.SecurityConfiguration;
import com.dbbl.mrt.recharge.entity.MPUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	@Value("${app.jwt.expiry}")
	private Integer TOKEN_EXPIRY;

	private String SECRET_LOCK;

	@PostConstruct
	void init() {

		SECRET_LOCK = SecurityConfiguration.getPasswordEncoder().encode(SECRET_KEY);
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String generateToken(MPUser mpUser) {

		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, mpUser.getUserName());
	}

	public Boolean validateToken(String token, MPUser mpUser) {
		final String userName = extractUserName(token);
		if (StringUtils.isBlank(userName)) {
			return false;
		}
		return StringUtils.equals(userName, mpUser.getUserName()) && !isTokenExpired(token);
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = exractAllClaims(token);
		if (claims != null) {
			return claimResolver.apply(claims);
		}
		return null;
	}

	private Claims exractAllClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET_LOCK).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error("Token Error => " + e.getLocalizedMessage());
		}
		return null;
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer("DBBL").setExpiration(new Date(System.currentTimeMillis() + (1000 * TOKEN_EXPIRY)))
				.signWith(SignatureAlgorithm.HS256, SECRET_LOCK).compact();
	}

}