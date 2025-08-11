package me.gachon.moosinsa_clone.Service;

import lombok.RequiredArgsConstructor;
import me.gachon.moosinsa_clone.Entity.User;
import me.gachon.moosinsa_clone.Repository.UserRepository;
import me.gachon.moosinsa_clone.Dto.User.UserListResponse;
import me.gachon.moosinsa_clone.Dto.User.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    /** GET /users/{userId} : 회원 단건 조회 */
    public UserResponse getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자: " + userId));
        return UserResponse.from(user);
    }

    /** GET /users : 회원 목록 조회 (뷰 렌더링용) */
    public List<UserListResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserListResponse::from)
                .toList();
    }
}
