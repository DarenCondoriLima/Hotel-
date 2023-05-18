package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
		contentPane.setBackground(new Color(255, 255, 255));
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

		ListSelectionModel selectionModelHuespedes = tbHuespedes.getSelectionModel();
		selectionModelHuespedes.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && tbHuespedes.getSelectedColumn() == 6) {
					bucarReservas(scroll_table, panel);
				}
			}
		});
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
				labelAtras.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(255, 255, 255));
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
				MensajeError error = new MensajeError("¿Seguro que desea salir? Los cambios no guardados se perderán.");
				error.setVisible(true);
				if (error.isOkPresionado()) {
					System.exit(0);
				} else {
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(new Color(255, 255, 255));
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
					MensajeError error = new MensajeError(
							"Se requiere que ingrese los números de reservas o el apellido del huésped");
					error.setVisible(true);
				} else {
					Buscar(text, scroll_tableHuespedes, scroll_table, panel);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnbuscar.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnbuscar.setBackground(new Color(12, 138, 199));
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

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnEditar.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnEditar.setBackground(new Color(12, 138, 199));
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

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnEliminar.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnEliminar.setBackground(new Color(12, 138, 199));
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
				recargar(scroll_tableHuespedes, scroll_table, panel);
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnRecargar.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnRecargar.setBackground(new Color(12, 138, 199));
			}
		});
		btnRecargar.setLayout(null);
		btnRecargar.setBackground(new Color(12, 138, 199));
		btnRecargar.setBounds(836, 125, 35, 35);
		btnRecargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
				huesped.getApellidoP() + " " + huesped.getApellidoM(), huesped.getFechaDeNacimiento(),
				huesped.getNacionalidad(), huesped.getTelefono(), controllerH.getReservas(huesped) }));
	}

	private void recargar(JScrollPane huespedes, JScrollPane reservas, JTabbedPane panel) {
		if (reservas.isVisible()) {
			limpiarTablaR();
			limpiarTablaH();
			cargarTablaH();
			cargarTablaR();
			panel.setSelectedComponent(reservas); // Selecciona la pestaña "Reservas"
			reservas.setVisible(true); // Hace visible el JScrollPane
		} else if (huespedes.isVisible()) {
			limpiarTablaR();
			limpiarTablaH();
			cargarTablaR();
			cargarTablaH();
			panel.setSelectedComponent(huespedes); // Selecciona la pestaña "Reservas"
			huespedes.setVisible(true); // Hace visible el JScrollPane
		}
	}

	private void Buscar(String apellidos, JScrollPane huespedes, JScrollPane reservas, JTabbedPane panel) {
		Boolean listaNumeros = controllerH.esListaNumeros(apellidos);
		if (listaNumeros == true) {
			String[] ids = apellidos.split(",");
			panel.setSelectedComponent(reservas); // Selecciona la pestaña "Reservas"
			reservas.setVisible(true); // Hace visible el JScrollPane
			limpiarTablaR();
			List<Long> idsNoEncontrados = new ArrayList<>();
			Long idR;
			for (String id : ids) {
				idR = Long.parseLong(id);
				try {
					Reserva datosR = controllerR.buscarId(idR);
					Object[] reserva = new Object[] { datosR.getId(), datosR.getTipoHabitacion(),
							datosR.getFechaEntrada(), datosR.getFechaSalida(), "$" + datosR.getValor(),
							datosR.getFormaPago() };
					modelo.addRow(reserva);
				} catch (NoResultException e) {
					e.printStackTrace();
					idsNoEncontrados.add(idR);
				}
			}
			if (!idsNoEncontrados.isEmpty()) {
				String idsNoEncontradosStr = idsNoEncontrados.stream().map(String::valueOf)
						.collect(Collectors.joining(", "));

				MensajeError error = new MensajeError(
						"No se encontraron resultados para los siguientes números de reserva: " + idsNoEncontradosStr);
				error.setVisible(true);
			}
		} else {
			List<Huesped> datosH = controllerH.bucarHuespedApellidos(apellidos);

			if (datosH.isEmpty()) {
				MensajeError error = new MensajeError("No se encontro resultados");
				error.setVisible(true);
			} else {
				panel.setSelectedComponent(huespedes); // Selecciona la pestaña "Reservas"
				huespedes.setVisible(true); // Hace visible el JScrollPane
				limpiarTablaH();
				datosH.forEach(ApellirdosH -> modeloHuesped.addRow(new Object[] { ApellirdosH.getId(),
						ApellirdosH.getNombre(), ApellirdosH.getApellidoP() + " " + ApellirdosH.getApellidoM(),
						ApellirdosH.getFechaDeNacimiento(), ApellirdosH.getNacionalidad(), ApellirdosH.getTelefono(),
						controllerH.getReservas(ApellirdosH) }));
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
						java.sql.Date fechaEntrada = null;
						java.sql.Date fechaSalida = null;
						Date fechaEntradaD = null;
						Date fechaSalidaD = null;
						BigDecimal Valor = null;
						try {
							fechaEntrada = java.sql.Date
									.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
							fechaSalida = java.sql.Date
									.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
							fechaEntradaD = new Date(fechaEntrada.getTime());
							fechaSalidaD = new Date(fechaSalida.getTime());
							BigDecimal valorH = controllerR.tipoHabitacionvalor(tipoHabitacion);
							Valor = controllerR.Calcular_Valor(fechaEntradaD, fechaSalidaD, valorH);
						} catch (IllegalArgumentException e) {
							MensajeError error = new MensajeError("La fecha proporcionada no es válida.");
							error.setVisible(true);
						}

						String formaPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 5);

						if (comprobarHabitaciom(tipoHabitacion)) {
							if (comprobarfecha(fechaEntradaD, fechaSalidaD) == true) {
								if (DateNow(fechaEntradaD)) {
									if (comprobarFormaPago(formaPago)) {
										Reserva filaModificada = this.controllerR.buscarId(id);
										filaModificada.setTipoHabitacion(tipoHabitacion);
										filaModificada.setFechaEntrada(fechaEntrada);
										filaModificada.setFechaSalida(fechaSalida);
										filaModificada.setValor(Valor);
										filaModificada.setFormaPago(formaPago);
										controllerR.actualizar(filaModificada);

										limpiarTablaR();
										cargarTablaR();
										MensajeExito exito = new MensajeExito(
												String.format("El ítem %d fue modificado con éxito.", id), null);
										exito.setVisible(true);
									} else {
									}
								} else {
								}
							} else {
							}
						} else {
						}
					}, () -> new MensajeError("Por favor, elije un item.").setVisible(true));

		} else if (huespedes.isVisible()) {
			if (filaSeleccionadaH()) {
				MensajeError error = new MensajeError("Por favor, elije un item.");
				error.setVisible(true);
				return;
			}

			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Long id = Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
						String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
						String apellidos = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
						java.sql.Date fechaNacimiento = java.sql.Date
								.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
						Date fechaNacimientoD = new Date(fechaNacimiento.getTime());
						String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
						int telefono = 1;
						try {
							telefono = Integer
									.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
						if (varificarApellidos(apellidos)) {
							String[] partes = apellidos.split(" ");

							String apellidoP = partes[0];
							String apellidoM = partes[1];

							Boolean telefonoV = telefonoValido(telefono);
							if (verificarEdad(fechaNacimientoD)) {
								if (comprobarNacionalidad(nacionalidad)) {
									if (telefonoV == true) {
										Huesped filaModificada = this.controllerH.buscarId(id);
										filaModificada.setNombre(nombre);
										filaModificada.setApellidoP(capitalizarPrimeraLetra(apellidoP));
										filaModificada.setApellidoM(capitalizarPrimeraLetra(apellidoM));
										filaModificada.setFechaDeNacimiento(fechaNacimiento);
										filaModificada.setNacionalidad(capitalizarPrimeraLetra(nacionalidad));
										filaModificada.setTelefono(telefono);
										controllerH.actualizar(filaModificada);

										limpiarTablaH();
										cargarTablaH();
										MensajeExito exito = new MensajeExito(
												String.format("El ítem %d fue modificado con éxito", id), null);
										exito.setVisible(true);
									} else {
									}
								} else {
								}
							} else {
								if (telefono == 1) {
									String telefonoS = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(),
											5);
									new MensajeError(String.format("El teléfono %s es inválido.", telefonoS))
											.setVisible(true);
								} else {
									new MensajeError(String.format("El teléfono %d es inválido.", telefono))
											.setVisible(true);
								}

							}
						} else {
						}

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
						MensajeExito exito = new MensajeExito(String.format("El ítem %d fue eliminado con éxito", id),
								null);
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
						MensajeError error = new MensajeError(
								"¿Está seguro de que desea eliminar este huésped? Todas las reservas asociadas serán eliminadas.");
						error.setVisible(true);
						if (error.isOkPresionado() == true) {
							controllerH.eliminar(filaEliminada);

							modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
							limpiarTablaR();
							cargarTablaR();
							MensajeExito exito = new MensajeExito(
									String.format("El ítem %d fue eliminado con éxito", id), null);
							exito.setVisible(true);
						} else {
						}
					}, () -> new MensajeError("Por favor, elije un item").setVisible(true));
		}
	}

	private void limpiarTablaR() {
		modelo.getDataVector().clear();
	}

	private void limpiarTablaH() {
		modeloHuesped.getDataVector().clear();
	}

	private boolean comprobarfecha(Date fechaEntrada, Date fechaSalida) {
		try {
			BigDecimal dias = controllerR.Calcular_Dias(fechaEntrada, fechaSalida);

			if (dias.compareTo(new BigDecimal(0)) == 1) {
				return true;
			} else {
				MensajeError error = new MensajeError("La segunda fecha no puede ser menor a la primera.");
				error.setVisible(true);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private Boolean DateNow(Date fechaEntrada) {
		try {
			LocalDate localDate = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fechaHoy = LocalDate.now();
			if (localDate.compareTo(fechaHoy) < 0) {
				MensajeError error = new MensajeError("La fecha seleccionada ya no es válida.");
				error.setVisible(true);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private Boolean comprobarHabitaciom(String tipoHabitacion) {
		Boolean valido = false;
		switch (tipoHabitacion) {
		case "Normal":
			valido = true;
			break;
		case "Ejecutiva":
			valido = true;
			break;
		case "Suite":
			valido = true;
			break;
		default:
			valido = false;
			new MensajeError("El tipo de habitación especificado no es válido.").setVisible(true);
			break;
		}
		return valido;
	}

	private boolean comprobarNacionalidad(String nacionalidad) {
		String[] nacionalidadesValidas = { "Afgano(a)", "Alemán(a)", "Árabe", "Argentino(a)", "Australiano(a)", "Belga",
				"Boliviano(a)", "Brasileño(a)", "Camboyano(a)", "Canadiense", "Chileno(a)", "Chino(a)", "Colombiano(a)",
				"Coreano(a)", "Costarricense", "Cubano(a)", "Danés(a)", "Ecuatoriano(a)", "Egipcio(a)",
				"Salvadoreño(a)", "Escocés(a)", "Español(a)", "Estadounidense", "Estonio(a)", "Etiope", "Filipino(a)",
				"Finlandés(a)", "Francés(a)", "Galés(a)", "Griego(a)", "Guatemalteco(a)", "Haitiano(a)", "Holandés(a)",
				"Hondureño(a)", "Indonés(a)", "Inglés(a)", "Iraquí", "Iraní", "Irlandés(a)", "Israelí", "Italiano(a)",
				"Japonés(a)", "Jordano(a)", "Laosiano(a)", "Letón(a)", "Malayo(a)", "Marroquí", "Mexicano(a)",
				"Nicaragüense", "Noruego(a)", "Neozelandés(a)", "Panameño(a)", "Paraguayo(a)", "Peruano(a)",
				"Polaco(a)", "Portugués(a)", "Puertorriqueño(a)", "Dominicano(a)", "Rumano(a)", "Ruso(a)", "Sueco(a)",
				"Suizo(a)", "Tailandés(a)", "Taiwanés(a)", "Turco(a)", "Ucraniano(a)", "Uruguayo(a)", "Venezolano(a)",
				"Vietnamita" };

		for (String nacionalidadValida : nacionalidadesValidas) {
			if (nacionalidadValida.equalsIgnoreCase(nacionalidad)) {
				System.out.println("nacionalidad correcta");
				return true;
			}
		}

		new MensajeError("La nacionalidad proporcionada no es válida.").setVisible(true);
		return false;
	}

	private Boolean comprobarFormaPago(String tipoHabitacion) {
		Boolean valido = false;
		switch (tipoHabitacion) {
		case "Tarjeta de Crédito":
			valido = true;
			break;
		case "Tarjeta de Débito":
			valido = true;
			break;
		case "Dinero en efectivo":
			valido = true;
			break;
		default:
			valido = false;
			new MensajeError("La forma de pago proporcionada no es válida.").setVisible(true);
			break;
		}
		return valido;
	}

	private boolean verificarEdad(Date fechaNacimiento) {
		LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int edad = Period.between(fechaNacimientoLocal, LocalDate.now()).getYears();
		if (edad >= 18 && edad <= 100) {
			System.out.println("El usuario es mayor de edad." + edad);
			return true;
		} else {
			MensajeError error = new MensajeError(
					"Se requiere que los usuarios sean mayores de edad y menores de 100 años para registrarse.");
			error.setVisible(true);
			System.out.println("El usuario es menor de edad. " + edad);
			return false;
		}
	}

	private Boolean telefonoValido(int numero) {
		if (numero < 10 || numero > 999_999_999) {
			return false;
		}
		return true;
	}

	private void bucarReservas(JScrollPane reservas, JTabbedPane panel) {
		try {
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
					.ifPresentOrElse(fila -> {

						String idsT = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6);

						String[] ids = idsT.split(",");
						panel.setSelectedComponent(reservas); // Selecciona la pestaña "Reservas"
						reservas.setVisible(true); // Hace visible el JScrollPane
						limpiarTablaR();
						List<Long> idsNoEncontrados = new ArrayList<>();
						Long idR;
						for (String id : ids) {
							idR = Long.parseLong(id);
							try {
								Reserva datosR = controllerR.buscarId(idR);
								Object[] reserva = new Object[] { datosR.getId(), datosR.getTipoHabitacion(),
										datosR.getFechaEntrada(), datosR.getFechaSalida(), "$" + datosR.getValor(),
										datosR.getFormaPago() };
								modelo.addRow(reserva);
							} catch (NoResultException e) {
								e.printStackTrace();
								idsNoEncontrados.add(idR);
							}
						}
						if (!idsNoEncontrados.isEmpty()) {
							String idsNoEncontradosStr = idsNoEncontrados.stream().map(String::valueOf)
									.collect(Collectors.joining(", "));

							MensajeError error = new MensajeError(
									"No se encontraron resultados para los siguientes números de reserva: "
											+ idsNoEncontradosStr);
							error.setVisible(true);
						}

					}, () -> new MensajeError("Por favor, elije un item").setVisible(true));
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private boolean varificarApellidos(String apellidos) {
		try {
			String[] partes = apellidos.split(" ");

			@SuppressWarnings("unused")
			String apellidoP = partes[0];
			@SuppressWarnings("unused")
			String apellidoM = partes[1];
			return true;
		} catch (Exception e) {
			MensajeError error = new MensajeError("Se requiere sus dos apellidos.");
			error.setVisible(true);
			return false;
		}
	}

	public String capitalizarPrimeraLetra(String texto) {
	    if (texto == null || texto.isEmpty()) {
	        return texto;
	    }
	    
	    String primeraLetra = texto.substring(0, 1).toUpperCase();
	    String restoTexto = texto.substring(1);
	    
	    return primeraLetra + restoTexto;
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
