package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.controller.SignupUserController;
import fordsoft.tech.mydesk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Service
public class UserService {
    @Autowired
    private ServiceUtil serviceUtil;


    /**
     * @param entity
     * @return this method saves an user in our users table
     */
    public User save(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }

    /**
     * @param entity
     * @return this methods updates an user in our users table
     */
    public User update(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }

    /**
     * @param id
     * @return an optional user that was find by id
     */
    public Optional<User> find(Long id) {
        return serviceUtil.getUserRepo().findById(id);
    }

    /**
     * @return a list of users that was find by id
     */
    public List<User> findAll() {
        return serviceUtil.getUserRepo().findAll();
    }


    /**
     * @param username
     * @param password
     * @return a boolean that checks if an user with a given username and password exists. If its exists and is not empty its return true
     */
    public boolean authenticate(String username, String password) {
        Optional<User> user = this.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        } else {
            User u = user.get();

            if (password.equals(u.getPassword())) return true;
            else return false;
        }
    }


    /**
     * @param username
     * @return a boolean that checks if a user exists
     */
    public boolean userExists(String username) {
        Optional<User> user = this.findByUsername(username);
        return user.isPresent();
    }

    /**
     * @param username
     * @return a user that was found by username
     */
    public Optional<User> findByUsername(String username) {
        return serviceUtil.getUserRepo().findByUsername(username);
    }

    /**
     * @param username
     * @param password
     * @return a user that was found by username and password
     */
    public User findByUsernameAndPassword(String username, String password) {
        return serviceUtil.getUserRepo().findByUsernameAndPassword(username, password);
    }

    /**
     * @param users delete a user in the table
     */
    public void deleteInBatch(List<User> users) {
        serviceUtil.getUserRepo().deleteInBatch(users);
    }

}
