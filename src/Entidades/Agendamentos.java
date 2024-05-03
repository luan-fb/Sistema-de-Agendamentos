package Entidades;


import java.util.Date;

public class Agendamentos {

	private Integer AgendamentoID;
	private Date Dia;
	private Date hora;
	private Clientes cliente;
    private Servicos servico;
    private Profissionais profissional;
    
    
    public Agendamentos() {
    	
    }


	public Agendamentos(Date dia, Date hora, Clientes cliente, Servicos servico,
			Profissionais profissional) {
		Dia = dia;
		this.hora = hora;
		this.cliente = cliente;
		this.servico = servico;
		this.profissional = profissional;
	}


	public Integer getAgendamentoID() {
		return AgendamentoID;
	}


	public void setAgendamentoID(Integer agendamentoID) {
		AgendamentoID = agendamentoID;
	}


	public Date getDia() {
		return Dia;
	}


	public void setDia(Date dia) {
		Dia = dia;
	}


	public Date getHora() {
		return hora;
	}


	public void setHora(Date hora) {
		this.hora = hora;
	}


	public Clientes getCliente() {
		return cliente;
	}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}


	public Servicos getServico() {
		return servico;
	}


	public void setServico(Servicos servico) {
		this.servico = servico;
	}


	public Profissionais getProfissional() {
		return profissional;
	}


	public void setProfissional(Profissionais profissional) {
		this.profissional = profissional;
	}


	@Override
	public String toString() {
		return "ID: " +  AgendamentoID + "\nDia: " + Dia + "\nHorario agendado: "  
	+ hora + "\n" + "\nNome Cliente: " + cliente.getName() + "\nServiço: " 
	+ servico.getNome() + "\nPreço: " + servico.getPreco() + "\nProfissional: " + profissional.getNome();
	}
    
	
	
	
}
