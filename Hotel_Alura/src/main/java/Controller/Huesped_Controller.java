package Controller;
import java.util.List;

import Dao.HuespedDAO;
import Modelo.Huesped;

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
	
	public String getReservas(Huesped huesped) {
		return this.huespedDAO.getReservas(huesped);
	}

	public List<Huesped> buscarApellido(String parametros) {
		return this.huespedDAO.buscarApellido(parametros);
	}
	
	//Métodos que no involucran conexión con la Base de Datos.
	
	public Boolean esNumero(String text) {
		try {
			Long.parseLong(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
