package view.menu;

import javax.swing.JOptionPane;

public class MenuExcluir {
	
	private int idEscolhido;
	
	public void apagarCadastroMenu () {
		
		String stringID = JOptionPane.showInputDialog(null, "Digite o codigo do ID: ", "", JOptionPane.INFORMATION_MESSAGE);
		if (stringID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por Favor, Digite o ID.");
				apagarCadastroMenu();
			}
		idEscolhido = Integer.parseInt(stringID);
		
		
		confirmaExclusao();
	}
	
	public void confirmaExclusao () {
		
		int botao = JOptionPane.showConfirmDialog(null, "Confirmar Exclusão do ID:" + idEscolhido + " ?", "ATENÇÂO!!!",
													JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		 
		switch (botao) {
		case JOptionPane.YES_OPTION:
			
			if (botao == 0) {
				
				JOptionPane.showMessageDialog(null, "Apagado!", "", JOptionPane.WARNING_MESSAGE);
				Menu menu = new Menu();
				menu.menuGraficoPrincipal();
				
			}
			
		break;
		
		case JOptionPane.NO_OPTION:
			
			if (botao == 1) {
				apagarCadastroMenu();
			}
			
		break;
		
		case JOptionPane.CANCEL_OPTION:
			
			if (botao == 2) {
				JOptionPane.showMessageDialog(null, "Exclusão Cancelada.\n Encerrando.", "Saindo", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			
		break;
		
		default:
			if (idEscolhido < 0) {
				JOptionPane.showMessageDialog(null, "Digite um ID valido!");
			}
		break;
		
		}	
	}
}
