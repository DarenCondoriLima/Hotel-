package Controller;

import javax.persistence.EntityManager;

import Dao.User_PasswordDAO;
import Modelo.User_Password;
import Utils.JPAUtils;

public class User_Password_Controler {
	
	User_PasswordDAO user_PasswordDAO;

	public User_Password_Controler() {
		EntityManager em=JPAUtils.getEntityManager();
		this.user_PasswordDAO= new User_PasswordDAO(em);
	}

//	public User_Password login(String user, String password) {
//	    return this.user_PasswordDAO.login(user, password);
//	}
	
	public boolean login(String user, String password) {
	    User_Password userPassword = this.user_PasswordDAO.login(user, password);
	    return userPassword != null; // devuelve true si el inicio de sesión fue exitoso
	}
	
	public boolean login1(String user, String password) {
		try {
			this.user_PasswordDAO.login(user, password);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
