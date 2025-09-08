package me.gachon.moosinsa_clone.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // JWT를 생성하고 검증할 때 사용할 비밀키(Secret Key)
    // 실제 프로덕션에서는 이 키를 외부 설정 파일(application.properties 등)으로 분리하여 안전하게 관리해야 합니다.
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 만료 시간 (예: 1시간)
    private final long expirationTime = 3600 * 1000L; // 1시간 (밀리초 단위)

    /**
     * 사용자 ID를 기반으로 JWT를 생성하는 메소드
     * @param userId - 토큰에 담을 사용자의 ID
     * @return 생성된 JWT 문자열
     */
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // 토큰의 주체로 사용자 ID를 설정
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 토큰 만료 시간
                .signWith(secretKey) // 비밀키로 서명
                .compact(); // JWT 문자열로 변환
    }

    // 참고: 아래는 나중에 "인가(Authorization)" 기능을 구현할 때 필요한 메소드들입니다.
    // 지금은 로그인(인증) 기능만 구현하므로 사용하지 않습니다.

    /**
     * JWT에서 사용자 ID를 추출하는 메소드
     * @param token - 검증할 JWT 문자열
     * @return 추출된 사용자 ID
     */
    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * JWT의 유효성을 검증하는 메소드
     * @param token - 검증할 JWT 문자열
     * @return 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 토큰이 유효하지 않은 경우 (만료, 서명 오류 등)
            return false;
        }
    }
}