package Technical_Assignment.demo.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserInsertDto;
import Technical_Assignment.demo.user.dto.UserSummaryDto;
import Technical_Assignment.demo.user.dto.UserUpdateDto;
import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.entity.UserAuth;
import Technical_Assignment.demo.user.mapper.UserMapper;
import Technical_Assignment.demo.user.repository.UserRepository;
import Technical_Assignment.demo.user_history.entity.ActionType;
import Technical_Assignment.demo.user_history.service.UserHistoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserHistoryService userHistoryService;

	public User findUserByUserId(String userId) {
		return userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("userId에 해당하는 유저가 없습니다."));
	}

	public List<UserDetailDto> findUsersByUserName(String userName) {
		return userRepository.findUsersByUserName(userName).stream()
			.map(UserMapper::toUserDetailDto)
			.collect(Collectors.toList());
	}

	public List<UserSummaryDto> findAll() {
		return userRepository.findAll().stream()
			.map(UserMapper::toUserSummaryDto)
			.collect(Collectors.toList());
	}

	public UserDetailDto getUserDetail(String userId) {
		User user = findUserByUserId(userId);
		return UserMapper.toUserDetailDto(user);
	}

	@Transactional
	public UserUpdateDto updateUser(String userId, String updateName) {
		User user = findUserByUserId(userId);
		user.updateUserName(updateName);

		userHistoryService.saveUserHistory(user.getId(), ActionType.U);
		return UserMapper.toUserUpdateDto(user);
	}

	@Transactional
	public UserDetailDto deleteUser(String userId) {
		User user = findUserByUserId(userId);
		userRepository.delete(user);

		userHistoryService.saveUserHistory(user.getId(), ActionType.D);
		return UserMapper.toUserDetailDto(user);
	}

	@Transactional
	public UserInsertDto insertUser(String userId, String password, String userName, String userAuth) {
		checkUserAuth(userAuth);

		User user = User.builder()
			.userId(userId)
			.userPassword(passwordEncoder.encode(password))
			.userName(userName)
			.userAuth(userAuth)
			.build();
		userRepository.save(user);

		userHistoryService.saveUserHistory(user.getId(), ActionType.C);
		return UserMapper.toUserInsertDto(user, password);
	}

	private void checkUserAuth(String userAuth) {
		if (!UserAuth.contains(userAuth)) {
			throw new IllegalArgumentException("올바른 권한이 아닙니다.");
		}
	}
}
