package view.exercicio06.cliente;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInternaListagemCliente extends JPanel{
	private JTextField txtNome;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JButton btnFechar;
	
	public TelaInternaListagemCliente() {
		setBounds(100, 100, 450, 341);
		setLayout(new MigLayout("", "[81px][10px][166px][10px][106px][10px][191px]", "[24px][21px][33px][313px,grow,fill]"));
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "cell 0 0,grow");
		
		txtNome = new JTextField();
		add(txtNome, "cell 2 0,grow");
		txtNome.setColumns(10);
		
		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ:");
		add(lblCpfcnpj, "cell 0 1,grow");
		
		textField = new JTextField();
		textField.setColumns(10);
		add(textField, "cell 2 1,grow");
		
		JLabel lblDtNascimento = new JLabel("Dt. Nascimento:");
		add(lblDtNascimento, "cell 4 0,grow");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		add(textField_1, "cell 6 0,grow");
		
		Object[][] data = new Object[][] {{"#", "Nome", "CPF", "Email", "Telefone"},};
		Object[] columnNames = new String[] {"#", "Nome", "CPF", "Email", "Telefone"};
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(e -> {
			System.exit(0);
		});
		add(btnFechar, "cell 6 2,grow");

		
		table = new JTable();
		add(table, "cell 0 3 7 1,grow");
		table.setModel(new DefaultTableModel(data, columnNames));
		
		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.addActionListener(e -> {
			
		});
		add(btnPesquisar, "cell 0 2 5 1,grow");
	}

}
