package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column
	private Long pk;
	
	@Column
	@NotNull(message="Name must not be null.")
	private String name;
	
	@Column(nullable = false, length = 6, unique = true)
	@Size(min = 6, max = 6, message = "ID number must be exactly 6 digits.")
	private String idNumber;
	
	@Column(nullable = false, length = 15)
	@Pattern(regexp="^(09\\d{9}|\\+639\\d{9})$", message="Phone number must be valid PH format.")
	private String phoneNumber;
	
	public User() {}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User(Long pk, @NotNull(message = "Name must not be null.") String name,
			@Size(min = 6, max = 6, message = "ID number must be exactly 6 digits.") String idNumber,
			@Pattern(regexp = "^(09\\d{9}|\\+639\\d{9})$", message = "Phone number must be valid PH format.") String phoneNumber) {
		super();
		this.pk = pk;
		this.name = name;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
	}

	
	
	
	
	

}
