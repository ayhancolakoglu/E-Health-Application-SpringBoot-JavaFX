package fordsoft.tech.mydesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @author Bayram Ayhan Colakoglu
 * @Lombok : @Data implements all setters and getters for the attributes in the class
 * @Lombok : @NoArgsConstructor implements a No Argmuents  Constructor of the class
 * @Lombok : @AllArgsConstructor implements a All Argmuents Constructor of the Class
 * @Spring : @Entity is a hint for our database that this class is used for our database
 * @Spring : @Table is for the database
 */
@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @ManyToOne
    Doctor doctor;
    @ManyToOne
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String time;
    private String description;
    private String date;

}