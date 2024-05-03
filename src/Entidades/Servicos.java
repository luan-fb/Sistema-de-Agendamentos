package Entidades;

public class Servicos {
 

	private Integer ServicoID;
	private String Nome;
	private Integer Duracao;
	private Double preco;
	
	public Servicos() {
	}
	
	public Servicos(String nome, int duracao, Double preco) {
		Nome = nome;
		Duracao = duracao;
		this.preco = preco;
	}

	public Integer getServicoID() {
		return ServicoID;
	}

	public void setServicoID(Integer servicoID) {
		ServicoID = servicoID;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Integer getDuracao() {
		return Duracao;
	}

	public void setDuracao(Integer duracao) {
		Duracao = duracao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "ServicoID: " + ServicoID + "\n Nome: " + Nome + "\n Duracao: " + Duracao +" Minutos" + "\n Preço: " + preco + " R$";
	}





	
	
}
