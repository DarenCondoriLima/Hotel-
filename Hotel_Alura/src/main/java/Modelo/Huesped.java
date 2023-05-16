package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "huespedes")
public class Huesped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String Apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private int telefono;
	
	@OneToMany(mappedBy="huesped", cascade = CascadeType.REMOVE)
	private List<Reserva> reserva =  new ArrayList<>();
	
	public Huesped() {}

	public Huesped(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, int telefono) {
		super();
		this.nombre = nombre;
		this.Apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public void agregarReserva(Reserva reserva) {
		reserva.setHuesped(this);
		this.reserva.add(reserva);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Reserva> getReservas() {
		return reserva;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reserva = reservas;
	}

	public Long getId() {
		return id;
	}
	
}
