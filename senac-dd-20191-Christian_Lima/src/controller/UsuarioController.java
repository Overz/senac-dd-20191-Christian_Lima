package controller;

import java.util.ArrayList;

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

			UsuarioBO bo = new UsuarioBO();
			mensagem = bo.validarCadastro(userVO);
		}

		return mensagem;
	}

	// VERIFICAÇÂO DE PERMISSÃO
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

	// VVERIFICAÇÂO PARA EXCLUSAO DO BANCO
	public String excluir(String nome, String email) {
		String mensagem = "";

		if (nome == null || nome.isEmpty()) {
			mensagem = "Por favor, Digite o Nome do usuario a ser excluido!!";
		}

		if (email == null || email.isEmpty()) {
			mensagem = "Por favor, Digite o email do usuario a ser excluido!";
		}

		if (mensagem == null || mensagem.isEmpty()) {
			UsuarioVO userVO = new UsuarioVO();
			UsuarioBO userBO = new UsuarioBO();
			userBO.excluir(userVO);
		}

		return mensagem;
	}

	/**
	 * Verifica se o objeto está null, caso esteja, chama outro metodo para retornar uma mensagem;
	 * @param nome
	 * @return userVO
	 */
	public UsuarioVO controllerConsultarPorNome(String nome){
		UsuarioVO userVO = null;
		UsuarioBO userBO = new UsuarioBO();

		controllerVerificarNomeCorreto(nome);

		userVO = userBO.consultarPorNomeBO(nome);

		return userVO;
	}
	/**
	 * Método auxiliar de 'consultarPorNome', retornando uma mensagem;
	 * @param nome
	 * @return
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