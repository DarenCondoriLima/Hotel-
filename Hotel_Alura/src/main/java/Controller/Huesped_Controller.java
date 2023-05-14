package Controller;
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
}
