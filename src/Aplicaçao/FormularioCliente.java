package Aplicaçao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entidades.Clientes;
import JDBC.ClientesJDBC;
import JDBC.DaoFactory;
import Servicos.ClienteServiços;
import exceções.NuloException;

public class FormularioCliente {

	static ClientesJDBC clientejdbc = DaoFactory.criaClienteJDBC();
	static ClienteServiços cs = new ClienteServiços();
	
	public static void cadastrarCliente() {

		JFrame frame = new JFrame("Formulário de Cadastro de Cliente");
		frame.setSize(300, 160);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel Nome = new JLabel("Nome:");
		Nome.setForeground(Color.BLACK);
		JTextField txtNome = new JTextField();
		formPanel.add(Nome);
		formPanel.add(txtNome);

		JLabel Email = new JLabel("Email:");
		Email.setForeground(Color.BLACK);
		JTextField txtEmail = new JTextField();
		formPanel.add(Email);
		formPanel.add(txtEmail);

		JLabel Telefone = new JLabel("Telefone:");
		Telefone.setForeground(Color.BLACK);
		formPanel.add(Telefone);
		JTextField txtTelefone = new JTextField();

		formPanel.add(txtTelefone);

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
			String email = txtEmail.getText();
			String telefone = txtTelefone.getText();

			Clientes cliente = new Clientes(nome, telefone, email);
			
				try {
					cs.CriarClientes(cliente);
				} catch (NuloException e) {
					e.printStackTrace();
				}
		

			txtNome.setText("");
			txtEmail.setText("");
			txtTelefone.setText("");
		});

		
		frame.add(panel);
		frame.setVisible(true); }

	public static void exibirListaClientes() {

		JFrame frame = new JFrame("Lista de Clientes Cadastrados");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Email");
		modelo.addColumn("Telefone");


		for (Clientes cliente : clientejdbc.listarClientes()) {
			modelo.addRow(new Object[] {cliente.getClienteID(), cliente.getName(), cliente.getEmail(), cliente.getTelefone() });
		}

		
		JTable tabela = new JTable(modelo);

		
		JScrollPane scrollPane = new JScrollPane(tabela);

	
		frame.getContentPane().add(scrollPane);

		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void deletarcliente() {
		JFrame frame = new JFrame("Deletar Cliente");
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("REMOVER CLIENTE(ID):");
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
			
             cs.deletarcliente(txtid);
		});

		frame.add(panel); 
		frame.setVisible(true); 
	}
	
	public static void Buscarcliente() {
		JFrame frame = new JFrame("Buscar Cliente");
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("BUSCAR CLIENTE(ID):");
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
			
			cs.buscarcliente(txtid);
             
		});
		
				frame.add(panel); 
				frame.setVisible(true); 
	
	}
	
	
	public static void Atulizarcliente() {
		JFrame frames = new JFrame("Atualizar Cliente");
		frames.setSize(300, 100);
		frames.setLocationRelativeTo(null);
		frames.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panels = new JPanel(new BorderLayout());
		JPanel formPanels = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanels.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("ATUALIZAR CLIENTE(ID):");
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
			
			if(cs.verificarID(txtid)) {
				try {
					cs.AtualizarCliente(txtid);
					
				} catch (NuloException e) {
				
					e.printStackTrace();
				}
			}
             
		});

				frames.add(panels); 
				frames.setVisible(true); 
	
	}
	

}
