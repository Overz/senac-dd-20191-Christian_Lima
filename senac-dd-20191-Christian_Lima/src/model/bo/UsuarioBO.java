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
	
	public String excluir(UsuarioVO usuarioParaExcluir, String email, String senha) {
		String mensagem = "";
		
		UsuarioDAO userDAO = new UsuarioDAO();
		
		UsuarioVO usuarioLogado = userDAO.consultarPorLoginESenha(email, senha);
		
		if(usuarioLogado == null) {
			mensagem = "Usuário não encontrado";
		}else if(usuarioLogado.getNivel().getId() == ID_NIVEL_ADMINISTRADOR){
			boolean codigoRetornoExclusao = userDAO.excluir(usuarioParaExcluir);
			
			if(codigoRetornoExclusao == false) {
				mensagem = "Usuário não foi excluído";
			}else {
				mensagem = "Usuário excluído com sucesso";
			}
		}
		
		return mensagem;
	}
}
