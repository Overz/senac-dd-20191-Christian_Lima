package exercicio01.model.vo;

public class PessoaJuridica  extends Clientes {
	
	private String cnpj;

	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public double getSaldo() {
		// TODO Auto-generated method stub
		return super.getSaldo();
	}
	
	
}
