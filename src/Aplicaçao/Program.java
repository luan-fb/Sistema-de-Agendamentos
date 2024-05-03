package Aplicaçao;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Program extends JFrame{

	 private JPanel mainPanel;

	    public Program() {
	        setTitle("Sistema de Gerenciamento de Agendamentos");
	        setSize(800, 600);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null); 
	        ImageIcon icon = new ImageIcon("img/Logo.jpeg");
	        setIconImage(icon.getImage());

	        initUI();

	    }

	    private void initUI() {
	        setLayout(new BorderLayout());
	        mainPanel = new JPanel(new BorderLayout());

	        
	        ImageIcon backgroundImage = new ImageIcon("img/Logo.jpeg");
	        JLabel backgroundLabel = new JLabel(backgroundImage);
	        mainPanel.add(backgroundLabel, BorderLayout.CENTER);
	       
	        JMenuBar menuBar = new JMenuBar();
	        
	        
	        JMenu menuClientes = new JMenu("Clientes");
	        
	        JMenuItem clienteCadastrar = new JMenuItem("Cadastrar Cliente");
	        clienteCadastrar.addActionListener(e -> FormularioCliente.cadastrarCliente());
	        menuClientes.add(clienteCadastrar);
	        
	        JMenuItem Buscarclientes = new JMenuItem("Buscar Cliente");
	        Buscarclientes.addActionListener(e -> FormularioCliente.Buscarcliente());
	        menuClientes.add(Buscarclientes);
	        
	        JMenuItem Removerclientes = new JMenuItem("Remover Cliente");
	        Removerclientes.addActionListener(e -> FormularioCliente.deletarcliente());
	        menuClientes.add(Removerclientes);
	        
	        JMenuItem Atualizarclientes = new JMenuItem("Atualizar Cliente");
	        Atualizarclientes.addActionListener(e -> FormularioCliente.Atulizarcliente());
	        menuClientes.add(Atualizarclientes);
	        
	        JMenuItem Verclientes = new JMenuItem("Clientes Cadastrados");
	        Verclientes.addActionListener(e -> FormularioCliente.exibirListaClientes());
	        menuClientes.add(Verclientes);
	        
	        menuBar.add(menuClientes);
	        
	        
	        
	        JMenu menuProfissionais = new JMenu("Profissionais");

	        JMenuItem ProfissionalCadastrar = new JMenuItem("Cadastrar Profissional");
	        ProfissionalCadastrar.addActionListener(e -> FormularioProfissionais.cadastrarProfissional());
	        menuProfissionais.add(ProfissionalCadastrar);
	        
	        JMenuItem Buscarprofissionais = new JMenuItem("Buscar Profissional");
	        Buscarprofissionais.addActionListener(e -> FormularioProfissionais.Buscarprofissionais());
	        menuProfissionais.add(Buscarprofissionais);
	        
	        JMenuItem Deletarprofissionais = new JMenuItem("Remover Profissional ");
	        Deletarprofissionais.addActionListener(e -> FormularioProfissionais.deletarprofissional());
	        menuProfissionais.add(Deletarprofissionais);
	        
	        JMenuItem Atualizarprofissionais = new JMenuItem("Atualizar Profissional");
	        Atualizarprofissionais.addActionListener(e -> FormularioProfissionais.Atulizarprofissional());
	        menuProfissionais.add(Atualizarprofissionais);
	        
	        JMenuItem Listarprofissionais = new JMenuItem("Profissionais Cadastrados");
	        Listarprofissionais.addActionListener(e -> FormularioProfissionais.exibirListaClientes());
	        menuProfissionais.add(Listarprofissionais);
	        
	        menuBar.add(menuProfissionais);

	        
	        
	        JMenu menuServicos = new JMenu("Serviços");
	        
	        JMenuItem servicoCadastrar = new JMenuItem("Cadastrar Serviço");
	        servicoCadastrar.addActionListener(e -> FormularioServico.cadastrarServico());
	        menuServicos.add(servicoCadastrar);
	        
	        JMenuItem Buscarservico = new JMenuItem("Buscar Serviço");
	        Buscarservico.addActionListener(e -> FormularioServico.Buscarservico());
	        menuServicos.add(Buscarservico);
	        
	        JMenuItem Removerservico = new JMenuItem("Remover Serviço");
	        Removerservico.addActionListener(e -> FormularioServico.deletarservico());
	        menuServicos.add(Removerservico);
	        
	        JMenuItem Atualizarservico = new JMenuItem("Atualizar Serviço");
	        Atualizarservico.addActionListener(e -> FormularioServico.Atulizarservico());
	        menuServicos.add(Atualizarservico);
	        
	        JMenuItem Listarservico = new JMenuItem("Serviços Cadastrados");
	        Listarservico.addActionListener(e -> FormularioServico.exibirListaServicos());
	        menuServicos.add(Listarservico);
	       	        
	        menuBar.add(menuServicos);
	        
	        JMenu menuAgendamentos = new JMenu("Agendamentos");
	        JMenuItem agendamentoCadastrar = new JMenuItem("Cadastrar Agendamento");
	        agendamentoCadastrar.addActionListener(e -> FormularioAgendamento.cadastrarAgendamento());
	        menuAgendamentos.add(agendamentoCadastrar);
	        
	        JMenuItem buscaragendamentonome = new JMenuItem("Buscar Agendamento (Nome)");
	        buscaragendamentonome.addActionListener(e -> FormularioAgendamento.Buscaragendamentopornome());
	        menuAgendamentos.add(buscaragendamentonome);
	        
	        JMenuItem buscaragendamentodata = new JMenuItem("Buscar Agendamento (Data)");
	        buscaragendamentodata.addActionListener(e -> FormularioAgendamento.Buscaragendamentopordata());
	        menuAgendamentos.add(buscaragendamentodata);
	        
	        JMenuItem deletaragendamento = new JMenuItem("Remover Agendamento ");
	        deletaragendamento.addActionListener(e -> FormularioAgendamento.deletaragendamento());
	        menuAgendamentos.add(deletaragendamento);
	        
	        JMenuItem  atualizaragendamento = new JMenuItem("Atualizar Agendamento ");
	        atualizaragendamento.addActionListener(e -> FormularioAgendamento.Atulizaragendamento());
	        menuAgendamentos.add(atualizaragendamento);
	        
	        JMenuItem listaragendamentos = new JMenuItem("Agendamento Cadastrados ");
	        listaragendamentos.addActionListener(e -> FormularioAgendamento.exibirListaAgendamento());
	        menuAgendamentos.add(listaragendamentos);
	        
	        
	        
	        
	        menuBar.add(menuAgendamentos);

	        setJMenuBar(menuBar);

	        add(mainPanel, BorderLayout.CENTER);
	        

	        
	        JLabel statusBar = new JLabel("Pronto");
	        add(statusBar, BorderLayout.SOUTH);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            Program frame = new Program();
	            frame.setVisible(true);
	            
	            
	            
	        });
	    }
	    
	    
	       
	    }
	