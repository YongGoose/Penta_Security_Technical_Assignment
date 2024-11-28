package Technical_Assignment.demo.user_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Technical_Assignment.demo.user_history.entity.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
}
