package exercicio1.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import model.vo.Cidade;
import model.vo.Cliente;
import model.vo.PessoaFisica;
import model.vo.PessoaJuridica;


public class Tela {

	//objetos
	private Cidade cidade;
	private Cliente cliente;
	private PessoaJuridica pjuridica;
	private PessoaFisica pfisica;
	//variavel tela
	private JFrame frmTelefonia;
	private JTextField textNome;
	private JTextField txtCPF;
	private JTextField cep;
	
	//variavel comum
	private String masc;
	private String femi;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
					window.frmTelefonia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		initialize();
	}

	private void initialize() {
		frmTelefonia = new JFrame();
		frmTelefonia.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frmTelefonia.setTitle("Telefonia");
		frmTelefonia.setBounds(100, 100, 492, 534);
		frmTelefonia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// MENU
		JMenuBar menuBar = new JMenuBar();
		frmTelefonia.setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Files");
		menuBar.add(mnFiles);
		
		JMenuItem mntmAbrirArquivo = new JMenuItem("Open File");
		mntmAbrirArquivo.setSelected(true);
		mnFiles.add(mntmAbrirArquivo);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnFiles.add(mntmSalvar);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mnFiles.add(mntmSearch);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmConfigurations = new JMenuItem("Configurations");
		mntmConfigurations.setSelected(true);
		mnOptions.add(mntmConfigurations);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInfo = new JMenuItem("About");
		mntmInfo.setSelected(true);
		mnHelp.add(mntmInfo);
		frmTelefonia.getContentPane().setLayout(null);
		// FIM MENU
		
		// TELA
		JRadioButton botaoPessoaFisica = new JRadioButton("Pessoa Fisica");
		botaoPessoaFisica.setFont(new Font("Arial Black", Font.PLAIN, 14));
		botaoPessoaFisica.setBounds(6, 7, 156, 34);
		frmTelefonia.getContentPane().add(botaoPessoaFisica);
		
		JRadioButton botaoPessoaJuridica = new JRadioButton("Pessoa Juridica");
		botaoPessoaJuridica.setFont(new Font("Arial Black", Font.PLAIN, 14));
		botaoPessoaJuridica.setBounds(246, 7, 156, 34);
		frmTelefonia.getContentPane().add(botaoPessoaJuridica);
		
		ButtonGroup group = new ButtonGroup();
		group.add(botaoPessoaFisica);
		group.add(botaoPessoaJuridica);
		
		
		// SEPARADOR 1
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(Color.BLACK);
		separator1.setBounds(5, 48, 461, 2);
		frmTelefonia.getContentPane().add(separator1);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNome.setBounds(6, 56, 460, 39);
		frmTelefonia.getContentPane().add(lblNome);
		
		textNome = new JTextField();
		textNome.setToolTipText("Digite o Nome");
		textNome.setFont(new Font("Arial", Font.PLAIN, 14));
		textNome.setBounds(102, 56, 364, 39);
		frmTelefonia.getContentPane().add(textNome);
		textNome.setColumns(10);
		
		
			// teste1
			if (botaoPessoaFisica.isSelected()) {
				cliente.setNome(textNome.getText());
			} else if (botaoPessoaJuridica.isSelected()) {
				cliente.setNome(textNome.getText());
			}
		
		JLabel lblCpf = new JLabel("CPF/CNPJ:");
		lblCpf.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblCpf.setBounds(6, 106, 460, 39);
		frmTelefonia.getContentPane().add(lblCpf);

		txtCPF = new JTextField();
		txtCPF.setToolTipText("Digite o CPF");
		txtCPF.setFont(new Font("Arial", Font.PLAIN, 14));
		txtCPF.setBounds(102, 106, 364, 39);
		frmTelefonia.getContentPane().add(txtCPF);
		txtCPF.setColumns(10);
		
			//teste2
			if (botaoPessoaFisica.isSelected()) {
					pfisica.setCpf(txtCPF.getText());
			} else if (botaoPessoaJuridica.isSelected()) {
				pjuridica.setCnpj(txtCPF.getText());
			}
		
		JLabel lblGenro = new JLabel("Gen\u00EAro:");
		lblGenro.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblGenro.setBounds(8, 156, 458, 27);
		frmTelefonia.getContentPane().add(lblGenro);
		
		JComboBox<Object> comboBoxGenero = new JComboBox<Object>();
		masc = "Masculino";
		femi = "Feminino";
		comboBoxGenero.setModel(new DefaultComboBoxModel<Object>(new String[] {masc, femi}));
		comboBoxGenero.setToolTipText("Selecione o Genero da Pessoa");
		comboBoxGenero.setBounds(70, 156, 84, 27);
		frmTelefonia.getContentPane().add(comboBoxGenero);
			
		
		JLabel lblDataDeNascimento = new JLabel("Nascimento:");
		lblDataDeNascimento.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(164, 156, 302, 27);
		frmTelefonia.getContentPane().add(lblDataDeNascimento);
		
			
			
		// SEPARADOR 2
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.BLACK);
		separator2.setBounds(10, 200, 456, 2);
		frmTelefonia.getContentPane().add(separator2);
		
		JComboBox<Object> comboBoxDataDia = new JComboBox<Object>();
		comboBoxDataDia.setToolTipText("Selecione o Dia");
		comboBoxDataDia.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDataDia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxDataDia.setBounds(260, 156, 47, 27);
		frmTelefonia.getContentPane().add(comboBoxDataDia);
		
		JComboBox<Object> comboBoxDataMes = new JComboBox<Object>();
		comboBoxDataMes.setModel(new DefaultComboBoxModel<Object>(new String[] {"Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBoxDataMes.setToolTipText("Selecione o M\u00EAs");
		comboBoxDataMes.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBoxDataMes.setBounds(306, 156, 81, 27);
		frmTelefonia.getContentPane().add(comboBoxDataMes);
		
		JComboBox<Object> comboBoxDataAno = new JComboBox<Object>();
		comboBoxDataAno.setModel(new DefaultComboBoxModel<Object>(new String[] {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"}));
		comboBoxDataAno.setToolTipText("Selecione o Ano");
		comboBoxDataAno.setBounds(385, 156, 81, 27);
		frmTelefonia.getContentPane().add(comboBoxDataAno);
			
		// SEPARADOR 3
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.BLACK);
		separator3.setBounds(6, 313, 460, 2);
		frmTelefonia.getContentPane().add(separator3);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setToolTipText("Digite o CEP");
		lblCep.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblCep.setBounds(6, 263, 460, 39);
		frmTelefonia.getContentPane().add(lblCep);
		
		cep = new JTextField();
		cep.setToolTipText("Digite o Cep");
		cep.setText("");
		cep.setBounds(102, 263, 364, 39);
		frmTelefonia.getContentPane().add(cep);
		cep.setColumns(10);
		
			//teste3
			if (botaoPessoaFisica.isSelected()) {
				cidade.setCep(cep.getText());
			} else if (botaoPessoaJuridica.isSelected()) {
				cidade.setCep(cep.getText());
			}
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblCidade.setBounds(246, 213, 220, 39);
		frmTelefonia.getContentPane().add(lblCidade);
		
		JComboBox<Object> comboBoxCidade = new JComboBox<Object>();
		comboBoxCidade.setModel(new DefaultComboBoxModel<Object>(new String[] {"AAA", "BBB", "CCC", "DDD", "EEEE", "FFFF", "GGG", "HHH", "IIIIIII", "JJJJJ", "KKK", "LLLL", "MM", "NNN", "OOO", "PPPP", "QQQ", "RRR", "SSS", "TTT", "UUU", "VVV", "WW", "XXX", "YYY", "ZZZ"}));
		comboBoxCidade.setToolTipText("Selecione a Cidade");
		comboBoxCidade.setBounds(333, 213, 133, 39);
		frmTelefonia.getContentPane().add(comboBoxCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblEstado.setBounds(6, 213, 231, 39);
		frmTelefonia.getContentPane().add(lblEstado);
		
		JComboBox<Object> comboBoxEstado = new JComboBox<Object>();
		comboBoxEstado.setModel(new DefaultComboBoxModel<Object>(new String[] {"Mato Grosso do Norte", "Espirito Santo", "Rio de Janeiro", "Goias", "Mato Grosso do Sul", "Minas Gerais", "S\u00E3o Paulo", "Parana", "Santa Catarina", "Rio Grande do Sul"}));
		comboBoxEstado.setToolTipText("Selecione a Cidade");
		comboBoxEstado.setBounds(102, 213, 138, 39);
		frmTelefonia.getContentPane().add(comboBoxEstado);
		
		JLabel lblNumeros = new JLabel("Numeros:");
		lblNumeros.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblNumeros.setBounds(7, 326, 459, 39);
		frmTelefonia.getContentPane().add(lblNumeros);
		
		JComboBox<Object> comboBoxDDD = new JComboBox<Object>();
		comboBoxDDD.setModel(new DefaultComboBoxModel<Object>(new String[] {"45", "46", "47", "48", "49"}));
		comboBoxDDD.setToolTipText("DDD");
		comboBoxDDD.setBounds(103, 326, 98, 39);
		frmTelefonia.getContentPane().add(comboBoxDDD);
		
		JComboBox<Object> comboBoxTelefones = new JComboBox<Object>();
		comboBoxTelefones.setToolTipText("Numeros Disponiveis");
		
		comboBoxTelefones.setModel(new DefaultComboBoxModel<Object>(new String[] {"9123456", "9912345", "9991234", "9999123"}));
		comboBoxTelefones.setModel(new DefaultComboBoxModel<Object>(new String[] {"0123456", "0012345", "0001234", "0000123"}));
		comboBoxTelefones.setBounds(200, 326, 266, 39);
		frmTelefonia.getContentPane().add(comboBoxTelefones);
		
		JLabel lblQuantiadeDeNumeros = new JLabel("Quantidade:");
		lblQuantiadeDeNumeros.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblQuantiadeDeNumeros.setBounds(7, 388, 133, 23);
		frmTelefonia.getContentPane().add(lblQuantiadeDeNumeros);
		
		JComboBox<Object> comboBoxQuantidade = new JComboBox<Object>();
		comboBoxQuantidade.setToolTipText("Quantidade de Telefones");
		comboBoxQuantidade.setModel(new DefaultComboBoxModel<Object>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		comboBoxQuantidade.setBounds(103, 388, 37, 23);
		frmTelefonia.getContentPane().add(comboBoxQuantidade);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblSaldo.setBounds(150, 388, 133, 23);
		frmTelefonia.getContentPane().add(lblSaldo);
		
		JComboBox<Object> saldoCliente = new JComboBox<Object>();
		saldoCliente.setModel(new DefaultComboBoxModel<Object>(new String[] {"R$ 10,00", "R$ 20,00", "R$ 30,00", "R$ 50,00"}));
		saldoCliente.setToolTipText("Selecione o Saldo do Cliente");
		saldoCliente.setBounds(200, 388, 83, 23);
		frmTelefonia.getContentPane().add(saldoCliente);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnSair.setToolTipText("Deseja Sair?");
		btnSair.setBounds(113, 432, 98, 30);
		frmTelefonia.getContentPane().add(btnSair);
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Deseja Salvar?");
		btnSalvar.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnSalvar.setBounds(260, 432, 96, 30);
		frmTelefonia.getContentPane().add(btnSalvar);
		
	}
}