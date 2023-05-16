package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MensajeExito extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame view=null;
			MensajeExito dialog = new MensajeExito("Datos guardados satisfactoriamente",view);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MensajeExito(String mensaje,JFrame ventana) {
		int size = sizeFrase(mensaje);
		int sizeMin = sizeMin(size);
		size=sizeMin;
		System.out.println(size);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MensajeError.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, size+70, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(MensajeError.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds((int) (27+(size*0.375)), 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel txtMensaje = new JLabel(mensaje);
			txtMensaje.setHorizontalAlignment(SwingConstants.CENTER);
			txtMensaje.setForeground(new Color(12, 138, 199));
			txtMensaje.setFont(new Font("Arial", Font.BOLD, 18));
			txtMensaje.setBounds(27, 98,size, 56);
			contentPanel.add(txtMensaje);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds((int) (27+(size*0.43)), 147, 56, 30);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();// sirve para cerrar la ventana actual
					try {
					ventana.setVisible(true);}
					catch (Exception ex) {
					}
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	private int sizeFrase(String frase) {
		Font font = new Font("Arial", Font.BOLD, 18);
		FontMetrics metrics = new JLabel().getFontMetrics(font);
		Rectangle2D bounds = metrics.getStringBounds(frase, null);
		int width = (int) bounds.getWidth();
		System.out.println(width);
		return width;
	}
	private int sizeMin(int size) {
		if(size<400) {
			setMinimumSize(new Dimension(400, 200));
			return size=400;
		}
		else{
			return size;
		}
	}

}