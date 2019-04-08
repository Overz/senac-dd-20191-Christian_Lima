package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	//A camada de controle deve validar o preenchimento dos campos, ou seja, 
	//só pode chamar um BO caso TODOS os dados estejam preenchidos na tela (não-vazios e não-nulos).


	// VALIDAÇÃO PARA SALVAR NO BANCO
	public String salvar(String nome, String email, String senha,  String senhaConfirmacao, NivelVO nivel) {
		String mensagem = "";

		if (nome == null || nome.trim().isEmpty()) {
			mensagem = "Por Favor, digite o Nome!";	
		}

		if (email == null || email.trim().isEmpty()) {
			mensagem = "Por favor, digite o Email!";
		}

		if (senha == null || senha.trim().isEmpty()) {
			mensagem = "Por favor, digite a Senha!";
		}

		if (senhaConfirmacao == null || senhaConfirmacao.trim().isEmpty()) {
			if (!senha.equals(senhaConfirmacao)) {
				mensagem = "Senha e Confirmação de Senha são diferentes, por favor, digite iguais!";
			}
			mensagem = "Por favor, digite a Senha!";
		}

		if (nivel == null) {
			mensagem = "Por Favor, Escolha um nível válido!";
		}

		if(mensagem.isEmpty()) {
			UsuarioVO userVO = new UsuarioVO(nome, email, senha, nivel);

			UsuarioBO userBO = new UsuarioBO();
			mensagem = userBO.validarCadastro(userVO);
		}

		return mensagem;
	}

	/**
	 * Método que verifica campos validos/invalidos, se true
	 * manda para o BO verificando permissão para exclusão de usuarios
	 * @param email
	 * @param senha
	 * @param senhaConfirmacao
	 * @param nivelSelecionado
	 * @return mensagem
	 */
	public String consultarPermissao (String email, String senha, String senhaConfirmacao, NivelVO nivelSelecionado) {
		UsuarioVO userVO = new UsuarioVO(null, email, senha, nivelSelecionado);
		String mensagem = "";

		if (email == null || email.isEmpty()) {
			mensagem = "Por favor, Digite o email do usuario a ser excluido!";
		}

		if (senha == null || senha.isEmpty()) {
			mensagem = "Por favor, Digite a Senha!";
			if (senhaConfirmacao == null || senhaConfirmacao.trim().isEmpty()) {
				if (!senha.equals(senhaConfirmacao)) {
					mensagem = "Senha e Confirmação de Senha são diferentes, por favor, digite Senhas iguais!";
				}
			}
		}

		if (nivelSelecionado == null) {
			mensagem = "Por favor, Escolha o Nivel!";
		}

		if (mensagem == null || mensagem.isEmpty()) {
			UsuarioBO userBO = new UsuarioBO();
			mensagem = userBO.verificarPermissao(userVO, email, senha);
		}
		

		return mensagem;
	}

	/**
	 * Método que verifica possivel exclusão do usuario no banco.
	 * @param nome
	 * @param email
	 * @return
	 */
	public String excluir(String nome, String email) {
		String mensagem = "";
		UsuarioBO userBO = new UsuarioBO();
		
		if (email == null || email.isEmpty()) {
			mensagem = "Por favor, Digite o email do usuario a ser excluido!";
		}

		if (nome == null || nome.isEmpty()) {
			mensagem = "Por favor, Digite o nome do usuario a ser excluido!";
		}
		if (mensagem == null || mensagem.isEmpty()) {
			userBO.excluir(nome, email);
		}

		return mensagem;
	}

	/**
	 * Método para consultar nome, chamando método auxiliar 'controllerVerificarNomeCorreto'
	 * verificando se o campo Nome esta preenchido, caso sim, prossegue com a busca no banco.
	 * @param nome a ser consultado
	 * @return userVO Usuario completo, com id, nome e email
	 */
	public UsuarioVO controllerConsultarPorNome(String nome){
		UsuarioVO userVO = null;
		UsuarioBO userBO = new UsuarioBO();

		controllerVerificarNomeCorreto(nome);
		userVO = userBO.consultarPorNomeBO(nome);

		return userVO;
	}
	/**
	 * Método auxiliar de 'controllerVerificarNomeCorreto', retornando uma mensagem caso esteja vazio o campo nome;
	 * @param nome
	 * @return mensagem
	 */
	private String controllerVerificarNomeCorreto(String nome) {
		String mensagem = "";

		if (nome == null || nome.isEmpty()) {
			mensagem = "Por favor, Digite o Nome!";
		}
		return mensagem;
	}

	public ArrayList<UsuarioVO> consultarTodos() {
		UsuarioBO userBO = new UsuarioBO();
		return userBO.consultarTodos();

	}
	
	
	
}