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

import Entidades.Clientes;
import JDBC.ClientesJDBC;
import JDBC.DaoFactory;
import exceções.NuloException;

public class ClienteServiços {

	ClientesJDBC clientes = DaoFactory.criaClienteJDBC();
	
	
     public void CriarClientes(Clientes cliente) throws NuloException  {
    	 try {
    		    if (cliente.getName().isEmpty() || cliente.getEmail().isEmpty() || cliente.getTelefone().isEmpty()) {
    		        throw new NuloException("Por favor preencha todos os campos do cliente!");
    		    } else {
    		        clientes.criarCliente(cliente);
    		        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
    		    }
    		} catch (NuloException e) {
    		    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
     }
     
     public void deletarcliente(JTextField txtid) {
    	 
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			   clientes.deletarCliente(idUsuario);
			
			 
			} catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
       
     }
     
     public void buscarcliente(JTextField txtid) {
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			  Clientes c1 =  clientes.buscarCliente(idUsuario);
			   if(c1 == null) {
				   JOptionPane.showMessageDialog(null, "Cliente nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
			   }
			   else {
				   JOptionPane.showMessageDialog(null, c1);
			   }
			 
			} catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
     }
     
     public boolean verificarID(JTextField txtid) {
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			  Clientes c1 =  clientes.buscarCliente(idUsuario);
			   if(c1 == null) {
				   JOptionPane.showMessageDialog(null, "Cliente nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
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
     
     public void AtualizarCliente(JTextField txtid) throws NuloException {
    	 int idUsuario = Integer.parseInt(txtid.getText());
    	 JFrame frame = new JFrame("Formulário de Atualizaçao de Cadastro ");
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
			JButton btnsSubmit = new JButton("Enviar");
			buttonPanel.add(btnsSubmit);
			panel.add(buttonPanel, BorderLayout.CENTER);

			btnsSubmit.addActionListener((ActionEvent o) -> {
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String telefone = txtTelefone.getText();

				Clientes cliente = new Clientes(nome, telefone, email);
				cliente.setClienteID(idUsuario);
				
					clientes.atualizarCliente(cliente);
					 JOptionPane.showMessageDialog(null, "Dados do cliente atualizado com sucesso! ");

				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
			});

			
			frame.add(panel); 
			frame.setVisible(true); 
     }
     
}
