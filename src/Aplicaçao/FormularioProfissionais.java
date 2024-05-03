package Aplicaçao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entidades.Profissionais;
import JDBC.DaoFactory;
import JDBC.ProfissionaisJDBC;
import Servicos.ProfissionalServiços;
import exceções.NuloException;

public class FormularioProfissionais {

	static ProfissionaisJDBC profissionaljdbc = DaoFactory.criaProfissionaisJDBC();
	static ProfissionalServiços ps = new ProfissionalServiços();
	
	public static void cadastrarProfissional() {

		JFrame frame = new JFrame("Formulário de Cadastro de Profissionais");
		frame.setSize(300, 125);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel Nome = new JLabel("Nome: ");
		Nome.setForeground(Color.BLACK);
		JTextField txtNome = new JTextField();
		formPanel.add(Nome);
		formPanel.add(txtNome);

		JLabel Especialidade = new JLabel("Especialidade: ");
		Especialidade.setForeground(Color.BLACK);
		JTextField txtEspecialidade = new JTextField();
		formPanel.add(Especialidade);
		formPanel.add(txtEspecialidade);

		panel.add(formPanel, BorderLayout.NORTH);

		panel.add(formPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(160, 160, 160));
		buttonPanel.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanel.add(btnSubmit);
		panel.add(buttonPanel, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
			String nome = txtNome.getText();
			String especialidade = txtEspecialidade.getText();

			Profissionais profissional = new Profissionais(nome, especialidade);
			
				try {
					ps.CriarProfissionais(profissional);
				} catch (NuloException e) {
					e.printStackTrace();
				}
			txtNome.setText("");
			txtEspecialidade.setText("");
		
		});

		frame.add(panel); 
		frame.setVisible(true); 
	}
	
	public static void exibirListaClientes() {

		JFrame frame = new JFrame("Lista de Funcionarios Cadastrados");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Especialidade");
		
		for (Profissionais profissional: profissionaljdbc.listarProfissionais()) {
			modelo.addRow(new Object[] {profissional.getProfissionalID(), profissional.getNome(), profissional.getEspecialidade() });
		}

		JTable tabela = new JTable(modelo);

		JScrollPane scrollPane = new JScrollPane(tabela);

		frame.getContentPane().add(scrollPane);

		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void Buscarprofissionais() {
		JFrame frame = new JFrame("Buscar Profissional");
		frame.setSize(350, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("BUSCAR PROFISSIONAL(ID):");
		ID.setForeground(Color.BLACK);
		JTextField txtid = new JTextField();
		formPanel.add(ID);
		formPanel.add(txtid);

		panel.add(formPanel, BorderLayout.NORTH);

		panel.add(formPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(160, 160, 160));
		buttonPanel.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanel.add(btnSubmit);
		panel.add(buttonPanel, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
			
		      ps.buscarserviço(txtid);
             
		});
		
				frame.add(panel); 
				frame.setVisible(true); 
	
	}
	
	public static void deletarprofissional() {
		JFrame frame = new JFrame("Deletar Profissional");
		frame.setSize(380, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("REMOVER PROFISSIONAL(ID):");
		ID.setForeground(Color.BLACK);
		JTextField txtid = new JTextField();
		formPanel.add(ID);
		formPanel.add(txtid);

		panel.add(formPanel, BorderLayout.NORTH);

		panel.add(formPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(160, 160, 160));
		buttonPanel.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanel.add(btnSubmit);
		panel.add(buttonPanel, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
			
             ps.deletarprofissionais(txtid);
		});

		
		frame.add(panel); 
		frame.setVisible(true); 
	}
	
	public static void Atulizarprofissional() {
		JFrame frames = new JFrame("Atualizar Profissionaç");
		frames.setSize(390, 100);
		frames.setLocationRelativeTo(null);
		frames.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panels = new JPanel(new BorderLayout());
		JPanel formPanels = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanels.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("ATUALIZAR PROFISISONAL (ID):");
		ID.setForeground(Color.BLACK);
		JTextField txtid = new JTextField();
		formPanels.add(ID);
		formPanels.add(txtid);

		panels.add(formPanels, BorderLayout.NORTH);

		panels.add(formPanels, BorderLayout.NORTH);

		JPanel buttonPanels = new JPanel();
		buttonPanels.setBackground(new Color(160, 160, 160));
		buttonPanels.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanels.add(btnSubmit);
		panels.add(buttonPanels, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
			
			if(ps.verificarID(txtid)) {
				try {
					ps.AtualizarProfissional(txtid);
				} catch (NuloException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
             
		});
		
				frames.add(panels); 
				frames.setVisible(true); 
	
	}
	
	
	
}
