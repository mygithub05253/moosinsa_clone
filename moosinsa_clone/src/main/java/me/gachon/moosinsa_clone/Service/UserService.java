package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Dto.User.UserCreateRequest;
import me.gachon.moosinsa_clone.Dto.User.UserListResponse;
import me.gachon.moosinsa_clone.Dto.User.UserLoginRequest;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import me.gachon.moosinsa_clone.Entity.Grade;
import me.gachon.moosinsa_clone.Entity.User;
import me.gachon.moosinsa_clone.Repository.GradeRepository;
import me.gachon.moosinsa_clone.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 로직 (기존 join 메소드를 save로 통합 및 개선)
     * @param dto - 사용자 생성 요청 DTO
     * @return 저장된 사용자의 ID
     */
    public String save(UserCreateRequest dto) {
        Grade grade = gradeRepository.findByUserGrade("BRONZE")
                .orElseThrow(() -> new IllegalStateException("기본 등급 'BRONZE'을 찾을 수 없습니다."));

        // 새로워진 DTO와 Entity에 맞게 모든 필드를 매핑합니다.
        User user = User.builder()
                .userId(dto.getUserId()) // DTO에서 별도로 받은 userId를 저장
                // .email(dto.getEmail()) // User 엔티티에 email 필드를 추가했다면 이 부분도 추가
                .userPwd(passwordEncoder.encode(dto.getPassword()))
                .userName(dto.getName())
                .nickname(dto.getNickname())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .userGrade(grade)
                .build();

        return userRepository.save(user).getUserId();
    }

    /**
     * 로그인 로직
     * @param dto - 사용자 로그인 요청 DTO
     * @return 로그인한 사용자의 정보 DTO
     * @throws LoginException
     */
    public UserResponse login(UserLoginRequest dto) throws LoginException {
        // 1. UserLoginRequest DTO의 필드는 'userId'이므로, getUserId()를 사용합니다.
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new LoginException("가입되지 않은 아이디입니다."));

        // 2. [오류 수정] DTO의 실제 필드명인 'userPwd'에 맞는 getUserPwd()를 사용합니다.
        if (!passwordEncoder.matches(dto.getUserPwd(), user.getUserPwd())) {
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 성공 시 User 엔티티를 UserResponse DTO로 변환하여 반환합니다.
        return new UserResponse(user);
    }

    /**
     * 특정 사용자 정보 조회 로직
     * @param userId - 조회할 사용자의 ID
     * @return 조회된 사용자의 정보 DTO
     */
    public UserResponse findUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. ID: " + userId));

        // [수정] UserResponse.from(user) 대신 생성자를 직접 호출합니다.
        return new UserResponse(user);
    }

    /**
     * 모든 사용자 목록 조회 로직
     * @return 모든 사용자 정보 DTO 리스트
     */
    public List<UserListResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserListResponse::new) // UserListResponse(User entity) 생성자 호출
                .collect(Collectors.toList());
    }
}