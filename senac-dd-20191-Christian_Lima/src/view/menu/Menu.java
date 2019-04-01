package view.menu;

import javax.swing.JOptionPane;

public class Menu {

	private static final int MENU_CADASTRAR = 1;
	private static final int MENU_LISTAR = 2;
	private static final int MENU_APAGAR = 3;
	private static final int MENU_SAIR = 4;
	
	public void menuGraficoPrincipal() {
		
		String txtMenuGrafico = "Menu de Cadastro\n"
								+ MENU_CADASTRAR + " - Cadastrar\n"
								+ MENU_LISTAR + " - Listar\n"
								+ MENU_APAGAR + " - Apagar\n"
								+ MENU_SAIR + " - Sair\n";
						
		String opcaoInformada = JOptionPane.showInputDialog(txtMenuGrafico);
		
		try {
			int opcaoInsegura = Integer.parseInt(opcaoInformada);
			menuOpcao(opcaoInsegura);
		} catch (NumberFormatException e) {
				
			JOptionPane.showMessageDialog(null, "Informe Opções Validas!");
			menuGraficoPrincipal();
		
			}
		}
		
	public void menuOpcao (int opcao) {
		
		switch (opcao) {
		case MENU_CADASTRAR:
			
			MenuCadastro menuCadastro = new MenuCadastro();
			menuCadastro.menuRegistrarCadastro();
			
		break;

		case MENU_LISTAR:
			
			MenuListar menuListar = new MenuListar();
			menuListar.menuListarTodos();
				
		break;
			
		case MENU_APAGAR:
			
			MenuExcluir menuExcluir = new MenuExcluir();
			menuExcluir.apagarCadastroMenu();
		
		break;	
		
		case MENU_SAIR:
			
			JOptionPane.showMessageDialog(null, "Encerrando.");
			System.exit(0);
			
			break;
			
		default:
			
			JOptionPane.showMessageDialog(null, "Opção Invalida.");
			menuGraficoPrincipal();
			
		break;
		}	
	}
}
