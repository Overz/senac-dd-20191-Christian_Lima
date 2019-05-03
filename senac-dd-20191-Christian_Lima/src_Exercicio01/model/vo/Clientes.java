package model.vo;

import java.util.Date;

public abstract class Clientes {

	private String nome;
	private Date dataNascimento;
	private Cidade cidade;
	private double saldo;
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public double getSaldo() {
		return saldo;
	}
}
