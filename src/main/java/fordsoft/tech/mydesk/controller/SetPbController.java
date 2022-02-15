package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
@FxmlView("/ui/SetPb.fxml")
public class SetPbController implements Initializable {


    @Autowired
    Router router;

    Stage stage;

    @Autowired
    UserService userService;


    @FXML
    private ImageView pb3;

    @FXML
    private ImageView pb2;

    @FXML
    private Button btnsave;

    @FXML
    private ImageView pb5;

    @FXML
    private ImageView pb4;

    @FXML
    private ImageView defaultpb;

    @FXML
    private ImageView pb7;

    @FXML
    private ImageView pb6;

    @FXML
    private ImageView pb9;

    @FXML
    private ImageView pb8;

    @FXML
    private ImageView save;

    @FXML
    private Button btnback;

    @FXML
    private ImageView pb13;

    @FXML
    private ImageView pb14;

    @FXML
    private Button btnpb19;

    @FXML
    private ImageView pb15;

    @FXML
    private Button btnpb18;

    @FXML
    private ImageView pb16;

    @FXML
    private Button btnpb17;

    @FXML
    private Button btnpb16;

    @FXML
    private ImageView pb10;

    @FXML
    private Button btnpb15;

    @FXML
    private ImageView pb11;

    @FXML
    private Button btnpb14;

    @FXML
    private ImageView pb12;

    @FXML
    private Button btnpb13;

    @FXML
    private ImageView pb17;

    @FXML
    private ImageView pb18;

    @FXML
    private ImageView pb19;

    @FXML
    private Button btnpb1;

    @FXML
    private Button btnpb3;

    @FXML
    private Button btnpb2;

    @FXML
    private Button btnpb5;

    @FXML
    private Button btnpb4;

    @FXML
    private Button btnpb7;

    @FXML
    private Button btnpb12;

    @FXML
    private Button btnpb6;

    @FXML
    private Button btnpb11;

    @FXML
    private Button btnpb9;

    @FXML
    private Button btnpb10;

    @FXML
    private Button btnpb8;

    @FXML
    private ImageView pb1;

    @FXML
    private Button btndefaultpb;


    @FXML
    void onSavePb(ActionEvent event) {

        userService.update(FxApplication.currentuser);


    }

    @FXML
    void onSettingsUser(ActionEvent event) {

        router.navigate(SettingsUserController.class, event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btndefaultpb.setOnAction(event1 -> FxApplication.currentuser.setPbPath("/images/pb/profilePicture.png"));
        btnpb1.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb1.png"));
        btnpb2.setOnAction(event1 -> FxApplication.currentuser.setPbPath("/images/pb/pb2.png"));
        btnpb3.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb3.png"));
        btnpb4.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb4.png"));
        btnpb5.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb5.png"));
        btnpb6.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb6.png"));
        btnpb7.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb7.png"));
        btnpb8.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb8.png"));
        btnpb9.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb9.png"));
        btnpb10.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb10.png"));
        btnpb11.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb11.png"));
        btnpb12.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb12.png"));
        btnpb13.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb13.png"));
        btnpb14.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb14.png"));
        btnpb15.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb15.png"));
        btnpb16.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb16.png"));
        btnpb17.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb17.png"));
        btnpb18.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb18.png"));
        btnpb19.setOnAction(event -> FxApplication.currentuser.setPbPath("/images/pb/pb19.png"));


    }


}
