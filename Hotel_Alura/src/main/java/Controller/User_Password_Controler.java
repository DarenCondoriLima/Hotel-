package Controller;

import Dao.User_PasswordDAO;
import Modelo.User_Password;

public class User_Password_Controler {
	
	User_PasswordDAO user_PasswordDAO;

	public User_Password_Controler() {
		this.user_PasswordDAO= new User_PasswordDAO();
	}
	
	public void guardar(User_Password user_Password) {
		this.user_PasswordDAO.guardar(user_Password);
	}
	
	public void actualizar(User_Password user_Password) {
		this.user_PasswordDAO.actualizar(user_Password);
	}
	
	public void eliminar(User_Password user_Password) {
		this.user_PasswordDAO.eliminar(user_Password);
	}
	
	public boolean login(String user, String password) {
	    User_Password userPassword = this.user_PasswordDAO.login(user, password);
	    return userPassword != null; // devuelve true si el inicio de sesión fue exitoso
	}
	
}
