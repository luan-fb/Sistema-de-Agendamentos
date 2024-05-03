package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.Profissionais;
import exceções.DbException;

public class ProfissionaisJDBC {
	private Connection conn;

	public ProfissionaisJDBC(Connection conn) {
		this.conn = conn;
	}

	public void criarProfissionais(Profissionais profissional) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO profissionais (Nome, Especialidade) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, profissional.getNome());
			st.setString(2, profissional.getEspecialidade());

			int afetados = st.executeUpdate();

			if (afetados > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					profissional.setProfissionalID(id);
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

	public void atualizarProfissionais(Profissionais profissional) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE profissionais " + "SET Nome = ?, Especialidade = ? " + "WHERE ProfissionalID = ?");

			st.setString(1, profissional.getNome());
			st.setString(2, profissional.getEspecialidade());
			st.setInt(3, profissional.getProfissionalID());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
		}

	}

	public void deletarProfissional(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM profissionais WHERE ProfissionalID = ?");

			st.setInt(1, id);
			int num = st.executeUpdate();

			if (num == 0) {
				JOptionPane.showMessageDialog(null, "Nenhum profissional encontrado. ", "Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Profissional removido com sucesso!  ");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			bd.DB.closeStatemnt(st);
		}
	}

	public List<Profissionais> listarProfissionais() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM profissionais");

			rs = st.executeQuery();

			List<Profissionais> profissionais = new ArrayList<>();

			while (rs.next()) {
				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("Nome"));
				profissional.setEspecialidade(rs.getString("Especialidade"));
				profissionais.add(profissional);
			}
			return profissionais;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public Profissionais buscarProfissionais(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM profissionais WHERE ProfissionalID = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("Nome"));
				profissional.setEspecialidade(rs.getString("Especialidade"));

				return profissional;
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
