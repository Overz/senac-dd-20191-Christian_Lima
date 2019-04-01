package model.vo;

public class UsuarioVO {
	
	private String email;
	private String nome;
	private String senha;
	private String senhaConfirmacao;
	private NivelVO nivelVO;
	
	
	public UsuarioVO(String nome, String email, String senha, String senhaConfirmacao, NivelVO nivelVO) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.senhaConfirmacao = senhaConfirmacao;
		this.nivelVO = nivelVO;
	}
	
	public UsuarioVO() {
		super();
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public NivelVO getNivel() {
		return nivelVO;
	}


	public void setNivel(NivelVO nivelVO) {
		this.nivelVO = nivelVO;
	}


	@Override
	public String toString() {
		return "Confirmação de Cadastro: "
				+ "\nEmail:" + this.getEmail() 
				+ "\nNome:" + this.getNome() 
				+ "\nSenha:" + this.getSenha()
				+ "\nConfirmação de Senha: " + this.getSenhaConfirmacao()
				+  this.getNivel().toString();
	}

}
