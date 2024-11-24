package Technical_Assignment.demo.auth.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Technical_Assignment.demo.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	private static final String ERROR_MESSAGE = "아이디 또는 비밀번호가 잘못되었습니다.";

	@GetMapping("/signin")
	public String loginPage() {
		return "signin";
	}

	@PostMapping("/signin")
	public String login(
		@RequestParam String userId,
		@RequestParam String userPassword,
		HttpSession session,
		Model model
	) {
		try {
			Authentication token = authService.signin(userId, userPassword);

			if (token != null && token.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(token);
				session.setAttribute("user", token.getPrincipal());
				return "redirect:/home";
			}
		} catch (AuthenticationException | IllegalArgumentException e) {
			model.addAttribute("errorMessage", ERROR_MESSAGE);
		}
		return "signin";
	}
}