package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.Appointment;
import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


/**
 * @author Bayram Ayhan Colakoglu
 * @Spring The AppintmentRepo extends from the JpaRepository, the JpaRepository includes many findBy or check methods.
 * @
 */


public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    /**
     * @param user is an object of the class User
     * @return an arraylist that includes appointments from a user
     */
    ArrayList<Appointment> findByUser(User user);


    /**
     * @param date
     * @param time
     * @param lastname
     * @param medicalField
     * @return an Optional Appointment , that means that it could be empty.
     * (find by Date and Time and lastname and medicalfield of the doctor
     */
    Optional<Appointment> findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(String date, String time, String lastname, String medicalField);
}
