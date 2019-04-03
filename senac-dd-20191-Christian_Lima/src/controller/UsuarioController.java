package controller;

import model.bo.UsuarioBO;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioController {

	//A camada de controle deve validar o preenchimento dos campos, ou seja, 
	//só pode chamar um BO caso TODOS os dados estejam preenchidos na tela (não-vazios e não-nulos).
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
				mensagem = "Senha e Confirmação de Senha são diferentes, por favor, digite iguais!";
			}
			mensagem = "Por favor, preencha a Senha!";
		}

		if (nivel == null) {
			mensagem = "Por Favor, Escolha um nível válido!";
		}

		if(mensagem.isEmpty()) {
			UsuarioVO userVO = new UsuarioVO(nome, email, senha, senhaConfirmacao, nivel);

			UsuarioBO bo = new UsuarioBO();
			mensagem = bo.validarSalvar(userVO);
		}

		return mensagem;
	}
	
	public String excluir ( String email, String senha, int id) {
		UsuarioVO userVO = new UsuarioVO();
		String mensagem = "";
		
		if (email == null || email.isEmpty()) {
			mensagem = "Por favor, Digite o email do usuario a ser excluido!";
		}
		
		if (id <= 0) {
			mensagem = "Por favor, Digite um ID Valido!"; 
		}
		
		if (userVO.getNivel() == null || userVO.getNivel().getId() == 0) {
			mensagem = "Por favor, Escolha o Nivel a ser de Usuario que deseja Excluir!";
		}
		
		if (mensagem == null || mensagem.isEmpty()) {
			
			UsuarioBO userBO = new UsuarioBO();
			mensagem = userBO.excluir(userVO, email, senha, id);
			
			//passar para o B.O com validações
		}
		
		return mensagem;
	}
}