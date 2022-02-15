package fordsoft.tech.mydesk.controller;

import fordsoft.tech.mydesk.config.Router;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Bayram Ayhan Colakoglu
 */

@Component
@FxmlView("/ui/Selection.fxml")
public class SelectionController implements Initializable {

    @Autowired
    Router router;


    Stage stage;

    @FXML
    private Button doctor;

    @FXML
    private Button user;

    /**
     * @param event ActionEvent
     * @ControllerMethod navigates to LoginUserController class
     */
    @FXML
    void onLoginuser(ActionEvent event) {
        router.navigate(LoginUserController.class, event);

    }

    /**
     * @param event ActionEvent
     * @ControllerMethod navigates to the AdminPasswordPageController class
     */
    @FXML
    void onAdmin(ActionEvent event) {

        router.navigate(AdminPasswordPageController.class, event);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();

    }
}
