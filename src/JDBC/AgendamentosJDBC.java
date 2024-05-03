package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.Agendamentos;
import Entidades.Clientes;
import Entidades.Profissionais;
import Entidades.Servicos;
import exceções.DbException;

public class AgendamentosJDBC {

	private Connection conn;

	public AgendamentosJDBC() {

	}

	public AgendamentosJDBC(Connection conn) {
		this.conn = conn;
	}

	public void criarAgendamento(Agendamentos agendamento) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO agendamentos (Dia, HORA, ClienteID, ServicoID, ProfissionalID) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setDate(1, new java.sql.Date(agendamento.getDia().getTime()));
			st.setTime(2, new java.sql.Time(agendamento.getHora().getTime()));
			st.setInt(3, agendamento.getCliente().getClienteID());
			st.setInt(4, agendamento.getServico().getServicoID());
			st.setInt(5, agendamento.getProfissional().getProfissionalID());

			int afetados = st.executeUpdate();

			if (afetados > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					agendamento.setAgendamentoID(id);
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

	public void atualizarAgendamentos(Agendamentos agendamento) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE agendamentos " + "SET Dia = ?, HORA = ?, ClienteID = ?, ServicoID = ?, ProfissionalID = ? "
							+ "WHERE AgendamentoID = ?");

