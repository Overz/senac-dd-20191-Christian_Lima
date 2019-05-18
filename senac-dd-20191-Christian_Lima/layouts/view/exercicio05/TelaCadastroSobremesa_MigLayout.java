package layouts.view.exercicio05;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;




/**
 * @Class Layout - MigLauout
 * 
 * ###############################################################################################################
 * ##	MigLayout é dinamico, mas também permite possibilidade de ser Static igual ao AbsoluteLayout.			##
 * ##	MigLayout trabalha com Colunas(col) e Linhas(row), permitindo diversos parametros para seus tamanhos,	##
 * ##	como Pixel, Porcentagem, Milimetros, Centimetros, Calculos, valores Nullos, e etc.						##
 * ###############################################################################################################
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * UnitValue A value that represents a size. Normally it consist of a value (integer or float) and the unit type (e.g. "mm"). MigLayout support defining custom unit types and there are some special ones built in.
 * These are listed below and some have a context to which they can appear. UnitValues can be quite rich expressions, like: "(10px + 0.25*((pref/2)-10))".
 * The currently supported unit types are:
 * 
 *	"" - No unit specified. This is the default unit and pixels will be used by default. Default unit can be set with PlatformDefaults.setDefaultHorizontal/VerticalUnit(int). E.g. "10"
 *	px - Pixels. Normal pixels mapped directly to the screen. E.g. "10px" or "10"
 * 	% - A percentage of the container's size. May also be used for alignments where for instance 50% means "centered". E.g. "100%"
 *	lp - Logical Pixels. If the normal font is used on the platform this maps 1:1 to pixels. If larger fonts are used the logical pixels gets proportionally larger. Used instead of Dialog Units. E.g. "10lp"
 *	pt - Points. 1/72:th of an inch. A unit normally used for printing. Will take the screen DPI that the component is showing on into account. E.g. "10pt"
 *	mm - Millimeters. Will take the screen DPI that the component is showing on into account. E.g. "10mm"
 *	cm - Centimeters. Will take the screen that the component is showing on DPI into account. E.g. "10cm"
 *	in - Inches. Will take the screen DPI that the component is showing on into account. E.g. "10.4in"
 *	sp - Percentage of the screen. Will take the pixel screen size that the component is showing on into account. 100.0 is the right/bottom edge of the screen. E.g. "sp 70" or "sp 73.627123"
 *	al - Visual bounds alignment. "0al" is left aligned, "0.5al" is centered and "1al" is right aligned. This unit is used with absolute positioning. E.g. "0.2al"
 *	n/null - Null value. Denotes the absence of a value. E.g. "n" or "null"
 * 	
 *	These are the unit values that are converted to pixels by the default PlatformConverter. The converted pixel sizes can be different for the vertical and horizontal dimension.
 *	
 *	-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *	
 *	r/rel/related - Indicates that two components or columns/rows are considered related. The exact pixel size is determined by the platform default. E.g. "r" or "related"
 *	u/unrel/unrelatedated - Indicates that two components or columns/rows are considered unrelated. The exact pixel size is determined by the platform default. E.g. "u" or "unrelated"
 * 	p/para/paragraph - A spacing that is considered appropriate for a paragraph is used. The exact pixel size is determined by the platform default. E.g. "para" or "paragraph"
 *	i/ind/indent - A spacing that is considered appropriate for indent. The exact pixel size is determined by the platform default. E.g. "i" or "indent"
 *	
 *	These are the unit values that can be specified as a reference to component(s) sizes. These can be used on column/row constraint's size and as a reference in component constaint expressions.
 *	
 *	-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 *	min/minimum - A reference to the largest minimum size of the column/row. E.g. "min" or "minimum"
 *	p/pref/preferred - A reference to the largest preferred size of the column/row. E.g. "p" or "pref" or "preferred"
 *	max/maximum - A reference to the smallest maximum size of the column/row. E.g. "max" or "maximum"
 *	
 *	These are the unit values that can be specified for a component's width. These can only be used on the width component constraints size.
 *	
 *	-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 *	button - A reference to the platform minimum size for a button. E.g. "wmin button"
 *	Always on button.
 *
 *	##########################################################################
 *	## More Information:													##
 *	## http://www.migcalendar.com/miglayout/mavensite/docs/cheatsheet.html	##
 *	##########################################################################
 *
 */



public class TelaCadastroSobremesa_MigLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtPreco;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroSobremesa_MigLayout frame = new TelaCadastroSobremesa_MigLayout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public TelaCadastroSobremesa_MigLayout() {
		setTitle("Cadastro de nova sobremesa");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new MigLayout("", "[left][grow,left]", "[25px:null:100px,grow,top][25px:n:100px,grow,fill][25px:n:100px,grow,fill][25px:null:100px,grow,bottom]"));

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNome, "cell 0 0,growx,aligny baseline");

		JLabel lblPreco = new JLabel("Pre\u00E7o. R$: ");
		lblPreco.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblPreco, "cell 0 1,alignx left,aligny center");

		txtNome = new JTextField();
		contentPane.add(txtNome, "cell 1 0,grow");
		txtNome.setColumns(10);
		

		txtPreco = new JTextField();
		txtPreco.setToolTipText("Informe um valor separado com vÃ­rgula");
		contentPane.add(txtPreco, "cell 1 1,grow");
		txtPreco.setColumns(10);

		final JCheckBox checkLight = new JCheckBox("Light");
		checkLight.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(checkLight, "cell 1 2,alignx left,aligny center");

		JButton btnSalvar = new JButton("Salvar");
		contentPane.add(btnSalvar, "cell 1 3,grow");
		Dimension MaxSize = contentPane.getMaximumSize();
		Dimension MinSize = contentPane.getMinimumSize();
		contentPane.setMaximumSize(null);
	}
}