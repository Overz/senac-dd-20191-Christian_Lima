package crud.view.atividade4;

import java.awt.Color;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import crud.controller.UsuarioController;
import crud.model.vo.NivelVO;
import crud.model.vo.UsuarioVO;


public class ExcluirUsuarioGUI {

	private JFrame frmExcluirUsuarios;
	private JTextField txtEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmacaoSenha;
	private List<NivelVO> niveis;
	private ArrayList<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>();
	private JComboBox cbExcluirUsuarios;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirUsuarioGUI window = new ExcluirUsuarioGUI();
					window.frmExcluirUsuarios.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	public ExcluirUsuarioGUI() {
		initialize();
	}

	private void initialize() {
		
		consultarNiveis();
		
		frmExcluirUsuarios = new JFrame();
		frmExcluirUsuarios.setTitle("Exclusao de Usuarios");
		frmExcluirUsuarios.setBounds(100, 100, 393, 282);
		frmExcluirUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluirUsuarios.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(13, 18, 51, 15);
		frmExcluirUsuarios.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(13, 52, 51, 15);
		frmExcluirUsuarios.getContentPane().add(lblSenha);
		
		JLabel lblConfirmaoDeSenha = new JLabel("Confirmação:");
		lblConfirmaoDeSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaoDeSenha.setBounds(187, 51, 66, 16);
		frmExcluirUsuarios.getContentPane().add(lblConfirmaoDeSenha);
		
		JLabel lblUsuarioComboBox = new JLabel("Usuario:");
		lblUsuarioComboBox.setBounds(13, 148, 51, 14);
		frmExcluirUsuarios.getContentPane().add(lblUsuarioComboBox);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(67, 11, 300, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(67, 46, 110, 28);
		frmExcluirUsuarios.getContentPane().add(pfSenha);
		
		pfConfirmacaoSenha = new JPasswordField();
		pfConfirmacaoSenha.setBounds(263, 45, 104, 28);
		frmExcluirUsuarios.getContentPane().add(pfConfirmacaoSenha);
		consultarNiveis();
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController controlador = new UsuarioController();
				String mensagem = controlador.excluir((UsuarioVO) cbExcluirUsuarios.getSelectedItem());
				
				JOptionPane.showMessageDialog(null, mensagem);
				
				atualizarComboUsuarios();
			}
		});
		
		JButton btnConsultar = new JButton("Consultar Permiss\u00E3o");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// passando valores para o controller, e conferindo se esta ok.
				String email = txtEmail.getText();
				String senha = new String(pfSenha.getPassword());
				String confirmacao = new String(pfConfirmacaoSenha.getPassword());
				// verificação
				UsuarioController controller = new UsuarioController();
				String mensagem = controller.consultarPermissao(email, senha, confirmacao);
				
				if (mensagem.equalsIgnoreCase("Login Aprovado!")) {
					cbExcluirUsuarios.setEnabled(true);
					btnExcluir.setEnabled(true);
				}
			JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 121, 341, 2);
		frmExcluirUsuarios.getContentPane().add(separator);
		
		btnExcluir.setBounds(117, 188, 156, 43);
		frmExcluirUsuarios.getContentPane().add(btnExcluir);
		btnConsultar.setBounds(67, 85, 145, 25);
		frmExcluirUsuarios.getContentPane().add(btnConsultar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
			}
		});
		
		cbExcluirUsuarios = new JComboBox(usuariosVO.toArray());
		atualizarComboUsuarios();
		cbExcluirUsuarios.setEnabled(false);
		cbExcluirUsuarios.setBounds(67, 134, 300, 43);
		cbExcluirUsuarios.setSelectedIndex(-1);
		frmExcluirUsuarios.getContentPane().add(cbExcluirUsuarios);
		
		btnLimpar.setBounds(222, 85, 145, 25);
		frmExcluirUsuarios.getContentPane().add(btnLimpar);
	}
	private void atualizarComboUsuarios() {
		UsuarioController controller = new UsuarioController();
		usuariosVO = controller.consultarTodos();
		cbExcluirUsuarios.setModel(new DefaultComboBoxModel(usuariosVO.toArray()));
		cbExcluirUsuarios.setSelectedIndex(-1);
	}
	
	private void consultarNiveis() {
		niveis = new ArrayList<NivelVO>();
		NivelVO nivelAdm = new NivelVO(1, "Administrador");
		NivelVO nivelNormal = new NivelVO(2, "Normal");
		niveis.add(nivelAdm);
		niveis.add(nivelNormal);
	}
}