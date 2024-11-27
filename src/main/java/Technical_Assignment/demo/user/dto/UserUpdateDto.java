package Technical_Assignment.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateDto {

	private Integer id;
	private String userId;
	private String userName;
	private String userAuth;
}
