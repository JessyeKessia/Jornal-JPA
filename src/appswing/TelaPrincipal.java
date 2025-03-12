package appswing;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;


public class TelaPrincipal {
	private JFrame frmNoticesJandanjess;
	private JMenu mnPessoa;
	private JMenu mnTelefone;
	private JMenu mnConsulta;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmNoticesJandanjess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNoticesJandanjess = new JFrame();
		frmNoticesJandanjess.setTitle("Notices JanDanJess");
		frmNoticesJandanjess.setBounds(100, 100, 698, 561);
		frmNoticesJandanjess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNoticesJandanjess.getContentPane().setLayout(null);
		frmNoticesJandanjess.setResizable(false);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 496, 444);
		label.setText("Inicializando...");
		label.setBounds(0, 0, frmNoticesJandanjess.getWidth(), frmNoticesJandanjess.getHeight());
		ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/journal.jpg"));
		imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(imagem);
		frmNoticesJandanjess.getContentPane().add(label);

		JMenuBar menuBar = new JMenuBar();
		frmNoticesJandanjess.setJMenuBar(menuBar);
		mnPessoa = new JMenu("Assuntos");
		mnPessoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaAssuntos();
			}
		});
		menuBar.add(mnPessoa);
		
		mnTelefone = new JMenu("Noticias");
		mnTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaNoticias();
			}
		});
		menuBar.add(mnTelefone);
		
		mnConsulta = new JMenu("Consultar");
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TelaConsultaNoticias();
			}
		});
		menuBar.add(mnConsulta);
	}
}