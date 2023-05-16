package Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import Dao.ReservaDAO;
import Modelo.Reserva;

public class Reserva_Controller {

	ReservaDAO reservaDAO;
	
	public Reserva_Controller(){
		this.reservaDAO= new ReservaDAO();
	}
	
	public void guardar(Reserva reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public void actualizar(Reserva reserva) {
		this.reservaDAO.actualizar(reserva);
	}
	
	public void eliminar(Reserva reserva) {
		this.reservaDAO.eliminar(reserva);
	}
	
	public Reserva buscarId(Long id) {
		return this.reservaDAO.buscarId(id);
	}
	
	public List<Reserva> getDatos(){
		return this.reservaDAO.getDatos();
	}
	
	//Métodos que no involucran conexión con la Base de Datos.
		public BigDecimal Calcular_Valor(Date date1, Date date2,BigDecimal tipoHabicionValor) {
			try {
				BigDecimal dias= Calcular_Dias(date1, date2);
				BigDecimal valor=dias.multiply(tipoHabicionValor);
				return valor;
			} catch (Exception e) {
				return BigDecimal.ZERO;
			}
		}

		public BigDecimal Calcular_Dias(Date date1, Date date2) {
//			(24 horas * 60 minutos * 60 segundos * 1000 milisegundos), se utiliza obtener los milisegundos de un dia,
//			pues la diferencia entre las fechas da un resultado en milisegundos.
			BigDecimal diasReserva = new BigDecimal((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24))
					.add(new BigDecimal(1));
			System.out.println("Dias= " + diasReserva);
			return diasReserva;
		}

		public BigDecimal tipoHabitacionvalor(String tipoHabicionValor) {
			BigDecimal valorH;
			switch (tipoHabicionValor) {
			case "Normal":
				valorH=new BigDecimal(30);
				break;
			case "Ejecutiva":
				valorH=new BigDecimal(60);
				break;
			case "Suite":
				valorH=new BigDecimal(100);
				break;
			default:
				valorH=null;
				break;
			}
			
			return valorH;
		}

}
