package layouts.view.exercicio06;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import layouts.view.exercicio06.cliente.PainelListagemCliente;
import layouts.view.exercicio06.cliente.TelaInternaAtualizarCliente;
import layouts.view.exercicio06.cliente.TelaInternaCadastroCliente;
import layouts.view.exercicio06.cliente.TelaInternaRelatorioCliente;
import net.miginfocom.swing.MigLayout;

public class TelaPrincipal extends JFrame{

	private JDesktopPane desktopPane;
	private TelaInternaCadastroCliente janelinhaCadastroCliente;
	private TelaInternaAtualizarCliente janelinhaAtualizarCliente;
	private PainelListagemCliente painelListagemCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/icones/icons8-\u0441harlie-\u0441haplin.png")));
		setTitle("Tela de Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 412);
		initialize();
	}

	public void fecharJanelinhas() {
		//Fechando JInternalFrame/Proibir outra janela interna
		janelinhaCadastroCliente = null;
		janelinhaAtualizarCliente = null;
	}

	public void fechar() {

		WindowEvent fechar = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(fechar);
	}

	private void initialize() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Pessoa");
		mnCliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-fila.png")));
		menuBar.add(mnCliente);

		JMenuItem jmiCadastrarCliente = new JMenuItem("Cadastrar");
		jmiCadastrarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		jmiCadastrarCliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-adicionar-usu\u00E1rio-masculino.png")));
		jmiCadastrarCliente.addActionListener(e -> {
			//Fechando JInternalFrame/Proibir outra janela interna
			if(janelinhaCadastroCliente == null) {
				janelinhaCadastroCliente = new TelaInternaCadastroCliente();
				desktopPane.add(janelinhaCadastroCliente);
				janelinhaCadastroCliente.show();
			}
			//Fechando JInternalFrame/Proibir outra janela interna
			janelinhaCadastroCliente.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {
					fecharJanelinhas();
				}
			});

		});
		mnCliente.add(jmiCadastrarCliente);

		JMenuItem jmiAtualizarCliente = new JMenuItem("Atualizar");
		jmiAtualizarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		jmiAtualizarCliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarCliente.addActionListener(e -> {

			janelinhaAtualizarCliente = new TelaInternaAtualizarCliente();
			desktopPane.add(janelinhaAtualizarCliente);
			janelinhaAtualizarCliente.setVisible(true);
			janelinhaAtualizarCliente.show();

			if(janelinhaAtualizarCliente == null) {
				janelinhaAtualizarCliente = new TelaInternaAtualizarCliente();
				desktopPane.add(janelinhaAtualizarCliente);
				janelinhaAtualizarCliente.show();
			}
			//Fechando JInternalFrame
			janelinhaAtualizarCliente.addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameClosed(InternalFrameEvent arg0) {
					fecharJanelinhas();
				}
			});

		});
		mnCliente.add(jmiAtualizarCliente);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmListar.addActionListener(e -> {
			if (painelListagemCliente == null) {
				painelListagemCliente = new PainelListagemCliente();
				getContentPane().add(painelListagemCliente);
				painelListagemCliente.setVisible(true);
				painelListagemCliente.show();
			}else {
				painelListagemCliente.setVisible(true);
			}
		});
		mntmListar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-card\u00E1pio.png")));
		mnCliente.add(mntmListar);

		JMenuItem jmiRelatorioCliente = new JMenuItem("Relatorio");
		jmiRelatorioCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
		jmiRelatorioCliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		jmiRelatorioCliente.addActionListener(e -> {

			TelaInternaRelatorioCliente janelinhaRelatorioCliente = new TelaInternaRelatorioCliente();
			desktopPane.add(janelinhaRelatorioCliente);
			janelinhaRelatorioCliente.setVisible(true);
			janelinhaRelatorioCliente.show();

		});
		mnCliente.add(jmiRelatorioCliente);

		JMenu mnProduto = new JMenu("Produtos");
		mnProduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-comprar.png")));
		menuBar.add(mnProduto);

		JMenuItem jmiCadastrarProduto = new JMenuItem("Cadastrar");
		jmiCadastrarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		jmiCadastrarProduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-comprar.png")));
		jmiCadastrarProduto.addActionListener(e -> {

			JInternalFrame frameCadastrarProduto = new JInternalFrame();
			desktopPane.add(frameCadastrarProduto);
			frameCadastrarProduto.setVisible(true);
			frameCadastrarProduto.show();
		});
		mnProduto.add(jmiCadastrarProduto);

		JMenuItem jmiAtualizarProduto = new JMenuItem("Atualizar");
		jmiAtualizarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_MASK));
		jmiAtualizarProduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarProduto.addActionListener(e ->{

			JInternalFrame frameAtualizarProduto = new JInternalFrame();
			desktopPane.add(frameAtualizarProduto);
			frameAtualizarProduto.setVisible(true);
			frameAtualizarProduto.show();

		});
		mnProduto.add(jmiAtualizarProduto);

		JMenuItem jmiListarProduto = new JMenuItem("Listar");
		jmiListarProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_MASK));
		jmiListarProduto.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		jmiListarProduto.addActionListener(e -> {

			JInternalFrame frameListarProduto = new JInternalFrame();
			desktopPane.add(frameListarProduto);
			frameListarProduto.setVisible(true);
			frameListarProduto.show();

		});
		mnProduto.add(jmiListarProduto);

		JMenu mnFuncionario = new JMenu("Funcionarios");
		mnFuncionario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-treinamento.png")));
		menuBar.add(mnFuncionario);

		JMenuItem jmiCadastrarFuncionario = new JMenuItem("Cadastrar");
		jmiCadastrarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.SHIFT_MASK));
		jmiCadastrarFuncionario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-adicionar-usu\u00E1rio-masculino.png")));
		jmiCadastrarFuncionario.addActionListener(e -> {

			JInternalFrame frameCadastrarFuncionarios = new JInternalFrame();
			desktopPane.add(frameCadastrarFuncionarios);
			frameCadastrarFuncionarios.setVisible(true);
			frameCadastrarFuncionarios.show();

		});
		mnFuncionario.add(jmiCadastrarFuncionario);

		JMenuItem jmiAtualizarFuncionario = new JMenuItem("Atualizar");
		jmiAtualizarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.SHIFT_MASK));
		jmiAtualizarFuncionario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-confian\u00E7a.png")));
		jmiAtualizarFuncionario.addActionListener(e -> {

			JInternalFrame frameAtualizarFuncionario = new JInternalFrame();
			desktopPane.add(frameAtualizarFuncionario);
			frameAtualizarFuncionario.setVisible(true);
			frameAtualizarFuncionario.show();

		});
		mnFuncionario.add(jmiAtualizarFuncionario);

		JMenuItem jmiListarFuncionario = new JMenuItem("Listar");
		jmiListarFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.SHIFT_MASK));
		jmiListarFuncionario.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-adicionar-ao-banco-de-dados.png")));
		jmiListarFuncionario.addActionListener(e -> {

			JInternalFrame frameListarFuncionario = new JInternalFrame();
			desktopPane.add(frameListarFuncionario);
			frameListarFuncionario.setVisible(true);
			frameListarFuncionario.show();

		});
		mnFuncionario.add(jmiListarFuncionario);

		JMenuItem jmiSobre = new JMenuItem("Sobre");
		jmiSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		jmiSobre.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/icons8-documento-regular.png")));
		jmiSobre.addActionListener(e -> {

		});
		menuBar.add(jmiSobre);

		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);

		//TODO testar outros layouts
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1560, 294);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		desktopPane.setBounds(10, 10, screenSize.width - 40, screenSize.height - 150);

		setContentPane(desktopPane);
		desktopPane.setLayout(new MigLayout("", "[grow,fill]", "[grow,fill]"));
	}
}
