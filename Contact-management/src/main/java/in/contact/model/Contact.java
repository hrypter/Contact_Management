package in.contact.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;	   
	
	@Size(min=3,max=20,message="FirstName should between 3 to 20 characters!!!")
	 @NotEmpty(message="????")  
	private String firstname;	   
	 @Size(min=3,max=10,message="LastName should between 3 to 10 characters!!!")
     @NotEmpty(message="????")  
    private String lastname;
	 @NotEmpty(message="Enter Your Email-Id!!!")
	 @Email(message="Enter a Valid Email-Id!!!")
    private String email;
	 @Size(min=10,message="PhoneNumber should have atleast 10 digits!!!")
		@NotEmpty(message="????")
    private String phone;
	 @Size(min=3,max=10,message="UserName should between 3 to 10 characters!!!")
	    @NotEmpty(message="????")
    private String username;
    private String password;
    public boolean isFavorite; 
	public long getId() {
		return id;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", isFavorite="
				+ isFavorite + ", address=" + address + "]";
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getFavorite() {
		return isFavorite;
	}
	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String address;

}

