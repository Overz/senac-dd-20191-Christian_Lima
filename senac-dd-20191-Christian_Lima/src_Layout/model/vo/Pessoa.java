package model.vo;

import java.util.Date;

public abstract class Pessoa {
	
	private int id;
	private String nome;
	private String Sobrenome;
	private String cpf_cnpj;
	private Date dtNascimento;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String complemento;
	
	public Pessoa(int id, String nome, String sobrenome, String cpf_cnpj, Date dtNascimento, String cidade,
			String bairro, String rua, int numero, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
		Sobrenome = sobrenome;
		this.cpf_cnpj = cpf_cnpj;
		this.dtNascimento = dtNascimento;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
	}
	
	public Pessoa() {
		super();
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
		return Sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
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
				+ "\nCidade: " + this.getCidade()
				+ "\nBairro: " + this.getBairro()
				+ "\nRua: " + this.getRua()
				+ "\nNº: " + this.getNumero()
				+ "\nComplemento: " + this.getComplemento();
	}
}
