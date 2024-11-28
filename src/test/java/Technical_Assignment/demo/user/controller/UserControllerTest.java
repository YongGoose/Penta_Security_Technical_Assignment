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
		given(userService.insertUser(일반_회원_생성_요청))
			.willReturn(일반_회원_추가_결과);

		// when & then
		mockMvc.perform(
				post("/user/create")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(일반_회원_생성_요청))
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").value(일반_회원_추가_결과.getUserId()))
			.andExpect(jsonPath("$.userPassword").value(일반_회원_추가_결과.getUserPassword()))
			.andExpect(jsonPath("$.userName").value(일반_회원_추가_결과.getUserName()))
			.andExpect(jsonPath("$.userAuth").value(일반_회원_추가_결과.getUserAuth()));
	}

	@Test
	void 관리자_회원_추가_테스트() throws Exception {
		// given
		given(userService.insertUser(관리자_회원_생성_요청))
			.willReturn(관리자_회원_추가_결과);

		// when & then
		mockMvc.perform(
				post("/user/create")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(관리자_회원_생성_요청))
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userId").value(관리자_회원_추가_결과.getUserId()))
			.andExpect(jsonPath("$.userPassword").value(관리자_회원_추가_결과.getUserPassword()))
			.andExpect(jsonPath("$.userName").value(관리자_회원_추가_결과.getUserName()))
			.andExpect(jsonPath("$.userAuth").value(관리자_회원_추가_결과.getUserAuth()));
	}

}