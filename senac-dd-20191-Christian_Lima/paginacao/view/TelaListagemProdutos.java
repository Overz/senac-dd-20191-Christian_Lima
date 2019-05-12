package paginacao.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import paginacao.controller.ProdutoController;
import paginacao.model.seletor.ProdutoSeletor;
import paginacao.model.vo.Produto;

public class TelaListagemProdutos extends JFrame {

	private static final String COR_AZUL = "Azul";
	private static final String COR_AMARELO = "Amarelo";
	private static final String COR_PRETO = "Preto";
	private static final String COR_VERDE = "Verde";
	private static final String COR_VERMELHO = "Vermelho";
	private static final int TAMANHO_PAGINA = 5;

	private ProdutoSeletor seletor;
	private JPanel contentPane;
	private JTable tblProdutos;
	private JButton btnGerarXls;
	private JButton btnGerarPdf;
	private JComboBox cbCor;
	private DatePicker dtInicioCadastro;
	private DatePicker dtFimCadastro;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private JButton btnAnterior;
	private JButton btnProximo;

	// Esta lista de produtos é atualizada a cada nova consulta realizada com os
	// filtros.
	// Será a lista usada para gerar os relatórios
	private List<Produto> produtosConsultados;
	private JTextField txtNome;
	private JTextField txtPeso;
	private JLabel lblPaginaAtual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemProdutos frame = new TelaListagemProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param cbCor
	 */
	public TelaListagemProdutos() {
		setTitle("Consulta de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] cores = { "---Selecione---", TelaListagemProdutos.COR_AZUL, TelaListagemProdutos.COR_AMARELO,
		TelaListagemProdutos.COR_PRETO, TelaListagemProdutos.COR_VERDE, TelaListagemProdutos.COR_VERMELHO };

		JLabel lblFiltrosDeConsulta = new JLabel("Filtros de consulta:");
		lblFiltrosDeConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblFiltrosDeConsulta.setBounds(10, 11, 448, 14);
		contentPane.add(lblFiltrosDeConsulta);

		JLabel lblFiltroNome = new JLabel("Nome:");
		lblFiltroNome.setBounds(10, 40, 50, 15);
		contentPane.add(lblFiltroNome);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(10, 70, 61, 16);
		contentPane.add(lblPeso);

		JLabel lblFiltroCor = new JLabel("Cor:");
		lblFiltroCor.setBounds(255, 40, 46, 14);
		contentPane.add(lblFiltroCor);

		JLabel lblPeriodoCadastro = new JLabel("Período de cadastro");
		lblPeriodoCadastro.setBounds(10, 110, 170, 20);
		contentPane.add(lblPeriodoCadastro);

		JLabel lblDataInicioCadastro = new JLabel("De:");
		lblDataInicioCadastro.setBounds(10, 140, 61, 16);
		contentPane.add(lblDataInicioCadastro);

		JLabel lblDataFimCadastro = new JLabel("Até:");
		lblDataFimCadastro.setBounds(10, 170, 61, 16);
		contentPane.add(lblDataFimCadastro);

		lblPaginaAtual = new JLabel("");
		lblPaginaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaginaAtual.setBounds(194, 461, 54, 14);
		lblPaginaAtual.setText(paginaAtual + " / " );
		contentPane.add(lblPaginaAtual);

		txtNome = new JTextField();
		txtNome.setBounds(80, 35, 160, 30);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtPeso = new JTextField();
		txtPeso.setBounds(80, 67, 160, 30);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);

		cbCor = new JComboBox(cores);
		cbCor.setBounds(300, 37, 160, 30);
		contentPane.add(cbCor);

		dtInicioCadastro = new DatePicker();
		dtInicioCadastro.setBounds(80, 130, 378, 30);
		contentPane.add(dtInicioCadastro);

