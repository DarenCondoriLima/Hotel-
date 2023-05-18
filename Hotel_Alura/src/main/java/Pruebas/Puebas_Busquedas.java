package Pruebas;

import java.util.List;

import javax.persistence.EntityManager;

import Controller.Huesped_Controller;
import Modelo.Huesped;
import Utils.JPAUtils;

public class Puebas_Busquedas {

	public static void main(String[] args) {

		EntityManager em = JPAUtils.getEntityManager();
		Huesped_Controller controllerH = new Huesped_Controller();

		String texto = "Condori";
		String numero = "38";

		List<Huesped> apellido = controllerH.bucarHuespedApellidos("Perez");

//		String jqpl = "SELECT h FROM Huesped As h WHERE h.Apellido=:Parametro";
//		List<Huesped> huesped = em.createQuery(jqpl, Huesped.class).setParameter("Parametro", texto).getResultList();

		if (apellido.isEmpty()) {
			System.out.println("No hay datos");
		} else {
			apellido.forEach(encontro -> System.out.println(encontro));
		}
//		huesped.forEach(encontro -> System.out.println(encontro));

//		comprobarNumero1(texto);
//		comprobarNumero1(numero);
	}

	private static void comprobarNumero1(String parametro) {
		Boolean esNumero;
		try {
			Long.parseLong(parametro);
			esNumero = true;
		} catch (NumberFormatException e) {
			esNumero = false;
		}

		if (esNumero == true) {
			System.out.println(parametro + " es numero");
		} else {
			System.out.println(parametro + " es texto");
		}
	}
}