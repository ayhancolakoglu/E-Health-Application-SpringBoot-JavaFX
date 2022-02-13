package fordsoft.tech.mydesk.service;

import fordsoft.tech.mydesk.repo.AppointmentRepo;
import fordsoft.tech.mydesk.repo.DoctorRepo;
import fordsoft.tech.mydesk.repo.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Data
public class ServiceUtil {
    @Autowired
    UserRepo userRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    AppointmentRepo appointmentRepo;
}
