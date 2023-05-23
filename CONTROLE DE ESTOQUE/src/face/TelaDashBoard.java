package face;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utilitarios.ConectaBanco;

public class TelaDashBoard extends JFrame {

	static boolean aberto5;
	private JPanel contentPane;
	
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable TableDashBoard = new JTable(modelo);

	JScrollPane scrollPane = new JScrollPane(TableDashBoard);
	
	public TelaDashBoard() {
		super("DashBoard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "DashBoard Estoque", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aberto5 = true;
		
		JLabel lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				aberto5 = false;
				dispose();
			}
		});

		final ConectaBanco conexao = new ConectaBanco();

		
		lblFechar.setIcon(new ImageIcon(TelaCadastrarFuncionarios.class.getResource("/face/fechar.png")));
		lblFechar.setBounds(867, 14, 40, 35);
		contentPane.add(lblFechar);
		
		JLabel lblProdutosEmEstoque = new JLabel("Produtos Em Estoque");
		lblProdutosEmEstoque.setFont(new Font("Stencil Std", Font.PLAIN, 18));
		lblProdutosEmEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutosEmEstoque.setBounds(10, 31, 920, 27);
		contentPane.add(lblProdutosEmEstoque);
		scrollPane.setViewportView(TableDashBoard);
		
		JScrollPane ScrolPaneDashBoard = new JScrollPane();
		ScrolPaneDashBoard.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ScrolPaneDashBoard.setBounds(20, 69, 888, 475);
		contentPane.add(ScrolPaneDashBoard);

		TableDashBoard = new JTable();
		TableDashBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableDashBoard.setColumnSelectionAllowed(true);
		ScrolPaneDashBoard.setViewportView(TableDashBoard);
				
				
				TableDashBoard.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"PRODUTO", "CATEGORIA", "QUANTIDADE EM ESTOQUE", "ESTOQUE MINIMO"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, Object.class, Integer.class, Integer.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				TableDashBoard.getColumnModel().getColumn(0).setPreferredWidth(330);
				TableDashBoard.getColumnModel().getColumn(1).setPreferredWidth(146);
				TableDashBoard.getColumnModel().getColumn(2).setPreferredWidth(150);
				TableDashBoard.getColumnModel().getColumn(3).setPreferredWidth(112);
						
				
				try {
					conexao.conexao();
					String sql = "SELECT nome,categoria,qtd_estoque,estoque_minimo FROM item ORDER BY categoria";  
					Statement stm = conexao.conn.createStatement();
					ResultSet rs = stm.executeQuery(sql);
					rs.next();
					do{
					DefaultTableModel dtmDash = (DefaultTableModel) TableDashBoard.getModel();
					dtmDash.addRow(new Object [] {rs.getObject("nome"), rs.getObject("categoria"), rs.getObject("qtd_estoque"), rs.getObject("estoque_minimo")});
					} while(rs.next());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Não foi possivel mostrar!"+e.getMessage());
				} finally {
					conexao.desconecta();
				}
				
				Button btnAtualizar = new Button("ATUALIZAR");
				btnAtualizar.setBounds(768, 555, 140, 32);
				contentPane.add(btnAtualizar);
				btnAtualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						((DefaultTableModel) TableDashBoard.getModel()).setNumRows(0); 
						TableDashBoard.updateUI();					
						
						try {
							conexao.conexao();
							String sql = "SELECT nome,categoria,qtd_estoque,estoque_minimo FROM item ORDER BY categoria";  
							Statement stm = conexao.conn.createStatement();
							ResultSet rs = stm.executeQuery(sql);
							rs.next();
							do{
							DefaultTableModel dtmDash = (DefaultTableModel) TableDashBoard.getModel();
							dtmDash.addRow(new Object [] {rs.getObject("nome"), rs.getObject("categoria"), rs.getObject("qtd_estoque"), rs.getObject("estoque_minimo")});
							} while(rs.next());
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Não foi possivel mostrar!"+e.getMessage());
						} finally {
							conexao.desconecta();
						}
					}
				});
}
	}
