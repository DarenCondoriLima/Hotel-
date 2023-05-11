package Modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class LoginId implements Serializable{
	
	private static final long serialVersionUID = -8806666919464646142L;
	
	private String user;
	private String password;
	
	
	public LoginId() {
	}

	public LoginId(String User, String password) {
		this.user = User;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String User) {
		this.user = User;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginId other = (LoginId) obj;
		return Objects.equals(user, other.user) && Objects.equals(password, other.password);
	}
	
	
}
