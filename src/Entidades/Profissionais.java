package Entidades;

public class Profissionais {

	private Integer ProfissionalID;
	private String nome;
	private String Especialidade;
	
	public Profissionais() {
		
	}
	
	
	public Profissionais(String nome, String especialidade) {
		this.nome = nome;
		Especialidade = especialidade;
	}



	public Integer getProfissionalID() {
		return ProfissionalID;
	}

	public void setProfissionalID(Integer profissionalID) {
		ProfissionalID = profissionalID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return Especialidade;
	}

	public void setEspecialidade(String especialidade) {
		Especialidade = especialidade;
	}


	@Override
	public String toString() {
		return "\nID: " + ProfissionalID + "\nProfissional: " + nome + "\nEspecialidade: " + Especialidade;
	}
	
	
	
	
}
