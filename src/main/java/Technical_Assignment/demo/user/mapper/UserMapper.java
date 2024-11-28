package Technical_Assignment.demo.user.mapper;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserInsertDto;
import Technical_Assignment.demo.user.dto.UserSummaryDto;
import Technical_Assignment.demo.user.dto.UserUpdateDto;
import Technical_Assignment.demo.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

	public static UserSummaryDto toUserSummaryDto(User user) {
		return new UserSummaryDto(user.getId(), user.getUserId(), user.getUserName());
	}

	public static UserDetailDto toUserDetailDto(User user) {
		return new UserDetailDto(user.getUserId(), user.getUserName(), user.getUserAuth());
	}

	public static UserUpdateDto toUserUpdateDto(User user) {
		return new UserUpdateDto(user.getId(), user.getUserId(), user.getUserName(), user.getUserAuth());
	}

	public static UserInsertDto toUserInsertDto(User user, String password) {
		return new UserInsertDto(user.getUserId(), password ,user.getUserName(), user.getUserAuth());
	}
}
