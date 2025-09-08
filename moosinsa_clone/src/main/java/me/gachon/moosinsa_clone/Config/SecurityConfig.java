package me.gachon.moosinsa_clone.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Session 정책 import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 비밀번호 암호화를 위한 PasswordEncoder를 Spring Bean으로 등록합니다.
     * 회원가입 시 사용자의 비밀번호를 안전하게 해시하기 위해 필수적입니다.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // --- 1. JWT 토큰 기반 인증/인가 방식 (현재 활성화된 코드) ---
        http
                // 1-1. CSRF 보호 기능 비활성화: STATELESS 서버에서는 CSRF 토큰을 사용하지 않으므로 비활성화합니다.
                //      이것이 403 Forbidden 오류를 해결하는 직접적인 코드입니다.
                .csrf(csrf -> csrf.disable())

                // 1-2. 세션 관리 정책을 STATELESS로 설정: 서버가 세션을 생성하거나 사용하지 않도록 설정합니다.
                //      모든 요청은 토큰을 통해 인증되므로 서버에 상태를 저장할 필요가 없습니다.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 1-3. HTTP 요청에 대한 접근 권한 설정
                .authorizeHttpRequests(authz -> authz
                        // "/api/signup", "/api/login" 같은 인증이 필요 없는 API 경로는 모두 허용합니다.
                        .requestMatchers("/api/signup", "/api/login").permitAll()
                        // 그 외 모든 요청은 일단 허용합니다. (나중에 인증이 필요한 경로를 추가할 수 있습니다.)
                        .anyRequest().permitAll()
                );


        /*
        // --- 2. 세션 기반 인증/인가 방식 (참고용 주석 처리) ---
        // 만약 전통적인 세션 방식을 사용한다면 아래와 같이 설정합니다.
        http
            // 2-1. CSRF 보호 기능을 활성화된 상태로 둡니다. (Spring Security 기본값)
            //      이 경우 프론트엔드에서 모든 POST/PUT/DELETE 요청에 CSRF 토큰을 함께 보내야 합니다.
            // .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

            // 2-2. 세션 관리 정책을 기본값(IF_REQUIRED)으로 사용합니다.
            //      로그인 시 서버가 세션을 생성하고 JSESSIONID 쿠키를 클라이언트에 보냅니다.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

            // 2-3. HTTP 요청에 대한 접근 권한 설정
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/signup", "/api/login").permitAll() // 회원가입, 로그인은 누구나 가능
                .requestMatchers("/api/mypage").authenticated() // 예: 마이페이지는 인증된 사용자만 가능
                .anyRequest().permitAll() // 그 외는 모두 허용
            )
            // 2-4. 로그인/로그아웃 페이지 설정 (Spring Security가 제공하는 기본 페이지 사용 시)
            .formLogin(form -> form.loginPage("/login").permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/"));
        */

        return http.build();
    }
}