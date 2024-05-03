package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.Clientes;
import exceções.DbException;

public class ClientesJDBC {

	private Connection conn;

	public ClientesJDBC() {
		
	}
	
	public ClientesJDBC(Connection conn) {
		this.conn = conn;
	}

	public void criarCliente(Clientes cliente) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO clientes (Nome, Telefone, Email) VALUES (?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);

			st.setString(1, cliente.getName());
			st.setString(2, cliente.getTelefone());
			st.setString(3, cliente.getEmail());
			int afetados = st.executeUpdate();

			if (afetados > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					cliente.setClienteID(id);
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

	public void atualizarCliente(Clientes cliente) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE clientes " + "SET Nome = ?, Telefone = ?, Email = ? " + "WHERE ClienteID = ?");

			st.setString(1, cliente.getName());
			st.setString(2, cliente.getTelefone());
			st.setString(3, cliente.getEmail());
			st.setInt(4, cliente.getClienteID());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
		}

	}

	public void deletarCliente(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM clientes WHERE ClienteID = ?");

			st.setInt(1, id);
			int num = st.executeUpdate();
			
			if(num == 0) {
				 JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado. ", "Erro", JOptionPane.ERROR_MESSAGE);
			}
			else {
				 JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!  ");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			bd.DB.closeStatemnt(st);
		}
	}

	public List<Clientes> listarClientes() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM clientes");

			rs = st.executeQuery();

			List<Clientes> clientes = new ArrayList<>();

			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				;
				cliente.setName(rs.getString("Nome"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setEmail(rs.getString("Email"));
				clientes.add(cliente);

			}
			return clientes;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			bd.DB.closeStatemnt(st);
			bd.DB.closeResultSet(rs);
		}
	}

	public Clientes buscarCliente(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Clientes WHERE ClienteID = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Clientes cliente = new Clientes();
				cliente.setClienteID(rs.getInt("ClienteID"));
				cliente.setName(rs.getString("Nome"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setEmail(rs.getString("Email"));
				
                 return cliente;
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