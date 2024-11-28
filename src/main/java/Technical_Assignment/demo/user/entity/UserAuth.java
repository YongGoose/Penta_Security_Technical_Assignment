package Technical_Assignment.demo.user.entity;

import java.util.Arrays;

public enum UserAuth {
	SYSTEM_ADMIN,
	SYSTEM_USER;

	public static boolean contains(String value) {
		return Arrays.stream(UserAuth.values())
			.anyMatch(auth -> auth.name().equals(value));
	}
}
