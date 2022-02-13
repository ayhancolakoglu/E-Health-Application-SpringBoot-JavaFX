package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.model.Appointment;
import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

@Service
public class AppointmentService {
    @Autowired
    private ServiceUtil serviceUtil;

    public Appointment save(Appointment entity) {
        return serviceUtil.getAppointmentRepo().save(entity);
    }

    public ArrayList<Appointment> findByUser(User user) {
        return serviceUtil.getAppointmentRepo().findByUser(user);
    }

    public Optional<Appointment> findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(String date, String time, String lastname, String medicalField){
        return serviceUtil.getAppointmentRepo().findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField( date,time, lastname, medicalField);
    }

    public boolean appointmentExists(String date, String time, String lastname, String medicalField){
        Optional<Appointment> appointment = this.findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(date,time, lastname, medicalField);
       return appointment.isEmpty();
    }

    public void deleteInBatch(List<Appointment> appointments) {
        serviceUtil.getAppointmentRepo().deleteInBatch(appointments);
    }



    /*
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


    public Doctor  findByUsernameAndPassword(String username, String password){
        return serviceUtil.getDoctorRepo().findByUsernameAndPassword(username, password);
    }

    public ArrayList<Doctor> findByMedicalFieldAndZip(String medicalField, String zip){
        return serviceUtil.getDoctorRepo().findByMedicalFieldAndZip(medicalField, zip);
    }

    public void deleteInBatch(List<Doctor> doctors) {
        serviceUtil.getDoctorRepo().deleteInBatch(doctors);
    }

     */
}

