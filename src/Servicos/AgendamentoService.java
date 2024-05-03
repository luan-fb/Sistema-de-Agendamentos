package Servicos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import exceções.HorarioException;
import exceções.NuloException;

public class AgendamentoService {

	private AgendamentosJDBC  agendamentos = DaoFactory.criaAgendamentosJDBC();
	
	static AgendamentosJDBC agendamentosJDBC = DaoFactory.criaAgendamentosJDBC();
	static ServicosJDBC servicojdbc = DaoFactory.criaServicosJDBC();
	static ProfissionaisJDBC profissionaljdbc = DaoFactory.criaProfissionaisJDBC();
	static ClientesJDBC clientejdbc = DaoFactory.criaClienteJDBC();
	
	static AgendamentoService as = new AgendamentoService();

	static Clientes c1 = new Clientes();
	static Profissionais p1 = new Profissionais();
	static Servicos s1 = new Servicos();
	
	public boolean adicionarAgendamento(Agendamentos agendamento) throws Exception {
       try {
    	   if (agendamento.getDia() == null || agendamento.getHora() == null || agendamento.getCliente() == null
                   || agendamento.getServico() == null || agendamento.getProfissional() == null) {
               throw new NuloException("Por favor preencha todos os campos corretamente!");
           } 
    	   
        List<Agendamentos> agendamentosExistentes = agendamentos.buscarAgendamentoPorData(agendamento.getDia());
        for (Agendamentos ag : agendamentosExistentes) {
            if (ag.getHora().equals(agendamento.getHora()) && ag.getProfissional().getProfissionalID() == agendamento.getProfissional().getProfissionalID()) {
                throw new HorarioException("Conflito de horário encontrado!");
            }
        }
        agendamentos.criarAgendamento(agendamento);
        JOptionPane.showMessageDialog(null, "Agendamento Cadastrado com Sucesso");
        return true;
       }catch(HorarioException e) {
    	   JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
       }catch (NuloException e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
       } 
	return false;
    }
	
	
	 public void buscaragendamentopornome(String txtnome) {
    	
 
			 List<Agendamentos> g1 = agendamentos.buscarAgendamentoPorNomeCliente(txtnome);
			   if(g1.isEmpty() || txtnome.isEmpty()) {
				   JOptionPane.showMessageDialog(null, "Agendamento nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
			   }
			   else {
				    JFrame frame = new JFrame("Lista de Agendamentos Cadastrados");
				    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				   
				    DefaultTableModel modelo = new DefaultTableModel();
				    modelo.addColumn("ID");
				    modelo.addColumn("Nome Cliente");
				    modelo.addColumn("Profissional");
				    modelo.addColumn("Serviço");
				    modelo.addColumn("Preço");
				    modelo.addColumn("Dia Agendado");
				    modelo.addColumn("Horario: ");
				    for (Agendamentos agendamento : agendamentos.buscarAgendamentoPorNomeCliente(txtnome)) {
				        modelo.addRow(new Object[] { agendamento.getAgendamentoID(), agendamento.getCliente().getName(), agendamento.getProfissional().getNome(), agendamento.getServico().getNome(),agendamento.getServico().getPreco(),agendamento.getDia(),agendamento.getHora() });
				    }

				    JTable tabela = new JTable(modelo);

				    JScrollPane scrollPane = new JScrollPane(tabela);

				    frame.getContentPane().add(scrollPane);

				    frame.setSize(610, 300);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
			   }
     }
	
	
	 public void buscaragendamentopordata(Date data) {
	    	
		 List<Agendamentos> g1 = agendamentos.buscarAgendamentoPorData(data);
		 
		   if(g1.isEmpty() || data == null){
			   JOptionPane.showMessageDialog(null, "Agendamento nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
		   }
		   else {
			    JFrame frame = new JFrame("Lista de Agendamentos Cadastrados");
			    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			   
			    DefaultTableModel modelo = new DefaultTableModel();
			    modelo.addColumn("ID");
			    modelo.addColumn("Nome Cliente");
			    modelo.addColumn("Profissional");
			    modelo.addColumn("Serviço");
			    modelo.addColumn("Preço");
			    modelo.addColumn("Dia Agendado");
			    modelo.addColumn("Horario: ");
			    for (Agendamentos agendamento : agendamentos.buscarAgendamentoPorData(data)) {
			        modelo.addRow(new Object[] { agendamento.getAgendamentoID(), agendamento.getCliente().getName(), agendamento.getProfissional().getNome(), agendamento.getServico().getNome(),agendamento.getServico().getPreco(),agendamento.getDia(),agendamento.getHora() });
			    }

			    JTable tabela = new JTable(modelo);

			    JScrollPane scrollPane = new JScrollPane(tabela);

			    frame.getContentPane().add(scrollPane);

			    frame.setSize(610, 300);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
		   }
	 }
		   public void deletarAgendamento(JTextField txtid) {
		    	 
		    	 try {
		    		 int idUsuario = Integer.parseInt(txtid.getText());
					   agendamentos.deletarAgendamento(idUsuario);
					
					 
					} catch (NumberFormatException e) {
					    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
		       
		     }
		   public boolean verificarID(JTextField txtid) {
		    	 try {
		    		 int idUsuario = Integer.parseInt(txtid.getText());
					  Agendamentos g1 = agendamentos.buscarAgendamentoID(idUsuario);
					   if(g1 == null) {
						   JOptionPane.showMessageDialog(null, "Agendamento nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
					      return false;
					   }
					   else {
						   return true;
					   }
					 
					} catch (NumberFormatException e) {
					    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				return false;
		     }
		   
		   public void atualizarAgendamento(JTextField txtid) throws NuloException {
		    	int idUsuario = Integer.parseInt(txtid.getText());
		        JFrame frame = new JFrame("Formulário de Atualização de Cadastro de Agendamento");
		        frame.setSize(600, 300);
		        frame.setLocationRelativeTo(null);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		        JPanel panel = new JPanel(new BorderLayout());
		        JPanel formPanel = new JPanel(new GridLayout(5, 3, 10, 10)); 
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
					agendamento.setAgendamentoID(idUsuario);
					try {
						agendamentos.atualizarAgendamentos(agendamento);
						JOptionPane.showMessageDialog(null, "Dados do Agendamento atualizado com sucesso!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					clienteComboBox.setSelectedIndex(0);
					profissionalComboBox.setSelectedIndex(0);
					servicoComboBox.setSelectedIndex(0);

				});

				frame.add(panel);
				frame.setVisible(true);
		        
		        
		        

		        frame.add(panel); 
		        frame.setVisible(true); 
		    }
	
	 
}
