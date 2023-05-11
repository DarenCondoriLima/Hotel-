package Modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class User_Password {

	@EmbeddedId
	private LoginId loginId;

	public User_Password() {}

	public User_Password(String nombre,String contrasehna) {
		this.loginId = new LoginId(nombre,contrasehna);
	}

	public String getUser() {
		return this.loginId.getUser();
	}

	public void setUser(String User) {
		this.loginId.setUser(User);;
	}

	public String getPassword() {
		return this.loginId.getPassword();
	}

	public void setPassword(String password) {
		this.loginId.setPassword(password);
	}
	
	
	
}
