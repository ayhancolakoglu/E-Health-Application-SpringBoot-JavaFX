package fordsoft.tech.mydesk.repo;

import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Bayram Ayhan Colakoglu
 * @Spring The AppintmentRepo extends from the JpaRepository, the JpaRepository includes many findBy or check methods.
 * @
 */
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    /**
     * @param username
     * @return Optional doctor, find by the username
     */
    Optional<Doctor> findByUsername(String username);

    /**
     * @param username
     * @param password
     * @return doctor, find by username and password
     */
    Doctor findByUsernameAndPassword(String username, String password);

    /**
     * @param lastname
     * @return doctor, find by lastname
     */
    Doctor findByLastname(String lastname);

    /**
     * @param medicalField
     * @param zip
     * @return an arraylist of doctors, find by medical field and zip
     */
    ArrayList<Doctor> findByMedicalFieldAndZip(String medicalField, String zip);

    /**
     * @param medicalField
     * @return an arraylist of doctors , find by medicalfield
     */
    ArrayList<Doctor> findByMedicalField(String medicalField);

}
