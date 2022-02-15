package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Bayram Ayhan Colakoglu
 * @Spring A Service Class takes or put data to a database
 */

@Service
public class DoctorService {
    @Autowired
    private ServiceUtil serviceUtil;

    /**
     * @param entity
     * @return this method saves a doctor in our doctors table
     */
    public Doctor save(Doctor entity) {
        return serviceUtil.getDoctorRepo().save(entity);
    }


    /**
     * @param id
     * @return a doctor object that was find by the id
     */
    public Optional<Doctor> find(Long id) {
        return serviceUtil.getDoctorRepo().findById(id);
    }


    /**
     * @param lastname
     * @return a doctor object that was find by lastname
     */
    public Doctor findByLastname(String lastname) {
        return serviceUtil.getDoctorRepo().findByLastname(lastname);
    }


    /**
     * @param medicalField
     * @param zip
     * @return an arraylist of doctor objects that was find by medical field and zip
     */
    public ArrayList<Doctor> findByMedicalFieldAndZip(String medicalField, String zip) {
        return serviceUtil.getDoctorRepo().findByMedicalFieldAndZip(medicalField, zip);
    }

    /**
     * @param medicalfield
     * @return an arraylist of doctors that was find by medicalfield
     */
    public ArrayList<Doctor> findByMedicalField(String medicalfield) {
        return serviceUtil.getDoctorRepo().findByMedicalField(medicalfield);
    }
}
