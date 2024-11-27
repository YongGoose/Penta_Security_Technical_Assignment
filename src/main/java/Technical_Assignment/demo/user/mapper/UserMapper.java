package Technical_Assignment.demo.user.mapper;

import Technical_Assignment.demo.user.dto.UserDetailDto;
import Technical_Assignment.demo.user.dto.UserSummaryDto;
import Technical_Assignment.demo.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

	public static UserSummaryDto toUserSummaryDto(User user) {
		return new UserSummaryDto(user.getId(), user.getUserName());
	}

	public static UserDetailDto toUserResponseDto(User user) {
		return new UserDetailDto(user.getUserId(), user.getUserName(), user.getUserAuth());
	}
}
