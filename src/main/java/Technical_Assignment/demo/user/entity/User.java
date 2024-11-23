package Technical_Assignment.demo.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SYSTEM_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_idx")
	private Integer id;

	@Column(name = "user_id", nullable = false, length = 30)
	private String userId;

	@Column(name = "user_pw", nullable = false, length = 100)
	private String userPassword;

	@Column(name = "user_nm", nullable = false, length = 100)
	private String userName;

	@Column(name = "user_auth", nullable = false, length = 20)
	private String userAuth;
}
