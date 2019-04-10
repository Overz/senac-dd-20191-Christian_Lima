package model.vo;

public class UsuarioVO {
	
	private int id;
	private String email;
	private String nome;
	private String senha;
	private NivelVO nivelVO;
	
	
	public UsuarioVO(String nome, String email, String senha, NivelVO nivelVO) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nivelVO = nivelVO;
	}
	
	public UsuarioVO() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NivelVO getNivelVO() {
		return nivelVO;
	}

	public void setNivelVO(NivelVO nivelVO) {
		this.nivelVO = nivelVO;
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

	public NivelVO getNivel() {
		return nivelVO;
	}


	public void setNivel(NivelVO nivelVO) {
		this.nivelVO = nivelVO;
	}


	@Override
	public String toString() {
		return "Confirmação de Cadastro: "
				+ "\nID:" + this.getId()
				+ "\nEmail:" + this.getEmail() 
				+ "\nNome:" + this.getNome() 
				+ "\nSenha:" + this.getSenha()
				+  this.getNivel().toString();
	}

}
