package Technical_Assignment.demo.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Technical_Assignment.demo.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserId(String userId);

	List<User> findUsersByUserName(String userName);
}
