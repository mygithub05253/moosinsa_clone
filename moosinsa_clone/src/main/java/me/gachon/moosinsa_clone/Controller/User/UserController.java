package me.gachon.moosinsa_clone.Controller.User;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Service.UserService;
import me.gachon.moosinsa_clone.Dto.User.UserCreateRequest;
import me.gachon.moosinsa_clone.Dto.User.UserLoginRequest;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import me.gachon.moosinsa_clone.Dto.User.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User 도메인 View Controller (Thymeleaf 화면 매핑)
 * - 파라미터는 최소화하고, 경로(PathVariable)는 URL의 끝에 배치
 * - 현재 단계: 내부 비즈니스 로직 없이, 시그니처/리턴뷰/주석만 정의
 */
@RequiredArgsConstructor
@RestController // @Controller와 @ResponseBoty가 합쳐진 어노테이션
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // =============== 회원가입 ===============

    // [API] POST /users/signup - 일반 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCreateRequest req) {
        userService.createUser(req); // 서비스 계층에 회원 생성 로직을 위임합니다.
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
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

    // [API] POST /users/login - 일반 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest req) {
        // TODO: Spring Security를 사용하여 로그인 로직 구현
        return ResponseEntity.ok("로그인 성공");
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

    // [API] GET /users - 회원 목록 조회
    @GetMapping
    public ResponseEntity<List<UserResponse>> listUsers() {
        // 모든 유저 정보를 조회하여 DTO 리스트로 반환합니다.
        return ResponseEntity.ok(userService.getUsers());
    }

    // [API] GET /users/{userId} - 회원 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        // 특정 유저 정보를 조회하여 DTO로 반환합니다.
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // [API] GET /users/grade/{userId} - 회원 등급 조회
    @GetMapping("/grade/{userId}")
    public ResponseEntity<UserResponse> getUserGrade(@PathVariable String userId) {
        // 회원 등급 조회도 회원 단건 조회로 충분히 가능합니다.
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // [API] GET /users/login-history/{userId} - 로그인 이력 조회 (구현 필요)
    @GetMapping("/login-history/{userId}")
    public ResponseEntity<String> getLoginHistory(@PathVariable String userId) {
        // TODO: 로그인 이력 조회 로직 구현
        return ResponseEntity.ok("로그인 이력 조회 기능은 추후 구현 예정입니다.");
    }

    // [API] GET /users/mypage/{userId} - 마이페이지
    @GetMapping("/mypage/{userId}")
    public ResponseEntity<UserResponse> getMyPage(@PathVariable String userId) {
        // 마이페이지는 회원 단건 조회와 동일한 정보이므로 재사용합니다.
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // =============== 수정/삭제 ===============

    // [API] PUT /users/{userId} - 회원 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest req) {
        userService.updateUser(userId, req); // 서비스 계층에 수정 로직을 위임합니다.
        return ResponseEntity.ok("회원 정보가 성공적으로 수정되었습니다.");
    }

    // [API] PUT /users/{userId}/password - 비밀번호 변경
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updatePassword(@PathVariable String userId, @RequestBody UserUpdateRequest req) {
        // TODO: 비밀번호 변경 로직 구현
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

    // [API] DELETE /users/{userId} - 회원 탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId); // 서비스 계층에 삭제 로직을 위임합니다.
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다.");
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
