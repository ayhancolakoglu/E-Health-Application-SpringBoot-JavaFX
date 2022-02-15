package fordsoft.tech.mydesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * @author Sinan Denizli
 * @Lombok : @Data implements all setters and getters for the attributes in the class
 * @Lombok : @NoArgsConstructor implements a No Argmuents  Constructor of the class
 * @Lombok : @AllArgsConstructor implements a All Argmuents Constructor of the Class
 * @Spring : @Entity is a hint for our database that this class is used for our database
 * @Spring : @Table is for the database
 */

@Data
@Table(name = "doctors")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    List<Appointment> appointments;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
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

}