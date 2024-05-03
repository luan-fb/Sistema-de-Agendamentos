package Entidades;

public class Clientes {

	private Integer ClienteID;
	private String name;
	private String Telefone;
	private String email;
	
	
	public Clientes() {
		
	}

	public Clientes(String name, String telefone, String email) {
		this.name = name;
		Telefone = telefone;
		this.email = email;
	}

	public Integer getClienteID() {
		return ClienteID;
	}

	public void setClienteID(Integer clienteID) {
		ClienteID = clienteID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "\nCLIENTE: " + name + "\nID: " + ClienteID + "\nTELEFONE: " + Telefone + "\nEMAIL: " + email;
	}
	
	
	
	
	
	
}
