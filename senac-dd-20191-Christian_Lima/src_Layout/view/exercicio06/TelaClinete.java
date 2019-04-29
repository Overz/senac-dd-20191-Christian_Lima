package view.exercicio06;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JDesktopPane;

public class TelaClinete extends JFrame{

	private JFrame frame;
	private JDesktopPane desktopPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClinete window = new TelaClinete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaClinete() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 412);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Clientes");
		mnCliente.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-fila.png")));
		menuBar.add(mnCliente);

		JMenuItem jmiCadastrarCliente = new JMenuItem("Cadastrar");
		jmiCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		jmiCadastrarCliente.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-adicionar-usu\u00E1rio-masculino.png")));
		jmiCadastrarCliente.addActionListener(e -> {
			
			frame.setContentPane(desktopPane);
			JInternalFrame frameCadastroCliente = new JInternalFrame();
			desktopPane.add(frameCadastroCliente);
			frameCadastroCliente.show();
			
		});
		mnCliente.add(jmiCadastrarCliente);

		JMenuItem jmiAtualizarCliente = new JMenuItem("Atualizar");
		jmiAtualizarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		jmiAtualizarCliente.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarCliente.addActionListener(e -> {

		});
		mnCliente.add(jmiAtualizarCliente);

		JMenuItem jmiListarCliente = new JMenuItem("Listar");
		jmiListarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		jmiListarCliente.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		mnCliente.add(jmiListarCliente);

		JMenu mnProduto = new JMenu("Produtos");
		mnProduto.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-comprar.png")));
		menuBar.add(mnProduto);

		JMenuItem jmiCadastrarProduto = new JMenuItem("Cadastrar");
		jmiCadastrarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		jmiCadastrarProduto.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-comprar.png")));
		jmiCadastrarProduto.addActionListener(e -> {

		});
		mnProduto.add(jmiCadastrarProduto);

		JMenuItem jmiAtualizarProduto = new JMenuItem("Atualizar");
		jmiAtualizarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		jmiAtualizarProduto.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarProduto.addActionListener(e ->{

		});
		mnProduto.add(jmiAtualizarProduto);

		JMenuItem jmiListarProduto = new JMenuItem("Listar");
		jmiListarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
		jmiListarProduto.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		jmiListarProduto.addActionListener(e -> {

		});
		mnProduto.add(jmiListarProduto);

		JMenu mnFuncionario = new JMenu("Funcionarios");
		mnFuncionario.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-treinamento.png")));
		menuBar.add(mnFuncionario);

		JMenuItem jmiCadastrarFuncionario = new JMenuItem("Cadastrar");
		jmiCadastrarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_MASK));
		jmiCadastrarFuncionario.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-adicionar-usu\u00E1rio-masculino.png")));
		jmiCadastrarFuncionario.addActionListener(e -> {

		});
		mnFuncionario.add(jmiCadastrarFuncionario);

		JMenuItem jmiAtualizarFuncionario = new JMenuItem("Atualizar");
		jmiAtualizarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_MASK));
		jmiAtualizarFuncionario.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarFuncionario.addActionListener(e -> {

		});
		mnFuncionario.add(jmiAtualizarFuncionario);

		JMenuItem jmiListarFuncionario = new JMenuItem("Listar");
		jmiListarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_MASK));
		jmiListarFuncionario.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		jmiListarFuncionario.addActionListener(e -> {

		});
		mnFuncionario.add(jmiListarFuncionario);

		JMenuItem jmiSobre = new JMenuItem("Sobre");
		jmiSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		jmiSobre.setIcon(new ImageIcon(TelaClinete.class.getResource("/icones/icons8-documento-regular.png")));
		jmiSobre.addActionListener(e -> {

		});
		menuBar.add(jmiSobre);
		frame.getContentPane().setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		desktopPane.setBounds(10, 10, screenSize.width - 40, screenSize.height - 150);
		frame.getContentPane().add(desktopPane);
	}
}
