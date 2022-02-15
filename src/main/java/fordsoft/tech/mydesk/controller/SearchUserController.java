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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * @author Bayram Ayhan Colakoglu
 */


@Component
@FxmlView("/ui/SearchUser.fxml")
public class SearchUserController implements Initializable {

    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private final ObservableList<Doctor> doctorList = FXCollections.observableArrayList();

    private final ObservableList<String> reminderlist = FXCollections.observableArrayList();
    private final ObservableList<String> timelist = FXCollections.observableArrayList();

    @Autowired
    Router router;

    Stage stage;

    @Autowired
    AppointmentService appointmentService;


    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    DoctorService doctorService;

    @FXML
    private ImageView save;


    @FXML
    private Button btnsave;

    @FXML
    private Button btnsearch;

    @FXML
    private Button btnhome;

    @FXML
    private TableView<Doctor> doctorTable;

    @FXML
    private ChoiceBox<String> boxtime;

    @FXML
    private ChoiceBox<String> boxreminder;

    @FXML
    private ChoiceBox<String> boxDoctor;

    @FXML
    private DatePicker date;

    @FXML
    private TextField tfmedicalfield;

    @FXML
    private TextArea tahealthinformation;

    @FXML
    private TextField tfzip;

    @FXML
    private TableColumn<Doctor, String> colCity;

    @FXML
    private TableColumn<Doctor, String> colDoctor;


    @FXML
    private TableColumn<Doctor, String> colAddress;

    @FXML
    private TableColumn<Doctor, String> colMedicalField;

    @FXML
    private TableColumn<Doctor, String> colZIP;

    @FXML
    private Label lblappointment;


    /*

    LocalDate pickedDate;

    @FXML
    void getDate(ActionEvent event){

        LocalDate pickedDate = appointmentdate.getValue();
    }


    void timerOneWeek(){

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                emailSenderService.sendSimpleMessage(FxApplication.currentuser.getEmail(),"Appointment Reminder!", "Your appointment is in one week");

            }
        };

        int day = pickedDate.getDayOfMonth();
        int month = pickedDate.getMonthValue();
        int year = pickedDate.getYear();


        LocalDate reminderdate = pickedDate.minusWeeks(1);


        Calendar date = Calendar.getInstance();
        date = localDateToDate(reminderdate);

        date.set(Calendar.HOUR_OF_DAY, 14);
        date.set(Calendar.MINUTE,55);



                timer.schedule(task, date.getTime());
    }

    public static Calendar localDateToDate(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        //assuming start of day
        calendar.set(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        return calendar;
    }

     */

    @FXML
    void onSearch(ActionEvent event) {

        ArrayList<TextField> tflist = new ArrayList<>();
        tflist.add(tfmedicalfield);
        tflist.add(tfzip);

        String medicalfield;
        String zip;

        medicalfield = tfmedicalfield.getText();
        zip = tfzip.getText();


        boxDoctor.getItems().clear();
        doctorList.clear();
        doctorList.addAll(doctorService.findByMedicalFieldAndZip(medicalfield, zip));
        for (int z = 0; z < doctorList.size(); z++) {
            boxDoctor.getItems().addAll(doctorList.get(z).getLastname());
        }


        loadDetails();

    }


    private void loadDetails() {

        String medicalfield;
        String zip;

        medicalfield = tfmedicalfield.getText();
        zip = tfzip.getText();

        doctorList.clear();
        doctorList.addAll(doctorService.findByMedicalFieldAndZip(medicalfield, zip));
        doctorTable.setItems(doctorList);

    }

