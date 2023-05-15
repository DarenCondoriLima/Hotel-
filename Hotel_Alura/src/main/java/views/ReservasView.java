package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import Controller.Reserva_Controller;
import Modelo.Reserva;

@SuppressWarnings("serial")
public class ReservasView extends JFrame {

	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaEntrada;
	public static JDateChooser txtFechaSalida;
	public static JComboBox<String> txtFormaPago;
	public static JComboBox<String> txtTipoHabitacion;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel labelAtras;
	private Reserva_Controller controllerR = new Reserva_Controller();
	private BigDecimal valor;
	private Long idActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
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
	public ReservasView() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 466, 289, 2);
		panel.add(separator_1_3);

		JSeparator separator_1_4 = new JSeparator();
		separator_1_4.setForeground(SystemColor.textHighlight);
		separator_1_4.setBackground(SystemColor.textHighlight);
		separator_1_4.setBounds(68, 188, 289, 2);
		panel.add(separator_1_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 381, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		// Código que crea los elementos de la interfáz gráfica

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 259, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 331, 289, 11);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 200, 198, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 271, 219, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckOut);

		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 394, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 60, 219, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(12, 138, 199));
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));

		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));

		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(68, 341, 217, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblValor);

		// Componentes para dejar la interfaz con estilo Material Design
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
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
		btnexit.setBackground(new Color(12, 138, 199));
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

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
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSiguiente.setBounds(0, 0, 122, 35);

		// Campos que guardaremos en la base de datos
		txtFechaEntrada = new JDateChooser();
		txtFechaEntrada.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaEntrada.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaEntrada.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaEntrada.setBounds(68, 225, 289, 35);
		txtFechaEntrada.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaEntrada.setBackground(Color.WHITE);
		txtFechaEntrada.setBorder(new LineBorder(SystemColor.window));
		txtFechaEntrada.setDateFormatString("yyyy-MM-dd");
		txtFechaEntrada.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaEntrada.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					DateNow();
					BigDecimal valor = getValor();
			        txtValor.setText("$" + valor.toString());
				} catch (Exception e) {
				}
			}
		});
		panel.add(txtFechaEntrada);

		txtFechaSalida = new JDateChooser();
		txtFechaSalida.getCalendarButton()
				.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaSalida.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaSalida.setBounds(68, 296, 289, 35);
		txtFechaSalida.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaSalida.setBackground(Color.WHITE);
		txtFechaSalida.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaSalida.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					comprobarfecha();
					BigDecimal valor = getValor();
			        txtValor.setText("$" + valor.toString());
				} catch (Exception e) {
				}
			}
		});
		txtFechaSalida.setDateFormatString("yyyy-MM-dd");
		txtFechaSalida.getCalendarButton().setBackground(SystemColor.textHighlight);
		txtFechaSalida.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaSalida);

		txtValor = new JTextField();
		txtValor.setOpaque(false);
		txtValor.setBackground(new Color(0, 0, 0, 0));
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(68, 350, 289, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtValor.setEditable(false);
		panel.add(txtValor);
		txtValor.setColumns(10);

		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 429, 289, 38);
		txtFormaPago.setBackground(SystemColor.text);
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JPanel btnsiguiente = new JPanel();
		btnsiguiente.setToolTipText("");
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Boolean fecha=comprobarfecha();
				Boolean fechaHoy=DateNow();
				if (ReservasView.txtFechaEntrada.getDate() != null && ReservasView.txtFechaSalida.getDate() != null && ReservasView.txtTipoHabitacion.getSelectedItem() != " ") {
					if(fecha == true) {
						if(fechaHoy==true) {
							try {
								guardar();
								RegistroHuesped registro = new RegistroHuesped(idActual);
								System.out.println("fin-reserva");
								dispose();
								MensajeExito exito= new MensajeExito("Datos guardados satisfactoriamente",registro);
								exito.setVisible(true);
							} catch (Exception ex) {
								
							}
						}else {}	
					}else {}
				} else {
					MensajeError error = new MensajeError("Debes llenar todos los campos.");
					error.setVisible(true);
				}
			}
		});
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(SystemColor.textHighlight);
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel imgSiguiente = new JLabel("Continuar");
		imgSiguiente.setFont(new Font("Roboto", Font.PLAIN, 18));
		imgSiguiente.setForeground(new Color(255, 255, 255));
		imgSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(imgSiguiente);
		imgSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		imgSiguiente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/calendario.png")));

		txtTipoHabitacion = new JComboBox();
		txtTipoHabitacion.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTipoHabitacion.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtTipoHabitacion.setBackground(Color.WHITE);
		txtTipoHabitacion.setBounds(68, 151, 289, 38);
		txtTipoHabitacion.setModel(new DefaultComboBoxModel(new String[] { " ", "Normal: $30 USD por día",
				"Ejecutiva: $60 USD por día", "Suite: $100 USD por día" }));
		txtTipoHabitacion.addActionListener((ActionListener) new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	BigDecimal valor = getValor();
		        txtValor.setText("$" + valor.toString());
		    }
		});
		panel.add(txtTipoHabitacion);

		JLabel lblTipoDeHabitacin = new JLabel("TIPO DE HABITACIÓN");
		lblTipoDeHabitacin.setForeground(SystemColor.textInactiveText);
		lblTipoDeHabitacin.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblTipoDeHabitacin.setBounds(68, 126, 198, 14);
		panel.add(lblTipoDeHabitacin);

	}
	
	private boolean comprobarfecha() {
		Date fechaEntrada = txtFechaEntrada.getDate();
		Date fechaSalida = txtFechaSalida.getDate();
		BigDecimal dias = controllerR.Calcular_Dias(fechaEntrada, fechaSalida);
		
		if(dias.compareTo(new BigDecimal(0))==1) {
			return true;
		}else {
			MensajeError error = new MensajeError("La segunda fecha no puede ser menor a la primera.");
			error.setVisible(true);
			return false;
		}
		
	}
	
	private Boolean DateNow() {
		Date fechaEntrada = txtFechaEntrada.getDate();
		LocalDate localDate = fechaEntrada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechaHoy = LocalDate.now();
		if(localDate.compareTo(fechaHoy)<0) {
			MensajeError error=new MensajeError("La fecha seleccionada ya no es válida.");
			error.setVisible(true);
			return false;
		}else {
			return true;
		}
		
	}
	
	private String tipoHabicion() {
		String habitacion;
		try {
		habitacion = (String) txtTipoHabitacion.getSelectedItem();
		
		switch (habitacion) {
		case "Normal: $30 USD por día":
			habitacion="Normal";
			break;
		case "Ejecutiva: $60 USD por día":
			habitacion="Ejecutiva";
			break;
		case "Suite: $100 USD por día":
			habitacion="Suite";
			break;
		default:
			habitacion="no seleccionada";
			break;
		}
		
		return habitacion;
		}catch (Exception e) {
		return 	habitacion=" ";
		}
		
	}

	private BigDecimal getValor() {
		Date fechaEntrada = txtFechaEntrada.getDate();
		Date fechaSalida = txtFechaSalida.getDate();
		String tipoHabicion = tipoHabicion();
		BigDecimal habitacionValor = controllerR.tipoHabitacionvalor(tipoHabicion);
		this.valor = controllerR.Calcular_Valor(fechaEntrada, fechaSalida,habitacionValor);
		return valor;
	}
	
	private void guardar() {
		Date fechaEntrada = txtFechaEntrada.getDate();
		Date fechaSalida = txtFechaSalida.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaEntradaStr = sdf.format(fechaEntrada);
		String fechaSalidaStr = sdf.format(fechaSalida);
		String formaPago = (String) txtFormaPago.getSelectedItem();
		String habitacion = tipoHabicion();
		BigDecimal valor = getValor();

		Reserva reserva = new Reserva(habitacion, fechaEntradaStr, fechaSalidaStr, valor, formaPago);

		controllerR.guardar(reserva);

		this.idActual = reserva.getId();
		controllerR.guardar(reserva);
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
