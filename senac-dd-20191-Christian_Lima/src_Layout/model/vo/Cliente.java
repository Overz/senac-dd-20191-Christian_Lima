package model.vo;

import java.util.Date;

public abstract class Cliente extends Pessoa{

	private String razaoSocial;

	public Cliente(int id,
				String nome,
				String sobrenome,
				String cpf_cnpj,
				Date dtNascimento,
				String cidade,
				String bairro,
				String rua,
				int numero,
				String complemento,
				String razaoSocial) {
		super(id, nome, sobrenome, cpf_cnpj, dtNascimento, cidade, bairro, rua, numero, complemento);
		
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
