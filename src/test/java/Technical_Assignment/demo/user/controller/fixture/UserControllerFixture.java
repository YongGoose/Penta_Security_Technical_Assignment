package Technical_Assignment.demo.user.controller.fixture;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import Technical_Assignment.demo.user.dto.UserCreateDto;
import Technical_Assignment.demo.user.dto.UserInsertDto;

@SuppressWarnings("NonAsciiCharacters")
public class UserControllerFixture {

	protected String 일반_회원_이름 = "foo";
	protected String 일반_회원_아이디 = "fooId";
	protected String 일반_회원_비밀번호 = "fooPassword";
	protected String 일반_회원_권한 = "SYSTEM_USER";

	protected UserCreateDto 일반_회원_생성_요청 = new UserCreateDto(일반_회원_아이디, 일반_회원_비밀번호, 일반_회원_이름, 일반_회원_권한);

	protected String 관리자_회원_이름 = "admin";
	protected String 관리자_회원_아이디 = "adminId";
	protected String 관리자_회원_비밀번호 = "adminPassword";
	protected String 관리자_회원_권한 = "SYSTEM_ADMIN";

	protected UserCreateDto 관리자_회원_생성_요청 = new UserCreateDto(관리자_회원_아이디, 관리자_회원_비밀번호, 관리자_회원_이름, 관리자_회원_권한);

	protected UserInsertDto 일반_회원_추가_결과 = new UserInsertDto(일반_회원_아이디, 일반_회원_비밀번호, 일반_회원_이름, 일반_회원_권한);
	protected UserInsertDto 관리자_회원_추가_결과 = new UserInsertDto(관리자_회원_아이디, 관리자_회원_비밀번호, 관리자_회원_이름, 관리자_회원_권한);

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	protected ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.alwaysDo(print())
			.build();

		objectMapper = new ObjectMapper();
	}
}
