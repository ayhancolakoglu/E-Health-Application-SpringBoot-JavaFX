package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.model.User;
import fordsoft.tech.mydesk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


@Component
@FxmlView("/ui/SettingsUser.fxml")
public class SettingsUserController implements Initializable {




    @Autowired
    Router router;

    Stage stage;

    @Autowired
    UserService userService;

    @FXML
    private Text profileName;

    @FXML
    private Button btnsave;

    @FXML
    private ImageView pb;

    @FXML
    private Button btnback;

    @FXML
    private TextField tfaddress;

    @FXML
    private TextField tfcity;

    @FXML
    private TextField tfzip;

    @FXML
    private TextField tfinsurancename;

    @FXML
    private TextField tfbirthday;

    @FXML
    private TextArea tagenhealthinfo;

    @FXML
    private TextField tfinsurancetype;

    @FXML
    private Button btneditpb;


    @FXML
    void saveChanges(ActionEvent event) {
        String address;
        String city;
        String zip;
        String birthday;
        String insurancename;
        String insurancetype;
        String healthinformation;

        ArrayList<TextField> tflist = new ArrayList<>();
        tflist.add(tfaddress);
        tflist.add(tfcity);
        tflist.add(tfzip);
        tflist.add(tfbirthday);
        tflist.add(tfinsurancename);
        tflist.add(tfinsurancetype);


        ArrayList<TextArea> taList = new ArrayList<>();
        taList.add(tagenhealthinfo);

        //store the input
        address = tfaddress.getText();
        city = tfcity.getText();
        zip = tfzip.getText();
        birthday = tfbirthday.getText();
        insurancename = tfinsurancename.getText();
        insurancetype = tfinsurancetype.getText();
        healthinformation = tagenhealthinfo.getText();





        FxApplication.currentuser.setAddress(address);
        FxApplication.currentuser.setCity(city);
        FxApplication.currentuser.setZip(zip);
        FxApplication.currentuser.setBirthday(birthday);
        FxApplication.currentuser.setInsuranceName(insurancename);
        FxApplication.currentuser.setInsuranceType(insurancetype);
        FxApplication.currentuser.setHealthInformation(healthinformation);

        userService.update(FxApplication.currentuser);
    }







@FXML
    void backToProfile(ActionEvent event) {

        router.navigate(DashboardUserController.class, event);

    }

    @FXML
    void editProfilePicture(ActionEvent event) {

        router.navigate(SetPbController.class, event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        profileName.setText(FxApplication.currentuser.getUsername());
        tfaddress.setText(FxApplication.currentuser.getAddress());
        tfcity.setText(FxApplication.currentuser.getCity());
        tfzip.setText(FxApplication.currentuser.getZip());
        tfbirthday.setText(FxApplication.currentuser.getBirthday());
        tfinsurancetype.setText(FxApplication.currentuser.getInsuranceType());
        tfinsurancename.setText(FxApplication.currentuser.getInsuranceName());
        tagenhealthinfo.setText(FxApplication.currentuser.getHealthInformation());

        Image image = new Image(Objects.requireNonNull(getClass().getResource(FxApplication.currentuser.getPbPath())).toString());
        pb.setImage(image);


    }
}
