package fordsoft.tech.mydesk.controller;


import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.model.User;
import fordsoft.tech.mydesk.repo.UserRepo;
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
@FxmlView("/ui/AdminPage.fxml")
public class AdminPageController implements Initializable {

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public PasswordField password;
    @FXML
    public Button saveUser;
    @FXML
    public Button reset;
    Stage stage;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private Router router;
    @FXML
    private Label userid;
    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField birthday;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField zip;
    @FXML
    private TextField email;
    @FXML
    private TextField insurancetype;
    @FXML
    private TextField insurancename;
    @FXML
    private ChoiceBox<Long> boxuserid;
    @FXML
    private MenuItem itemdelete;
    @FXML
    private MenuItem itemexit;
    @FXML
    private MenuItem itemdeleteUsers;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Long> colUserId;
    @FXML
    private TableColumn<User, String> colBirthday;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> colCity;
    @FXML
    private TableColumn<User, String> colInsuranceType;
    @FXML
    private TableColumn<User, Boolean> colEdit;
    @FXML
    private TableColumn<User, String> colUsername;
    @FXML
    private TableColumn<User, String> colLastname;
    @FXML
    private TableColumn<User, String> colAddress;
    @FXML
    private TableColumn<User, String> colFirstname;
    @FXML
    private TableColumn<User, String> colInsuranceName;
    @FXML
    private TableColumn<User, String> colZIP;
    @FXML
    private Button btnLogout;

    @FXML
    void deleteUsers(ActionEvent event) {

        List<User> users = userTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) userService.deleteInBatch(users);

        loadUserDetails();
    }


    @FXML
    void onLogout(ActionEvent event) {

        router.navigate(SelectionController.class, event);

    }

    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    public void onReload(ActionEvent event) {

        if (boxuserid.getSelectionModel().getSelectedItem().equals(0L)) {
            clearFields();
        }
        Long userid;

        String usernameS;
        String firstnameS;
        String lastnameS;
        String emailS;
        String addressS;
        String cityS;
        String zipS;
        String insurancenameS;
        String insurancetypeS;
        String birthdayS;
        String passwordS;

        User user = new User();

        userid = boxuserid.getSelectionModel().getSelectedItem();

        username.setText(userService.find(userid).get().getUsername());
        firstname.setText(userService.find(userid).get().getFirstname());
        lastname.setText(userService.find(userid).get().getLastname());
        email.setText(userService.find(userid).get().getEmail());
        address.setText(userService.find(userid).get().getAddress());
        city.setText(userService.find(userid).get().getCity());
        zip.setText(userService.find(userid).get().getZip());
        insurancename.setText(userService.find(userid).get().getInsuranceName());
        insurancetype.setText(userService.find(userid).get().getInsuranceType());
        birthday.setText(userService.find(userid).get().getBirthday());
        password.setText(userService.find(userid).get().getPassword());

    }


    @FXML
    private void onSaveUser(ActionEvent event) {

        if (boxuserid.getSelectionModel().getSelectedItem() == 0L) {

            User user = new User();
            user.setUsername(username.getText());
            user.setFirstname(firstname.getText());
            user.setLastname(lastname.getText());
            user.setBirthday(birthday.getText());
            user.setAddress(address.getText());
            user.setCity(city.getText());
            user.setZip(zip.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            user.setInsuranceType(insurancetype.getText());
            user.setInsuranceName(insurancename.getText());
            User newUser = userService.save(user);

            saveAlert(newUser);


        } else {
            User user = new User();

            Long userid;
            userid = boxuserid.getSelectionModel().getSelectedItem();

            user = userService.findByUsernameAndPassword(getUsername(), getPassword());


            user.setUsername(username.getText());
            user.setFirstname(firstname.getText());
            user.setLastname(lastname.getText());
            user.setBirthday(birthday.getText());
            user.setAddress(address.getText());
            user.setCity(city.getText());
            user.setZip(zip.getText());
            user.setEmail(email.getText());
            user.setPassword(password.getText());
            user.setInsuranceType(insurancetype.getText());
            user.setInsuranceName(insurancename.getText());

            userService.update(user);


            updateAlert(user);
        }

        loadUserDetails();
    }


    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }


    @FXML
    private void onDeleteUser(ActionEvent event) {
        List<User> users = userTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) userService.deleteInBatch(users);

        loadUserDetails();
    }

    private void clearFields() {

        // userid.setText(null);
        username.clear();
        firstname.clear();
        lastname.clear();
        birthday.clear();
        address.clear();
        city.clear();
        zip.clear();
        email.clear();
        insurancename.clear();
        insurancetype.clear();
        password.clear();
    }

    private void saveAlert(User user) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFirstname() + " has been created ");
        alert.showAndWait();
    }

    private void updateAlert(User user) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFirstname() + " has been updated.");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();

        userTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadUserDetails();
    }


    private void setColumnProperties() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));

        //Darum kümmern
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colZIP.setCellValueFactory(new PropertyValueFactory<>("zip"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colInsuranceName.setCellValueFactory(new PropertyValueFactory<>("insuranceName"));
        colInsuranceType.setCellValueFactory(new PropertyValueFactory<>("insuranceType"));

    }

    private void loadUserDetails() {
        userList.clear();
        userList.addAll(userService.findAll());
        userTable.setItems(userList);


        boxuserid.getItems().clear();

        Long newid = 0L;
        boxuserid.getItems().add(newid);
        for (int i = 0; i < userList.size(); i++) {
            boxuserid.getItems().addAll(userList.get(i).getId());
        }

    }


}

