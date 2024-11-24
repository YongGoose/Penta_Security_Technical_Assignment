package Technical_Assignment.demo.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		// TODO : 예외 처리 추가
		User user = userService.findUserByUserId((String) session.getAttribute("user"));

		model.addAttribute("userName", user.getUserName());
		model.addAttribute("isAdmin", user.getUserAuth().equals("SYSTEM_ADMIN"));
		return "home";
	}

}
