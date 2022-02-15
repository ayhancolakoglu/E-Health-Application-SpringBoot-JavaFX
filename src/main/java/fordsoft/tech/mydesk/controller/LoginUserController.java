package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.service.EmailSenderService;
import fordsoft.tech.mydesk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
@FxmlView("/ui/LoginUser.fxml")
public class LoginUserController implements Initializable {

    @Autowired
    private UserService userService;


    @Autowired
    private EmailSenderService emailService;


    @Autowired
    private Router router;

    @FXML
    private Button btnlogin;

    @FXML
    private Button btncreateaccount;

    @FXML
    private PasswordField pfpassword;

    @FXML
    private TextField tfusername;

    @FXML
    private Label lbllogin;
    private Stage stage;

    /**
     * @param event
     * @ControllerMethod This method navigates to the SelectionController class
     */
    @FXML
    void onSelection(ActionEvent event) {
        router.navigate(SelectionController.class, event);
    }

    /**
     * @param event
     * @ControllerMethod This method navigates to the SignupUserController class
     */
    @FXML
    void onSignup(ActionEvent event) {

        router.navigate(SignupUserController.class, event);
    }

    /**
     * @param event ActionEvent
     * @ControllerMethod This method navigates after a correct
     * authentication to the DashboardUserController class
     */
    @FXML
    private void login(ActionEvent event) throws IOException {

        if (userService.authenticate(getUsername(), getPassword())) {
            FxApplication.currentuser = userService.findByUsernameAndPassword(getUsername(), getPassword());
            router.navigate(DashboardUserController.class, event);
            //emailService.sendSimpleMessage("ayhancolakoglu45@gmail.com","Thema", "Youre Logged in");

        } else {
            lbllogin.setText("Wrong username or password");
        }

    }

    /**
     * @return pfpassword text
     */
    public String getPassword() {
        return pfpassword.getText();
    }

    /**
     * @return tfusername text
     */
    public String getUsername() {
        return tfusername.getText();
    }


    public void initialize(URL location, ResourceBundle resources) {
        this.stage = new Stage();
    }


}
