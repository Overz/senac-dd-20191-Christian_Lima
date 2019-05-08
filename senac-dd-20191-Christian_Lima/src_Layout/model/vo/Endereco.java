package model.vo;

public class Endereco {

	private int id;
	private String cidade;
	private String bairro;
	private String rua;
	
	public Endereco(int id, String cidade, String bairro, String rua) {
		super();
		this.id = id;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + "]";
	}
	
}