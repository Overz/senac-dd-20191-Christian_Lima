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

public class Excluir_ListarUsuarioGUI {

	private JFrame frmExcluir_ListarUsuarios;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JComboBox cbNivel;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmacaoSenha;
	private List<NivelVO> niveis;
	private JTextField textField;
	private String nome;
	private String email;
	private String senha;
	private String confirmarSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Excluir_ListarUsuarioGUI window = new Excluir_ListarUsuarioGUI();
					window.frmExcluir_ListarUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Excluir_ListarUsuarioGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//TODO consultar os nÃ­veis no banco (criei na mÃ£o aqui :D)
		//consultarNiveis(); //TODO alterar esta chamada AQUI
		
		frmExcluir_ListarUsuarios = new JFrame();
		frmExcluir_ListarUsuarios.setTitle("Exclus\u00E3o de usu\u00E1rios");
		frmExcluir_ListarUsuarios.setBounds(100, 100, 396, 281);
		frmExcluir_ListarUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluir_ListarUsuarios.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 55, 15);
		frmExcluir_ListarUsuarios.getContentPane().add(lblNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 55, 55, 15);
		frmExcluir_ListarUsuarios.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 90, 55, 15);
		frmExcluir_ListarUsuarios.getContentPane().add(lblSenha);
		
		JLabel lblConfirmaoDeSenha = new JLabel("Confirmação:");
		lblConfirmaoDeSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaoDeSenha.setBounds(165, 90, 94, 16);
		frmExcluir_ListarUsuarios.getContentPane().add(lblConfirmaoDeSenha);
		
		JLabel lblNivel = new JLabel("Nível:");
		lblNivel.setBounds(20, 125, 55, 15);
		frmExcluir_ListarUsuarios.getContentPane().add(lblNivel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(20, 166, 55, 15);
		frmExcluir_ListarUsuarios.getContentPane().add(lblId);
		
		txtNome = new JTextField();
		txtNome.setBounds(70, 15, 280, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(70, 50, 280, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(70, 85, 90, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(pfSenha);
		
		pfConfirmacaoSenha = new JPasswordField();
		pfConfirmacaoSenha.setBounds(260, 84, 90, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(pfConfirmacaoSenha);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(70, 157, 280, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(textField);
		
		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));
		
		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);
		
		cbNivel.setBounds(70, 120, 280, 28);
		frmExcluir_ListarUsuarios.getContentPane().add(cbNivel);
		
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
		btnLimpar.setBounds(240, 196, 100, 35);
		frmExcluir_ListarUsuarios.getContentPane().add(btnLimpar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1 - Ler os valores digitados nos campos da tela
				nome = txtNome.getText();
				email = txtEmail.getText();
				senha = pfSenha.getText();
				confirmarSenha = pfConfirmacaoSenha.getText();

				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				
				//2 - Chamar o mÃ©todo salvar(...) de UsuarioController, passando os valores digitados
				UsuarioController controlador = new UsuarioController();
				String mensagem = controlador.excluir(nome, email);
				
				//3 - Mostrar a mensagem devolvida por UsuarioController na tela, por exemplo com JOptionPane
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		btnExcluir.setBounds(20, 196, 100, 35);
		frmExcluir_ListarUsuarios.getContentPane().add(btnExcluir);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Implementar Método Listar, tendo opções 1- Todos, 2- Adm, 3- Usuarios;
			}
		});
		btnListar.setBounds(130, 196, 100, 35);
		frmExcluir_ListarUsuarios.getContentPane().add(btnListar);
	}

	private void niveisTela() {
		niveis = new ArrayList<NivelVO>();
		
		NivelVO nivelAdm = new NivelVO(1, "Administrador");
		NivelVO nivelNormal = new NivelVO(2, "Normal");
		NivelVO nivelGeral = new NivelVO(3, "Todos");
		
		niveis.add(nivelAdm);
		niveis.add(nivelNormal);
		niveis.add(nivelGeral);
	}
}
