package face;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utilitarios.ConectaBanco;
import javax.swing.border.TitledBorder;

public class TelaEstocar extends JFrame {
	static boolean aberto6;
	private JPanel contentPane;
	private JTextField txtQuantidade;
	String permissao[] = {"","P","PP","M","G","GG","36","37","38","39","40","41","42","43","44"};
	String cores[] = {"", "AMARELO", "AZUL", "PRETO", "MAGENTA","BRANCO", "VERDE", "VERMELHO"};
	JLabel lblItem = new JLabel("Item");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFazerPedidos frame = new TelaFazerPedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEstocar() {
		super("Estocar!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Estocar Itens", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		this.setFocusableWindowState(true); 
		
		contentPane.setLayout(null);
		
		aberto6 = true;
		
		JLabel lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				aberto6 = false;
				dispose();
			}
		});
		lblFechar.setIcon(new ImageIcon(TelaCadastrarFuncionarios.class.getResource("/face/fechar.png")));
		lblFechar.setBounds(867, 14, 40, 35);
		contentPane.add(lblFechar);

		final JRadioButton rdbtnEpi = new JRadioButton("EPI");
		rdbtnEpi.setBounds(234, 118, 50, 23);
		contentPane.add(rdbtnEpi);
		
		final JRadioButton rdbtnLimpeza = new JRadioButton("LIMPEZA");
		rdbtnLimpeza.setBounds(286, 118, 79, 23);
		contentPane.add(rdbtnLimpeza);
		
		final JRadioButton rdbtnEscritorio = new JRadioButton("ESCRIT\u00D3RIO");
		rdbtnEscritorio.setBounds(364, 118, 100, 23);
		contentPane.add(rdbtnEscritorio);
		
		final JRadioButton rdbtnUniforme = new JRadioButton("UNIFORME");
		rdbtnUniforme.setBounds(466, 117, 89, 24);
		contentPane.add(rdbtnUniforme);
		
		final JRadioButton rdbtnTonner = new JRadioButton("TONNER");
		rdbtnTonner.setBounds(557, 118, 73, 23);
		contentPane.add(rdbtnTonner);
		
		JLabel lblInformeOTipo = new JLabel("Informe o tipo de material que voc\u00EA deseja estocar");
		lblInformeOTipo.setBounds(292, 95, 335, 14);
		lblInformeOTipo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblInformeOTipo);
		
		ButtonGroup grupoDeItens = new ButtonGroup();
		grupoDeItens.add(rdbtnTonner);
		grupoDeItens.add(rdbtnEpi);
		grupoDeItens.add(rdbtnEscritorio);
		grupoDeItens.add(rdbtnLimpeza);
		grupoDeItens.add(rdbtnUniforme);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(261, 185, 128, 29);
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(391, 185, 50, 29);
		txtQuantidade.setColumns(10);
		contentPane.add(txtQuantidade);
		
				
		final JComboBox cbItemEpi = new JComboBox();
		cbItemEpi.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemEpi);
		cbItemEpi.setVisible(false);
		
		
		lblItem.setBounds(246, 148, 100, 26);
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblItem);
		
		JLabel lblPedidoDeItens = new JLabel("Estocar Itens");
		lblPedidoDeItens.setBounds(140, 40, 639, 26);
		lblPedidoDeItens.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidoDeItens.setFont(new Font("Stencil", Font.PLAIN, 27));
		contentPane.add(lblPedidoDeItens);
		 
		
		final JComboBox cbItemLimpeza = new JComboBox();
		cbItemLimpeza.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemLimpeza);
		cbItemLimpeza.setVisible(false);
		
		final JComboBox cbItemEscritorio = new JComboBox();
		cbItemEscritorio.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemEscritorio);
		cbItemEscritorio.setVisible(false);
		
		final JComboBox cbItemUniforme = new JComboBox();
		cbItemUniforme.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemUniforme);
		cbItemUniforme.setVisible(false);
		
		final JComboBox cbItemTonner = new JComboBox();
		cbItemTonner.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemTonner);
		cbItemTonner.setVisible(false);
		
		final JComboBox cbItemDefault = new JComboBox();
		cbItemDefault.setBounds(334, 148, 302, 25);
		contentPane.add(cbItemDefault);
		cbItemDefault.setVisible(true);
		
		final boolean itensEpi = false;
		final boolean itensLimpeza = false;
		final boolean itensEscritorio=false;
		final boolean itensUniforme=false;
		final boolean itensTonner=false;

		final JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectaBanco conexao = new ConectaBanco();
				conexao.conexao();
				String valor = txtQuantidade.getText();
				Integer.parseInt(valor);
				
				try {
					if(cbItemEscritorio.isShowing()){
						PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque + "+valor+" WHERE nome='"+cbItemEscritorio.getSelectedItem()+"';");
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Estocado com sucesso!");	
						}
						
					if(cbItemEpi.isShowing()){
						PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque + "+valor+" WHERE nome='"+cbItemEpi.getSelectedItem()+"';");
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Estocado com sucesso!");	
						}
					
					if(cbItemLimpeza.isShowing()){
						PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque + "+valor+" WHERE nome='"+cbItemLimpeza.getSelectedItem()+"';");
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Estocado com sucesso!");	
						}
					
					if(cbItemTonner.isShowing()){
						PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque + "+valor+" WHERE nome='"+cbItemTonner.getSelectedItem()+"';");
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Estocado com sucesso!");	
						}
						
					if(cbItemUniforme.isShowing()){
						PreparedStatement pst = conexao.conn.prepareStatement("UPDATE item SET qtd_estoque = qtd_estoque + "+valor+" WHERE nome='"+cbItemUniforme.getSelectedItem()+"';");
						pst.executeUpdate();

						JOptionPane.showMessageDialog(null, "Estocado com sucesso!");	
						}
						} 
				
				catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "valor = "+valor+"nome= "+cbItemEscritorio.getSelectedItem());

							JOptionPane.showMessageDialog(null, "Erro ao estocar!"+e1.getMessage());
						}
				finally {
						conexao.desconecta();
						}
						
					}
				});
		
		

		btnConfirmar.setBounds(412, 225, 100, 36);
		contentPane.add(btnConfirmar);
		
	JButton btnOkItem = new JButton("OK");
	btnOkItem.setBounds(636, 118, 61, 23);
	contentPane.add(btnOkItem);
	
	
	btnOkItem.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			ConectaBanco conexao = new ConectaBanco();					
			String query = null;
			
			String q1 = "SELECT nome from item WHERE categoria='EPI'";
			String q2 = "SELECT nome from item WHERE categoria='LIMPEZA'";
			String q3 = "SELECT nome from item WHERE categoria='ESCRITORIO'";
			String q4 = "SELECT nome from item WHERE categoria='UNIFORME'";
			String q5 = "SELECT nome from item WHERE categoria='TONNER'";

				
			if (rdbtnEpi.isSelected()){
				
				if (cbItemEpi.isShowing())
					JOptionPane.showMessageDialog(null, "Esse item ja foi selecionado");				
				else {
					cbItemDefault.setVisible(false);
					cbItemEpi.setVisible(true);
					cbItemEscritorio.setVisible(false);
					cbItemLimpeza.setVisible(false);
					cbItemTonner.setVisible(false);
					cbItemUniforme.setVisible(false);
				
				
					query = q1;
			
					try {
					conexao.conexao();
					Statement stm = conexao.conn.createStatement();
					ResultSet rs = stm.executeQuery(query);
					
					while (rs.next()){
						cbItemEpi.addItem(rs.getString(1));
					}
					}
					catch (SQLException exx){
					JOptionPane.showMessageDialog(null, "Erro ao obter itens"+exx.getMessage());
					}finally{
						conexao.desconecta();
					}
					
				}
			} // fecha EPI - isSelected
			
			if (rdbtnLimpeza.isSelected()){
				if (cbItemLimpeza.isShowing()){
					JOptionPane.showMessageDialog(null, "Esse item ja foi selecionado!");}
				else {
					cbItemDefault.setVisible(false);
					cbItemEpi.setVisible(false);
					cbItemEscritorio.setVisible(false);
					cbItemLimpeza.setVisible(true);
					cbItemTonner.setVisible(false);
					cbItemUniforme.setVisible(false);
					query = q2;
					try{
						conexao.conexao();
						Statement stm = conexao.conn.createStatement();
						ResultSet rs = stm.executeQuery(query);
						while (rs.next()){
							cbItemLimpeza.addItem(rs.getString(1));
						}
					}catch (SQLException exx){
						JOptionPane.showMessageDialog(null, "Erro ao obter itens"+exx.getMessage());
					}finally{
					conexao.desconecta();
					}
				}
			}

			if(rdbtnEscritorio.isSelected()){
				if (cbItemEscritorio.isShowing()){
					JOptionPane.showMessageDialog(null, "Esse item ja foi selecionado!");}
				else {
			
					if (rdbtnEscritorio.isSelected()){
						cbItemDefault.setVisible(false);
						cbItemEpi.setVisible(false);
						cbItemEscritorio.setVisible(true);
						cbItemLimpeza.setVisible(false);
						cbItemTonner.setVisible(false);
						cbItemUniforme.setVisible(false);
						query = q3;
						try{
						conexao.conexao();
						Statement stm = conexao.conn.createStatement();
						ResultSet rs = stm.executeQuery(query);
						while (rs.next()){
							cbItemEscritorio.addItem(rs.getString(1));
						}
							
						}catch (SQLException exx){
							JOptionPane.showMessageDialog(null, "Erro ao obter itens"+exx.getMessage());
						}finally{
							conexao.desconecta();
						}
					}
				}
			}
			
			if (rdbtnUniforme.isSelected()){
				if (cbItemUniforme.isShowing()){
				JOptionPane.showMessageDialog(null, "Esse item ja foi selecionado!");}
					else {			
						cbItemDefault.setVisible(false);
						cbItemEpi.setVisible(false);
						cbItemEscritorio.setVisible(false);
						cbItemLimpeza.setVisible(false);
						cbItemTonner.setVisible(false);
						cbItemUniforme.setVisible(true);
						query = q4;
						try{
							conexao.conexao();
							Statement stm = conexao.conn.createStatement();
							ResultSet rs = stm.executeQuery(query);
					
							while (rs.next()){
								cbItemUniforme.addItem(rs.getString(1));
							}
						}
						catch (SQLException exx){
							JOptionPane.showMessageDialog(null, "Erro ao obter itens"+exx.getMessage());
						}
						finally{
							conexao.desconecta();
						}
					}
			}
			
			if (rdbtnTonner.isSelected()){
				if (cbItemTonner.isShowing()){
					JOptionPane.showMessageDialog(null, "Esse item ja foi selecionado!");}
				
					else {
			
						cbItemDefault.setVisible(false);
						cbItemEpi.setVisible(false);
						cbItemEscritorio.setVisible(false);
						cbItemLimpeza.setVisible(false);
						cbItemTonner.setVisible(true);
						cbItemUniforme.setVisible(false);
						query = q5;
						try{
							conexao.conexao();
							Statement stm = conexao.conn.createStatement();
							ResultSet rs = stm.executeQuery(query);
					
							while (rs.next()){
								cbItemTonner.addItem(rs.getString(1));
							}
						}catch (SQLException exx){
							JOptionPane.showMessageDialog(null, "Erro ao obter itens"+exx.getMessage());
						}finally{
							conexao.desconecta();
						}
					}
				}
			
			
			
		} // fecha public void actin listener
	}); // fecha new acticon listener

	}
}
	
				
				
				
				
				
				
				
				
				
				
