package Pruebas;

import java.math.BigDecimal;
import java.util.List;

import Controller.Huesped_Controller;
import Controller.Reserva_Controller;
import Modelo.Huesped;
import Modelo.Reserva;

public class Prueba_getDatos {
	public static void main(String[] args) {
		
		Reserva_Controller controllerR= new Reserva_Controller();
		Huesped_Controller controllerH= new Huesped_Controller();
//		List<Reserva> datos = controllerR.getDatos();
//		for (Reserva reserva : datos) {
//			System.out.println(reserva.getId());
//			System.out.println(reserva.getValor());
//		}
//		List<Huesped> datos2 = controllerH.getDatos();
//		datos2.forEach(huesped->System.out.println(huesped.getReservas()));
		Huesped huesped = controllerH.buscarId(1L);
//		Reserva reserva= new Reserva("Ejecutiva","2023/05/05","2023/05/10",new BigDecimal(60),"Dinero en efectivo g",huesped);
//		controllerR.guardar(reserva);
//		huesped.agregarReserva(reserva);
		String reservas = controllerH.getReservas(huesped);
		System.out.println(reservas);
	}
}
