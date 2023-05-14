package Dao;

import javax.persistence.EntityManager;

import Modelo.Huesped;
import Utils.JPAUtils;

public class HuespedDAO {

	private EntityManager em;

	public HuespedDAO() {
		em = JPAUtils.getEntityManager();
	}

	public void guardar(Huesped huesped) {
		try {
			em.getTransaction().begin();
			em.persist(huesped);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void actualizar(Huesped huesped) {
		try {
			em.getTransaction().begin();
			em.merge(huesped);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void eliminar(Huesped huesped) {
		try {
			em.getTransaction().begin();
			huesped = em.merge(huesped);
			em.remove(huesped);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
}
