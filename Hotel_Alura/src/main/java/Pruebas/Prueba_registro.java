package Pruebas;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import Controller.Huesped_Controller;
import Controller.Reserva_Controller;
import Modelo.Huesped;
import Modelo.Reserva;
import Utils.JPAUtils;

public class Prueba_registro {

	public static void main(String[] args) {
		registrar();
	}
	
	public static void registrar() {
		
//		EntityManager em=JPAUtils.getEntityManager();
		
		Huesped juan = new Huesped("Juan", "Condori", "2003/10/25", "peruano-peruana", 888888);
		Reserva r9 = new Reserva("Ejecutiva","2023/05/05","2023/05/10",new BigDecimal(60),"Dinero en efectivo");
		
		Reserva_Controller controllerR= new Reserva_Controller();
		Huesped_Controller controllerH= new Huesped_Controller();
		
		controllerH.guardar(juan);
		juan.agregarReserva(r9);
		controllerR.guardar(r9);
		
		Reserva id = controllerR.buscarId(r9.getId());
		id.setHuesped(juan);
		id.setFormaPago("Tarjeta de Crédito");
//		em.getTransaction().begin();
//		em.merge(id);
//		em.getTransaction().commit();
//		em.close();ç
		controllerR.actualizar(id);
		
		
	}
	
}