			st.setDate(1, new java.sql.Date(agendamento.getDia().getTime()));
			st.setTime(2, new java.sql.Time(agendamento.getHora().getTime()));
			st.setInt(3, agendamento.getCliente().getClienteID());
			st.setInt(4, agendamento.getServico().getServicoID());
			st.setInt(5, agendamento.getProfissional().getProfissionalID());
			st.setInt(6, agendamento.getAgendamentoID());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
		}

	}

	public void deletarAgendamento(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM agendamentos WHERE AgendamentoID = ?");

			st.setInt(1, id);
			int num = st.executeUpdate();

			if (num == 0) {
				JOptionPane.showMessageDialog(null, "Nenhum Agendamento encontrado. ", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Agendamento removido com sucesso!  ");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			bd.DB.closeStatemnt(st);
		}
	}

	public List<Agendamentos> listarAgendamentos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// Building SQL query
			String sql = "SELECT a.AgendamentoID, a.Dia, a.HORA, "
					+ "c.ClienteID, c.Nome AS ClienteNome, c.Telefone AS ClienteTelefone, c.Email AS ClienteEmail, "
					+ "s.ServicoID, s.Nome AS ServicoNome, s.Duracao AS ServicoDuracao, s.Preco AS ServicoPreco, "
					+ "p.ProfissionalID, p.Nome AS ProfissionalNome, p.Especialidade AS ProfissionalEspecialidade "
					+ "FROM agendamentos a " + "JOIN clientes c ON a.ClienteID = c.ClienteID "
					+ "JOIN servicos s ON a.ServicoID = s.ServicoID "
					+ "JOIN profissionais p ON a.ProfissionalID = p.ProfissionalID";

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();

			List<Agendamentos> agendamentoList = new ArrayList<>();

			while (rs.next()) {

				Agendamentos agenda = new Agendamentos();
				agenda.setAgendamentoID(rs.getInt("AgendamentoID"));
				agenda.setDia(rs.getDate("Dia"));
				agenda.setHora(rs.getTime("HORA"));

				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				cliente.setName(rs.getString("ClienteNome"));
				cliente.setTelefone(rs.getString("ClienteTelefone"));
				cliente.setEmail(rs.getString("ClienteEmail"));

				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("ServicoNome"));
				servico.setDuracao(rs.getInt("ServicoDuracao"));
				servico.setPreco(rs.getDouble("ServicoPreco"));

				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("ProfissionalNome"));
				profissional.setEspecialidade(rs.getString("ProfissionalEspecialidade"));

				agenda.setCliente(cliente);
				agenda.setServico(servico);
				agenda.setProfissional(profissional);

				agendamentoList.add(agenda);
			}
			return agendamentoList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			// Close resources
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public List<Agendamentos> buscarAgendamentoPorNomeCliente(String nomeCliente) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT a.AgendamentoID, a.Dia, a.HORA, "
					+ "c.ClienteID, c.Nome AS ClienteNome, c.Telefone AS ClienteTelefone, c.Email AS ClienteEmail, "
					+ "s.ServicoID, s.Nome AS ServicoNome, s.Duracao AS ServicoDuracao, s.Preco AS ServicoPreco, "
					+ "p.ProfissionalID, p.Nome AS ProfissionalNome, p.Especialidade AS ProfissionalEspecialidade "
					+ "FROM agendamentos a " + "JOIN clientes c ON a.ClienteID = c.ClienteID "
					+ "JOIN servicos s ON a.ServicoID = s.ServicoID "
					+ "JOIN profissionais p ON a.ProfissionalID = p.ProfissionalID " + "WHERE c.Nome = ?";

			st = conn.prepareStatement(sql);
			st.setString(1, nomeCliente);
			rs = st.executeQuery();

			List<Agendamentos> agendamentoList = new ArrayList<>();
			while (rs.next()) {
				Agendamentos agenda = new Agendamentos();
				agenda.setAgendamentoID(rs.getInt("AgendamentoID"));
				agenda.setDia(rs.getDate("Dia"));
				agenda.setHora(rs.getTime("HORA"));

				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				cliente.setName(rs.getString("ClienteNome"));
				cliente.setTelefone(rs.getString("ClienteTelefone"));
				cliente.setEmail(rs.getString("ClienteEmail"));

				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("ServicoNome"));
				servico.setDuracao(rs.getInt("ServicoDuracao"));
				servico.setPreco(rs.getDouble("ServicoPreco"));

				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("ProfissionalNome"));
				profissional.setEspecialidade(rs.getString("ProfissionalEspecialidade"));

				agenda.setCliente(cliente);
				agenda.setServico(servico);
				agenda.setProfissional(profissional);

				agendamentoList.add(agenda);
			}
			return agendamentoList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public List<Agendamentos> buscarAgendamentoPorData(Date data) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT a.AgendamentoID, a.Dia, a.HORA, "
					+ "c.ClienteID, c.Nome AS ClienteNome, c.Telefone AS ClienteTelefone, c.Email AS ClienteEmail, "
					+ "s.ServicoID, s.Nome AS ServicoNome, s.Duracao AS ServicoDuracao, s.Preco AS ServicoPreco, "
					+ "p.ProfissionalID, p.Nome AS ProfissionalNome, p.Especialidade AS ProfissionalEspecialidade "
					+ "FROM agendamentos a " + "JOIN clientes c ON a.ClienteID = c.ClienteID "
					+ "JOIN servicos s ON a.ServicoID = s.ServicoID "
					+ "JOIN profissionais p ON a.ProfissionalID = p.ProfissionalID " + "WHERE a.Dia = ?";

			java.sql.Date sqlDate = new java.sql.Date(data.getTime());

			st = conn.prepareStatement(sql);
			st.setDate(1, sqlDate);
			rs = st.executeQuery();

			List<Agendamentos> agendamentoList = new ArrayList<>();
			while (rs.next()) {
				Agendamentos agenda = new Agendamentos();
				agenda.setAgendamentoID(rs.getInt("AgendamentoID"));
				agenda.setDia(rs.getDate("Dia"));
				agenda.setHora(rs.getTime("HORA"));

				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				cliente.setName(rs.getString("ClienteNome"));
				cliente.setTelefone(rs.getString("ClienteTelefone"));
				cliente.setEmail(rs.getString("ClienteEmail"));

				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("ServicoNome"));
				servico.setDuracao(rs.getInt("ServicoDuracao"));
				servico.setPreco(rs.getDouble("ServicoPreco"));

				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("ProfissionalNome"));
				profissional.setEspecialidade(rs.getString("ProfissionalEspecialidade"));

				agenda.setCliente(cliente);
				agenda.setServico(servico);
				agenda.setProfissional(profissional);

				agendamentoList.add(agenda);
			}
			return agendamentoList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public Agendamentos buscarAgendamentoID(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT a.AgendamentoID, a.Dia, a.HORA, "
					+ " c.ClienteID, c.Nome AS ClienteNome, c.Telefone AS ClienteTelefone, c.Email AS ClienteEmail, "
					+ " s.ServicoID, s.Nome AS ServicoNome, s.Duracao AS ServicoDuracao, s.Preco AS ServicoPreco, "
					+ " p.ProfissionalID, p.Nome AS ProfissionalNome, p.Especialidade AS ProfissionalEspecialidade "
					+ "FROM agendamentos a "
					+ "INNER JOIN clientes c ON a.ClienteID = c.ClienteID "
					+ "INNER JOIN servicos s ON a.ServicoID = s.ServicoID "
					+ "INNER JOIN profissionais p ON a.ProfissionalID = p.ProfissionalID "
					+ "WHERE a.AgendamentoID = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Agendamentos agendamento = new Agendamentos();
				agendamento.setAgendamentoID(rs.getInt("AgendamentoID"));
				agendamento.setDia(rs.getDate("Dia"));
				agendamento.setHora(rs.getTime("Hora"));

				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				cliente.setName(rs.getString("ClienteNome"));
				cliente.setTelefone(rs.getString("ClienteTelefone"));
				cliente.setEmail(rs.getString("ClienteEmail"));

				Servicos servico = new Servicos();
				servico.setServicoID(rs.getInt("ServicoID"));
				servico.setNome(rs.getString("ServicoNome"));
				servico.setDuracao(rs.getInt("ServicoDuracao"));
				servico.setPreco(rs.getDouble("ServicoPreco"));

				Profissionais profissional = new Profissionais();
				profissional.setProfissionalID(rs.getInt("ProfissionalID"));
				profissional.setNome(rs.getString("ProfissionalNome"));
				profissional.setEspecialidade(rs.getString("ProfissionalEspecialidade"));

				agendamento.setCliente(cliente);
				agendamento.setServico(servico);
				agendamento.setProfissional(profissional);

				return agendamento;
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
