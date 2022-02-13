package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private ServiceUtil serviceUtil;

    public Doctor save(Doctor entity) {
        return serviceUtil.getDoctorRepo().save(entity);
    }

    public Doctor update(Doctor entity) {
        return serviceUtil.getDoctorRepo().save(entity);
    }

    public void delete(Doctor entity) {
        serviceUtil.getDoctorRepo().delete(entity);
    }

    public void delete(Long id) {
        serviceUtil.getDoctorRepo().deleteById(id);
    }

    public Optional<Doctor> find(Long id) {
        return serviceUtil.getDoctorRepo().findById(id);
    }

    public List<Doctor> findAll() {
        return serviceUtil.getDoctorRepo().findAll();
    }

    public boolean authenticate(String username, String password){
        Optional<Doctor> doctor = this.findByUsername(username);
        if(doctor.isEmpty()){
            return false;
        }else{
            Doctor d = doctor.get();
            if(password.equals(d.getPassword())) return true;
            else return false;
        }
    }

    public Optional<Doctor> findByUsername(String username) {
        return serviceUtil.getDoctorRepo().findByUsername(username);
    }

    public  Doctor findByLastname(String lastname){
        return serviceUtil.getDoctorRepo().findByLastname(lastname);
    }

    public Doctor  findByUsernameAndPassword(String username, String password){
        return serviceUtil.getDoctorRepo().findByUsernameAndPassword(username, password);
    }

    public ArrayList<Doctor> findByMedicalFieldAndZip(String medicalField, String zip){
        return serviceUtil.getDoctorRepo().findByMedicalFieldAndZip(medicalField, zip);
    }

    public void deleteInBatch(List<Doctor> doctors) {
        serviceUtil.getDoctorRepo().deleteInBatch(doctors);
    }

    public ArrayList<Doctor> findByMedicalField(String medicalfield) {
        return serviceUtil.getDoctorRepo().findByMedicalField(medicalfield);
    }
}
