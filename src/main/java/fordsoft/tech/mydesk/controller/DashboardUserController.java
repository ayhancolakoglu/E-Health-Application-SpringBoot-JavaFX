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


    @FXML
    void onDentalmedicare(ActionEvent event) {

        router.navigate(DentalMedicarePageController.class, event);


    }

    @FXML
    void onFamilydoctor(ActionEvent event) {
        router.navigate(FamilyDoctorPageController.class, event);

    }

    @FXML
    void onPediatrician(ActionEvent event) {
        router.navigate(PediatricianPageController.class, event);

    }

    @FXML
    void onOtorhinolaryngologist(ActionEvent event) {
        router.navigate(OtorhinolaryngologistPageController.class, event);

    }

    @FXML
    void onOrthodontist(ActionEvent event) {
        router.navigate(OrthodontistPageController.class, event);

    }

    @FXML
    void onOphthalmologist(ActionEvent event) {
        router.navigate(OphthalmologistPageController.class, event);

    }

    @FXML
    void onPsychotherapist(ActionEvent event) {
        router.navigate(PsychotherapistPageController.class, event);

    }

    @FXML
    void onNeurologist(ActionEvent event) {
        router.navigate(NeurologistPageController.class, event);

    }

    @FXML
    void onGynecologist(ActionEvent event) {
        router.navigate(GynecologistPageController.class, event);

    }

    @FXML
    void onLogout(ActionEvent event) {
        router.navigate(LoginUserController.class, event);
    }

    @FXML
    void onSearchUser(ActionEvent event) {
        router.navigate(SearchUserController.class, event);

    }

    @FXML
    void onSettings(ActionEvent event) {
        router.navigate(SettingsUserController.class, event);

    }


    private void setColumnProperties() {
        colMedicalField.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getDoctor().getMedicalField()));
        colDoctor.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getDoctor().getTitle() + ". " + celldata.getValue().getDoctor().getFirstname() + " " + celldata.getValue().getDoctor().getLastname()));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));


    }

    @FXML
    private void onDeleteAppointment(ActionEvent event) {
        List<Appointment> appointments = appointmentTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) appointmentService.deleteInBatch(appointments);

        emailSenderService.sendSimpleMessage(FxApplication.currentuser.getEmail(), "Appointment canceled", "Appointment is succesfully canceled.");


        loadAppointmentDetails();
    }


    private void loadAppointmentDetails() {
        appointmentTable.getItems().clear();
        appointmentList.clear();
        appointmentList.addAll(appointmentService.findByUser(FxApplication.currentuser));
        appointmentTable.setItems(appointmentList);

    }


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
