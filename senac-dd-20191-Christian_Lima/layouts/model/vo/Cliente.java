package layouts.model.vo;

import java.util.Date;

public abstract class Cliente extends Pessoa{

	private String razaoSocial;

	public Cliente(int id, String nome, String sobrenome, String cpf_cnpj, Date dtNascimento, Endereco endereco,
			int numero, String complemento, String razaoSocial) {
		super(id, nome, sobrenome, cpf_cnpj, dtNascimento, endereco, numero, complemento);
		this.razaoSocial = razaoSocial;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Override
	public String toString() {
		return "Clientes: "
				+ "Razão Social " + this.getRazaoSocial();
	}

	

}
