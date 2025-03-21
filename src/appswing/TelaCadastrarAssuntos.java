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
import regras_negocio.Fachada;

public class TelaCadastrarAssuntos {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JLabel label;
	private JTextField textField;
	private JLabel lblNovoNome;
	private JTextField textField_1;
	private JButton button_2;

	private JLabel lblNome;
	private JLabel lblSelecioneUmAsssunto;
	private JButton button_3;

	
	/**
	 * Create the application.
	 */
	public TelaCadastrarAssuntos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);		//janela modal
		
		frame.setTitle("Cadastrar Assuntos");
		frame.setBounds(100, 100, 378, 341);
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
		scrollPane.setBounds(26, 44, 315, 152);
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
						String nome = (String) table.getValueAt( table.getSelectedRow(), 2);
						textField_1.setText(nome);
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
					if(textField_1.getText().isEmpty()) {
						label.setText("numero vazio");
						return;
					}
					String nome = textField_1.getText();
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
		button_1.setBounds(218, 250, 95, 23);
		frame.getContentPane().add(button_1);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(26, 309, 326, 14);
		frame.getContentPane().add(label);

		button = new JButton("Listar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(229, 10, 112, 23);
		frame.getContentPane().add(button);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(122, 11, 86, 20);
		frame.getContentPane().add(textField);

		lblNovoNome = new JLabel("Novo nome:");
		lblNovoNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNovoNome.setBounds(26, 221, 71, 14);
		frame.getContentPane().add(lblNovoNome);

		lblNome = new JLabel("Nome do Assunto: ");
		lblNome.setBounds(26, 15, 123, 14);
		frame.getContentPane().add(lblNome);

		lblSelecioneUmAsssunto = new JLabel("selecione um asssunto");
		lblSelecioneUmAsssunto.setBounds(26, 194, 336, 14);
		frame.getContentPane().add(lblSelecioneUmAsssunto);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 219, 105, 20);
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
		button_2.setBounds(26, 250, 77, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Atualizar");
		button_3.setToolTipText("alterar o Assunto");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField.getText();
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
		button_3.setBounds(113, 250, 95, 23);
		frame.getContentPane().add(button_3);

		frame.setVisible(true);
	}

	public void listagem () {
		try{
			List<Assunto> lista = Fachada.consultarAssuntos(textField.getText());
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
			
			model.addColumn("Id");
			model.addColumn("Nome");

			for(Assunto t : lista) {
				if(t.getNoticias() != null)
					model.addRow(new Object[]{t.getId(), t.getNome() });
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