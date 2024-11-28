package Technical_Assignment.demo.user.service;


import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import Technical_Assignment.demo.user.entity.User;
import Technical_Assignment.demo.user.service.fixture.UserServiceFixture;

@SpringBootTest
@SuppressWarnings("NonAsciiCharacters")
@Testcontainers
class UserServiceTest extends UserServiceFixture {

	@Autowired
	private UserService userService;

	@Container
	static MariaDBContainer MARIA_DB_CONTAINER = new MariaDBContainer("mariadb:10.5.9");

	@Test
	void 회원_추가_테스트() {
		// given

		// when
		userService.insertUser(일반_회원_생성_요청);
		userService.insertUser(관리자_회원_생성_요청);

		// then
		User actual_일반 = userService.findUserByUserId(일반_회원_아이디);
		User actual_관리자 = userService.findUserByUserId(관리자_회원_아이디);

		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actual_일반.getUserAuth()).isEqualTo(일반_회원_권한);
			softAssertions.assertThat(actual_관리자.getUserAuth()).isEqualTo(관리자_회원_권한);
		});
	}
}