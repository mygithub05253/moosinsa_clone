package me.gachon.moosinsa_clone.Controller.User;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Service.UserService;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * User 도메인 View Controller (Thymeleaf 화면 매핑)
 * - 파라미터는 최소화하고, 경로(PathVariable)는 URL의 끝에 배치
 * - 현재 단계: 내부 비즈니스 로직 없이, 시그니처/리턴뷰/주석만 정의
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // =============== 회원가입 ===============

    // [API] POST /users/signup  - 일반 회원가입
    @PostMapping("/signup")
    public String signup() {
        return "user/signup";
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
    public String login() {
        return "user/login";
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
    public String logout() {
        return "user/logout";
    }

    // =============== 조회 ===============

    // [VIEW] 회원 목록 페이지
    // GET /users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "user_list"; // templates/user_list.html
    }

    // [API] 회원 단건 조회 (JSON)
    // GET /users/{userId}
    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // [API] GET /users/grade/{userId} - 사용자 등급 조회
    @GetMapping("/grade/{userId}")
    public String getUserGrade(@PathVariable String userId, Model model) {
        return "user/grade";  // 예: user/grade.html
    }

    // [API] GET /users/login-history/{userId} - 로그인 이력 조회
    @GetMapping("/login-history/{userId}")
    public String getLoginHistory(@PathVariable String userId, Model model) {
        return "user/loginHistory";  // 예: user/loginHistory.html
    }

    // [API] GET /users/mypage/{userId} - 마이페이지
    @GetMapping("/mypage/{userId}")
    public String getMyPage(@PathVariable String userId, Model model) {
        return "user/mypage"; // 예: user/mypage.html
    }

    // =============== 수정/삭제 ===============

    // [API] PUT /users/{userId} - 회원 정보 수정
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "user/update"; // 예: user/update.html
    }

    // [API] DELETE /users/{userId} - 회원 탈퇴
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "user/delete"; // 예: user/delete.html
    }

    // [API] PUT /users/{userId}/password - 비밀번호 변경
    @PutMapping("/{userId}/password")
    public String updatePassword(@PathVariable String userId) {
        return "user/updatePassword"; // 예: user/updatePassword.html
    }

    // =============== 기타 ===============

    // [API] GET /users/check-id/{userId} - 아이디 중복 확인
    @GetMapping("/check-id/{userId}")
    public String checkUserId(@PathVariable String userId, Model model) {
        return "user/checkId"; // 예: user/checkId.html
    }

    // [API] POST /users/image - 프로필 이미지 업로드
    @PostMapping("/image")
    public String uploadProfileImage() {
        return "user/uploadImage"; // 예: user/uploadImage.html
    }

    // [API] POST /users/reset-password - 비밀번호 재설정/찾기
    @PostMapping("/reset-password")
    public String resetPassword() {
        return "user/resetPassword"; // 예: user/resetPassword.html
    }
}
