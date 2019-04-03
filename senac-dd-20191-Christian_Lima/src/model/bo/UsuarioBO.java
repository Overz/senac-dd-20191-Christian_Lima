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
	public String validarSalvar(UsuarioVO userVO) {
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
	 * Exclui um usu�rio, caso quem chamou o m�todo tenha permiss�o de administrador
	 * @param userVO o usu�rio a ser exclu�do
	 * @param email o email de quem chamou o m�todo
	 * @param senha a senha de quem chamou o m�todo
	 * @return
	 */
	public String excluir(UsuarioVO userVO, String email, String senha) {
		String mensagem = "";
		
		UsuarioDAO userDAO = new UsuarioDAO();
		
		UsuarioVO usuarioLogado = userDAO.consultarPorEmailESenha(email, senha);
		
		if(usuarioLogado == null) {
			mensagem = "Usu�rio n�o encontrado";
		}else if(usuarioLogado.getNivel().getId() == ID_NIVEL_ADMINISTRADOR){
			boolean retornoExclusaoDAO = userDAO.excluir(userVO);
			
			if(retornoExclusaoDAO == false) {
				mensagem = "Usu�rio n�o foi exclu�do";
			}else {
				mensagem = "Usu�rio exclu�do com sucesso";
			}
		}
		
		return mensagem;
	}

	public String listagem(String nome, String email) {
		String mensagem = "";
		
		UsuarioDAO userDAO = new UsuarioDAO();
		
		UsuarioVO userVO = (UsuarioVO) userDAO.consultarTodos(nome, email);
		
		if (userVO ==  null) {
			mensagem = "Usuarios nao encontrado!";
		}

		return mensagem;
	}
}
