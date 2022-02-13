package fordsoft.tech.mydesk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;

	@Lob
	private String password;

	private String birthday;
	private String address;
	private String city;
	private String zip;
	private String insuranceName;
	private String insuranceType;
	private String pbPath;


	@Lob
	private String healthInformation;

	@Lob
	private String healthProblem;




	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastName=" + lastname + ", email="
				+ email + "]";
	}

	
}

