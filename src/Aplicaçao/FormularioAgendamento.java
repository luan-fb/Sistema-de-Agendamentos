package Aplicaçao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Entidades.Agendamentos;
import Entidades.Clientes;
import Entidades.Profissionais;
import Entidades.Servicos;
import JDBC.AgendamentosJDBC;
import JDBC.ClientesJDBC;
import JDBC.DaoFactory;
import JDBC.ProfissionaisJDBC;
import JDBC.ServicosJDBC;
import Servicos.AgendamentoService;
import exceções.NuloException;

public class FormularioAgendamento {

	static AgendamentosJDBC agendamentosJDBC = DaoFactory.criaAgendamentosJDBC();
	static ServicosJDBC servicojdbc = DaoFactory.criaServicosJDBC();
	static ProfissionaisJDBC profissionaljdbc = DaoFactory.criaProfissionaisJDBC();
	static ClientesJDBC clientejdbc = DaoFactory.criaClienteJDBC();
	
	static AgendamentoService as = new AgendamentoService();

	static Clientes c1 = new Clientes();
	static Profissionais p1 = new Profissionais();
	static Servicos s1 = new Servicos();

	
	public static void cadastrarAgendamento() {
		JFrame frame = new JFrame("Formulário de Cadastro de Agendamento");
	    frame.setSize(530, 300);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    JPanel panel = new JPanel(new BorderLayout());

	    JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
	    formPanel.setBackground(new Color(160, 160, 160));

	   
	    JLabel dataLabel = new JLabel("Data do agendamento: ");
	    dataLabel.setForeground(Color.BLACK);
	    formPanel.add(dataLabel);

	    JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    dataPanel.setBackground(UIManager.getColor("ComboBox.background"));
	    dataPanel.setBorder(UIManager.getBorder("ComboBox.border"));
	    
	    
	    JLabel dial= new JLabel("Dia: ");
	    dial.setForeground(Color.BLACK);
	    dataPanel.add(dial);
	    JComboBox<String> diaComboBox = new JComboBox<>();
	    
	    JComboBox<String> mesComboBox = new JComboBox<>();
	    JComboBox<String> anoComboBox = new JComboBox<>();
	    
	    
	    for (int dia = 1; dia <= 31; dia++) {
	        diaComboBox.addItem(String.valueOf(dia));
	    }dataPanel.add(diaComboBox);
	    
	    JLabel mesl = new JLabel("Mes: ");
	    mesl.setForeground(Color.BLACK);
	    dataPanel.add(mesl);
	  
	    for (int mes = 1; mes <= 12; mes++) {
	        mesComboBox.addItem(String.valueOf(mes));
	    }dataPanel.add(mesComboBox);
	    
	    JLabel anol = new JLabel("Ano: ");
	    anol.setForeground(Color.BLACK);
	    dataPanel.add(anol);
	    
	    for (int ano = 2024; ano <= 2025; ano++) {
	        anoComboBox.addItem(String.valueOf(ano));
	    }dataPanel.add(anoComboBox);
	    formPanel.add(dataPanel);

	    JLabel horaLabel = new JLabel("Hora: ");
	    horaLabel.setForeground(Color.BLACK);
	    formPanel.add(horaLabel);
	    JComboBox<String> horaComboBox = new JComboBox<>();
	
	    for (int i = 0; i < 24; i++) {
	        horaComboBox.addItem(String.format("%02d:00", i)); 
	    }
	    formPanel.add(horaComboBox);

	
	    JLabel clienteLabel = new JLabel("Cliente: ");
	    clienteLabel.setForeground(Color.BLACK);
	    formPanel.add(clienteLabel);
	    JComboBox<String> clienteComboBox = new JComboBox<>();
	    for (Clientes cliente : clientejdbc.listarClientes()) {
	        clienteComboBox.addItem(cliente.getName());
	    }
	    formPanel.add(clienteComboBox);

	    JLabel profissionalLabel = new JLabel("Profissional: ");
	    profissionalLabel.setForeground(Color.BLACK);
	    formPanel.add(profissionalLabel);
	    JComboBox<String> profissionalComboBox = new JComboBox<>();
	    for (Profissionais profissional : profissionaljdbc.listarProfissionais()) {
	        profissionalComboBox.addItem(profissional.getNome());
	    }
	    formPanel.add(profissionalComboBox);

	    JLabel servicoLabel = new JLabel("Serviço: ");
	    servicoLabel.setForeground(Color.BLACK);
	    formPanel.add(servicoLabel);
	    JComboBox<String> servicoComboBox = new JComboBox<>();
	    for (Servicos servico : servicojdbc.listarServicos()) {
	        servicoComboBox.addItem(servico.getNome());
	    }
	    formPanel.add(servicoComboBox);

	    panel.add(formPanel, BorderLayout.NORTH);

	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setBackground(new Color(160, 160, 160));
	    JButton btnSubmit = new JButton("Enviar");
	    buttonPanel.add(btnSubmit);
	    panel.add(buttonPanel, BorderLayout.CENTER);
		
		btnSubmit.addActionListener((ActionEvent i) -> {
			String nomeCliente = (String) clienteComboBox.getSelectedItem();
			for (Clientes cliente : clientejdbc.listarClientes()) {
				if(cliente.getName().equals(nomeCliente)) {
					c1 = new Clientes(cliente.getName(),cliente.getTelefone(),cliente.getEmail());
					c1.setClienteID(cliente.getClienteID());
				}
			}
			String nomeProfissional = (String) profissionalComboBox.getSelectedItem();
			for (Profissionais profissional : profissionaljdbc.listarProfissionais()) {
				if(profissional.getNome().equals(nomeProfissional)) {
					p1 = new Profissionais(profissional.getNome(),profissional.getEspecialidade());
					p1.setProfissionalID(profissional.getProfissionalID());
				}
			}
			
			String nomeServico = (String) servicoComboBox.getSelectedItem();
			for (Servicos servico : servicojdbc.listarServicos()) {
				if(servico.getNome().equals(nomeServico)) {
					s1 = new Servicos(servico.getNome(),servico.getDuracao(),servico.getPreco());
					s1.setServicoID(servico.getServicoID());
				}
			}
			
			int diaSelecionado = Integer.parseInt((String) diaComboBox.getSelectedItem());
			int mesSelecionado = Integer.parseInt((String) mesComboBox.getSelectedItem());
			int anoSelecionado = Integer.parseInt((String) anoComboBox.getSelectedItem());
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, anoSelecionado);
			cal.set(Calendar.MONTH, mesSelecionado - 1); 
			cal.set(Calendar.DAY_OF_MONTH, diaSelecionado);

			Date dataSelecionada = cal.getTime();
			
			String hora = (String) horaComboBox.getSelectedItem();
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			java.util.Date datehora;
			try {
			    datehora = sdf.parse(hora);
			} catch (ParseException e) {
			    e.printStackTrace();
			    return;
			}

			Agendamentos agendamento = new Agendamentos(dataSelecionada, datehora, c1,s1,p1);
			try {
				as.adicionarAgendamento(agendamento);
			} catch (Exception e) {
				e.printStackTrace();
			}
			clienteComboBox.setSelectedIndex(0);
			profissionalComboBox.setSelectedIndex(0);
			servicoComboBox.setSelectedIndex(0);

		});

