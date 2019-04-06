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
	@SuppressWarnings("rawtypes")
	private JComboBox cbNivel;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {

		//TODO consultar os níveis no banco (criei na mão aqui :D)
		consultarNiveis(); //TODO alterar esta chamada AQUI

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
				
				
				
				

			}
		});
		btnConsultarPorNivel.setBounds(390, 49, 160, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarPorNivel);

		JButton btnConsultarPorNome = new JButton("Consultar por nome");
		btnConsultarPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Chamar o método do controller: consultarPorNome(String nome)

				String nome = txtNome.getText();
				UsuarioController controller = new UsuarioController();
				@SuppressWarnings("unused")
				String mensagem = controller.consultarPorNome(nome);
				
				//Mostrar na tabela a lista retornada
				ArrayList<UsuarioVO> userVO = new ArrayList<UsuarioVO>();
				UsuarioVO u1 = new UsuarioVO("User1", null, null, null);
				UsuarioVO u2 = new UsuarioVO("User2", null, null, null);
				UsuarioVO u3 = new UsuarioVO("User3", null, null, null);
				userVO.add(u1);
				userVO.add(u2);
				userVO.add(u3);
				
				//Chamar sempre que for atualizar a tabela com os usuários
				atualizarTabelaUsuarios(userVO);
			}
		});
		btnConsultarPorNome.setBounds(390, 14, 160, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarPorNome);

		JButton btnConsultarTodos = new JButton("Consultar todos");
		btnConsultarTodos.setBounds(70, 85, 240, 30);
		frmListarUsuarios.getContentPane().add(btnConsultarTodos);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(310, 85, 240, 30);
		frmListarUsuarios.getContentPane().add(btnLimpar);

		//Novo componente: tabela
		tblUsuarios = new JTable();
		tblUsuarios.setVisible(true);

		//Cria a tabela vazia apenas com as colunas
		tblUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{"id", "Nome", "email"},
			},
			new String[] {
				"id", "Nome", "email"
			}
		));

		tblUsuarios.setBounds(70, 120, 480, 230);
		frmListarUsuarios.getContentPane().add(tblUsuarios);
	}

	/**
	 * Atualiza o JTable de usuários.
	 * @param usuarios
	 */
	protected void atualizarTabelaUsuarios(ArrayList<UsuarioVO> usuarios) {
		DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
		
		Object novaLinha[] = new Object[2];
		for(UsuarioVO userVO: usuarios){
			novaLinha[0] = userVO.getNivel().getId();
			novaLinha[1] = userVO.getNome();
			model.addRow(novaLinha);
		}
	}

	private void consultarNiveis() {
		//TODO trocar para uma chamada ao BO de Nivel	
		niveis = new ArrayList<NivelVO>();

		NivelVO nivelAdm = new NivelVO(1, "Administrador");
		NivelVO nivelNormal = new NivelVO(2, "Normal");

		niveis.add(nivelAdm);
		niveis.add(nivelNormal);
	}
}