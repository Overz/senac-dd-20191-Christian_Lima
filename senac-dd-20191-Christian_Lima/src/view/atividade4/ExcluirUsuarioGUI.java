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
import javax.swing.JSeparator;
import java.awt.Color;

import controller.UsuarioController;
import model.vo.NivelVO;


public class ExcluirUsuarioGUI {

	private JFrame frmExcluirUsuarios;
	private JTextField txtEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmacaoSenha;
	private List<NivelVO> niveis;
	private JTextField txtNomeExcluir;
	private JTextField txtEmailExcluir;

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
		frmExcluirUsuarios.setBounds(100, 100, 387, 282);
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
		
		JLabel labelNomeExcluir = new JLabel("Nome:");
		labelNomeExcluir.setBounds(20, 139, 55, 15);
		frmExcluirUsuarios.getContentPane().add(labelNomeExcluir);
		
		JLabel lblEmailExcluir = new JLabel("Email:");
		lblEmailExcluir.setBounds(20, 174, 55, 15);
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
		txtNomeExcluir.setBounds(70, 134, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtNomeExcluir);
		
		txtEmailExcluir = new JTextField();
		txtEmailExcluir.setEnabled(false);
		txtEmailExcluir.setColumns(10);
		txtEmailExcluir.setBounds(70, 169, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmailExcluir);
		consultarNiveis();
		
		JButton btnLimpar2 = new JButton("Limpar");
		btnLimpar2.setEnabled(false);
		btnLimpar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmailExcluir.setText("");
				txtNomeExcluir.setText("");
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
				// verificação
				UsuarioController controller = new UsuarioController();
				String mensagem = controller.consultarPermissao(email, senha, confirmacao);
				
				if (mensagem.equalsIgnoreCase("Login Aprovado!")) {
					txtNomeExcluir.setEnabled(true);
					txtEmailExcluir.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnLimpar2.setEnabled(true);
					
				}
			JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		btnExcluir.setBounds(69, 207, 146, 25);
		frmExcluirUsuarios.getContentPane().add(btnExcluir);
		btnConsultar.setBounds(70, 85, 145, 25);
		frmExcluirUsuarios.getContentPane().add(btnConsultar);
		
		JButton btnLimpar1 = new JButton("Limpar");
		btnLimpar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
			}
		});
		btnLimpar1.setBounds(225, 85, 125, 25);
		frmExcluirUsuarios.getContentPane().add(btnLimpar1);
		btnLimpar2.setBounds(225, 207, 125, 25);
		frmExcluirUsuarios.getContentPane().add(btnLimpar2);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 121, 341, 2);
		frmExcluirUsuarios.getContentPane().add(separator);
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