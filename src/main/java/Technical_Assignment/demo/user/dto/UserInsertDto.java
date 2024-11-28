package Technical_Assignment.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInsertDto {

	private String userId;
	private String userPassword;
	private String userName;
	private String userAuth;
}
