package Technical_Assignment.demo.user_history.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import Technical_Assignment.demo.user_history.entity.ActionType;
import Technical_Assignment.demo.user_history.entity.UserHistory;
import Technical_Assignment.demo.user_history.repository.UserHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserHistoryService {

	private final UserHistoryRepository userHistoryRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveUserHistory(Integer userId, ActionType actionType) {
		UserHistory userHistory = UserHistory.builder()
			.regUserId(userId)
			.actionType(actionType)
			.regDate(LocalDateTime.now())
			.build();

		userHistoryRepository.save(userHistory);
	}
}