		frame.add(panel);
		frame.setVisible(true);
	}

	
	public static void Buscaragendamentopornome() {
		JFrame frame = new JFrame("Buscar Agendamento");
		frame.setSize(400, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel nome = new JLabel("BUSCAR AGENDAMENTO(NOME) ");
		nome.setForeground(Color.BLACK);
		JTextField txtnome = new JTextField();
		formPanel.add(nome);
		formPanel.add(txtnome);

		panel.add(formPanel, BorderLayout.NORTH);

		panel.add(formPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(160, 160, 160));
		buttonPanel.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanel.add(btnSubmit);
		panel.add(buttonPanel, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
              			
			String nomeCliente = txtnome.getText(); 
		    as.buscaragendamentopornome(nomeCliente);
             
		});
		
				frame.add(panel); 
				frame.setVisible(true); 
	
	}
	
	public static void Buscaragendamentopordata() {
		JFrame frame = new JFrame("Buscar Agendamento");
		frame.setSize(280, 110);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));


	    JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    dataPanel.setBackground(new Color(160, 160, 160));
	    JLabel dial= new JLabel("Dia: ");
	    dial.setForeground(Color.BLACK);
	    dataPanel.add(dial);
	    JComboBox<String> diaComboBox = new JComboBox<>();
	    
	    JComboBox<String> mesComboBox = new JComboBox<>();
	    JComboBox<String> anoComboBox = new JComboBox<>();
	    
	    
	    for (int dia = 1; dia <= 31; dia++) {
	        diaComboBox.addItem(String.valueOf(dia));
	    }dataPanel.add(diaComboBox);
	    
	    JLabel mesl = new JLabel("Mes: ");
	    mesl.setForeground(Color.BLACK);
	    dataPanel.add(mesl);
	  
	    for (int mes = 1; mes <= 12; mes++) {
	        mesComboBox.addItem(String.valueOf(mes));
	    }dataPanel.add(mesComboBox);
	    
	    JLabel anol = new JLabel("Ano: ");
	    anol.setForeground(Color.BLACK);
	    dataPanel.add(anol);
	    
	    for (int ano = 2024; ano <= 2025; ano++) {
	        anoComboBox.addItem(String.valueOf(ano));
	    }dataPanel.add(anoComboBox);
	    formPanel.add(dataPanel);


		panel.add(formPanel, BorderLayout.NORTH);

		panel.add(formPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(160, 160, 160));
		buttonPanel.setSize(50, 00);
		JButton btnSubmit = new JButton("Enviar");
		buttonPanel.add(btnSubmit);
		panel.add(buttonPanel, BorderLayout.CENTER);

		btnSubmit.addActionListener((ActionEvent i) -> {
			
			int diaSelecionado = Integer.parseInt((String) diaComboBox.getSelectedItem());
			int mesSelecionado = Integer.parseInt((String) mesComboBox.getSelectedItem());
			int anoSelecionado = Integer.parseInt((String) anoComboBox.getSelectedItem());
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, anoSelecionado);
			cal.set(Calendar.MONTH, mesSelecionado - 1); 
			cal.set(Calendar.DAY_OF_MONTH, diaSelecionado);

			Date dataSelecionada = cal.getTime();
		    as.buscaragendamentopordata(dataSelecionada);
             
		});
		
				frame.add(panel); 
				frame.setVisible(true); 
	
	}
	
	
	public static void deletaragendamento() {
		JFrame frame = new JFrame("Deletar Agendamento");
		frame.setSize(380, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanel.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("REMOVER AGENDAMENTO(ID):");
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
	
             as.deletarAgendamento(txtid);
		});

		
		frame.add(panel); 
		frame.setVisible(true); 
	}
	
	public static void Atulizaragendamento() {
		JFrame frames = new JFrame("Atualizar Agendamento");
		frames.setSize(360, 100);
		frames.setLocationRelativeTo(null);
		frames.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panels = new JPanel(new BorderLayout());
		JPanel formPanels = new JPanel(new GridLayout(1, 1, 5, 5));
		formPanels.setBackground(new Color(160, 160, 160));

		JLabel ID = new JLabel("Atualizar Agendamento(ID):");
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
			
			if(as.verificarID(txtid)) {
				try {
					as.atualizarAgendamento(txtid);
				} catch (NuloException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
             
		});
		
				frames.add(panels); 
				frames.setVisible(true); 
	
	}
	
	
	public static void exibirListaAgendamento() {
	    JFrame frame = new JFrame("Lista de Agendamentos Cadastrados");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("ID");
	    modelo.addColumn("Nome do Cliente");
	    modelo.addColumn("Dia");
	    modelo.addColumn("Hora");
	    modelo.addColumn("Serviço");
	    modelo.addColumn("Preço");
	    modelo.addColumn("Profissional");

	    for (Agendamentos ag : agendamentosJDBC.listarAgendamentos()) {
	        modelo.addRow(new Object[] {ag.getAgendamentoID(),ag.getCliente().getName(),ag.getDia(),ag.getHora(),ag.getServico().getNome(),ag.getServico().getPreco(),ag.getProfissional().getNome() });
	    }

	    JTable tabela = new JTable(modelo);

	    JScrollPane scrollPane = new JScrollPane(tabela);

	    frame.getContentPane().add(scrollPane);

	    frame.setSize(600, 300);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	
}
