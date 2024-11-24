package Technical_Assignment.demo.user.service;

import org.springframework.stereotype.Service;

import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User findUserByUserId(String userId) {
		return userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("userId에 해당하는 유저가 없습니다."));
	}
}
