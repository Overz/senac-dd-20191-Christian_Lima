package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	private static final int ID_NIVEL_ADMINISTRADOR = 1;

	/**
	 * @author Christian Lima;
	 * @param user;
	 * @return mensagem, verificando regras de negocio, senha,email,nivel, se tudo estiver OK, cria o UsuarioDAO;
	 */
	public String validarCadastro(UsuarioVO userVO) {
		String mensagem = "";

		String[] conferirEmail = new String [2];
		conferirEmail = userVO.getEmail().split("@");

		if (userVO.getNome().length() <= 3 || userVO.getNome().length() >= 18) {
			mensagem = "Nome deve ser maior que 3 e menor que 18 Caracteres!";
		}

		if (userVO.getSenha().length() <= 6 || userVO.getSenha().length() >= 12) {
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
	 * Exclui um usuário, caso quem chamou o método tenha permissão de administrador
	 * @param userVO o usuário a ser excluído
	 * @param email o email de quem chamou o método
	 * @param senha a senha de quem chamou o método
	 * @return
	 */
	@SuppressWarnings("unused")
	public String verificarPermissao(UsuarioVO userVO, String email, String senha) {
		String mensagem = "";

		String[] conferirEmail = new String [2];
		conferirEmail = userVO.getEmail().split("@");
		if (conferirEmail.length > 2) {
			mensagem = "Email Invalido!";
		}

		if (senha.length() <= 6 || senha.length() >= 12) {
			mensagem = "Senha deve ser maior que 6 e menor que 12 Caracteres!";
		}

		UsuarioDAO userDAO = new UsuarioDAO();
		UsuarioVO usuarioLogado = userDAO.consultarPorEmailESenha(email, senha);

		if (usuarioLogado == null) {
			mensagem = "Login Invalido, por favor, tente novamente!";
			if (usuarioLogado != null && usuarioLogado.getNivel().getId() == ID_NIVEL_ADMINISTRADOR) {
				mensagem = null;
				excluir(userVO);
			}
		}

		return mensagem;
	}
	//Método complementar
	public String excluir (UsuarioVO userVO) {
		String mensagem = "";

		UsuarioDAO userDAO = new UsuarioDAO();

		if (userDAO.excluir(userVO) == true) {
			mensagem = "Usuario Excluido com Sucesso!";
		} else {
			mensagem = "Erro ao Excluir o Usuario!";
		}

		return mensagem;
	}

	/**
	 * Método que manda para o DAO a consulta por nome.
	 * @param nome
	 * @return
	 */
	public UsuarioVO consultarPorNomeBO(String nome) {
		UsuarioVO userVO = null;
		UsuarioDAO userDAO = new UsuarioDAO();

		verificarNomeCorretoBO(nome);
		 userVO = userDAO.consultarPorNomeDAO(nome);
		
		return userVO ;
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

	public ArrayList<UsuarioVO> consultarTodos() {
		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.consultarTodos();
	}
}
