package model.bo;

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

		if (userVO.getSenha().length() < 5 || userVO.getSenha().length() > 13) {
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
	public String verificarPermissao(UsuarioVO userVO, String nome, String email, String senha) {
		String mensagem = "";

		UsuarioDAO userDAO = new UsuarioDAO();
		UsuarioVO usuarioLogado = userDAO.consultarPorEmailESenha(nome, email, senha);
	
		System.out.println(usuarioLogado.toString());
		
		if (usuarioLogado == null) {
			mensagem = "Login Invalido, por favor, tente novamente!";
			if (usuarioLogado != null && usuarioLogado.getNivel().getId() == ID_NIVEL_ADMINISTRADOR) {
				mensagem = null;
				excluir(userVO);
			}
		}

		return mensagem;
	}


	public String excluir (UsuarioVO userVO) {
		String mensagem = "";
		boolean retornoExclusaoDAO;

		UsuarioDAO userDAO = new UsuarioDAO();

		if (userDAO.excluir(userVO) == true) {
			retornoExclusaoDAO = userDAO.excluir(userVO);
			mensagem = "Usuario Excluido com Sucesso!";

		} if (userDAO.excluir(userVO) == false) {
			mensagem = "Erro ao Excluir o Usuario!";
		}

		return mensagem;
	}
	
	
	public String listagem(String nome, String email) {
		String mensagem = "";
		
		UsuarioDAO userDAO = new UsuarioDAO();
		
		UsuarioVO userVO = (UsuarioVO) userDAO.consultarTodos(nome, email);
		
		if (userVO ==  null) {
			mensagem = "Usuarios nao encontrado!";
		} else if (userVO != null) {
			mensagem = "Usuarios encontrados!";
		}
		
		return mensagem;
	}
}
