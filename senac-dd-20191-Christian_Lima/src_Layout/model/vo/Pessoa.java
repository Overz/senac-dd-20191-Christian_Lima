package model.vo;

import java.util.Date;

public abstract class Pessoa {
	
	private int id;
	private String nome;
	private String sobrenome;
	private String cpf_cnpj;
	private Date dtNascimento;
	private Endereco endereco;
	private int numero;
	private String complemento;
	
	public Pessoa(int id, String nome, String sobrenome, String cpf_cnpj, Date dtNascimento, Endereco endereco,
			int numero, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf_cnpj = cpf_cnpj;
		this.dtNascimento = dtNascimento;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "Dados: "
				+ "\nID: " + this.getId()
				+ "\nNome: " + getNome() + " " + getSobrenome()
				+ "\nCPF/CNPJ: " + this.getCpf_cnpj()
				+ "\nData de Nascimento: " + this.getDtNascimento()
				+ "\nEndereço: " + this.getEndereco().toString()
				+ "\nNº: " + this.getNumero()
				+ "\nComplemento: " + this.getComplemento();
	}

}
