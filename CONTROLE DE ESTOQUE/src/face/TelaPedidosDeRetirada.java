package face;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTabbedPane;

import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

import java.awt.Label;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import utilitarios.ConectaBanco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class TelaPedidosDeRetirada extends JFrame {
static boolean aberto7;
	private JPanel contentPane;
	JTabbedPane table = new JTabbedPane();
	ConectaBanco conexao = new ConectaBanco();
	private JTextField txtCod;
	private JTextField txtFunc;
	private JTextField txtQtd;
	private JTextField txtSetor;
	private JTextField txtData;
	private JTextField txtStatus;
	private JTextField txtItem;
	private JTextField txtEmpresa;
	
	public TelaPedidosDeRetirada() {
		super("Solicitações");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Pedidos de Retirada", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		aberto7 = true;
		
		JLabel lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				aberto7 = false;
				dispose();
			}
		});
		lblFechar.setIcon(new ImageIcon(TelaCadastrarFuncionarios.class.getResource("/face/fechar.png")));
		lblFechar.setBounds(867, 14, 40, 35);
		contentPane.add(lblFechar);
		
		JLabel lblPedidosDeRetirada = new JLabel("Pedidos de Retirada");
		lblPedidosDeRetirada.setBounds(139, 34, 642, 59);
		lblPedidosDeRetirada.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidosDeRetirada.setFont(new Font("Stencil Std", Font.PLAIN, 25));
		contentPane.add(lblPedidosDeRetirada);
		
		Label lblPedidos = new Label("C\u00F3digo do Pedido");
		lblPedidos.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblPedidos.setAlignment(Label.CENTER);
		lblPedidos.setBounds(342, 99, 113, 22);
		contentPane.add(lblPedidos);
		
		final JComboBox cbPedidosFeitos = new JComboBox();
		cbPedidosFeitos.setBounds(476, 101, 47, 20);
		contentPane.add(cbPedidosFeitos);
		
		
	
		
		Label lblCodigoDoPedido = new Label("C\u00F3digo do Pedido");
		lblCodigoDoPedido.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblCodigoDoPedido.setAlignment(Label.CENTER);
		lblCodigoDoPedido.setBounds(247, 161, 113, 22);
		contentPane.add(lblCodigoDoPedido);
		
		Label lblFuncionario = new Label("Funcion\u00E1rio");
		lblFuncionario.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblFuncionario.setAlignment(Label.CENTER);
		lblFuncionario.setBounds(247, 205, 113, 22);
		contentPane.add(lblFuncionario);
		
		Label lblSetor = new Label("Setor");
		lblSetor.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSetor.setAlignment(Label.CENTER);
		lblSetor.setBounds(247, 242, 113, 22);
		contentPane.add(lblSetor);
		
		Label lblQuantidade = new Label("Quantidade");
		lblQuantidade.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblQuantidade.setAlignment(Label.CENTER);
		lblQuantidade.setBounds(247, 320, 113, 22);
		contentPane.add(lblQuantidade);
		
		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setBounds(372, 161, 62, 20);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		Label lblDataHora = new Label("Data e Hora");
		lblDataHora.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblDataHora.setAlignment(Label.CENTER);
		lblDataHora.setBounds(247, 390, 113, 22);
		contentPane.add(lblDataHora);
		
		Label lblStatus = new Label("Status");
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblStatus.setAlignment(Label.CENTER);
		lblStatus.setBounds(247, 425, 113, 22);
		contentPane.add(lblStatus);
		
		txtFunc = new JTextField();
		txtFunc.setEditable(false);
		txtFunc.setColumns(10);
		txtFunc.setBounds(372, 205, 244, 20);
		contentPane.add(txtFunc);
		
		txtQtd = new JTextField();
		txtQtd.setEditable(false);
		txtQtd.setColumns(10);
		txtQtd.setBounds(372, 322, 244, 20);
		contentPane.add(txtQtd);
		
		txtSetor = new JTextField();
		txtSetor.setEditable(false);
		txtSetor.setColumns(10);
		txtSetor.setBounds(372, 242, 244, 20);
		contentPane.add(txtSetor);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(372, 392, 244, 20);
		contentPane.add(txtData);
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(372, 427, 244, 20);
		contentPane.add(txtStatus);
		
		Button btnDarBaixa = new Button("Dar baixa no pedido");
		btnDarBaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDarBaixa.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnDarBaixa.setBounds(491, 465, 125, 30);
		contentPane.add(btnDarBaixa);
		
		Label lblItem = new Label("Item");
		lblItem.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblItem.setAlignment(Label.CENTER);
		lblItem.setBounds(247, 284, 113, 22);
		contentPane.add(lblItem);
		
		txtItem = new JTextField();
		txtItem.setEditable(false);
		txtItem.setColumns(10);
		txtItem.setBounds(372, 286, 244, 20);
		contentPane.add(txtItem);
		
	
		
		
		try{
			conexao.conexao();
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT id_pedido FROM pedido_item where status_pedido='true';");
			while (rs.next()){
				cbPedidosFeitos.addItem(rs.getString(1));}
			} catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Falha ao obter pedidos");
			}
			finally {
				conexao.desconecta();
			}
		
		
		JButton btnOkPedido = new JButton("ok");
		btnOkPedido.setBounds(547, 99, 89, 23);
		contentPane.add(btnOkPedido);
		
		Label lblEmpresa = new Label("Empresa");
		lblEmpresa.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblEmpresa.setAlignment(Label.CENTER);
		lblEmpresa.setBounds(247, 353, 113, 22);
		contentPane.add(lblEmpresa);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setEditable(false);
		txtEmpresa.setColumns(10);
		txtEmpresa.setBounds(372, 355, 244, 20);
		contentPane.add(txtEmpresa);
		btnOkPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexao.conexao();
					Statement stm = conexao.conn.createStatement();
				
					ResultSet rs = stm.executeQuery("SELECT id_pedido,funcionario,setor_funcionario,nome_item,qtd_pedida,data_hora,status_pedido,empresa FROM pedido_item where id_pedido='"+cbPedidosFeitos.getSelectedItem()+"';");
					while (rs.next()){
					txtCod.setText(rs.getString(1));
					txtFunc.setText(rs.getString(2));
					txtSetor.setText(rs.getString(3));
					txtItem.setText(rs.getString(4));
					txtQtd.setText(rs.getString(5));
					txtData.setText(rs.getString(6));
					txtStatus.setText(rs.getString(7));
					txtEmpresa.setText(rs.getString(8));
					}
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao consultar pedido");
				}
				
			}
		});
	
		btnDarBaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque -"+txtQtd.getText()+" WHERE nome= '"+txtItem.getText()+"';");
					pst.executeUpdate();
					
					PreparedStatement pst2 = conexao.conn.prepareStatement("UPDATE pedido_item SET status_pedido = 'false' WHERE id_pedido= '"+txtCod.getText()+"';");
					pst2.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "SUCESSO!");

				} 
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao dar baixa no pedido");
				}
				
				
				
				
				
				
			}
		});
	
		
				
			

		
}
}
