package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.model.User;
import fordsoft.tech.mydesk.repo.UserRepo;
import fordsoft.tech.mydesk.service.EmailSenderService;
import fordsoft.tech.mydesk.service.UserService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


@Component
@FxmlView("/ui/AdminPasswordPage.fxml")
public class AdminPasswordPageController implements Initializable {

    @Autowired
    Router router;

    @Autowired
    EmailSenderService emailSenderService;

    @FXML
    private PasswordField pfadminpassword;

    @FXML
    private Label lblwarning;


    @FXML
    void onAdminPage(ActionEvent event) {

        if (pfadminpassword.getText().equals("admin")) {
            router.navigate(AdminPageController.class, event);
        } else {

            lblwarning.setText("Wrong Password");
            emailSenderService.sendSimpleMessage("ehealthfrankfurt@gmail.com", "Admin Page Warning", "Someone tried to access the Admin Page!");

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}