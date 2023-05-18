package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public Huesped buscarHuesped(String nombre,String apellidos) {
		String[] partes = apellidos.split(" ");
		String apellidoP=null;
		String apellidoM=null;
		try {
		apellidoP = partes[0];
		apellidoM = partes[1];
		}catch (Exception e) {
		}
		
		String jpql = " SELECT H FROM Huesped AS H WHERE H.nombre=:nombre AND H.ApellidoP=:apellidoP AND H.ApellidoM=:apellidoM";
		return em.createQuery(jpql, Huesped.class).setParameter("nombre", nombre).setParameter("apellidoP", apellidoP).setParameter("apellidoM",apellidoM).getSingleResult();
		
	}
	
	public List<Huesped> bucarHuespedApellidos(String apellidos) {
		String[] partes = apellidos.split(" ");
		String apellidoP=null;
		String apellidoM=null;
		try {
		apellidoP = partes[0];
		apellidoM = partes[1];
		}catch (Exception e) {
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Huesped> query = builder.createQuery(Huesped.class);
		Root<Huesped> from=query.from(Huesped.class); 
		
		Predicate filtro = builder.and();
		if(apellidoP!=null && !apellidoP.trim().isEmpty()) {
			filtro=builder.and(filtro,builder.equal(from.get("ApellidoP"),apellidoP));
		}
		if(apellidoM!=null && !apellidoM.trim().isEmpty()) {
			filtro=builder.and(filtro,builder.equal(from.get("ApellidoM"),apellidoM));
		}
		
		query=query.where(filtro);
		
		List<Huesped> resultList = em.createQuery(query).getResultList();
		System.out.println(resultList);
		return 	resultList;
	}
	
}
