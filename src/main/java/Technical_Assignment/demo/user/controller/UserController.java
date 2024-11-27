package Technical_Assignment.demo.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserUpdateDto;
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
		User user = userService.findUserByUserId((String)session.getAttribute("user"));
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
	public String userDetailPage(@RequestParam String id, Model model) {
		UserDetailDto userDetail = userService.getUserDetail(id);
		model.addAttribute("userDetail", userDetail);
		return "userdetail";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@GetMapping("/update")
	public String userUpdatePage(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "userupdate";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@PostMapping("/update")
	public String userUpdate(@RequestParam String id, @RequestParam String updateName) {
		UserUpdateDto userUpdate = userService.updateUser(id, updateName);
		return "redirect:/user/detail?id=" + userUpdate.getUserId();
	}

	// Rest API
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<UserUpdateDto> updateUser(@RequestParam String id, @RequestParam String updateName) {
		UserUpdateDto userUpdate = userService.updateUser(id, updateName);
		return ResponseEntity.ok(userUpdate);
	}
}
