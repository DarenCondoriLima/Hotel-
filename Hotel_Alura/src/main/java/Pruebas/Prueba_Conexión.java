package Pruebas;

import javax.persistence.EntityManager;

import Utils.JPAUtils;

public class Prueba_Conexión {

	public static void main(String[] args) {
		
		System.out.println("Conexión");
		EntityManager em=JPAUtils.getEntityManager();
		em.close();
		System.out.println("Coxión close");
	}

}
