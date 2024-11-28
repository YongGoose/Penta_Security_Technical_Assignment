package Technical_Assignment.demo.user.controller;

import static Technical_Assignment.demo.user.entity.UserAuth.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Technical_Assignment.demo.user.dto.UserCreateDto;
import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserInsertDto;
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
		model.addAttribute("isAdmin", user.getUserAuth().equals(SYSTEM_ADMIN.name()));
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
	public String userDetailPage(@RequestParam String userId, Model model) {
		UserDetailDto userDetail = userService.getUserDetail(userId);
		model.addAttribute("userDetail", userDetail);
		return "userdetail";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@GetMapping("/update")
	public String userUpdatePage(@RequestParam String userId, Model model) {
		model.addAttribute("userId", userId);
		return "userupdate";
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@PostMapping("/update")
	public String userUpdate(@RequestParam String userId, @RequestParam String updateName) {
		UserUpdateDto userUpdate = userService.updateUser(userId, updateName);
		return "redirect:/user/detail?userId=" + userUpdate.getUserId();
	}

	@PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
	@PostMapping("/delete")
	public String userDelete(@RequestParam String userId) {
		userService.deleteUser(userId);
		return "redirect:/user/list";
	}

	/*
	Rest API
	 */
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<UserUpdateDto> updateUser(@RequestParam String userId, @RequestParam String updateName) {
		UserUpdateDto userUpdate = userService.updateUser(userId, updateName);
		return ResponseEntity.ok(userUpdate);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	public ResponseEntity<UserDetailDto> deleteUser(@RequestParam String userId) {
		UserDetailDto userDetailDto = userService.deleteUser(userId);
		return ResponseEntity.ok(userDetailDto);
	}

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<UserInsertDto> addUser(@RequestBody @Valid UserCreateDto userCreateDto) {
		UserInsertDto userInsertDto = userService.insertUser(userCreateDto);
		return ResponseEntity.ok(userInsertDto);
	}

	@GetMapping("/find/id")
	@ResponseBody
	public ResponseEntity<UserDetailDto> findUserByUserId(@RequestParam String userId) {
		UserDetailDto userDetail = userService.getUserDetail(userId);
		return ResponseEntity.ok(userDetail);
	}

	@GetMapping("/find/name")
	@ResponseBody
	public ResponseEntity<List<UserDetailDto>> findUserByUserName(@RequestParam String userName) {
		List<UserDetailDto> userDetails = userService.findUsersByUserName(userName);
		return ResponseEntity.ok(userDetails);
	}
}
