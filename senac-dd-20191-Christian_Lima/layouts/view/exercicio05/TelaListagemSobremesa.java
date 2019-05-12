package layouts.view.exercicio05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class TelaListagemSobremesa {

	private JFrame frmConsultaDeSobremesas;
	private JTable tblSobremesas;
	private JButton btnLimpar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemSobremesa window = new TelaListagemSobremesa();
					window.frmConsultaDeSobremesas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListagemSobremesa() {
		initialize();
	}

	private void initialize() {
		frmConsultaDeSobremesas = new JFrame();
		frmConsultaDeSobremesas.setTitle("Consulta de sobremesas");
		frmConsultaDeSobremesas.setBounds(100, 100, 520, 343);
		frmConsultaDeSobremesas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		tblSobremesas = new JTable();
		limparTabela();
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparTabela();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmConsultaDeSobremesas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpar, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
							.addGap(9))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tblSobremesas, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLimpar)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tblSobremesas, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
					.addContainerGap())
		);
		frmConsultaDeSobremesas.getContentPane().setLayout(groupLayout);
	}

	private void limparTabela() {
		tblSobremesas.setModel(new DefaultTableModel(
				new Object[][] {
					{"id", "Nome", "Valor", "Light?"},
				},
				new String[] {
					"id", "Nome", "Valor", "Light?"
				}
			));
	}
}
