package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	
	private static final String DataBase = "hotelalura2";
	private static EntityManagerFactory entityManagerFactory;

	private JPAUtils() {
	    }

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(DataBase);
			System.out.println("Abriendo Conexión");
		}else {System.out.println("Conexión ya abrierta");}
		return entityManagerFactory.createEntityManager();
	}
}
