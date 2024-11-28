package Technical_Assignment.demo.user.controller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import Technical_Assignment.demo.user.controller.fixture.UserControllerFixture;
import Technical_Assignment.demo.user.service.UserService;

@WebMvcTest(UserController.class)
@SuppressWarnings("NonAsciiCharacters")
class UserControllerTest extends UserControllerFixture {

	@MockBean
	private UserService userService;

	@Test
	void 일반_회원_추가_테스트() throws Exception {
		// given
		given(userService.insertUser(일반_회원_아이디, 일반_회원_비밀번호, 일반_회원_이름, 일반_회원_권한))
			.willReturn(일반_회원_추가_요청);

		// when & then
		mockMvc.perform(
				post("/user/add")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.param("userId", 일반_회원_아이디)
					.param("password", 일반_회원_비밀번호)
					.param("name", 일반_회원_이름)
					.param("auth", 일반_회원_권한)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").value(일반_회원_추가_요청.getUserId()))
			.andExpect(jsonPath("$.userPassword").value(일반_회원_추가_요청.getUserPassword()))
			.andExpect(jsonPath("$.userName").value(일반_회원_추가_요청.getUserName()))
			.andExpect(jsonPath("$.userAuth").value(일반_회원_추가_요청.getUserAuth()));
	}

	@Test
	void 관리자_회원_추가_테스트() throws Exception {
		// given
		given(userService.insertUser(관리자_회원_아이디, 관리자_회원_비밀번호, 관리자_회원_이름, 관리자_회원_권한))
			.willReturn(관리자_회원_추가_요청);

		// when & then
		mockMvc.perform(
				post("/user/add")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.param("userId", 관리자_회원_아이디)
					.param("password", 관리자_회원_비밀번호)
					.param("name", 관리자_회원_이름)
					.param("auth", 관리자_회원_권한)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").value(관리자_회원_추가_요청.getUserId()))
			.andExpect(jsonPath("$.userPassword").value(관리자_회원_추가_요청.getUserPassword()))
			.andExpect(jsonPath("$.userName").value(관리자_회원_추가_요청.getUserName()))
			.andExpect(jsonPath("$.userAuth").value(관리자_회원_추가_요청.getUserAuth()));
	}

}