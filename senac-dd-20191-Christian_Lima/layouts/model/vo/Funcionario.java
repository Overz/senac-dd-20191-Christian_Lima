package layouts.model.vo;

import java.util.Date;

public abstract class Funcionario extends Pessoa{
	
	private String setor;
	private String numeroContrato;
	private double salario;
	
	public Funcionario(int id, String nome, String sobrenome, String cpf_cnpj, Date dtNascimento, Endereco endereco,
			int numero, String complemento, String setor, String numeroContrato, double salario) {
		super(id, nome, sobrenome, cpf_cnpj, dtNascimento, endereco, numero, complemento);
		this.setor = setor;
		this.numeroContrato = numeroContrato;
		this.salario = salario;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Funcionario: "
				+ "\nSetor:" + this.getSetor() + ", Numero Contrato:" + this.getNumeroContrato()
				+ ", Salario: " + this.getSalario();
	}
	
	
	
}
