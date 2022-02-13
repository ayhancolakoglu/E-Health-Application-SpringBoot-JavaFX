package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.service.DoctorService;
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


@Component
@FxmlView("/ui/LoginDoctor.fxml")
public class LoginDoctorController implements Initializable {

    @Autowired
    private DoctorService doctorService;


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
    private Label lblLogin;


    @FXML
    void onSelection(ActionEvent event){
        router.navigate(SelectionController.class,event);
    }

    @FXML
    void onSignup(ActionEvent event) {

        router.navigate(SignupUserController.class, event);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        if (doctorService.authenticate(getUsername(), getPassword())) {
            FxApplication.currentdoctor = doctorService.findByUsernameAndPassword(getUsername(), getPassword());
            router.navigate(SelectionController.class, event);

        } else {
            lblLogin.setText("Login Failed.");
        }

    }

    public String getPassword() {
        return pfpassword.getText();
    }

    public String getUsername() {
        return tfusername.getText();
    }

    private Stage stage;

    public void initialize(URL location, ResourceBundle resources) {
        this.stage = new Stage();
    }


}
