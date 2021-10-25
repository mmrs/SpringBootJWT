package com.dbbl.mrt.recharge.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dbbl.mrt.recharge.entity.MPUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

//	@Value("${app.jwt.secret}")
//	private String SECRET_KEY;
	@Value("${app.jwt.expiry}")
	private Integer TOKEN_EXPIRY;

	@Value("${app.rsa.private-key-file}")
	private String PRIVATE_KEY_FILE;
	@Value("${app.rsa.public-key-file}")
	private String PUBLIC_KEY_FILE;

//	private String SECRET_LOCK;

	private PrivateKey privateKey;
	private PublicKey publicKey;

	@PostConstruct
	void init() {

//		SECRET_LOCK = SecurityConfiguration.getPasswordEncoder().encode(SECRET_KEY);
		try {
			privateKey = RSAUtils.getPrivateKey(PRIVATE_KEY_FILE);
			publicKey = RSAUtils.getPublicKey(PUBLIC_KEY_FILE);

			log.info(Constants.COMMON.LINE_DASH);
			log.info("Public/Private Key Loaded Successfully.");
			log.info(Constants.COMMON.LINE_DASH);

		} catch (Exception e) {
			log.error(Constants.COMMON.LINE_DASH);
			log.error("Public/Private Key Loading failed due to error : " + e.getLocalizedMessage());
			log.error(Constants.COMMON.LINE_DASH);
			log.error("Stack Trace => ", e);
		}
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String generateToken(MPUser mpUser) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", mpUser.getRoles());
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
			return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error("Token Error => " + e.getLocalizedMessage());
		}
		return null;
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuer("DBBL")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * TOKEN_EXPIRY)))
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}

}
