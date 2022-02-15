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


/**
 * @author Bayram Ayhan Colakoglu
 * @Spring A Service Class takes or put data to a database
 */


@Service
public class AppointmentService {


    @Autowired
    private ServiceUtil serviceUtil;

    /**
     * @param entity
     * @return this method saves an appointment in our appointments table
     */
    public Appointment save(Appointment entity) {
        return serviceUtil.getAppointmentRepo().save(entity);
    }


    /**
     * @param user is an object of the class User
     * @return an arraylist that includes appointments from a user
     */
    public ArrayList<Appointment> findByUser(User user) {
        return serviceUtil.getAppointmentRepo().findByUser(user);
    }

    /**
     * @param date
     * @param time
     * @param lastname
     * @param medicalField
     * @return an appintment, find by date, time and lastname and medicalfield from doctor
     */
    public Optional<Appointment> findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(String date, String time, String lastname, String medicalField) {
        return serviceUtil.getAppointmentRepo().findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(date, time, lastname, medicalField);
    }

    /**
     * @param date
     * @param time
     * @param lastname
     * @param medicalField
     * @return a boolean, that checks if a apppointment exists or not
     */
    public boolean appointmentExists(String date, String time, String lastname, String medicalField) {
        Optional<Appointment> appointment = this.findByDateAndTimeAndDoctor_lastnameAndDoctor_medicalField(date, time, lastname, medicalField);
        return appointment.isEmpty();
    }


    /**
     * @param appointments deletes a list of apppintments
     */

    public void deleteInBatch(List<Appointment> appointments) {
        serviceUtil.getAppointmentRepo().deleteInBatch(appointments);
    }

}