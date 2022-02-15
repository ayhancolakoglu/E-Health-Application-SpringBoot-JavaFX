package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author Bayram Ayhan Colakoglu
 */


public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * @param username
     * @return optional user, find by username
     */
    Optional<User> findByUsername(String username);

    /**
     * @param username
     * @param password
     * @return user , find by username and password
     */
    User findByUsernameAndPassword(String username, String password);
}
