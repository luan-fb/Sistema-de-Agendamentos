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

import Entidades.Profissionais;
import Entidades.Servicos;
import JDBC.DaoFactory;
import JDBC.ServicosJDBC;
import Servicos.ServiçosService;
import exceções.NuloException;

public class FormularioServico {
	
	
	 static ServicosJDBC servicojdbc = DaoFactory.criaServicosJDBC();
	 static ServiçosService ss = new ServiçosService();

	public static void cadastrarServico() {

        JFrame frame = new JFrame("Formulário de Cadastro de Serviços");
        frame.setSize(300, 155);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        formPanel.setBackground(new Color(160, 160, 160));

        JLabel nomeLabel = new JLabel("Nome: ");
        nomeLabel.setForeground(Color.BLACK);
        JTextField txtNome = new JTextField();
        formPanel.add(nomeLabel);
        formPanel.add(txtNome);

        JLabel duracaoLabel = new JLabel("Duração (em minutos): ");
        duracaoLabel.setForeground(Color.BLACK);
        JTextField txtDuracao = new JTextField();
        formPanel.add(duracaoLabel);
        formPanel.add(txtDuracao);

        JLabel precoLabel = new JLabel("Preço: ");
        precoLabel.setForeground(Color.BLACK);
        JTextField txtPreco = new JTextField();
        formPanel.add(precoLabel);
        formPanel.add(txtPreco);

        panel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(160, 160, 160));
        buttonPanel.setSize(50, 00);
        JButton btnSubmit = new JButton("Enviar");
        buttonPanel.add(btnSubmit);
        panel.add(buttonPanel, BorderLayout.CENTER);

        btnSubmit.addActionListener((ActionEvent i) -> {
        
            try {
            	String nome = txtNome.getText();
                int duracao;
                double preco;
                duracao = Integer.parseInt(txtDuracao.getText());
                preco = Double.parseDouble(txtPreco.getText());
                
            Servicos servico = new Servicos(nome, duracao, preco);
            
                ss.criarServico(servico);
            } catch (NuloException e) {
                e.printStackTrace();
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "\"Por favor preencha todos os campos corretamente!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            txtNome.setText("");
            txtDuracao.setText("");
            txtPreco.setText("");

        });

        frame.add(panel);
        frame.setVisible(true);
    }
	
	public static void Buscarservico() {
		JFrame frame = new JFrame("Buscar Serviço");
		frame.setSize(300, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("BUSCAR SERVIÇO(ID):");
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
			
		      ss.buscarserviço(txtid);
             
		});
		
				frame.add(panel); 
				frame.setVisible(true); 
	
	}
	
	
	public static void deletarservico() {
		JFrame frame = new JFrame("Deletar Serviço");
		frame.setSize(380, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("REMOVER SERVIÇO(ID):");
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
	
             ss.deletarservico(txtid);
		});

		
		frame.add(panel); 
		frame.setVisible(true); 
	}
	
	
	public static void Atulizarservico() {
		JFrame frames = new JFrame("Atualizar Serviço");
		frames.setSize(360, 100);
		frames.setLocationRelativeTo(null);
		frames.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panels = new JPanel(new BorderLayout());
		JPanel formPanels = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanels.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("ATUALIZAR SERVIÇO(ID):");
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
			
			if(ss.verificarID(txtid)) {
				try {
					ss.AtualizarServico(txtid);
				} catch (NuloException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
             
		});
		
				frames.add(panels); 
				frames.setVisible(true); 
	
	}
	
	public static void exibirListaServicos() {
	    JFrame frame = new JFrame("Lista de Serviços Cadastrados");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("ID");
	    modelo.addColumn("Nome");
	    modelo.addColumn("Duração (minutos)");
	    modelo.addColumn("Preço");

	    for (Servicos servico : servicojdbc.listarServicos()) {
	        modelo.addRow(new Object[] { servico.getServicoID(), servico.getNome(), servico.getDuracao(), servico.getPreco() });
	    }

	    JTable tabela = new JTable(modelo);

	    JScrollPane scrollPane = new JScrollPane(tabela);

	    frame.getContentPane().add(scrollPane);

	    frame.setSize(600, 300);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	
}
