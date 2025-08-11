package me.gachon.moosinsa_clone.Controller.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {
    // 일반 회원가입 (POST /users/signup)
    @PostMapping("/signup")
    public String signup() {
        return "user/signup";
    }

    // 네이버 회원가입 (POST /users/signup/naver)
    @PostMapping("/signup/naver")
    public String signupWithNaver() {
        return "user/signupNaver";
    }

    // 구글 회원가입 (POST /users/signup/google)
    @PostMapping("/signup/google")
    public String signupWithGoogle() {
        return "user/signupGoogle";
    }

    // 카카오 회원가입 (POST /users/signup/kakao)
    @PostMapping("/signup/kakao")
    public String signupWithKakao() {
        return "user/signupKakao";
    }

    // 아이디 중복 확인 (GET /users/check-id/{userId})
    @GetMapping("/check-id/{userId}")
    public String checkUserId(@PathVariable String userId, Model model) {
        return "user/checkId";
    }

    // 일반 로그인 (POST /users/login)
    @PostMapping("/login")
    public String login() {
        return "user/login";
    }

    // 네이버 로그인 (POST /users/login/naver)
    @PostMapping("/login/naver")
    public String loginWithNaver() {
        return "user/loginNaver";
    }

    // 구글 로그인 (POST /users/login/google)
    @PostMapping("/login/google")
    public String loginWithGoogle() {
        return "user/loginGoogle";
    }

    // 카카오 로그인 (POST /users/login/kakao)
    @PostMapping("/login/kakao")
    public String loginWithKakao() {
        return "user/loginKakao";
    }

    // 로그아웃 (POST /users/logout)
    @PostMapping("/logout")
    public String logout() {
        return "user/logout";
    }

    // 로그인 이력 조회 (GET /users/login-history/{userId})
    @GetMapping("/login-history/{userId}")
    public String getLoginHistory(@PathVariable String userId, Model model) {
        return "user/loginHistory";
    }

    // 마이페이지 (GET /users/mypage/{userId})
    @GetMapping("/mypage/{userId}")
    public String getMyPage(@PathVariable String userId, Model model) {
        return "user/mypage";
    }

    // 회원 정보 수정 (PUT /users/{userId})
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "user/update";
    }

    // 회원 정보 조회 (GET /users/{userId})
    @GetMapping("/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        return "user/info";
    }

    // 회원 등급 조회 (GET /users/grade/{userId})
    @GetMapping("/grade/{userId}")
    public String getUserGrade(@PathVariable String userId, Model model) {
        return "user/grade";
    }

    // 회원 탈퇴 (DELETE /users/{userId})
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "user/delete";
    }

    // 회원 프로필 이미지 업로드 (POST /users/image)
    @PostMapping("/image")
    public String uploadProfileImage() {
        return "user/uploadImage";
    }

    // 비밀번호 재설정 및 찾기 (POST /users/reset-password)
    @PostMapping("/reset-password")
    public String resetPassword() {
        return "user/resetPassword";
    }

    // 비밀번호 변경 (PUT /users/{userId}/password)
    @PutMapping("/{userId}/password")
    public String updatePassword(@PathVariable String userId) {
        return "user/updatePassword";
    }
}
