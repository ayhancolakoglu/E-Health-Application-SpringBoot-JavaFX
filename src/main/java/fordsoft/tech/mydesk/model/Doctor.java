package fordsoft.tech.mydesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "doctors")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private  Long id;
    private String username;
    private String password;
    private String title;
    private String firstname;
    private String lastname;
    private String medicalField;
    private String address;
    private String city;
    private String zip;
    private String email;
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    List<Appointment> appointments;

}