package Dao;

import javax.persistence.EntityManager;

import Modelo.LoginId;
import Modelo.User_Password;

public class User_PasswordDAO {
	
	private EntityManager em;
	
	public User_PasswordDAO(EntityManager em) {
		this.em = em;
	}

	public void guardar(User_Password usuario_Contrasenha) {
		this.em.persist(usuario_Contrasenha);
	}

	public void actualizar(User_Password usuario_Contrasenha) {
		this.em.merge(usuario_Contrasenha);
	}

	public void eliminar(User_Password usuario_Contrasenha) {
		usuario_Contrasenha = this.em.merge(usuario_Contrasenha);
		this.em.remove(usuario_Contrasenha);
	}
	
	public User_Password login(String user, String password) {
	    return this.em.find(User_Password.class, new LoginId(user, password));
	}    
}
