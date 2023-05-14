package Pruebas;

import javax.persistence.EntityManager;

import Controller.User_Password_Controler;
import Dao.User_PasswordDAO;
import Modelo.User_Password;
import Utils.JPAUtils;

public class Pruebas_login {

	public static void main(String[] args) {
		registrar();
//		EntityManager em=JPAUtils.getEntityManager();
		
//		User_Password find = em.find(User_Password.class, new LoginId("Juan", "password1234"));
		
//		User_PasswordDAO dao=new User_PasswordDAO(em);
		User_Password_Controler controller= new User_Password_Controler();
//		User_Password find=dao.login("Juan", "password1234");
//		System.out.println(find.getUser());
		
		//User_Password find=controller.login("Juan", "password1234");
		//System.out.println(find.getUser());
		
		if(controller.login("Juan", "password1234")) {
			System.out.println("Correcto");
		}
		else {
			System.out.println("Usuario o Contraseña no válidos");
		}
	}
	
	public static void  registrar() {
		User_Password usuario1= new User_Password("Juan", "password1234");

//		EntityManager em=JPAUtils.getEntityManager();
		
		User_PasswordDAO user_PasswordDAO= new User_PasswordDAO();
		
//		em.getTransaction().begin();
		
		user_PasswordDAO.guardar(usuario1);
		
//		em.getTransaction().commit();
//		em.close();
		
	}
	
}
