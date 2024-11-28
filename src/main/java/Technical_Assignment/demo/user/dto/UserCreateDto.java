package Technical_Assignment.demo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateDto {

	@NotNull
	@Size(max = 30)
	private String userId;

	@NotNull
	@Size(max = 100)
	private String userPassword;

	@NotNull
	@Size(max = 100)
	private String userName;

	@NotNull
	@Size(max = 20)
	private String userAuth;
}
