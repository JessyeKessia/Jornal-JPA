/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Assunto;
import modelo.Noticia;
import regras_negocio.Fachada;

public class TelaAssuntos {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_1;
	private JLabel label;
	private JLabel lblNovoNome;
	private JTextField textField_1;
	private JButton button_2;
	private JLabel lblSelecioneUmAsssunto;
	private JButton button_3;
	private JTextField textField_2;
	private JLabel lblNoticias;
	private JTextField textField_3;

	
	/**
	 * Create the application.
	 */
	public TelaAssuntos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);		//janela modal
		
		frame.setTitle("Cadastrar Assuntos");
		frame.setBounds(100, 100, 396, 395);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		frame.setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 360, 152);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			//proibir alteracao de celulas
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		//evento de selecao de uma linha da tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						//pegar o nome selecionado
						String nome = (String) table.getValueAt( table.getSelectedRow(), 1);
						textField_2.setText(nome);
						label.setText("");
						String noticias = (String) table.getValueAt( table.getSelectedRow(), 2);
						textField_3.setText(noticias);
						label.setText("");
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.YELLOW);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button_1 = new JButton("Apagar");
		button_1.setToolTipText("remover o Assunto");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_2.getText().isEmpty()) {
						label.setText("sem nome");
						return;
					}
					String nome = textField_2.getText();
					//confirma��o
					Object[] options = { "Confirmar", "Cancelar" };
					int escolha = JOptionPane.showOptionDialog(null, "Confirma exclus�o do assunto "+nome, "Alerta",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					if(escolha == 0) {
						Fachada.apagarAssunto(nome);
						label.setText("exclus�o realizada");
						listagem();
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setBounds(218, 313, 95, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(26, 293, 326, 14);
		frame.getContentPane().add(label);

		lblNovoNome = new JLabel("Novo nome:");
		lblNovoNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNovoNome.setBounds(26, 243, 71, 14);
		frame.getContentPane().add(lblNovoNome);

		lblSelecioneUmAsssunto = new JLabel("selecione um assunto");
		lblSelecioneUmAsssunto.setBounds(26, 194, 336, 14);
		frame.getContentPane().add(lblSelecioneUmAsssunto);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(134, 241, 105, 20);
		frame.getContentPane().add(textField_1);

		button_2 = new JButton("Criar");
		button_2.setToolTipText("adicionar novo assunto");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField_1.getText();
					Fachada.criarAssunto(nome);
					label.setText("Assunto criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(26, 313, 77, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Atualizar");
		button_3.setToolTipText("alterar o nome do Assunto");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField_2.getText();
					String nomenovo = textField_1.getText();
					Fachada.alterarnome(nome, nomenovo);
					label.setText("assunto atualizado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(113, 313, 95, 23);
		frame.getContentPane().add(button_3);
		
		JLabel lblNovoNome_1 = new JLabel("Nome do Assunto:");
		lblNovoNome_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoNome_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNovoNome_1.setBounds(26, 218, 123, 14);
		frame.getContentPane().add(lblNovoNome_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(134, 216, 105, 20);
		frame.getContentPane().add(textField_2);
		
		lblNoticias = new JLabel("Noticias:");
		lblNoticias.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoticias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoticias.setBounds(26, 268, 71, 14);
		frame.getContentPane().add(lblNoticias);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(134, 266, 197, 20);
		frame.getContentPane().add(textField_3);

		frame.setVisible(true);
	}

	public void listagem () {
		try{
			List<Assunto> lista = Fachada.consultarAssuntos(textField_2.getText());
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Noticias");
			
			String texto1;

			for(Assunto t : lista) {
				if(t.getNoticias() != null){
					texto1 = "";
					for (Noticia p: t.getNoticias())
						texto1 += p.getTitulo() + ", ";
					model.addRow(new Object[]{t.getId(), t.getNome(), texto1 });
					}
				else
					model.addRow(new Object[]{t.getId(),  t.getNome(),  "desconhecido"});
			}
			lblSelecioneUmAsssunto.setText("resultados: "+lista.size()+ " Assuntos  - selecione uma linha para editar");
			
			// redimensionar a coluna 0
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // desabilita
			table.getColumnModel().getColumn(0).setMaxWidth(40); // coluna id
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // desabilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}