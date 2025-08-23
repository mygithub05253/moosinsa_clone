package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Entity.Grade;
import me.gachon.moosinsa_clone.Entity.User;
import me.gachon.moosinsa_clone.Repository.GradeRepository;
import me.gachon.moosinsa_clone.Repository.UserRepository;
import me.gachon.moosinsa_clone.Dto.User.UserListResponse;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import me.gachon.moosinsa_clone.Dto.User.UserCreateRequest;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import me.gachon.moosinsa_clone.Dto.User.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GradeRepository gradeRepository;

    /** GET /users/{userId} : 회원 단건 조회 */
    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자: " + userId));
        return UserResponse.from(user);
    }

    /** GET /users : 회원 목록 조회 */
    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    /** POST /users/signup : 회원 생성 (회원가입) */
    public void createUser(UserCreateRequest req) {
        String gradeKey = req.getUserGrade();
        Grade grade = gradeRepository.findById(gradeKey)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 등급 코드: " + gradeKey));

        User user = User.builder()
                .userId(req.getUserId())
                .userPwd(req.getUserPwd())
                .userName(req.getUserName())
                .phoneNumber(req.getPhoneNumber())
                .address(req.getAddress())
                .userGrade(grade)
                .build();

        userRepository.save(user);
    }

    /** PUT /users/{userId} : 회원 정보 수정 */
    public void updateUser(String userId, UserUpdateRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자: " + userId));

        if (req.getUserName() != null) user.setUserName(req.getUserName());
        if (req.getPhoneNumber() != null) user.setPhoneNumber(req.getPhoneNumber());
        if (req.getAddress() != null) user.setAddress(req.getAddress());

        String gradeKey = req.getUserGrade();
        if (gradeKey != null && !gradeKey.isBlank()) {
            Grade grade = gradeRepository.findById(gradeKey)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 등급 코드: " + gradeKey));
            user.setUserGrade(grade);
        }

        userRepository.save(user);
    }

    /** DELETE /users/{userId} : 회원 탈퇴 */
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("존재하지 않는 사용자: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