		dtFimCadastro = new DatePicker();
		dtFimCadastro.setBounds(80, 170, 378, 30);
		contentPane.add(dtFimCadastro);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 205, 462, 14);
		contentPane.add(separator);

		btnGerarXls = new JButton("Gerar XLS");
		btnGerarXls.setBounds(100, 404, 117, 29);
		btnGerarXls.setEnabled(false);
		contentPane.add(btnGerarXls);
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				
				
				
				
				
			}
		});

		btnAnterior = new JButton(" < Anterior");
		btnAnterior.setBounds(80, 457, 110, 23);
		contentPane.add(btnAnterior);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
					mostrarBotoesPaginacao();
					consultarProdutos();
				}
			}
		});


		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(160, 215, 150, 40);
		contentPane.add(btnConsultar);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarProdutos();
			}
		});

		btnProximo = new JButton("Proximo >");
		btnProximo.setBounds(255, 457, 107, 23);
		contentPane.add(btnProximo);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				mostrarBotoesPaginacao();
				consultarProdutos();
			}
		});

		btnGerarPdf = new JButton("Gerar PDF");
		btnGerarPdf.setBounds(248, 404, 117, 29);
		contentPane.add(btnGerarPdf);
		btnGerarPdf.setEnabled(false);

		tblProdutos = new JTable();
		tblProdutos.setBounds(10, 259, 444, 174);
		tblProdutos.setModel(new DefaultTableModel(new String[][] {
							 { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" },},
				new String[] { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }));
		contentPane.add(tblProdutos);
	}

	protected void consultarProdutos() {
		lblPaginaAtual.setText(paginaAtual + " / " + totalPaginas);

		ProdutoController controlador = new ProdutoController();
		seletor = new ProdutoSeletor();

		seletor.setPagina(paginaAtual);
		seletor.setLimite(TAMANHO_PAGINA);

		// Preenche os campos de filtro da tela no seletor
		if (cbCor.getSelectedIndex() > 0) {
			seletor.setCorProduto(cbCor.getSelectedItem().toString());
		}

		seletor.setNomeProduto(txtNome.getText());

		if (txtPeso.getText() != null && !txtPeso.getText().isEmpty()) {
			seletor.setPesoProduto(Double.parseDouble(txtPeso.getText()));
		} else {
			seletor.setPesoProduto(-1.0);
		}

		seletor.setDataInicioCadastro(dtInicioCadastro.getDate());
		seletor.setDataFimCadastro(dtFimCadastro.getDate());

		List<Produto> produtos = controlador.listarProdutos(seletor);
		atualizarTotalPaginas(produtos.size());
		atualizarTabelaProdutos(produtos);

	}

	private void atualizarTotalPaginas(int totalProdutos) {
		
		int resto = (totalPaginas % TAMANHO_PAGINA);
		int quociente = (totalPaginas / TAMANHO_PAGINA); // igual
		totalPaginas = quociente + (resto > 0 ? 1 : 0);
	}

	protected void atualizarTabelaProdutos(List<Produto> produtos) {
		// atualiza o atributo produtosConsultados
		produtosConsultados = produtos;

		// TODO descomentar quando chegarmos em relatórios
		// btnGerarXls.setEnabled(produtos != null && produtos.size() > 0);
		// btnGerarPdf.setEnabled(produtos != null && produtos.size() > 0);

		// Limpa a tabela
		tblProdutos.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }, },
				new String[] { "#", "Nome", "Marca", "Peso", "Dt. Cadastro" }));

		DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();

		for (Produto produto : produtos) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do produto
			// na ORDEM do cabeçalho da tabela
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormatada = produto.getDataCadastro().format(formatter);

			String[] novaLinha = new String[] { produto.getId() + "", produto.getNome(), produto.getFabricante(),
					produto.getPeso() + "", dataFormatada };
			modelo.addRow(novaLinha);
		}

	}

	protected void mostrarBotoesPaginacao() {
		if (paginaAtual == 1) {
			btnAnterior.setEnabled(false);	
		} else if (paginaAtual > 1) {
			btnAnterior.setEnabled(true);
		}
	}
}