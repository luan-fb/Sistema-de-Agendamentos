package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.Servicos;
import exceções.DbException;

public class ServicosJDBC {

	private Connection conn;

	public ServicosJDBC(Connection conn) {
		this.conn = conn;
	}

	public void criarServico(Servicos servico) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO servicos (Nome, Duracao, Preco) VALUES (?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);

			st.setString(1, servico.getNome());
			st.setInt(2, servico.getDuracao());
			st.setDouble(3, servico.getPreco());
			
			int afetados = st.executeUpdate();

			if (afetados > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					servico.setServicoID(id);
				} else {
					throw new DbException("Erro Inesperado! Nenhuma linha afetada");
				}

				bd.DB.closeResultSet(rs);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
		}
	}

	public void atualizarServico(Servicos servico) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE servicos " + "SET Nome = ?, Duracao = ?, Preco = ? " + "WHERE ServicoID = ?");

			st.setString(1, servico.getNome());
			st.setInt(2, servico.getDuracao());
			st.setDouble(3, servico.getPreco());
			st.setInt(4, servico.getServicoID());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
		}

	}

	public void deletarServico(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM servicos WHERE ServicoID = ?");

			st.setInt(1, id);
			int num = st.executeUpdate();
			
			if(num == 0) {
				 JOptionPane.showMessageDialog(null, "Nenhum Serviço encontrado. ", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			else {
				 JOptionPane.showMessageDialog(null, "Serviço removido com sucesso!  ");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			bd.DB.closeStatemnt(st);
		}
	}

	public List<Servicos> listarServicos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM servicos");

			rs = st.executeQuery();

			List<Servicos> servicos = new ArrayList<>();

			while (rs.next()) {
				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("Nome"));
				servico.setDuracao(rs.getInt("Duracao"));
				servico.setPreco(rs.getDouble("Preco"));
				servicos.add(servico);
			}
			return servicos;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public Servicos buscarServicos(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM servicos WHERE ServicoID = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("Nome"));
				servico.setDuracao(rs.getInt("Duracao"));
				servico.setPreco(rs.getDouble("Preco"));
				
                 return servico;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
             
	}

	
	
	
}
