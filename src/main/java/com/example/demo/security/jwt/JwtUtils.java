package com.example.demo.security.jwt;

import com.example.demo.entity.TokenRefreshTokenPairEntity;
import com.example.demo.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${niennq.app.jwtSecret}")
	private String jwtSecret;

	@Value("${niennq.app.jwtExpirationMs}")
	private int jwtExpirationMs;

//	@Autowired
//	private TokenRefreshTokenPairRepository tokenRefreshTokenPairRepository;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public ResponseCookie getCleanJwtCookie() {
		ResponseCookie cookie = ResponseCookie.from(jwtSecret, null).path("/api").build();
		return cookie;
	}

	public String parseJwtFromRequest(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}
		return null;
	}

//	public void deleteJwtTokens(String jwtToken) {
//		try {
//			Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
//			String jti = claims.getBody().getId();
//			TokenRefreshTokenPairEntity tokens = tokenRefreshTokenPairRepository.findTokenRefreshTokenPairByJti(jti);
//			tokenRefreshTokenPairRepository.delete(tokens);
//		} catch (JwtException e) {
//			logger.error("Lỗi khi xóa JWT token: {}", e.getMessage());
//		}
//	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

//	public void invalidateToken(String token) {
//		// Add the token to the blacklist in Redis with a TTL of the token's expiration time
//		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(jwtSecret).build();
//		Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
//		Date expiration = claimsJws.getBody().getExpiration();
//		long ttl = expiration.getTime() - System.currentTimeMillis();
//		String key = "blacklist:" + token;
//		redisTemplate.opsForValue().set(key, "invalid", ttl, TimeUnit.MILLISECONDS);
//	}
}
