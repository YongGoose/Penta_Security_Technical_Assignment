package Technical_Assignment.demo.user.service.fixture;

import Technical_Assignment.demo.user.dto.UserCreateDto;

@SuppressWarnings("NonAsciiCharacters")
public class UserServiceFixture {

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
}
