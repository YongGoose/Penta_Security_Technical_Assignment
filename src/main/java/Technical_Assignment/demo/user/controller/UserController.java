package Technical_Assignment.demo.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping
	public String homePage(HttpSession session, Model model) {
		User user = userService.findUserByUserId((String) session.getAttribute("user"));
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("isAdmin", user.getUserAuth().equals("SYSTEM_ADMIN"));
		return "home";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@GetMapping("/list")
	public String userListPage(Model model) {
		model.addAttribute("userList", userService.findAll());
		return "userlist";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@GetMapping("/detail")
	public String userDetailPage(@RequestParam Integer id, Model model) {
		UserDetailDto userDetail = userService.getUserDetail(id);
		model.addAttribute("userDetail", userDetail);
		return "userdetail";
	}
}
