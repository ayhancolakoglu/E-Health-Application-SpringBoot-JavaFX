package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.service.EmailSenderService;
import fordsoft.tech.mydesk.service.EncryptionService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
@FxmlView("/ui/SignUpUser.fxml")
public class SignupUserController implements Initializable {


    public Button tfalreadyaccount;
    @Autowired
    Router router;
    Stage stage;
    @Autowired
    UserService userService;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tffirstname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfbirthday;
    @FXML
    private TextField tfaddress;
    @FXML
    private TextField tfcity;
    @FXML
    private TextField tfzip;
    @FXML
    private TextField tfinsurancename;
    @FXML
    private TextField tfinsurancetype;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    @FXML
    private Button btnregister;
    @FXML
    private Label lblPassword;

    @FXML
    private Label lblNotFilled;

    @FXML
    private Label lbluserexists;

    @Autowired
    private EmailSenderService emailSenderService;


    private PasswordEncoder passwordEncoder;

    /**
     * @param event ActionEvent
     * @ControllerMethod this method navigates to the LoginUserController class
     */
    @FXML
    void onLoginuser(ActionEvent event) {
        router.navigate(LoginUserController.class, event);
    }

    /**
     * @param event ActionEvent
     * @ControllerMethod this method is for the registration
     */
    @FXML
    void onRegistration(ActionEvent event) throws IOException {
        String username;
        String firstname;
        String lastname;
        String birthday;
        String address;
        String city;
        String zip;
        String insurancename;
        String insurancetype;
        String email;
        String password;


        ArrayList<TextField> tfList = new ArrayList<>();
        tfList.add(tfusername);
        tfList.add(tffirstname);
        tfList.add(tflastname);
        tfList.add(tfbirthday);
        tfList.add(tfaddress);
        tfList.add(tfcity);
        tfList.add(tfzip);
        tfList.add(tfinsurancename);
        tfList.add(tfinsurancetype);
        tfList.add(tfemail);


        ArrayList<PasswordField> pfList = new ArrayList<>();
        pfList.add(pfpassword);


        //store the user inputs
        username = tfusername.getText();
        firstname = tffirstname.getText();
        lastname = tflastname.getText();
        birthday = tfbirthday.getText();
        address = tfaddress.getText();
        city = tfcity.getText();
        zip = tfzip.getText();
        insurancename = tfinsurancename.getText();
        insurancetype = tfinsurancetype.getText();
        email = tfemail.getText();
        password = pfpassword.getText();


        /**
         * if a user is valid the user will be saved and will get an email to his email
         */
        if (validate()) {
            FxApplication.currentuser.setUsername(username);
            FxApplication.currentuser.setFirstname(firstname);
            FxApplication.currentuser.setLastname(lastname);
            FxApplication.currentuser.setBirthday(birthday);
            FxApplication.currentuser.setAddress(address);
            FxApplication.currentuser.setCity(city);
            FxApplication.currentuser.setZip(zip);
            FxApplication.currentuser.setInsuranceName(insurancename);
            FxApplication.currentuser.setInsuranceType(insurancetype);
            FxApplication.currentuser.setEmail(email);
            FxApplication.currentuser.setPassword(password);
            FxApplication.currentuser.setPbPath("/images/pb/profilePicture.png");


            userService.save(FxApplication.currentuser);
            router.navigate(LoginUserController.class, event);
            emailSenderService.sendSimpleMessage(email, "E-health FFM Sign Up", "Hey " + FxApplication.currentuser.getFirstname() + " you are now part of the E-health FFM Community");
        }
    }


    /**
     * @return this method returns a boolean, if a username already exists it will be false
     * if the password length is <8 it will be false, and if any field is empfy it will be false
     * if every condition asre valid it will be true .
     */
    public boolean validate() {

        boolean valid = true;

        lbluserexists.setText("");
        lblPassword.setText("");
        lblNotFilled.setText("");

        //USERNAME CHECK

        if (userService.userExists(tfusername.getText())) {
            lbluserexists.setText("Username already exists");
            valid = false;
        }

        if (pfpassword.getText().length() < 8) {
            lblPassword.setText("Password is too short");
            valid = false;
        }

        if (tfusername.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tffirstname.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tflastname.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfbirthday.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfaddress.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfcity.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfzip.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfinsurancename.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        } else if (tfinsurancetype.getText().isEmpty()) {
            lblNotFilled.setText("Not all Data is filled");
            valid = false;
        }

        return valid;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
