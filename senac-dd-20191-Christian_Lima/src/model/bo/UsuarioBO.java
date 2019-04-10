package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	private static final int ID_NIVEL_ADMINISTRADOR = 1;

	/**
	 * Método que valida regras de negocio para cadastrar o usuario no banco.
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

		if (userVO.getNivel() == null) {
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
	 * Exclui um usuário, caso quem chamou o método tenha permissão de administrador;
	 * @param email o email de quem chamou o método;
	 * @param senha a senha de quem chamou o método;
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
		} else if (usuarioLogado != null && usuarioLogado.getNivel().getId() == ID_NIVEL_ADMINISTRADOR) {
			mensagem = "Login Aprovado!";
		}

		return mensagem;
	}
	/**
	 * Verifica se a exclusão do banco é True, caso sim, retorna uma mensagem;
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
	 * Método Auxiliar de 'ConsultarPorNomeBO', verificando regras de negocio
	 * @param nome
	 * @return mensagem
	 */
	private String verificarNomeCorretoBO(String nome) {
		String mensagem = "";

		if (nome == null || nome.isEmpty()) {
			mensagem = "Por favor, Digite o Nome!";
		}
		if (nome.length() <= 3 || nome.length() >= 18) {
			mensagem = "Nome deve ser maior que 3 e menor que 18 Caracteres!";
		}
		return mensagem;

	}
	/**
	 * Método que busca todos os usuarios no banco
	 * @return tudo
	 */
	public ArrayList<UsuarioVO> consultarTodos() {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarTodos();
	}
	/**
	 * Método que busca todos os usuarios no banco por NOME
	 * @param nome
	 * @return lista de usuarios por nome
	 */
	public ArrayList<UsuarioVO> consultarUsuariosPorNome(String nome) {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarPorNomeDAO(nome);
	}
	/**
	 * Método que busca todos os usuarios no banco por NIVEL
	 * @param nivelSelecionado
	 * @return lista de usuarios por nivel
	 */
	public ArrayList<UsuarioVO> consultarUsuarioPorNivelBO(NivelVO nivelSelecionado) {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarPorNivelDAO(nivelSelecionado);
	}
}
