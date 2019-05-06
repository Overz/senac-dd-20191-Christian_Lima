package view.exercicio06.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TelaInternaListagemCliente extends JInternalFrame{
	private JTable tbListagem;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInternaListagemCliente window = new TelaInternaListagemCliente();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaInternaListagemCliente() {
		setBounds(100, 100, 560, 418);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		initialize();
	}

	private void initialize() {
		
		JLabel lblCpfcnpj = new JLabel("CPF/CNPJ:");
		lblCpfcnpj.setBounds(10, 11, 73, 33);
		getContentPane().add(lblCpfcnpj);
		
		JFormattedTextField ftfCPF_CNPJ = new JFormattedTextField();
		ftfCPF_CNPJ.setBounds(116, 11, 151, 33);
		getContentPane().add(ftfCPF_CNPJ);
		
		Object[][] data = new Object[][] {{"Nome", "CPF", "Email", "Telefone"},};
		Object[] columnNames = new String[] {"Nome", "CPF", "Email", "Telefone"};
		
		tbListagem = new JTable();
		tbListagem.setBounds(10, 112, 524, 265);
		tbListagem.setModel(new DefaultTableModel(data, columnNames));
		getContentPane().add(tbListagem);
		
		JButton btnListar = new JButton("Buscar");
		btnListar.setBounds(188, 55, 188, 46);
		getContentPane().add(btnListar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(277, 11, 257, 33);
		getContentPane().add(comboBox);
		
		
		
	}
}
