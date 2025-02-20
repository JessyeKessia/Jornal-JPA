/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranh�o Ayres
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
import java.util.ArrayList;
import java.util.Arrays;
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

import modelo.Noticia;
import modelo.Assunto;
import regras_negocio.Fachada;

public class TelaCadastrarNoticias {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JLabel label;
	private JLabel lblSelecioneUmaNoticia;
	private JLabel lblTitulo;
	private JLabel lblData;
	private JLabel lblLink;
	private JLabel lblNomeDaNoticia;
	private JLabel lblAssunto;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the application.
	 */
	public TelaCadastrarNoticias() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true); // janela modal

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
		frame.setTitle("Noticias");
		frame.setBounds(100, 100, 744, 428);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 63, 685, 155);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			// proibir alteracao de celulas
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		// evento de selecao de uma linha da tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						// pegar o nome, data e link da noticia
						String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
						Noticia p = Fachada.localizarNoticia(nome);
						String data = p.getData();
						textField_1.setText(nome);
						textField_2.setText(data);
						textField_3.setText(String.join(",", p.getLinkWeb()));
						textField_4.setText("");
						label.setText("");
					}
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button_4 = new JButton("Apagar");
		button_4.setToolTipText("apagar noticia");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					// pegar o nome na linha selecionada
					String nome = textField_1.getText();
					Object[] options = { "Confirmar", "Cancelar" };
					int escolha = JOptionPane.showOptionDialog(null,
							"Esta opera��o apagar� a noticia " + nome, "Alerta",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					if (escolha == 0) {
						Fachada.apagarNoticia(nome);
						label.setText("noticia excluida");
						listagem(); // listagem
					} else
						label.setText("exclus�o cancelada");

				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_4.setBounds(169, 340, 74, 23);
		frame.getContentPane().add(button_4);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 372, 607, 14);
		frame.getContentPane().add(label);

		button = new JButton("Buscar por nome");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(227, 27, 149, 23);
		frame.getContentPane().add(button);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(111, 28, 106, 20);
		frame.getContentPane().add(textField);

		lblSelecioneUmaNoticia = new JLabel("selecione uma noticia para editar");
		lblSelecioneUmaNoticia.setBounds(21, 216, 394, 14);
		frame.getContentPane().add(lblSelecioneUmaNoticia);

		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitulo.setBounds(31, 239, 62, 14);
		frame.getContentPane().add(lblTitulo);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(101, 235, 165, 20);
		frame.getContentPane().add(textField_1);

		lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblData.setBounds(31, 264, 62, 14);
		frame.getContentPane().add(lblData);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(101, 260, 86, 20);
		frame.getContentPane().add(textField_2);

		lblLink = new JLabel("Link:");
		lblLink.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLink.setHorizontalAlignment(SwingConstants.LEFT);
		lblLink.setBounds(31, 289, 62, 14);
		frame.getContentPane().add(lblLink);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(101, 284, 264, 20);
		frame.getContentPane().add(textField_3);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar nova noticia");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String titulo = textField_1.getText().trim();
					String data = textField_2.getText().trim();
					String link = textField_3.getText().trim();
					String assunto = textField_4.getText().trim();


					Fachada.criarNoticia(titulo, data, link, assunto);
					String Assunto = textField_4.getText();
					if (!Assunto.isEmpty())
						Fachada.criarAssunto(Assunto);

					label.setText("noticia criada");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(21, 340, 62, 23);
		frame.getContentPane().add(button_1);

		button_5 = new JButton("Atualizar");
		button_5.setToolTipText("atualizar pessoa ");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().trim().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField_1.getText();
					String novotitulo = textField_5.getText();
					Fachada.alterartitulo(nome, novotitulo);
					
					label.setText("Noticia alterada");
					listagem();
				} catch (Exception ex2) {
					label.setText(ex2.getMessage());
				}
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_5.setBounds(82, 340, 87, 23);
		frame.getContentPane().add(button_5);

		lblNomeDaNoticia = new JLabel("Nome da Noticia:");
		lblNomeDaNoticia.setBounds(21, 32, 106, 14);
		frame.getContentPane().add(lblNomeDaNoticia);

		button_3 = new JButton("Limpar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		button_3.setBounds(539, 235, 89, 23);
		frame.getContentPane().add(button_3);

		lblAssunto = new JLabel("Assunto:");
		lblAssunto.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssunto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAssunto.setBounds(31, 314, 62, 14);
		frame.getContentPane().add(lblAssunto);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(101, 309, 86, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(348, 235, 165, 20);
		frame.getContentPane().add(textField_5);
		
		JLabel lblNovoTitulo = new JLabel("Novo titulo:");
		lblNovoTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNovoTitulo.setBounds(276, 239, 62, 14);
		frame.getContentPane().add(lblNovoTitulo);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			List<Noticia> lista = Fachada.listarNoticias();

			// objeto model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			// criar as colunas (0,1,2) da tabela
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Data");
			model.addColumn("Link");
			model.addColumn("Assuntos");

			// criar as linhas da tabela
			String texto1, texto2;
			for (Noticia p : lista) {
				texto1 = String.join(",", p.getLinkWeb()); // concatena strings
				if (p.getAssuntos().size() > 0) {
					texto2 = "";
					for (Assunto t : p.getAssuntos())
						texto2 += t.getId() + " ";
				} else
					texto2 = "sem assuntos";
				//adicionar linha no table
				model.addRow(new Object[] { p.getId(), p.getTitulo(), p.getData(), texto1, texto2 });

			}
			lblSelecioneUmaNoticia.setText("resultados: " + lista.size() + " pessoas   - selecione uma linha para editar");

			// redimensionar a coluna 0,3 e 4
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // desabilita
			table.getColumnModel().getColumn(0).setMaxWidth(40); // coluna id
			table.getColumnModel().getColumn(3).setMinWidth(200); // coluna dos apelidos
			table.getColumnModel().getColumn(4).setMinWidth(200); // coluna dos telefones
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // desabilita

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
}