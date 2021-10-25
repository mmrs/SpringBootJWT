package com.dbbl.mrt.recharge.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
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

	PrivateKey privateKey;
	PublicKey publicKey;

	@PostConstruct
	void init() throws InvalidKeySpecException, NoSuchAlgorithmException {

//		SECRET_LOCK = SecurityConfiguration.getPasswordEncoder().encode(SECRET_KEY);

		String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArH2nJuyNNx35vFPjp2eB\r\n"
				+ "5i3X4VJY1bRMVs0GXOoaWMMl0dqrowTygeiHOmJhdt2RCpYuU8G3p02lmp+YU1AN\r\n"
				+ "DOkR7gxhvqRE85vw1+eIsZ4Fwv12lfAl9zrM3eAVAq3pf8D/ACd0lGffQy4l0OfD\r\n"
				+ "BKEhXKcyJFqRwloZ8BTNJpDFBVvMenqh1reQpaeZOeJMdA5CBB5dBlDYOndqDNjR\r\n"
				+ "BYdYUrRTEaayma3jAryb3iitu9DB0QMX82UGoefO6fPIZRw+pqv0AJl2eGVTefOQ\r\n"
				+ "xHK7cBqnVhN9b+yPXhceGi9a1/MWFy2riSZFtDIbtPt1syWOLz9oCUbeg/WnQzLS\r\n" + "wwIDAQAB\r\n";
		String pri = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCsfacm7I03Hfm8\r\n"
				+ "U+OnZ4HmLdfhUljVtExWzQZc6hpYwyXR2qujBPKB6Ic6YmF23ZEKli5TwbenTaWa\r\n"
				+ "n5hTUA0M6RHuDGG+pETzm/DX54ixngXC/XaV8CX3Oszd4BUCrel/wP8AJ3SUZ99D\r\n"
				+ "LiXQ58MEoSFcpzIkWpHCWhnwFM0mkMUFW8x6eqHWt5Clp5k54kx0DkIEHl0GUNg6\r\n"
				+ "d2oM2NEFh1hStFMRprKZreMCvJveKK270MHRAxfzZQah587p88hlHD6mq/QAmXZ4\r\n"
				+ "ZVN585DEcrtwGqdWE31v7I9eFx4aL1rX8xYXLauJJkW0Mhu0+3WzJY4vP2gJRt6D\r\n"
				+ "9adDMtLDAgMBAAECggEBAKZUVEa4fEPV5+eujSv0J9KqCi4AliEcxzA8bBJUvCsz\r\n"
				+ "otiFoFSGhMK4Uw39qDZS2XX385xYhJwTx8kedGiCHNOCPAPsdKS9CrBOgyPu5GVC\r\n"
				+ "GBQ7DYrwE+wfC0Y4uonm4e6LUFn5sfUZZLUHXvffRLLGHcGWiEd9/mgHMlPL+zdf\r\n"
				+ "dMJUuEqmy+ozcDhWhZSqxD2JLNcrNsfict2L7Zcib7Lvwk19/R7h4lSh0e9VqjDT\r\n"
				+ "ZcJR/7s468lxgTNiulS9s/K1Xwg/zK9I8zAXKZVvHHlSkNfvzTVpQZioRK9myRrc\r\n"
				+ "8mhOm4YtfUi1e5LQ319TazvJjW353BbQOH+UmvDbicECgYEA2t5l8MuH5NOLBjYX\r\n"
				+ "4uoWHH+4CTIG6PCiyTQR5/SjcOsPvzATzkNd7YGdSrgk/dhMB+PZxZwudFxgdVCE\r\n"
				+ "3KvWRD+lrJ9tcSh8QPeQdac4nzMweT1nqnd2F4PO6FcmoGI8Z9fzgJ/tk4ROWvKF\r\n"
				+ "hLdTj6971zNoJRUTMc6NH3LU7ekCgYEAycEItV1RgKZtbRu2i1fZcCr8VMlftqLn\r\n"
				+ "t0yHyD5aAAsLKLGW/yfDq0Fd+QF3m+wsPtSk35omGStnoB/WGRWv8ehWh2iNIIIu\r\n"
				+ "FUUpFfdQqCGSU+ojFE+sW6hgnca17evnz+4vwflLx11qxMpXXc7EhRJXFoFxK7cc\r\n"
				+ "+etcmDCW88sCgYBVjsjE15tY3UUkeXLe9mkMXPUBSzgeSSspghxZ020s0AbI0y96\r\n"
				+ "2yTVmmx1cASt4qbeErjnocUbIZ1nXsGBTf8lkMff8jajHJNuBhjHlUXyHd2eF131\r\n"
				+ "6lsUmCcC9kaYPa6lXWrH5jzGBNtofBOrrMqSiaPcnTDiBhoJx1etaoNIOQKBgA5H\r\n"
				+ "KPSc3A28uXXFRk/qMassf5sIfUuRj9B7DAjx0LC8F1gT6Vm5WLGf+KSMpAhW2HLB\r\n"
				+ "3cEtSZDyb2z3k9FGpaL7DFSc44/vZo9+y3+QdxbO+WoS4dSoJsx9yAiibXGfBlLC\r\n"
				+ "yoJxwBkl1U6D+1baMTIxsBQZqQas+NH/BBiJJ8WtAoGAWi0FiH3eLjcpc+MSzna8\r\n"
				+ "BplVeWAolJnPiaZe5OLo3RhjXKg38fZ2WOZvOL5WI0BeXRib/4zres+CD2/nOdpe\r\n"
				+ "k9RSuI3vDyOV2WTSLBDgAFLWmD+BYBm5BYCZuc8Kbs97UFKHv4cUON/aRvS3h3/s\r\n"
				+ "gFjKFVyVEpMccyZdhpXSKKE=\r\n";

		privateKey = KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pri.replaceAll("\r\n", ""))));
		publicKey = KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pub.replaceAll("\r\n", ""))));

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
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer("DBBL").setExpiration(new Date(System.currentTimeMillis() + (1000 * TOKEN_EXPIRY)))
				.signWith(SignatureAlgorithm.RS256, privateKey).compact();
	}

}
