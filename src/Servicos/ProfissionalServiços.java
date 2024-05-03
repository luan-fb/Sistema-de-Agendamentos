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
import JDBC.DaoFactory;
import JDBC.ProfissionaisJDBC;
import exceções.NuloException;

public class ProfissionalServiços {

	 ProfissionaisJDBC profissionais = DaoFactory.criaProfissionaisJDBC();
	       
	 public void CriarProfissionais(Profissionais profissional) throws NuloException  {
    	 try {
    		    if (profissional.getNome().isEmpty() || profissional.getEspecialidade().isEmpty() ) {
    		        throw new NuloException("Por favor preencha todos os campos !");
    		    } else {
    		       profissionais.criarProfissionais(profissional);
    		        JOptionPane.showMessageDialog(null, "Profissional cadastrado com sucesso!");
    		    }
    		} catch (NuloException e) {
    		    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    		}
     }
	 
	 
	 public boolean verificarID(JTextField txtid) {
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			  Profissionais p1 = profissionais.buscarProfissionais(idUsuario);
			   if(p1 == null) {
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
	 
	 
	 public void buscarserviço(JTextField txtid) {
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			  Profissionais p1 = profissionais.buscarProfissionais(idUsuario);
			   if(p1 == null) {
				   JOptionPane.showMessageDialog(null, "Profissional nao encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
			   }
			   else {
				   JOptionPane.showMessageDialog(null, p1);
			   }
			 
			} catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
     }
	 
public void deletarprofissionais(JTextField txtid) {
    	 
    	 try {
    		 int idUsuario = Integer.parseInt(txtid.getText());
			   profissionais.deletarProfissional(idUsuario);
			
			 
			} catch (NumberFormatException e) {
			    JOptionPane.showMessageDialog(null, "Por favor, insira um número inteiro válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
       
     }

public void AtualizarProfissional(JTextField txtid) throws NuloException {
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

		JLabel Especialidade = new JLabel("Email:");
		Especialidade.setForeground(Color.BLACK);
		JTextField txtEspecialidade = new JTextField();
		formPanel.add(Especialidade);
		formPanel.add(txtEspecialidade);

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
			String especialidade = txtEspecialidade.getText();
			

			Profissionais profissional = new Profissionais(nome,especialidade);
			profissional.setProfissionalID(idUsuario);
			
				profissionais.atualizarProfissionais(profissional);
				 JOptionPane.showMessageDialog(null, "Dados do profissional atualizado com sucesso! ");

			txtNome.setText("");
			txtEspecialidade.setText("");
			
		});
		frame.add(panel); 
		frame.setVisible(true); 
}
	
}
