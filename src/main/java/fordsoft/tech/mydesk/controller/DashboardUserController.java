package fordsoft.tech.mydesk.controller;

import fordsoft.tech.mydesk.FxApplication;
import fordsoft.tech.mydesk.config.Router;
import fordsoft.tech.mydesk.model.Appointment;
import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.service.AppointmentService;
import fordsoft.tech.mydesk.service.DoctorService;
import fordsoft.tech.mydesk.service.EmailSenderService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * @author Bayram Ayhan Colakoglu
 */

@Component
@FxmlView("/ui/DashboardUser.fxml")
public class DashboardUserController implements Initializable {

    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private final ObservableList<Doctor> doctorList = FXCollections.observableArrayList();


    @Autowired
    Router router;

    Stage stage;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    EmailSenderService emailSenderService;


    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, String> colTime;


    @FXML
    private TableColumn<Appointment, String> colDate;

    @FXML
    private TableColumn<Appointment, String> colDoctor;


    @FXML
    private TableColumn<Appointment, String> colMedicalField;

    @FXML
    private MenuItem itemdeleteappointment;


    @FXML
    private Text birthday;

    @FXML
    private Text zip;

    @FXML
    private Text city;

    @FXML
    private Text firstlastname;

    @FXML
    private Button btnsearch;

    @FXML
    private Button btnlogout;

    @FXML
    private Text address;

    @FXML
    private TextField tfsearchdoctor;

    @FXML
    private Text insurancename;

    @FXML
    private ImageView pb;

    @FXML
    private Text healthinformation;

    @FXML
    private Button btnedit;

    @FXML
    private Text insurancetype;

    @FXML
    private TextField tfzip;

    //Button
    @FXML
    private Button btndentalmedicare;

    @FXML
    private Button btnfamilydoctor;

    @FXML
    private Button btnpediatrician;

    @FXML
    private Button btnotorhinolaryngologist;

    @FXML
    private Button btnorthodontist;

    @FXML
    private Button btnophthalmologist;

    @FXML
    private Button btnpsychotherapist;

    @FXML
    private Button btnneurologist;

    @FXML
    private Button btngynecologist;


    /**
     * @param event
     * @ControllerMethod navigates to DentalMedicarePageController class
     */
    @FXML
    void onDentalmedicare(ActionEvent event) {

        router.navigate(DentalMedicarePageController.class, event);


    }

    /**
     * @param event
     * @ControllerMethod navigates to FamilyDoctorController class
     */
    @FXML
    void onFamilydoctor(ActionEvent event) {
        router.navigate(FamilyDoctorPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to PediatricianPageController class
     */
    @FXML
    void onPediatrician(ActionEvent event) {
        router.navigate(PediatricianPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to OtorhinolaryngologistPageController class
     */

    @FXML
    void onOtorhinolaryngologist(ActionEvent event) {
        router.navigate(OtorhinolaryngologistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to OrthodontistPageController class
     */

    @FXML
    void onOrthodontist(ActionEvent event) {
        router.navigate(OrthodontistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to OphthalmologistPageController class
     */
    @FXML
    void onOphthalmologist(ActionEvent event) {
        router.navigate(OphthalmologistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to PsychotherapistPageController class
     */
    @FXML
    void onPsychotherapist(ActionEvent event) {
        router.navigate(PsychotherapistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to NeurologistPageController class
     */
    @FXML
    void onNeurologist(ActionEvent event) {
        router.navigate(NeurologistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to GynecologistPageController class
     */
    @FXML
    void onGynecologist(ActionEvent event) {
        router.navigate(GynecologistPageController.class, event);

    }

    /**
     * @param event
     * @ControllerMethod navigates to the LoginUserController class
     */
    @FXML
    void onLogout(ActionEvent event) {
        router.navigate(LoginUserController.class, event);
    }

    /**
     * @param event
     * @ControllerMethod navigates to the SearchUserController class
     */
    @FXML
    void onSearchUser(ActionEvent event) {
        router.navigate(SearchUserController.class, event);

    }


    /**
     * @param event
     * @ControllerMethod navigates to the SettingsUserController class
     */
    @FXML
    void onSettings(ActionEvent event) {
        router.navigate(SettingsUserController.class, event);

    }


    /**
     * Here we set Column properties that the table in the scene can find the matching columns in the database table
     */
    private void setColumnProperties() {
        colMedicalField.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getDoctor().getMedicalField()));
        colDoctor.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getDoctor().getTitle() + ". " + celldata.getValue().getDoctor().getFirstname() + " " + celldata.getValue().getDoctor().getLastname()));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));


    }

    /**
     * @param event
     * @ControllerMethod This method delete selected appointments, and opens a pop-up window that asks for deleting apoointment
     * The user will get an email with information about the canceled appointment
     */
    @FXML
    private void onDeleteAppointment(ActionEvent event) {
        List<Appointment> appointments = appointmentTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to cancle the selected Appointment");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) appointmentService.deleteInBatch(appointments);

        emailSenderService.sendSimpleMessage(FxApplication.currentuser.getEmail(), "Appointment canceled", "Appointment is succesfully canceled.");


        loadAppointmentDetails();
    }


    /**
     * This method load appintment details, at first it clear the table, then the list. After that it add All appointments from the current user
     * And then set to the items to the table
     */
    private void loadAppointmentDetails() {
        appointmentTable.getItems().clear();
        appointmentList.clear();
        appointmentList.addAll(appointmentService.findByUser(FxApplication.currentuser));
        appointmentTable.setItems(appointmentList);

    }


    /**
     * @param url
     * @param resourceBundle
     * @ControllerMethod This method initialize the scene with user information and user image
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();

        appointmentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnProperties();
        loadAppointmentDetails();

        address.setText(FxApplication.currentuser.getAddress());
        city.setText(FxApplication.currentuser.getCity());
        zip.setText(FxApplication.currentuser.getZip());
        birthday.setText(FxApplication.currentuser.getBirthday());
        insurancename.setText(FxApplication.currentuser.getInsuranceName());
        insurancetype.setText(FxApplication.currentuser.getInsuranceType());
        healthinformation.setText(FxApplication.currentuser.getHealthInformation());
        firstlastname.setText(FxApplication.currentuser.getFirstname() + " " + FxApplication.currentuser.getLastname());


        Image image = new Image(Objects.requireNonNull(getClass().getResource(FxApplication.currentuser.getPbPath())).toString());
        pb.setImage(image);


    }
}
