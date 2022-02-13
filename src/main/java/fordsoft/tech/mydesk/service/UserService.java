package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.controller.SignupUserController;
import fordsoft.tech.mydesk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private ServiceUtil serviceUtil;




    public User save(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }

    public User update(User entity) {
        return serviceUtil.getUserRepo().save(entity);
    }


    public void delete(User entity) {
        serviceUtil.getUserRepo().delete(entity);
    }

    public void delete(Long id) {
        serviceUtil.getUserRepo().deleteById(id);
    }

    public Optional<User> find(Long id) {
        return serviceUtil.getUserRepo().findById(id);
    }

    public List<User> findAll() {
        return serviceUtil.getUserRepo().findAll();
    }

    public boolean authenticate(String username, String password){
        Optional<User> user = this.findByUsername(username);
        if(user.isEmpty()){
            return false;
        }else{
            User u = user.get();

            if(password.equals(u.getPassword())) return true;
            else return false;
        }
    }


    public boolean userExists(String username){
        Optional<User> user = this.findByUsername(username);
        return user.isPresent();
    }

    public Optional<User> findByUsername(String username) {
        return serviceUtil.getUserRepo().findByUsername(username);
    }

    public User  findByUsernameAndPassword(String username, String password){
        return serviceUtil.getUserRepo().findByUsernameAndPassword(username, password);
    }

    /*
    public User findByUserId(Long id){
        return serviceUtil.getUserRepo().findByUserId(id);
    }


     */
    public void deleteInBatch(List<User> users) {
        serviceUtil.getUserRepo().deleteInBatch(users);
    }

}
