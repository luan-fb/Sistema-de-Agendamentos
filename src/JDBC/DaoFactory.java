package JDBC;

public class DaoFactory {

	public static ClientesJDBC criaClienteJDBC() {
		return new ClientesJDBC(bd.DB.getConnection());
	}
	
	public static ServicosJDBC criaServicosJDBC() {
		return new ServicosJDBC(bd.DB.getConnection());
	}
	
	public static ProfissionaisJDBC criaProfissionaisJDBC() {
		return new ProfissionaisJDBC(bd.DB.getConnection());
	}
	
	public static AgendamentosJDBC criaAgendamentosJDBC() {
		return new AgendamentosJDBC(bd.DB.getConnection());
	}
	
}
