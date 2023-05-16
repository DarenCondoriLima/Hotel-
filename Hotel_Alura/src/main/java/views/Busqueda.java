package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Huesped_Controller;
import Controller.Reserva_Controller;
import Modelo.Huesped;
import Modelo.Reserva;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private Reserva_Controller controllerR = new Reserva_Controller();
	private Huesped_Controller controllerH = new Huesped_Controller();
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setOpaque(false);
		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar.setBounds(539, 134, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Tipo de Habitación");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Números de Reservas");
		cargarTablaR();
		cargarTablaH();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = txtBuscar.getText();
				if (text.isEmpty()) {
					MensajeError error = new MensajeError("Se requiere que ingrese el número de reserva o el apellido del huésped");
					error.setVisible(true);
				} else {
					Buscar(text, scroll_tableHuespedes, scroll_table);
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(746, 125, 87, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 87, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar(scroll_tableHuespedes, scroll_table);
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar(scroll_tableHuespedes, scroll_table);
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);

		JPanel btnRecargar = new JPanel();
		btnRecargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTablaR();
				limpiarTablaH();
				cargarTablaR();
				cargarTablaH();
			}
		});
		btnRecargar.setLayout(null);
		btnRecargar.setBackground(new Color(12, 138, 199));
		btnRecargar.setBounds(836, 125, 35, 35);
		contentPane.add(btnRecargar);

		JLabel lblRecargar = new JLabel("");
		lblRecargar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/reload.png")));
		lblRecargar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecargar.setForeground(Color.WHITE);
		lblRecargar.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblRecargar.setBounds(0, 0, 35, 35);
		btnRecargar.add(lblRecargar);
		setResizable(false);
	}

	private void cargarTablaR() {
		List<Reserva> datosR = this.controllerR.getDatos();

		datosR.forEach(reserva -> modelo
				.addRow(new Object[] { reserva.getId(), reserva.getTipoHabitacion(), reserva.getFechaEntrada(),
						reserva.getFechaSalida(), "$" + reserva.getValor(), reserva.getFormaPago() }));
	}
	
	private void cargarTablaH() {
		List<Huesped> datosH = this.controllerH.getDatos();

		datosH.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(),
				huesped.getApellido(), huesped.getFechaDeNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(),
				controllerH.getReservas(huesped) }));
	}

	private void Buscar(String parametros, JScrollPane huespedes, JScrollPane reservas) {

		Boolean esNumero = controllerH.esNumero(parametros);
		if (esNumero == true) {
			try {
				reservas.setVisible(true);
				Reserva datosR = controllerR.buscarId(Long.parseLong(parametros));
				Object[] reserva = new Object[] { datosR.getId(), datosR.getTipoHabitacion(), datosR.getFechaEntrada(),
						datosR.getFechaSalida(), "$" + datosR.getValor(), datosR.getFormaPago() };
				limpiarTablaR();
				modelo.addRow(reserva);
			} catch (NoResultException e) {
				MensajeError error = new MensajeError("No se encontro resultados");
				error.setVisible(true);
				limpiarTablaR();
				limpiarTablaH();
				cargarTablaR();
				cargarTablaH();
			}

		} else {

			List<Huesped> datosH = controllerH.buscarApellido(parametros);

			if (datosH.isEmpty()) {
				MensajeError error = new MensajeError("No se encontro resultados");
				error.setVisible(true);
				limpiarTablaR();
				limpiarTablaH();
				cargarTablaR();
				cargarTablaH();
			} else {
				huespedes.setVisible(true);
				limpiarTablaH();
				datosH.forEach(ApellirdosH -> modeloHuesped
						.addRow(new Object[] { ApellirdosH.getId(), ApellirdosH.getNombre(), ApellirdosH.getApellido(),
								ApellirdosH.getFechaDeNacimiento(), ApellirdosH.getNacionalidad(),
								ApellirdosH.getTelefono(), controllerH.getReservas(ApellirdosH) }));
			}
		}

	}

	private Boolean filaSeleccionadaR() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}

	private Boolean filaSeleccionadaH() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}

	private void editar(JScrollPane huespedes, JScrollPane reservas) {
		if (reservas.isVisible()) {
			if (filaSeleccionadaR()) {
				MensajeError error = new MensajeError("Por favor, elije un item");
				error.setVisible(true);
				return;
			}

			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Long id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						String tipoHabitacion = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
						String fechaEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
						String fechaSalida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
//						BigDecimal valor = new BigDecimal(modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString());
						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 5);
						Reserva filaModificada = this.controllerR.buscarId(id);
						filaModificada.setTipoHabitacion(tipoHabitacion);
						filaModificada.setFechaEntrada(fechaEntrada);
						filaModificada.setFechaSalida(fechaSalida);
//						filaModificada.setValor(valor);
						filaModificada.setFormaPago(formaPago);
						controllerR.actualizar(filaModificada);
						
						limpiarTablaR();
						cargarTablaR();
						MensajeExito exito = new MensajeExito(
								String.format("El ítem %d fue modificado con éxito", id), null);
						exito.setVisible(true);
					}, () -> new MensajeError("Por favor, elije un item").setVisible(true));

		} else if (huespedes.isVisible()) {
			if (filaSeleccionadaH()) {
				MensajeError error = new MensajeError("Por favor, elije un item");
				error.setVisible(true);
				return;
			}
			
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Long id = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
				String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
				String fechaDeNacimiento = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3);
				String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
				int telefono = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
				Huesped filaModificada = this.controllerH.buscarId(id);
				filaModificada.setNombre(nombre);
				filaModificada.setApellido(apellido);
				filaModificada.setFechaDeNacimiento(fechaDeNacimiento);
				filaModificada.setNacionalidad(nacionalidad);
				filaModificada.setTelefono(telefono);
				controllerH.actualizar(filaModificada);
				
				limpiarTablaH();
				cargarTablaH();
				MensajeExito exito = new MensajeExito(
						String.format("El ítem %d fue modificado con éxito", id), null);
				exito.setVisible(true);
			}, () -> new MensajeError("Por favor, elije un item").setVisible(true));
		}
	}
	
	private void eliminar(JScrollPane huespedes, JScrollPane reservas) {
		if (reservas.isVisible()) {
			if (filaSeleccionadaR()) {
				MensajeError error = new MensajeError("Por favor, elije un item");
				error.setVisible(true);
				return;
			}

			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Long id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Reserva filaEliminada = this.controllerR.buscarId(id);
						controllerR.eliminar(filaEliminada);
						
						modelo.removeRow(tbReservas.getSelectedRow());
						MensajeExito exito = new MensajeExito(
								String.format("El ítem %d fue eliminado con éxito", id), null);
						exito.setVisible(true);
					}, () -> new MensajeError("Por favor, elije un item").setVisible(true));

		} else if (huespedes.isVisible()) {
			if (filaSeleccionadaH()) {
				MensajeError error = new MensajeError("Por favor, elije un item");
				error.setVisible(true);
				return;
			}
			
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Long id = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				Huesped filaEliminada = this.controllerH.buscarId(id);
				MensajeError error = new MensajeError("¿Está seguro de que desea eliminar este huésped? Todas las reservas asociadas serán eliminadas.");
				error.setVisible(true);
				if(error.isOkPresionado()==true) {
					controllerH.eliminar(filaEliminada);
					
					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
					limpiarTablaR();
					cargarTablaR();
					MensajeExito exito = new MensajeExito(
							String.format("El ítem %d fue eliminado con éxito", id), null);
					exito.setVisible(true);
				}else {}
			}, () -> new MensajeError("Por favor, elije un item").setVisible(true));
		}
	}
	
	private void limpiarTablaR() {
		modelo.getDataVector().clear();
	}

	private void limpiarTablaH() {
		modeloHuesped.getDataVector().clear();
	}

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
