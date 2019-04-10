package view.atividade4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controller.UsuarioController;
import model.vo.NivelVO;
import model.vo.UsuarioVO;

public class ListagemUsuarioGUI {

	private JFrame frmListarUsuarios;
	private JTextField txtNome;
	private JComboBox<NivelVO> cbNivel;
	private java.util.List<NivelVO> niveis;
	private JTable tblUsuarios;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemUsuarioGUI window = new ListagemUsuarioGUI();
					window.frmListarUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListagemUsuarioGUI() {
		initialize();
	}

	private void initialize() {

		consultarNiveis();

		frmListarUsuarios = new JFrame();
		frmListarUsuarios.setTitle("Consulta de usuários");
		frmListarUsuarios.setBounds(100, 100, 585, 405);
		frmListarUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListarUsuarios.getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 55, 15);
		frmListarUsuarios.getContentPane().add(lblNome);

		JLabel lblNivel = new JLabel("Nível:");
		lblNivel.setBounds(20, 55, 55, 15);
		frmListarUsuarios.getContentPane().add(lblNivel);

		txtNome = new JTextField();
		txtNome.setBounds(70, 15, 320, 28);
		frmListarUsuarios.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));

		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);

		cbNivel.setBounds(70, 50, 320, 28);
		frmListarUsuarios.getContentPane().add(cbNivel);

		JButton btnConsultarPorNivel = new JButton("Consultar por nível");
		btnConsultarPorNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pegar o nível selecionado!
				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = controller.controllerConsultarPorNivel(nivelSelecionado);
				
				//Atualizar a tabela com os usuarios selecionados
				atualizarTabelaUsuarios(usuarios);
			}
		});
		btnConsultarPorNivel.setBounds(390, 49, 160, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarPorNivel);

		JButton btnConsultarPorNome = new JButton("Consultar por nome");
		btnConsultarPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*METODO QUE CONSULTA POR NOME */
				String nome = txtNome.getText();
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = controller.consultarUsuariosPorNome(nome);
				
				//array list retornando vazio
				System.out.println(usuarios);
				
				//Chamar sempre que for atualizar a tabela com os usuários
				atualizarTabelaUsuarios(usuarios);
			}
		});
		btnConsultarPorNome.setBounds(390, 14, 160, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarPorNome);

		JButton btnConsultarTodos = new JButton("Consultar todos");
		btnConsultarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controller = new UsuarioController();
				ArrayList<UsuarioVO> usuarios = controller.consultarTodos();
				atualizarTabelaUsuarios(usuarios);
			}
		});
		btnConsultarTodos.setBounds(70, 85, 240, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarTodos);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				limparTela();
			}
		});
		btnLimpar.setBounds(310, 85, 240, 30);
		frmListarUsuarios.getContentPane().add(btnLimpar);

		//Novo componente: tabela
		tblUsuarios = new JTable();
		tblUsuarios.setVisible(true);

		//Cria a tabela vazia apenas com as colunas
		tblUsuarios.setModel(new DefaultTableModel(
				new Object[][] {
					{"id", "Nome", "email", "Nível"},
				},
				new String[] {
						"id", "Nome", "email", "Nível"
				}
				));

		tblUsuarios.setBounds(70, 120, 480, 230);
		frmListarUsuarios.getContentPane().add(tblUsuarios);
		
	}
	
	public void limparTela() {
		tblUsuarios.setModel(new DefaultTableModel(
			new Object[][] {{"id", "Nome", "email", "Nível"}},
			new String[] {"id", "Nome", "email", "Nível"}));
		}

	/**
	 * Atualiza o JTable de usuários.
	 * @param usuarios a lista de usuários que vem do banco.
	 */
	protected void atualizarTabelaUsuarios(ArrayList<UsuarioVO> usuarios) {
		DefaultTableModel modeloTabela = (DefaultTableModel) tblUsuarios.getModel();

		Object novaLinha[] = new Object[4];
		for(int i = 0; i < usuarios.size(); i++){
			UsuarioVO userVO = usuarios.get(i);
			novaLinha[0] = userVO.getId();
			novaLinha[1] = userVO.getNome();
			novaLinha[2] = userVO.getEmail();
			novaLinha[3] = userVO.getNivel().getDescricao();
			modeloTabela.addRow(novaLinha);
		}
	}

	private void consultarNiveis() {
		niveis = new ArrayList<NivelVO>();
		NivelVO nivelAdm = new NivelVO(1, "Administrador");
		NivelVO nivelNormal = new NivelVO(2, "Normal");
		niveis.add(nivelAdm);
		niveis.add(nivelNormal);
	}
}