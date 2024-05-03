package Servicos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Entidades.Profissionais;
import Entidades.Servicos;
import JDBC.DaoFactory;
import JDBC.ServicosJDBC;
import exceções.NuloException;

public class ServiçosService {
	
		 ServicosJDBC servicosJDBC = DaoFactory.criaServicosJDBC();

		    public void criarServico(Servicos servico)throws NuloException {
		        try {
		            if (servico.getNome().isEmpty() || servico.getDuracao() <= 0 || servico.getPreco() <= 0) {
		                throw new NuloException("Por favor preencha todos os campos corretamente!");
		            } else {
		                servicosJDBC.criarServico(servico);
		                JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
		            }
		        } catch (NuloException e) {
		            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }

		    public void buscarserviço(JTextField txtid) {
		    	 try {
		    		 int idUsuario = Integer.parseInt(txtid.getText());
					 Servicos s1 = servicosJDBC.buscarServicos(idUsuario);
					   if(s1 == null) {
						   JOptionPane.showMessageDialog(null, "Serviço nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
					   }
					   else {
						   JOptionPane.showMessageDialog(null, s1);
					   }
					 
					} catch (NumberFormatException e) {
					    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
		     }
		    
		    public void deletarservico(JTextField txtid) {
		    	 
		    	 try {
		    		 int idUsuario = Integer.parseInt(txtid.getText());
					   servicosJDBC.deletarServico(idUsuario);
					
					 
					} catch (NumberFormatException e) {
					    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
		       
		     }
		    
		    public boolean verificarID(JTextField txtid) {
		    	 try {
		    		 int idUsuario = Integer.parseInt(txtid.getText());
					  Servicos p1 = servicosJDBC.buscarServicos(idUsuario);
					   if(p1 == null) {
						   JOptionPane.showMessageDialog(null, "Serviço nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
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
		    

		    public void AtualizarServico(JTextField txtid) throws NuloException {
		    	int idUsuario = Integer.parseInt(txtid.getText());
		        JFrame frame = new JFrame("Formulário de Atualização de Cadastro de Serviço");
		        frame.setSize(300, 160);
		        frame.setLocationRelativeTo(null);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		        JPanel panel = new JPanel(new BorderLayout());
		        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		        formPanel.setBackground(new Color(160, 160, 160));

		        JLabel nomeLabel = new JLabel("Nome:");
		        nomeLabel.setForeground(Color.BLACK);
		        JTextField txtNome = new JTextField();
		        formPanel.add(nomeLabel);
		        formPanel.add(txtNome);

		        JLabel duracaoLabel = new JLabel("Duração (minutos):");
		        duracaoLabel.setForeground(Color.BLACK);
		        JTextField txtDuracao = new JTextField();
		        formPanel.add(duracaoLabel);
		        formPanel.add(txtDuracao);

		        JLabel precoLabel = new JLabel("Preço:");
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

		        btnSubmit.addActionListener((ActionEvent e) -> {
		            String nome = txtNome.getText();
		            int duracao = Integer.parseInt(txtDuracao.getText());
		            double preco = Double.parseDouble(txtPreco.getText());
		            
		            Servicos servico = new Servicos(nome, duracao, preco);
		            servico.setServicoID(idUsuario);

		            servicosJDBC.atualizarServico(servico);
		            JOptionPane.showMessageDialog(null, "Dados do serviço atualizado com sucesso!");

		            txtNome.setText("");
		            txtDuracao.setText("");
		            txtPreco.setText("");
		        });

		        frame.add(panel); 
		        frame.setVisible(true); 
		    }
		    
	}
