package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.Appointment;
import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

	ArrayList<Appointment> findByUser(User user);
	//ArrayList<Appointment> findByDateAndTime(String medicalField, String zip);
	Optional<Appointment> findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(String date, String time, String lastname, String medicalField);
}
