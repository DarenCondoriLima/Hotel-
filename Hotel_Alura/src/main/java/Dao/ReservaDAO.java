package Dao;

import javax.persistence.EntityManager;

import Modelo.Reserva;
import Utils.JPAUtils;

public class ReservaDAO {
	
	private EntityManager em;

	public ReservaDAO() {
		em = JPAUtils.getEntityManager();
	}

	public void guardar(Reserva reserva) {
        try {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

	public void actualizar(Reserva reserva) {
        try {
            em.getTransaction().begin();
            em.merge(reserva);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

	public void eliminar(Reserva reserva) {
        try {
            em.getTransaction().begin();
            reserva = em.merge(reserva);
            em.remove(reserva);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
	
	public Reserva buscarId(Long id) {
        String jpql =" SELECT R FROM Reserva AS R WHERE R.id=:id ";
        return em.createQuery(jpql,Reserva.class).setParameter("id", id).getSingleResult();
    }
}
