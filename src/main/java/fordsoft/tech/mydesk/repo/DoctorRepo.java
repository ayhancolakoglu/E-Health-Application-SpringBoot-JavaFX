package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface DoctorRepo extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByUsername(String username);
	Doctor  findByUsernameAndPassword(String username, String password);
	Doctor findByLastname(String lastname);
	ArrayList<Doctor> findByMedicalFieldAndZip(String medicalField, String zip);
	ArrayList<Doctor> findByMedicalField(String medicalField);

}
