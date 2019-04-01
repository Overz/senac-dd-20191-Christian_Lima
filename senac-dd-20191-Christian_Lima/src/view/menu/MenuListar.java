package view.menu;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class MenuListar {
	
	private ArrayList<UsuarioVO> usuarioVOs = new ArrayList<UsuarioVO>();
	private NivelVO nivelVO;
	private static final String OPCAO_ADM = "1- ADM";
	private static final String OPCAO_NORMAL = "2- Normal";
	private static final String OPCAO_GERAL = "3- Todos";

		public MenuListar() {
		
			UsuarioVO user1 = new UsuarioVO("Edson Arantes", "1@1.com", "pele10",  "1", nivelVO);
			UsuarioVO user2 = new UsuarioVO("Arthur Antunes", "2@1.com", "zico10", "1",  nivelVO);
			UsuarioVO user3 = new UsuarioVO("Sócrates Brasileiro", "3@1.com", "socrates8",  "2", nivelVO);
			UsuarioVO user4 = new UsuarioVO("Matheus Pereira", "4@1.com", "matheus1",  "2", nivelVO);
			UsuarioVO user5 = new UsuarioVO("Jose carlos", "5@1.com", "zezinho10",  "2", nivelVO);
			UsuarioVO user6 = new UsuarioVO("Maria do Rosario", "6@1.com", "mary55", "2", nivelVO);
			
			usuarioVOs.add(user1);
			usuarioVOs.add(user2);
			usuarioVOs.add(user3);
			usuarioVOs.add(user4);
			usuarioVOs.add(user5);
			usuarioVOs.add(user6);
			
		}
		
		public void menuListarTodos() {
			
			Menu menu = new Menu();
			UsuarioVO user = new UsuarioVO();
			NivelVO nivelVO = new NivelVO();
			
			//Percorrer cada um dos usuários da lista 'usuarioVOs', listando pelas Opções 1- ADM, ou 2- Normal
			
			String [] comboBoxNivel = new String [3];
			comboBoxNivel[0] = OPCAO_ADM;
			comboBoxNivel[1] = OPCAO_NORMAL;
			comboBoxNivel[2] = OPCAO_GERAL;
			
			String stringOpcao = (String) JOptionPane.showInputDialog(null, "Existem: " + usuarioVOs.size() + " Usuarios.\n Deseja Listar por:", "Listagem",
										JOptionPane.YES_NO_CANCEL_OPTION, null, comboBoxNivel, comboBoxNivel[0]);
			
				switch (stringOpcao) {
					case OPCAO_ADM:
						
						for (UsuarioVO usuarioVO : usuarioVOs) {
							if (nivelVO.getId() == 1) {
		
								JOptionPane.showMessageDialog(null, nivelVO.toString(), "Usuarios Administradores ", JOptionPane.INFORMATION_MESSAGE);
								
							}
						}
						
						menu.menuGraficoPrincipal();
						
					break;
					
					case OPCAO_NORMAL:
						
						for (UsuarioVO usuarioVO : usuarioVOs) {
							if (nivelVO.getId() == 2) {
								JOptionPane.showMessageDialog(null, usuarioVO.toString(), "Usuarios Normais", JOptionPane.INFORMATION_MESSAGE);
								
							}
						}
						
						menu.menuGraficoPrincipal();
					break;
					
					case OPCAO_GERAL:
						
						JOptionPane.showMessageDialog(null, usuarioVOs.toString());
						
					break;
				}
			
		}
}
