package paginacao.model.dto;

/**
 * Classe que representa um objeto de transfêrencia de dados 
 * (Data Transfer Object - DTO) de Produto (Prato + Bebida).
 * 
 * @author Adriano de Melo
 *
 */
public class ProdutoDTO {
	
	private int idProduto;
	private String nome;
	
	public ProdutoDTO(int idProduto, String nome) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
	}

	public ProdutoDTO() {
		super();
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void imprimir() {
		System.out.printf("%5d   %-40s   \n", 
		this.getIdProduto(), 
		this.getNome());
	}
}