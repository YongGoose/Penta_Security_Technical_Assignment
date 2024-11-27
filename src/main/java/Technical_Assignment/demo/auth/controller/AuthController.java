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

	/*
	로그인 되어 있는 경우 -> /user 페이지로 리다이렉트
	 */
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
				return "redirect:/user";
			}
		} catch (AuthenticationException | IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "signin";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(
		@RequestParam String userId,
		@RequestParam String userPassword,
		@RequestParam String userName,
		Model model
	) {
		try {
			authService.signup(userId, userPassword, userName);
			return "redirect:/auth/signin";
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "signup";
	}
}