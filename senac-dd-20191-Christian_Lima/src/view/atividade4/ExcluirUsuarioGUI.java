package view.atividade4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSeparator;
import java.awt.Color;

public class ExcluirUsuarioGUI {

	private JFrame frmExcluirUsuarios;
	private JTextField txtEmail;
	@SuppressWarnings("rawtypes")
	private JComboBox cbNivel;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmacaoSenha;
	private java.util.List<NivelVO> niveis;
	private JTextField txtNomeExcluir;
	private JTextField txtEmailExcluir;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirUsuarioGUI window = new ExcluirUsuarioGUI();
					window.frmExcluirUsuarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ExcluirUsuarioGUI() {
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		
		frmExcluirUsuarios = new JFrame();
		frmExcluirUsuarios.setTitle("Exclusao de Usuarios");
		frmExcluirUsuarios.setBounds(100, 100, 387, 320);
		frmExcluirUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluirUsuarios.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 16, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 51, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblSenha);
		
		JLabel lblConfirmaoDeSenha = new JLabel("Confirmação:");
		lblConfirmaoDeSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaoDeSenha.setBounds(165, 51, 94, 16);
		frmExcluirUsuarios.getContentPane().add(lblConfirmaoDeSenha);
		
		JLabel lblNivel = new JLabel("Nível:");
		lblNivel.setBounds(20, 86, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblNivel);
		
		JLabel labelNomeExcluir = new JLabel("Nome:");
		labelNomeExcluir.setBounds(20, 174, 55, 15);
		frmExcluirUsuarios.getContentPane().add(labelNomeExcluir);
		
		JLabel lblEmailExcluir = new JLabel("Email:");
		lblEmailExcluir.setBounds(20, 209, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblEmailExcluir);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(70, 11, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(70, 46, 90, 28);
		frmExcluirUsuarios.getContentPane().add(pfSenha);
		
		pfConfirmacaoSenha = new JPasswordField();
		pfConfirmacaoSenha.setBounds(260, 45, 90, 28);
		frmExcluirUsuarios.getContentPane().add(pfConfirmacaoSenha);
		
		txtNomeExcluir = new JTextField();
		txtNomeExcluir.setEnabled(false);
		txtNomeExcluir.setColumns(10);
		txtNomeExcluir.setBounds(70, 169, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtNomeExcluir);
		
		txtEmailExcluir = new JTextField();
		txtEmailExcluir.setEnabled(false);
		txtEmailExcluir.setColumns(10);
		txtEmailExcluir.setBounds(70, 204, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmailExcluir);
		
		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));
		
		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);
		
		cbNivel.setBounds(70, 81, 280, 28);
		frmExcluirUsuarios.getContentPane().add(cbNivel);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setEnabled(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
				cbNivel.setSelectedIndex(-1);
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			@SuppressWarnings({ "deprecation", "unused" })
			public void actionPerformed(ActionEvent e) {
				
				String email = txtEmail.getText();
				String senha = pfSenha.getText();
				String confirmacao = pfConfirmacaoSenha.getText();
				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				
				UsuarioController controlador = new UsuarioController();
				String mensagem = controlador.excluir(email, senha);
				
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		JButton btnConsultar = new JButton("Consultar Permiss\u00E3o");
		btnConsultar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				// passando valores para o controller, e conferindo se esta ok.
				String email = txtEmail.getText();
				String senha = pfSenha.getText();
				String confirmacao = pfConfirmacaoSenha.getText();
				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				// verificação
				UsuarioController controller = new UsuarioController();
				String mensagem = controller.consultarPermissao(email, senha, confirmacao, nivelSelecionado);
				
				if (mensagem == null || mensagem.isEmpty()) {
					txtNomeExcluir.setEnabled(true);
					txtEmailExcluir.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnLimpar.setEnabled(true);
					
				}
			JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		btnExcluir.setBounds(69, 242, 146, 25);
		frmExcluirUsuarios.getContentPane().add(btnExcluir);
		btnConsultar.setBounds(70, 120, 145, 25);
		frmExcluirUsuarios.getContentPane().add(btnConsultar);
		
		JButton button = new JButton("Limpar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
				cbNivel.setSelectedIndex(-1);
			}
		});
		button.setBounds(225, 120, 125, 25);
		frmExcluirUsuarios.getContentPane().add(button);
		btnLimpar.setBounds(225, 242, 125, 25);
		frmExcluirUsuarios.getContentPane().add(btnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 156, 341, 2);
		frmExcluirUsuarios.getContentPane().add(separator);
	}

}
