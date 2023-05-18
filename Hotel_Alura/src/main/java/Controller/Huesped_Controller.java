package Controller;
import java.util.ArrayList;
import java.util.List;

import Dao.HuespedDAO;
import Modelo.Huesped;
import Modelo.Reserva;

public class Huesped_Controller {
	
	HuespedDAO huespedDAO;

	public Huesped_Controller(){
		this.huespedDAO= new HuespedDAO();
	}

	public void guardar(Huesped huesped) {
		this.huespedDAO.guardar(huesped);
	}
	
	public void actualizar(Huesped huesped) {
		this.huespedDAO.actualizar(huesped);
	}
	
	public void eliminar(Huesped huesped) {
		this.huespedDAO.eliminar(huesped);
	}
	
	public Huesped buscarId(Long id) {
		return this.huespedDAO.buscarId(id);
	}
	
	public List<Huesped> getDatos() {
		return this.huespedDAO.getDatos();
	}

	public List<Huesped> bucarHuespedApellidos(String apellidos) {
		return this.huespedDAO.bucarHuespedApellidos(apellidos);
	}
	
	public Huesped buscarHuesped(String nombre,String apellidos) {
		return this.huespedDAO.buscarHuesped(nombre,apellidos);
	}
	
	//Métodos que no involucran conexión con la Base de Datos.
	
	public String getReservas(Huesped huesped) {
		List<Long> reservasId = new ArrayList<>();
		List<Reserva> reservas = huesped.getReservas();
		for (Reserva Id : reservas) {
			reservasId.add(Id.getId());
		}
		CharSequence[] reservasIdArray = reservasId.stream().map(Object::toString).toArray(CharSequence[]::new);
		return String.join(",", reservasIdArray);
	}
	
	public Boolean esNumero(String text) {
		try {
			Long.parseLong(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public Boolean esListaNumeros(String texto) {
		if (texto.matches("\\d+(,\\d+)*")) {
			return true;
		} else {
			return false;
		}
	}

}
