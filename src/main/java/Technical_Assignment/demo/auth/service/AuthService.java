package Technical_Assignment.demo.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private static final String ADMIN = "SYSTEM_ADMIN";
	private static final String USER = "USER";
	private static final String ROLE = "ROLE_";

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Authentication signin(final String userId, final String userPassword) {
		User user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다."));

		// 관리자인 경우 암호화되지 않은 비밀번호와 비교, dml.sql 참조
		if (user.getUserAuth().equals(ADMIN) && user.getUserPassword().equals(userPassword)) {
			return new UsernamePasswordAuthenticationToken(
				userId, userPassword, List.of(new SimpleGrantedAuthority(ROLE + user.getUserAuth()))
			);
		}

		if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
			throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
		}

		return new UsernamePasswordAuthenticationToken(
			userId, userPassword, List.of(new SimpleGrantedAuthority(ROLE + user.getUserAuth()))
		);
	}

	@Transactional
	public void signup(final String userId, final String userPassword, final String userName) {
		Optional<User> userOptional = userRepository.findByUserId(userId);
		if (userOptional.isPresent()) {
			throw new IllegalArgumentException("이미 가입된 아이디입니다.");
		}

		User user = User.builder()
			.userId(userId)
			.userPassword(passwordEncoder.encode(userPassword))
			.userName(userName)
			.userAuth(USER)
			.build();

		userRepository.save(user);
	}
}
