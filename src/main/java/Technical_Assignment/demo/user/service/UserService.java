package Technical_Assignment.demo.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserSummaryDto;
import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.mapper.UserMapper;
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

	public List<UserSummaryDto> findAll() {
		return userRepository.findAll().stream()
			.map(UserMapper::toUserSummaryDto)
			.collect(Collectors.toList());
	}

	public UserDetailDto getUserDetail(Integer id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("id에 해당하는 유저가 없습니다."));
		return UserMapper.toUserResponseDto(user);
	}
}
