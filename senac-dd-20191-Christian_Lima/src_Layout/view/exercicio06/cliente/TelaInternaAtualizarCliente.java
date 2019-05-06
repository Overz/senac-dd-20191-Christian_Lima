package view.exercicio06.cliente;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.vo.Pessoa;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaInternaAtualizarCliente extends JInternalFrame{

	private JTextField textField;
	private JTable tableClientes;
	private JButton btnApagar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaAtualizarCliente window = new TelaInternaAtualizarCliente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaAtualizarCliente() {
		setBounds(100, 100, 482, 403);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		getContentPane().setLayout(new MigLayout("", "[60.00px][grow][grow][79px]", "[29.00px][29.00][40.00][grow][fill]"));
		initialize();
	}

	private void initialize() {
		
		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0,alignx center,growy");
		
		JLabel lblCpf = new JLabel("CPF:");
		getContentPane().add(lblCpf, "cell 0 1,alignx center,growy");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 1 0 2 1,grow");
		textField.setColumns(10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		getContentPane().add(formattedTextField, "cell 1 1 2 1,grow");
		
		JButton btnPesquisar01 = new JButton("Pesquisar");
		getContentPane().add(btnPesquisar01, "cell 3 0 1 2,grow");
		
		JButton btnSalvar = new JButton("Salvar Dados");
		getContentPane().add(btnSalvar, "cell 0 2 2 1,grow");
		
		JButton btnLimpar = new JButton("Limpar Campos");
		getContentPane().add(btnLimpar, "flowx,cell 2 2,grow");
		
		JButton btnPesquisarTodos = new JButton("Pesquisar Todos");
		getContentPane().add(btnPesquisarTodos, "cell 3 2,grow");
		
		Object[][] data = new Object[][] {{"Nome", "CPF", "Email", "Telefone"},};
		Object[] columnNames = new String[] {"Nome", "CPF", "Email", "Telefone"};
		
		tableClientes = new JTable();
		getContentPane().add(tableClientes, "cell 0 3 4 1,grow");
		tableClientes.setModel(new DefaultTableModel(data, columnNames));
		
		JCheckBox chkbApagarCliente = new JCheckBox("Apagar Clientes");
		getContentPane().add(chkbApagarCliente, "cell 1 4,grow");
		
		if (chkbApagarCliente.isSelected()) {
			JOptionPane.showMessageDialog(null, "Atenção, Cuidado ao apagar um Clientes!\nIsso pode Causar Problemas!");
			btnApagar.setEnabled(true);
		}
		
		btnApagar = new JButton("APAGAR");
		btnApagar.addActionListener(e -> {
			//TODO Pegar a linha/coluna selecionada e alterar diretamente na tabela
			int[] id = tableClientes.getSelectedRows();
		});
		btnApagar.setEnabled(false);
		getContentPane().add(btnApagar, "cell 2 4,grow");
		
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }
	
	

}
