package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Controller.Huesped_Controller;
import Controller.Reserva_Controller;
import Modelo.Huesped;
import Modelo.Reserva;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JLabel labelExit;
	private JLabel labelAtras;
	private Long idActual;
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
					RegistroHuesped frame = new RegistroHuesped(01L);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistroHuesped(Long id) {
		this.idActual = id;

		setIconImage(
				Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
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

		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		contentPane.add(btnexit);
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
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.white);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(SystemColor.black);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasView reservas = new ReservasView();
				reservas.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setForeground(Color.WHITE);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);

		txtFechaN = new JDateChooser();
		txtFechaN.setBounds(560, 278, 285, 36);
		txtFechaN.getCalendarButton()
				.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		txtFechaN.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					verificarEdad();
				} catch (Exception e) {
				}
			}
		});
		contentPane.add(txtFechaN);

		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 350, 289, 36);
		txtNacionalidad.setBackground(SystemColor.text);
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {
				  "Seleccione una nacionalidad",
				  "Afgano(a)", "Alemán(a)", "Árabe", "Argentino(a)", "Australiano(a)", "Belga", "Boliviano(a)",
				  "Brasileño(a)", "Camboyano(a)", "Canadiense", "Chileno(a)", "Chino(a)", "Colombiano(a)", "Coreano(a)",
				  "Costarricense", "Cubano(a)", "Danés(a)", "Ecuatoriano(a)", "Egipcio(a)", "Salvadoreño(a)", "Escocés(a)",
				  "Español(a)", "Estadounidense", "Estonio(a)", "Etiope", "Filipino(a)", "Finlandés(a)", "Francés(a)",
				  "Galés(a)", "Griego(a)", "Guatemalteco(a)", "Haitiano(a)", "Holandés(a)", "Hondureño(a)", "Indonés(a)",
				  "Inglés(a)", "Iraquí", "Iraní", "Irlandés(a)", "Israelí", "Italiano(a)", "Japonés(a)", "Jordano(a)",
				  "Laosiano(a)", "Letón(a)", "Malayo(a)", "Marroquí", "Mexicano(a)", "Nicaragüense", "Noruego(a)",
				  "Neozelandés(a)", "Panameño(a)", "Paraguayo(a)", "Peruano(a)", "Polaco(a)", "Portugués(a)",
				  "Puertorriqueño(a)", "Dominicano(a)", "Rumano(a)", "Ruso(a)", "Sueco(a)", "Suizo(a)", "Tailandés(a)",
				  "Taiwanés(a)", "Turco(a)", "Ucraniano(a)", "Uruguayo(a)", "Venezolano(a)", "Vietnamita"
				}));
		contentPane.add(txtNacionalidad);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(562, 119, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("APELLIDOS");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblApellido);

		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 14);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblFechaN);

		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 326, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidad);

		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 406, 253, 14);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 424, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);

		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setBounds(606, 55, 234, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);

		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 474, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);

		txtNreserva = new JTextField();
		txtNreserva.setEditable(false);
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 495, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNreserva.setText(this.idActual.toString());
		contentPane.add(txtNreserva);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		JSeparator separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(560, 314, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 457, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 529, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 560, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Boolean verificarEdad = verificarEdad();
				int telefono=1;
				try {
				telefono = Integer.parseInt(txtTelefono.getText());
				}catch (NumberFormatException ex) {
				}
				if (txtNombre.getText() != null && txtApellido.getText() != null && txtFechaN.getDate() != null
						&& txtNacionalidad.getSelectedItem() != " " && txtTelefono != null) {
					if (verificarEdad == true) {
						if (telefonoValido(telefono)) {
							if(varificarApellidos()) {
							try {
								guardar();
								MenuUsuario usuario = new MenuUsuario();
								System.out.println("Datos guardados");
								dispose();
								MensajeExito exito = new MensajeExito("Datos guardados satisfactoriamente", usuario);
								exito.setVisible(true);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}else {}
						} else {
							if(telefono==1) {
								String telefonoS = txtTelefono.getText();
								new MensajeError(String.format("El teléfono %s es inválido.", telefonoS)).setVisible(true);
							}else {
								new MensajeError(String.format("El teléfono %d es inválido.", telefono)).setVisible(true);
							}
						}
					} else {
					}
				} else {
					MensajeError error = new MensajeError("Debes llenar todos los campos.");
					error.setVisible(true);
				}
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
	}

	private void guardar() throws ParseException {
		Date fechaNacimiento = txtFechaN.getDate();
		String nacionalidad = (String) txtNacionalidad.getSelectedItem();
		java.sql.Date fechaNacimientosql = new java.sql.Date(fechaNacimiento.getTime());
		int telefono = Integer.parseInt(txtTelefono.getText());
		if (verificarHuesped(txtNombre.getText(), txtApellido.getText())) {

			Huesped huesped = controllerH.buscarHuesped(txtNombre.getText(), txtApellido.getText());
			Reserva id = controllerR.buscarId(idActual);
			huesped.agregarReserva(id);
			id.setHuesped(huesped);
			controllerR.actualizar(id);
		} else {
			try {
				String apellidos = txtApellido.getText();
				String[] partes = apellidos.split(" ");

				String apellidoP = partes[0];
				String apellidoM = partes[1];

				Huesped huesped = new Huesped(txtNombre.getText(), capitalizarPrimeraLetra(apellidoP), capitalizarPrimeraLetra(apellidoM), fechaNacimientosql,
						nacionalidad, telefono);

				Reserva id = controllerR.buscarId(idActual);
				controllerH.guardar(huesped);
				huesped.agregarReserva(id);
				id.setHuesped(huesped);
				controllerR.actualizar(id);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	private boolean varificarApellidos() {
		try {
			String apellidos = txtApellido.getText();
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

	private boolean verificarEdad() {
		try {
			Date fechaNacimiento = txtFechaN.getDate();
			LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			int edad = Period.between(fechaNacimientoLocal, LocalDate.now()).getYears();
			if (edad >= 18 && edad <= 100) {
				System.out.println("El usuario es mayor de edad. " + edad);
				return true;
			} else {
				MensajeError error = new MensajeError(
						"Se requiere que los usuarios sean mayores de edad y menores de 100 años para registrarse.");
				error.setVisible(true);
				System.out.println("El usuario es menor de edad. " + edad);
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	private Boolean telefonoValido(int numero) {
		if (numero < 10 || numero > 999_999_999) {
			return false;
		}
		return true;
	}

	private boolean verificarHuesped(String nombre,String apellidos) {
		try {
			controllerH.buscarHuesped(nombre,apellidos);
			return true;
		} catch (Exception e) {
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
	
	// Código que permite mover la ventana por la pantalla según la posición de "x"
	// y "y"
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
