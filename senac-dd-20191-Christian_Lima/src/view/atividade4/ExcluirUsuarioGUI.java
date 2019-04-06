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
	private JTextField txtNome;
	private JTextField txtEmail;
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

	private void initialize() {
		
		//TODO consultar os n√≠veis no banco (criei na m√£o aqui :D)
		consultarNiveis(); //TODO alterar esta chamada AQUI
		
		frmExcluirUsuarios = new JFrame();
		frmExcluirUsuarios.setTitle("Exclusao de Usuarios");
		frmExcluirUsuarios.setBounds(100, 100, 387, 355);
		frmExcluirUsuarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluirUsuarios.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 20, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblNome);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 55, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(20, 90, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblSenha);
		
		JLabel lblConfirmaoDeSenha = new JLabel("ConfirmaÁ„o:");
		lblConfirmaoDeSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmaoDeSenha.setBounds(165, 90, 94, 16);
		frmExcluirUsuarios.getContentPane().add(lblConfirmaoDeSenha);
		
		JLabel lblNivel = new JLabel("NÌvel:");
		lblNivel.setBounds(20, 125, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblNivel);
		
		JLabel labelNomeExcluir = new JLabel("Nome:");
		labelNomeExcluir.setBounds(20, 213, 55, 15);
		frmExcluirUsuarios.getContentPane().add(labelNomeExcluir);
		
		JLabel lblEmailExcluir = new JLabel("Email:");
		lblEmailExcluir.setBounds(20, 248, 55, 15);
		frmExcluirUsuarios.getContentPane().add(lblEmailExcluir);
		
		txtNome = new JTextField();
		txtNome.setBounds(70, 15, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(70, 50, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(70, 85, 90, 28);
		frmExcluirUsuarios.getContentPane().add(pfSenha);
		
		pfConfirmacaoSenha = new JPasswordField();
		pfConfirmacaoSenha.setBounds(260, 84, 90, 28);
		frmExcluirUsuarios.getContentPane().add(pfConfirmacaoSenha);
		
		txtNomeExcluir = new JTextField();
		txtNomeExcluir.setEnabled(false);
		txtNomeExcluir.setColumns(10);
		txtNomeExcluir.setBounds(70, 208, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtNomeExcluir);
		
		txtEmailExcluir = new JTextField();
		txtEmailExcluir.setEnabled(false);
		txtEmailExcluir.setColumns(10);
		txtEmailExcluir.setBounds(70, 243, 280, 28);
		frmExcluirUsuarios.getContentPane().add(txtEmailExcluir);
		
		//Novo componente: Combobox
		cbNivel = new JComboBox();
		cbNivel.setModel(new DefaultComboBoxModel(niveis.toArray()));
		
		//Inicia sem nada selecionado no combo
		cbNivel.setSelectedIndex(-1);
		
		cbNivel.setBounds(70, 120, 280, 28);
		frmExcluirUsuarios.getContentPane().add(cbNivel);
		
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
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = pfSenha.getText();
				String confirmacao = pfConfirmacaoSenha.getText();
				
				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				
				UsuarioController controlador = new UsuarioController();
				String mensagem = controlador.excluir(nome, email);
				
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		JButton btnConsultar = new JButton("Consultar Permiss\u00E3o");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = txtNome.getText();
				String email = txtEmail.getText();
				String senha = pfSenha.getText();
				String confirmacao = pfConfirmacaoSenha.getText();
				NivelVO nivelSelecionado = (NivelVO) cbNivel.getSelectedItem();
				
				UsuarioController controller = new UsuarioController();
				String mensagem = controller.consultarPermissao(nome, email, senha, confirmacao, nivelSelecionado);
				
				if (mensagem == null || mensagem.isEmpty()) {
					txtNomeExcluir.setEnabled(true);
					txtEmailExcluir.setEnabled(true);
					btnExcluir.setEnabled(true);
					
				}
			JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		
		btnExcluir.setBounds(69, 281, 146, 25);
		frmExcluirUsuarios.getContentPane().add(btnExcluir);
		btnConsultar.setBounds(70, 159, 145, 25);
		frmExcluirUsuarios.getContentPane().add(btnConsultar);
		
		JButton button = new JButton("Limpar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtEmail.setText("");
				pfSenha.setText("");
				pfConfirmacaoSenha.setText("");
				cbNivel.setSelectedIndex(-1);
			}
		});
		button.setBounds(225, 159, 125, 25);
		frmExcluirUsuarios.getContentPane().add(button);
		btnLimpar.setBounds(225, 281, 125, 25);
		frmExcluirUsuarios.getContentPane().add(btnLimpar);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(20, 195, 341, 2);
		frmExcluirUsuarios.getContentPane().add(separator);
	}

	private void consultarNiveis() {
		//TODO trocar para uma chamada ao BO de Nivel	
		niveis = new ArrayList<NivelVO>();
		
		NivelVO nivelAdm = new NivelVO(1, "Administrador");
		NivelVO nivelNormal = new NivelVO(2, "Normal");
		NivelVO nivelGeral = new NivelVO(3, "Todos");
		
		niveis.add(nivelAdm);
		niveis.add(nivelNormal);
		niveis.add(nivelGeral);
	}
}
