package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import Modelo.Huesped;
import Modelo.Reserva;
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
	
	public Huesped buscarId(Long id) {
		String jpql = " SELECT H FROM Huesped AS H WHERE H.id=:id ";
		return em.createQuery(jpql, Huesped.class).setParameter("id", id).getSingleResult();
	}

	public List<Huesped> getDatos() {
		List<Huesped> huespedes = null;
		try {
			huespedes = em.createQuery("FROM Huesped", Huesped.class).getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		return huespedes;
	}

	public String getReservas(Huesped huesped) {
		List<Long> reservasId = new ArrayList<>();
		List<Reserva> reservas = huesped.getReservas();
		for (Reserva Id : reservas) {
			reservasId.add(Id.getId());
		}
		CharSequence[] reservasIdArray = reservasId.stream().map(Object::toString).toArray(CharSequence[]::new);
		return String.join(",", reservasIdArray);
	}

	public List<Huesped> buscarApellido(String parametro) {
		List<Huesped> huesped = null;
			try {
				String jqpl = "SELECT h FROM Huesped As h WHERE h.Apellido=:Parametro";;
				huesped = em.createQuery(jqpl, Huesped.class).setParameter("Parametro", parametro).getResultList();

			} catch (Exception e) {
			}
		return huesped;
	}
}
