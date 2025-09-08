package me.gachon.moosinsa_clone.Controller.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.User.*;
import me.gachon.moosinsa_clone.Service.UserService;
import me.gachon.moosinsa_clone.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Map;

/**
 * User 도메인 View Controller (Thymeleaf 화면 매핑)
 * - 파라미터는 최소화하고, 경로(PathVariable)는 URL의 끝에 배치
 * - 현재 단계: 내부 비즈니스 로직 없이, 시그니처/리턴뷰/주석만 정의
 */
@RequiredArgsConstructor
@RestController // @Controller와 @ResponseBoty가 합쳐진 어노테이션
@RequestMapping("/api") // API 경로 일관성을 위해 /api 접두사 사용
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // =============== 회원가입 ===============

    /**
     * [POST /api/signup] - 새로운 사용자를 생성(회원가입)하는 API
     */
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest request) {
        // [수정] savedId 변수의 타입을 Long에서 String으로 변경합니다.
        // userService.save(request)가 String 타입의 userId를 반환하기 때문입니다.
        String savedId = userService.save(request);

        // 사용자 생성이 성공하면, HTTP 상태 코드 201(Created)와 함께 성공 메시지를 응답으로 보냅니다.
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully with ID: " + savedId);
    }

    // [API] POST /users/signup/naver  - 네이버 회원가입
    @PostMapping("/signup/naver")
    public String signupWithNaver() {
        return "user/signupNaver";
    }

    // [API] POST /users/signup/google - 구글 회원가입
    @PostMapping("/signup/google")
    public String signupWithGoogle() {
        return "user/signupGoogle";
    }

    // [API] POST /users/signup/kakao  - 카카오 회원가입
    @PostMapping("/signup/kakao")
    public String signupWithKakao() {
        return "user/signupKakao";
    }

    // =============== 로그인/로그아웃 ===============

    // [POST /api/login] - 로그인을 처리하는 API
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request) throws LoginException {
        // 1. 서비스 레이어에서 사용자 인증 (이메일, 비밀번호 검증)
        UserResponse userResponse = userService.login(request);

        // 2. 인증 성공 시, JwtUtil을 사용하여 토큰 생성
        String token = jwtUtil.generateToken(userResponse.getUserId());

        // 3. 사용자 정보와 토큰을 함께 담아 응답
        //    프론트엔드에서는 이 토큰을 저장해두고, 다음 요청부터 헤더에 담아 보내게 됩니다.
        return ResponseEntity.ok().body(Map.of(
                "user", userResponse,
                "token", token
        ));
    }

    // [API] POST /users/login/naver - 네이버 로그인
    @PostMapping("/login/naver")
    public String loginWithNaver() {
        return "user/loginNaver";
    }

    // [API] POST /users/login/google - 구글 로그인
    @PostMapping("/login/google")
    public String loginWithGoogle() {
        return "user/loginGoogle";
    }

    // [API] POST /users/login/kakao - 카카오 로그인
    @PostMapping("/login/kakao")
    public String loginWithKakao() {
        return "user/loginKakao";
    }

    // [API] POST /users/logout - 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // TODO: Spring Security를 사용하여 로그아웃 로직 구현
        return ResponseEntity.ok("로그아웃 성공");
    }

    // =============== 조회 ===============

    // [GET /users] - 모든 사용자 목록을 JSON 데이터로 반환하는 API
    @GetMapping("/users")
    public ResponseEntity<List<UserListResponse>> getUsers() {
        List<UserListResponse> users = userService.findAll();
        // HTTP 200 (OK) 상태 코드와 함께 사용자 목록을 응답 본문에 담아 보냅니다.
        return ResponseEntity.ok().body(users);
    }

    /**
     * [GET /users/{userId}] - 특정 사용자 정보 조회 API
     * @param userId - 조회할 사용자의 ID
     * @return 조회된 사용자의 정보 DTO
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {
        // [수정] userService.getUser(userId) -> userService.findUser(userId)
        // 서비스 레이어에 실제로 존재하는 'findUser' 메소드를 호출하도록 수정합니다.
        return ResponseEntity.ok(userService.findUser(userId));
    }

    // =============== 기타 ===============

    // [API] GET /users/check-id/{userId} - 아이디 중복 확인
    @GetMapping("/check-id/{userId}")
    public ResponseEntity<String> checkUserId(@PathVariable String userId) {
        // TODO: 아이디 중복 확인 로직 구현
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }

    // [API] POST /users/image - 회원 프로필 이미지 업로드
    @PostMapping("/image")
    public ResponseEntity<String> uploadProfileImage() {
        // TODO: 이미지 업로드 로직 구현
        return ResponseEntity.ok("프로필 이미지가 성공적으로 업로드되었습니다.");
    }

    // [API] POST /users/reset-password - 비밀번호 재설정/찾기
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword() {
        // TODO: 비밀번호 재설정 로직 구현
        return ResponseEntity.ok("비밀번호 재설정 이메일을 보냈습니다.");
    }
}