    private void setColumnProperties() {
        colMedicalField.setCellValueFactory(new PropertyValueFactory<>("medicalField"));
        colDoctor.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getTitle() + ". " + celldata.getValue().getFirstname() + " " + celldata.getValue().getLastname()));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colZIP.setCellValueFactory(new PropertyValueFactory<>("zip"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));

    }


    @FXML
    void onSaveAppointment(ActionEvent event) {

        Appointment appointment = new Appointment();

        appointment.setUser(FxApplication.currentuser);

        String selectedDoctor;
        selectedDoctor = boxDoctor.getSelectionModel().getSelectedItem();
        appointment.setDoctor(doctorService.findByLastname(selectedDoctor));
        appointment.setDescription(tahealthinformation.getText());
        appointment.setTime(boxtime.getSelectionModel().getSelectedItem());
        appointment.setDate(date.getValue().toString());


        if (appointmentService.appointmentExists(appointment.getDate(), appointment.getTime(), appointment.getDoctor().getLastname(), appointment.getDoctor().getMedicalField())) {

            appointmentService.save(appointment);
            try {
                String filename = appointment.getUser().getUsername() + appointment.getDoctor().getUsername() + appointment.getId() + ".txt";
                FileWriter healthinfo = new FileWriter(filename);

                healthinfo.write("Hello Mr/Mrs. " + FxApplication.currentuser.getLastname() + ",\n" +
                        "In the following you can see your Appointment informations and general informations\n" +
                        "Doctor: " + appointment.getDoctor().getTitle() + ". " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastname() +
                        "\nAddress:\n " + appointment.getDoctor().getAddress() + "\n" +
                        "Appointment Information: " + appointment.getDate() + " at " + appointment.getTime() + "\n" +
                        "Appointment Healthinformation: " + appointment.getDescription() +
                        "General Healthinformation: " + appointment.getUser().getHealthInformation() + " \n"
                );
                healthinfo.close();
                String abpath = "/home/ayhan/Dokumente/main3/";

                String filestringname = appointment.getUser().getUsername() + appointment.getDoctor().getUsername() + appointment.getId() + ".txt";
                System.out.println(abpath + filestringname);

                emailSenderService.sendMailwithAttachment(FxApplication.currentuser.getEmail(), "Appointment Information", "Informations are in the txt file", abpath + filestringname);
                lblappointment.setText("Appointment is saved");


                emailSenderService.sendSimpleMessage(
                        FxApplication.currentuser.getEmail(),
                        "Appointment " + appointment.getDate() + " at " + appointment.getTime(),
                        "Hello " + FxApplication.currentuser.getFirstname() + " you successfully get an appointment.\n" +
                                "Doctor: " + appointment.getDoctor().getTitle() + ". " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastname() +
                                "\nAddress:\n " + appointment.getDoctor().getAddress() + "\n"
                                + appointment.getDoctor().getZip() + " " + appointment.getDoctor().getCity() + "\n\n Please be 15 minutes before your Appointment there." + "\n Best regards your E-Health FFM Team");


            } catch (IOException | MessagingException e) {
                e.printStackTrace();

            }

        } else lblappointment.setText("Appointment at this time is not available");

    }

    @FXML
    void onDashboardUser(ActionEvent event) {
        router.navigate(DashboardUserController.class, event);

    }

    void loadReminder() {

        String tenminutes = "10 minutes";
        String onehour = "1 Hour";
        String threedays = "3 Days";
        String oneweek = "1 Week";


        boxreminder.getItems().clear();
        reminderlist.clear();

        reminderlist.addAll(tenminutes, onehour, threedays, oneweek);
        boxreminder.getItems().addAll(reminderlist);
    }

    void loadTimes() {

        String a = "9:00";
        String b = "9:30";
        String c = "10:00";
        String d = "10:30";
        String e = "11:00";
        String f = "11:30";
        String g = "12:00";
        String h = "14:00";
        String i = "14:30";
        String j = "15:00";
        String k = "15:30";
        String l = "16:00";
        String m = "16:30";
        String n = "17:00";

        boxtime.getItems().clear();
        timelist.clear();
        timelist.addAll(a, b, c, d, e, f, g, h, i, j, k, l, m, n);
        boxtime.getItems().addAll(timelist);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = new Stage();

        setColumnProperties();
        loadReminder();
        loadTimes();


    }
}
