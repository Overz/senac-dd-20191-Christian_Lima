package crud.model.bo;

import java.util.ArrayList;

import crud.model.dao.UsuarioDAO;
import crud.model.vo.NivelVO;
import crud.model.vo.UsuarioVO;

public class UsuarioBO {

	private static final int ID_NIVEL_ADMINISTRADOR = 1;

	/**
	 * M�todo que valida regras de negocio para cadastrar o usuario no banco.
	 * @param userVO;
	 * @return mensagem registrado com sucesso/campos invalidos;
	 */
	public String validarCadastro(UsuarioVO userVO) {
		String mensagem = "";

		String[] conferirEmail = new String [2];
		conferirEmail = userVO.getEmail().split("@");

		if (userVO.getNome().length() <= 3 || userVO.getNome().length() >= 18) {
			mensagem = "Nome deve ser maior que 3 e menor que 18 Caracteres!";
		}

		if (userVO.getSenha().length() <= 5 || userVO.getSenha().length() >= 13) {
			mensagem = "Senha deve ser maior que 6 e menor que 12 Caracteres!";
		}

		if (userVO.getNivelVO() == null) {
			mensagem = "Por favor, selecione o Nivel!";
		}

		if (conferirEmail.length > 2) {
			mensagem = "Email Invalido!";
		}

		if (mensagem.isEmpty()) {
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.cadastrar(userVO);
			mensagem = "Usuario Cadastrado com Sucesso!";
		}

		return mensagem;
	}

	/**
	 * Exclui um usu�rio, caso quem chamou o m�todo tenha permiss�o de administrador;
	 * @param email o email de quem chamou o m�todo;
	 * @param senha a senha de quem chamou o m�todo;
	 * @return mensagem Aprovado/Rejeitado;
	 */
	public String verificarPermissao(String email, String senha) {
		String mensagem = "";
		String[] conferirEmail = new String [2];
		conferirEmail = email.split("@");

		if (conferirEmail.length > 2) {
			mensagem = "Email Invalido!";
		}

		if (senha.length() <= 5 || senha.length() >= 13) {
			mensagem = "Senha deve ser maior que 6 e menor que 12 Caracteres!";
		}

		UsuarioDAO userDAO = new UsuarioDAO();
		UsuarioVO usuarioLogado = userDAO.consultarPorEmailESenha(email, senha);

		if (usuarioLogado == null) {
			mensagem = "Login Invalido, por favor, tente novamente!";
		} else if (usuarioLogado != null && usuarioLogado.getNivelVO().getId() == ID_NIVEL_ADMINISTRADOR) {
			mensagem = "Login Aprovado!";
		}

		return mensagem;
	}
	/**
	 * Verifica se a exclus�o do banco � True, caso sim, retorna uma mensagem;
	 * @param nome do usuario a ser excluido;
	 * @param email do usuario a ser excluido;
	 * @return mensagem valido/invalido;
	 */
	public String excluir (UsuarioVO userVO) {
		String mensagem = "";
		UsuarioDAO userDAO = new UsuarioDAO();
		
		boolean retornoExclusao = userDAO.excluir(userVO);
		
		if (retornoExclusao == true) {
			mensagem = "Usuario " + userVO.toString() + "Excluido com Sucesso!";
		} else {
			mensagem = "Erro ao Excluir o Usuario!";
		}

		return mensagem;
	}

	/**
	 * M�todo que busca todos os usuarios no banco
	 * @return tudo
	 */
	public ArrayList<UsuarioVO> consultarTodos() {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarTodos();
	}
	/**
	 * M�todo que busca todos os usuarios no banco por NOME
	 * @param nome
	 * @return lista de usuarios por nome
	 */
	public ArrayList<UsuarioVO> consultarUsuariosPorNome(String nome) {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarPorNomeDAO(nome);
	}
	/**
	 * M�todo que busca todos os usuarios no banco por NIVEL
	 * @param nivelSelecionado
	 * @return lista de usuarios por nivel
	 */
	public ArrayList<UsuarioVO> consultarUsuarioPorNivelBO(NivelVO nivelSelecionado) {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarPorNivelDAO(nivelSelecionado);
	}
}
