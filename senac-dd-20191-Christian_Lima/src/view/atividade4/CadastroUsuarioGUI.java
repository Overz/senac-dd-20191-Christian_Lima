package view.atividade4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.UsuarioController;
import model.vo.NivelVO;

/**
 * 
 * Tela de cadastro de usu√°rios (item 1a da Atividade 4)
 * 
 * @author Vilmar C√©sar Pereira J√∫nior
 *
 */
public class CadastroUsuarioGUI {

	private JFrame frmCadastroDeUsuarios;
	private JTextField txtNome;
	private JTextField txtEmail;
	@SuppressWarnings("rawtypes")
	private JComboBox cbNivel;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmacaoSenha;
	private List<NivelVO> niveis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuarioGUI window = new CadastroUsuarioGUI();
					window.frmCadastroDeUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroUsuarioGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		//TODO consultar os n√≠veis no banco (criei na m√£o aqui :D)
		consultarNiveis(); //TODO alterar esta chamada AQUI

		frmCadastroDeUsuarios = new JFrame();
		frmCadastroDeUsuarios.setTitle("Cadastro de usu·rios");
		frmCadastroDeUsuarios.setBounds(100, 100, 376, 237);
		frmCadastroDeUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeUsuarios.getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblNome);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 55, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 90, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblSenha);

		JLabel lblConfirmaoDeSenha = new JLabel("ConfirmaÁ„o:");
		lblConfirmaoDeSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaoDeSenha.setBounds(165, 90, 94, 16);
		frmCadastroDeUsuarios.getContentPane().add(lblConfirmaoDeSenha);

		JLabel lblNivel = new JLabel("NÌvel:");
		lblNivel.setBounds(20, 125, 55, 15);
		frmCadastroDeUsuarios.getContentPane().add(lblNivel);

		txtNome = new JTextField();
		txtNome.setBounds(70, 15, 280, 28);
		frmCadastroDeUsuarios.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(70, 50, 280, 28);
		frmCadastroDeUsuarios.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(70, 85, 90, 28);
		frmCadastroDeUsuarios.getContentPane().add(pfSenha);

		pfConfirmacaoSenha = new JPasswordField();
		pfConfirmacaoSenha.setBounds(260, 84, 90, 28);
		frmCadastroDeUsuarios.getContentPane().add(pfConfirmacaoSenha);

		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));

		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);

		cbNivel.setBounds(70, 120, 280, 28);
		frmCadastroDeUsuarios.getContentPane().add(cbNivel);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
				cbNivel.setSelectedIndex(-1);
			}
		});
		btnLimpar.setBounds(190, 155, 160, 35);
		frmCadastroDeUsuarios.getContentPane().add(btnLimpar);

		JButton button = new JButton("Salvar");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//1 - Ler os valores digitados nos campos da tela
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = pfSenha.getText();
				String confirmacao = pfConfirmacaoSenha.getText();

				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();

				//2 - Chamar o m√©todo salvar(...) de UsuarioController, passando os valores digitados
				UsuarioController controlador = new UsuarioController();
				String mensagem = controlador.salvar(nome,email,senha,confirmacao,nivelSelecionado);

				//3 - Mostrar a mensagem devolvida por UsuarioController na tela, por exemplo com JOptionPane
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		button.setBounds(20, 155, 160, 35);
		frmCadastroDeUsuarios.getContentPane().add(button);
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