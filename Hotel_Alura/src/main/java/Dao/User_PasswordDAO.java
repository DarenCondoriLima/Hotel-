package Dao;

import javax.persistence.EntityManager;

import Modelo.LoginId;
import Modelo.User_Password;
import Utils.JPAUtils;

public class User_PasswordDAO {
	
	private EntityManager em;
	
	public User_PasswordDAO() {
		em = JPAUtils.getEntityManager();
	}

	public void guardar(User_Password user_Password) {
		try {
			em.getTransaction().begin();
			em.persist(user_Password);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void actualizar(User_Password user_Password) {
		try {
			em.getTransaction().begin();
			em.merge(user_Password);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void eliminar(User_Password user_Password) {
		try {
			em.getTransaction().begin();
			user_Password = em.merge(user_Password);
			em.remove(user_Password);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
	
	public User_Password login(String user, String password) {
	    return this.em.find(User_Password.class, new LoginId(user, password));
	}    
}
