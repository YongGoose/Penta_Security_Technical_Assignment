package Technical_Assignment.demo.user_history.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER_HISTORY")
public class UserHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_idx")
	private Integer id;

	@Column(name = "url", nullable = false, columnDefinition = "TEXT")
	private String url;

	@Enumerated(EnumType.STRING)
	@Column(name = "action_type", nullable = false, columnDefinition = "ENUM('C','U','D')")
	private ActionType actionType;

	@Column(name = "reg_user_idx", nullable = false)
	private Integer regUserId;

	@Column(name = "reg_ip", nullable = false, length = 16)
	private String regIp;

	@Column(name = "reg_dt", nullable = false)
	private LocalDateTime regDate;

	@Builder
	public UserHistory(final ActionType actionType, final Integer regUserId, final LocalDateTime regDate) {
		this.actionType = actionType;
		this.regUserId = regUserId;
		this.regDate = regDate;
		this.url = "url";
		this.regIp = "regIp";
	}
}
