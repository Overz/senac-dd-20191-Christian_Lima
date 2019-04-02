package controller;

import model.bo.UsuarioBO;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	//A camada de controle deve validar o preenchimento dos campos, ou seja, 
	//s� pode chamar um BO caso TODOS os dados estejam preenchidos na tela (n�o-vazios e n�o-nulos).
	public String salvar(String nome, String email, String senha,  String senhaConfirmacao, NivelVO nivel) {
		String mensagem = "";
		//NivelVO nivelVO = new NivelVO();

		if (nome == null || nome.trim().isEmpty()) {
			mensagem = "Por Favor, preencha Nome!";	
		}

		if (email == null || email.trim().isEmpty()) {
			mensagem = "Por favor, preencha o Email!";
		}

		if (senha == null || senha.trim().isEmpty()) {
			mensagem = "Por favor, preencha a Senha!";
		}

		if (senhaConfirmacao == null || senhaConfirmacao.trim().isEmpty()) {
			if (!senha.equals(senhaConfirmacao)) {
				mensagem = "Senha e Confirma��o de Senha s�o diferentes, por favor, digite iguais!";
			}
			mensagem = "Por favor, preencha a Senha!";
		}

		if (nivel == null) {
			mensagem = "Por Favor, Escolha um n�vel v�lido!";
		}

		if(mensagem.isEmpty()) {
			UsuarioVO user = new UsuarioVO(nome, email, senha, senhaConfirmacao, nivel);

			UsuarioBO bo = new UsuarioBO();
			mensagem = bo.validarSalvar(user);
		}

		return mensagem;
	}
}