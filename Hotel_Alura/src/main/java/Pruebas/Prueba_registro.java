package Pruebas;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import Controller.Huesped_Controller;
import Controller.Reserva_Controller;
import Modelo.Huesped;
import Modelo.Reserva;

public class Prueba_registro {

	public static void main(String[] args) {
		registrar();
	}
	
	public static void registrar() {
		
//		EntityManager em=JPAUtils.getEntityManager();
		Date fNacimiento= new Date(2003/10/25);
		Date fEntrada = new Date(2023/05/05);
		Date fSalida = new Date(2023/05/10);
		
		System.out.println(fNacimiento);
		System.out.println(fEntrada);
		System.out.println(fSalida);
		
//		Huesped juan = new Huesped("Juan", "Condori", fNacimiento, "peruano-peruana", 888888);
		Reserva r9 = new Reserva("Ejecutiva",fEntrada,fSalida,new BigDecimal(60),"Dinero en efectivo");
		
		Reserva_Controller controllerR= new Reserva_Controller();
		Huesped_Controller controllerH= new Huesped_Controller();
		Huesped juan = controllerH.buscarId(9L);
//		controllerH.guardar(juan);
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
	
	private static String fechaFormatted(Date fechaEntrada) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaStr = sdf.format(fechaEntrada);
		System.out.println(fechaStr);
		return fechaStr;
	}
	
}

