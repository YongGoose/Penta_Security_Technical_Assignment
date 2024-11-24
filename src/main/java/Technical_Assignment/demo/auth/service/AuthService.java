package Technical_Assignment.demo.auth.service;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private static final String ADMIN = "SYSTEM_ADMIN";

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Authentication signin(final String userId, final String userPassword) {
		User user = userRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다."));

		// 관리자인 경우 암호화되지 않은 비밀번호와 비교, dml.sql 참조
		if (user.getUserAuth().equals(ADMIN) && user.getUserPassword().equals(userPassword)) {
			return new UsernamePasswordAuthenticationToken(
				userId, userPassword, List.of(new SimpleGrantedAuthority(user.getUserAuth()))
			);
		}

		if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
			throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
		}

		return new UsernamePasswordAuthenticationToken(
			userId, userPassword, List.of(new SimpleGrantedAuthority(user.getUserAuth()))
		);
	}
}
