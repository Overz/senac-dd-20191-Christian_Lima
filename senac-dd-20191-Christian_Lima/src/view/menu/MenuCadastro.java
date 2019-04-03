package view.menu;

import javax.swing.JOptionPane;

import controller.UsuarioController;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class MenuCadastro {

	private String nome;
	private String email;
	private String senha;
	private String senhaConfirmacao;

	private NivelVO nivelVO;
	private UsuarioVO user = new UsuarioVO(nome, email, senha, nivelVO);

	private String [] comboBoxNivel = new String [2];
	private static final String OPCAO_ADM = "1 - UsuarioVO Comum";
	private static final String OPCAO_NORMAL = "2 - Administrador";


	public void menuRegistrarCadastro () {
		comboBoxNivel[0] = OPCAO_ADM;
		comboBoxNivel[1] = OPCAO_NORMAL;

		// Tela para registrar o Cadastro
		nome = JOptionPane.showInputDialog(null, "Digite o Nome de Login: ", "Login", JOptionPane.QUESTION_MESSAGE);
		email = JOptionPane.showInputDialog(null, "Digite o Email: ", "Email", JOptionPane.QUESTION_MESSAGE);
		senha = JOptionPane.showInputDialog(null, "Digite a Senha: ", "Senha", JOptionPane.WARNING_MESSAGE);
		senhaConfirmacao = JOptionPane.showInputDialog(null, "Confirme sua Senha: ", "Confirmação", JOptionPane.WARNING_MESSAGE);
		String stringNivel = (String) JOptionPane.showInputDialog(null, "Digite o NivelVO(1-ADM, 2-Normal)", "NivelVO",
				JOptionPane.WARNING_MESSAGE, null, comboBoxNivel, comboBoxNivel[0]);

		//TODO chamar UsuarioController, passando os campos informados na tela. Este controlador retornará uma mensagem
		//informando se salvou ou ocorreu algum problema de validação

		UsuarioController control = new UsuarioController();
		//	String mensagem = control.salvar(nome, email, senha, senhaConfirmacao, nivel);

		//	JOptionPane.showMessageDialog(null, mensagem);

		menuRegistrarCadastro();

	}

	public void salvarNivel() {
		if (nivelVO.getId() != 1 && nivelVO.getId() != 2) {

			JOptionPane.showMessageDialog(null, "Digite um NivelVO Valido!", "Invalido", JOptionPane.ERROR_MESSAGE);
			String stringNivel = (String) JOptionPane.showInputDialog(null, "Digite o NivelVO(1-ADM, 2-Normal)", "NivelVO",
					JOptionPane.WARNING_MESSAGE, null, comboBoxNivel, comboBoxNivel[0]);
			nivelVO.setId(Integer.parseInt(stringNivel));

			// controller conferir dados
			UsuarioController control = new UsuarioController();
			//String mensagem = control.conferirNivel(stringNivel);
			salvarNivel();
		} else {
			UsuarioVO user = new UsuarioVO(nome, email, senha, nivelVO);
		}
	}

	public void confirmandoDados () {

		// Confirmando os Dados

		int botao = JOptionPane.showConfirmDialog(null, user.toString(), "Informações", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		switch (botao) {
		case JOptionPane.YES_OPTION:

			if (botao == 0) {
				JOptionPane.showMessageDialog(null, "Cadastro Confirmado!", "Confirmação", JOptionPane.DEFAULT_OPTION);
				Menu menu = new Menu();
				menu.menuGraficoPrincipal();
			}
			break;
			
		case JOptionPane.NO_OPTION:
			if (botao == 1) {
				menuRegistrarCadastro();
			}
			break;
			
		case JOptionPane.CANCEL_OPTION:
			if (botao == 2) {
				JOptionPane.showMessageDialog(null, "Cadastro Cancelado\n Encerrando!", "Saindo", JOptionPane.CLOSED_OPTION);
				System.exit(0);
			}

			break;
		}
	}
}