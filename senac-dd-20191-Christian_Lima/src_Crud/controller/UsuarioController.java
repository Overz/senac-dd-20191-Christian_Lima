package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	//A camada de controle deve validar o preenchimento dos campos, ou seja, 
	//s� pode chamar um BO caso TODOS os dados estejam preenchidos na tela (n�o-vazios e n�o-nulos).

	// VALIDA��O PARA SALVAR NO BANCO
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
				mensagem = "Senha e Confirma��o de Senha s�o diferentes, por favor, digite iguais!";
			}
			mensagem = "Por favor, digite a Senha!";
		}

		if (nivel == null) {
			mensagem = "Por Favor, Escolha um n�vel v�lido!";
		}

		if(mensagem.isEmpty()) {
			UsuarioVO userVO = new UsuarioVO(nome, email, senha, nivel);

			UsuarioBO userBO = new UsuarioBO();
			mensagem = userBO.validarCadastro(userVO);
		}

		return mensagem;
	}

	/**
	 * M�todo que verifica campos validos/invalidos, se true
	 * manda para o BO verificando permiss�o para exclus�o de usuarios
	 * @param email
	 * @param senha
	 * @param senhaConfirmacao
	 * @param nivelSelecionado
	 * @return mensagem
	 */
	public String consultarPermissao (String email, String senha, String senhaConfirmacao) {
		String mensagem = "";

		if (email == null || email.isEmpty()) {
			mensagem = "Por favor, Digite o email do usuario a ser excluido!";
		}

		if (senha == null || senha.isEmpty()) {
			mensagem = "Por favor, Digite a Senha!";
			if (senhaConfirmacao == null || senhaConfirmacao.trim().isEmpty()) {
				if (!senha.equals(senhaConfirmacao)) {
					mensagem = "Senha e Confirma��o de Senha s�o diferentes, por favor, digite Senhas iguais!";
				}
			}
		}

		if (mensagem == null || mensagem.isEmpty()) {
			UsuarioBO userBO = new UsuarioBO();
			mensagem = userBO.verificarPermissao(email, senha);
		}

		return mensagem;
	}

	/**
	 * M�todo que verifica possivel exclus�o do usuario no banco.
	 * @param nome
	 * @param email
	 * @return
	 */
	public String excluir(UsuarioVO userVO) {
		String mensagem = "";
		UsuarioBO userBO = new UsuarioBO();
		
		if (userVO == null) {
			mensagem = "Por Favor, Selecione um Usuario a Excluir!";
		}
		
		if (mensagem == null || mensagem.isEmpty()) {
			userBO.excluir(userVO);
		}

		return mensagem;
	}

	public ArrayList<UsuarioVO> consultarTodos() {
		UsuarioBO userBO = new UsuarioBO();
		return userBO.consultarTodos();
	}

	public ArrayList<UsuarioVO> consultarUsuariosPorNome(String nome) {
		UsuarioBO userBO = new UsuarioBO();
		return userBO.consultarUsuariosPorNome(nome);
	}

	public ArrayList<UsuarioVO> controllerConsultarPorNivel(NivelVO nivelSelecionado) {
		UsuarioBO userBO = new UsuarioBO();
		return userBO.consultarUsuarioPorNivelBO(nivelSelecionado);
	}
}