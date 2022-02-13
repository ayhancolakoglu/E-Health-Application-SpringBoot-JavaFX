package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	User  findByUsernameAndPassword(String username, String password);



	//boolean existsById(Long id);
}
