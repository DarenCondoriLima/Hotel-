package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotelalura2");
	
	public static EntityManager getEntityManager() {
		System.out.println("Iniciando conexión");
		return FACTORY.createEntityManager();		
	}
}
