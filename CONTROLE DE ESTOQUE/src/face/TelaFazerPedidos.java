package face;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
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

public class TelaFazerPedidos extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuantidade;
	private JTextField txtFuncionario;
	private JTextField txtSetor;
	String permissao[] = {"","P","PP","M","G","GG","36","37","38","39","40","41","42","43","44"};
	String cores[] = {"", "AMARELO", "AZUL", "PRETO", "MAGENTA","BRANCO", "VERDE", "VERMELHO"};
	String empresas[] = {"QUALYLAB ANALISES AMBIENTAIS", "C&M CONSULTORIA", "FENIX EMERGENCIAS","GRUPO QUALITY AMBIENTAL"};
	JLabel lblItem = new JLabel("Item");
	static boolean aberto;
	static boolean aberto2;

	public TelaFazerPedidos() {
		super("Fazer Pedidos!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Fazer Pedidos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		this.setFocusableWindowState(true); 
		
		contentPane.setLayout(null);
		
		aberto = true;
		aberto2 = true;
		
		JLabel lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				aberto = false;
				aberto2 = false;
				dispose();
			}
		});
		lblFechar.setIcon(new ImageIcon(TelaCadastrarFuncionarios.class.getResource("/face/fechar.png")));
		lblFechar.setBounds(867, 14, 40, 35);
		contentPane.add(lblFechar);

		final JRadioButton rdbtnEpi = new JRadioButton("EPI");
		rdbtnEpi.setBounds(241, 249, 50, 23);
		contentPane.add(rdbtnEpi);
		
		final JRadioButton rdbtnLimpeza = new JRadioButton("LIMPEZA");
		rdbtnLimpeza.setBounds(293, 249, 79, 23);
		contentPane.add(rdbtnLimpeza);
		
		final JRadioButton rdbtnEscritorio = new JRadioButton("ESCRIT\u00D3RIO");
		rdbtnEscritorio.setBounds(371, 249, 100, 23);
		contentPane.add(rdbtnEscritorio);
		
		final JRadioButton rdbtnUniforme = new JRadioButton("UNIFORME");
		rdbtnUniforme.setBounds(473, 248, 89, 24);
		contentPane.add(rdbtnUniforme);
		
		final JRadioButton rdbtnTonner = new JRadioButton("TONNER");
		rdbtnTonner.setBounds(564, 249, 73, 23);
		contentPane.add(rdbtnTonner);
		
		JLabel lblInformeOTipo = new JLabel("Informe o tipo de material que voc\u00EA deseja solicitar");
		lblInformeOTipo.setBounds(299, 226, 335, 14);
		lblInformeOTipo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblInformeOTipo);
		
		ButtonGroup grupoDeItens = new ButtonGroup();
		grupoDeItens.add(rdbtnTonner);
		grupoDeItens.add(rdbtnEpi);
		grupoDeItens.add(rdbtnEscritorio);
		grupoDeItens.add(rdbtnLimpeza);
		grupoDeItens.add(rdbtnUniforme);
		
		JLabel lblFuncionario = new JLabel("Funcion\u00E1rio");
		lblFuncionario.setBounds(341, 77, 253, 36);
		lblFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblFuncionario);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(268, 316, 128, 29);
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblQuantidade);
		
		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setBounds(341, 141, 253, 29);
		lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblSetor);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(398, 316, 50, 29);
		txtQuantidade.setColumns(10);
		contentPane.add(txtQuantidade);
		
		txtFuncionario = new JTextField();
		txtFuncionario.setEditable(false);
		txtFuncionario.setBounds(341, 113, 253, 29);
		txtFuncionario.setHorizontalAlignment(SwingConstants.CENTER);
		txtFuncionario.setColumns(10);
		contentPane.add(txtFuncionario);
		
		txtSetor = new JTextField();
		txtSetor.setBounds(341, 172, 253, 29);
		txtSetor.setEditable(false);
		txtSetor.setHorizontalAlignment(SwingConstants.CENTER);
		txtSetor.setColumns(10);
		contentPane.add(txtSetor);
		
				
		final JComboBox cbItemEpi = new JComboBox();
		cbItemEpi.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemEpi);
		cbItemEpi.setVisible(false);
		
		
		lblItem.setBounds(253, 279, 100, 26);
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblItem);
		
		JLabel lblPedidoDeItens = new JLabel("Pedido de Itens");
		lblPedidoDeItens.setBounds(0, 40, 917, 26);
		lblPedidoDeItens.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidoDeItens.setFont(new Font("Stencil", Font.PLAIN, 27));
		contentPane.add(lblPedidoDeItens);
		
		final JComboBox cbItemLimpeza = new JComboBox();
		cbItemLimpeza.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemLimpeza);
		cbItemLimpeza.setVisible(false);
		
		final JComboBox cbItemEscritorio = new JComboBox();
		cbItemEscritorio.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemEscritorio);
		cbItemEscritorio.setVisible(false);
		
		final JComboBox cbItemUniforme = new JComboBox();
		cbItemUniforme.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemUniforme);
		cbItemUniforme.setVisible(false);
		
		final JComboBox cbItemTonner = new JComboBox();
		cbItemTonner.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemTonner);
		cbItemTonner.setVisible(false);
		
		final JComboBox cbItemDefault = new JComboBox();
		cbItemDefault.setBounds(341, 280, 302, 25);
		contentPane.add(cbItemDefault);
		cbItemDefault.setVisible(true);
		
		final boolean itensEpi = false;
		final boolean itensLimpeza = false;
		final boolean itensEscritorio=false;
		final boolean itensUniforme=false;
		final boolean itensTonner=false;

		final JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(419, 396, 100, 36);
		contentPane.add(btnConfirmar);
		
		ConectaBanco conexao = new ConectaBanco();
		
		try {
			conexao.conexao();
			String SQL = "SELECT setor,nome FROM funcionario WHERE login='"+TelaLogin.txtLogin.getText()+"';";
			Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs = stm.executeQuery(SQL);
			 if (rs.next()){
			 txtSetor.setText(rs.getString(1));
			 txtFuncionario.setText(rs.getString(2));
			 }
		} catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Erro ao obter setor!");
		} finally {
			conexao.desconecta();
		}
		
	

	JButton btnOkItem = new JButton("OK");
	btnOkItem.setBounds(643, 249, 61, 23);
	contentPane.add(btnOkItem);
	
	JLabel lblItemPara = new JLabel("Empresa");
	lblItemPara.setHorizontalAlignment(SwingConstants.CENTER);
	lblItemPara.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblItemPara.setBounds(268, 356, 100, 29);
	contentPane.add(lblItemPara);
	
	final JComboBox cbEmpresa = new JComboBox();
	cbEmpresa.setBounds(371, 360, 272, 25);
	contentPane.add(cbEmpresa);
	
	
	cbEmpresa.setModel(new DefaultComboBoxModel(empresas));	
	
	
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
			
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ConectaBanco conexao = new ConectaBanco();
					conexao.conexao();
					int valor = Integer.parseInt(txtQuantidade.getText());
					try {
						PreparedStatement pst = conexao.conn.prepareStatement("INSERT INTO pedido_item (funcionario,setor_funcionario,nome_item,qtd_pedida,data_hora,status_pedido,empresa) VALUES(?,?,?,?,?,?,?); ");
						pst.setString(1, txtFuncionario.getText());
						pst.setString(2, txtSetor.getText());
						
						if(cbItemEscritorio.isShowing()){
							pst.setString(3, (String) cbItemEscritorio.getSelectedItem());
							//-------------------------------------------------------------------
							Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stm.executeQuery("SELECT qtd_estoque FROM item WHERE nome='"+cbItemEscritorio.getSelectedItem()+"';");
							if (rs.next()){
							if(valor < rs.getInt(1) ){
							pst.setInt(4, Integer.parseInt(txtQuantidade.getText()));}
							else{
							JOptionPane.showMessageDialog(null, "Você pediu uma quantidade maior do que o disponivel no estoque");	
							}}}
							
						if(cbItemEpi.isShowing()){
							pst.setString(3, (String) cbItemEpi.getSelectedItem());
							//-------------------------------------------------------------------
							Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stm.executeQuery("SELECT qtd_estoque FROM item WHERE nome='"+cbItemEpi.getSelectedItem()+"';");
							if (rs.next()){
							if(valor < rs.getInt(1) ){
							pst.setInt(4, Integer.parseInt(txtQuantidade.getText()));}
							else{
							JOptionPane.showMessageDialog(null, "Você pediu uma quantidade maior do que o disponivel no estoque");	
							}}}
							
						if(cbItemLimpeza.isShowing()){
							pst.setString(3, (String) cbItemLimpeza.getSelectedItem());
							//-------------------------------------------------------------------
							Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stm.executeQuery("SELECT qtd_estoque FROM item WHERE nome='"+cbItemLimpeza.getSelectedItem()+"';");
							if (rs.next()){
							if(valor < rs.getInt(1) ){
							pst.setInt(4, Integer.parseInt(txtQuantidade.getText()));}
							else{
							JOptionPane.showMessageDialog(null, "Você pediu uma quantidade maior do que o disponivel no estoque");	
							}}}
							
						if(cbItemUniforme.isShowing()){
							pst.setString(3, (String) cbItemUniforme.getSelectedItem());
							//-------------------------------------------------------------------
							Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stm.executeQuery("SELECT qtd_estoque FROM item WHERE nome='"+cbItemUniforme.getSelectedItem()+"';");
							if (rs.next()){
							if(valor < rs.getInt(1) ){
							pst.setInt(4, Integer.parseInt(txtQuantidade.getText()));}
							else{
							JOptionPane.showMessageDialog(null, "Você pediu uma quantidade maior do que o disponivel no estoque");	
							}}}
						
						if(cbItemTonner.isShowing()){
							pst.setString(3, (String) cbItemTonner.getSelectedItem());
							//-------------------------------------------------------------------
							Statement stm = conexao.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							ResultSet rs = stm.executeQuery("SELECT qtd_estoque FROM item WHERE nome='"+cbItemTonner.getSelectedItem()+"';");
							if (rs.next()){
							if(valor < rs.getInt(1) ){
							pst.setInt(4, Integer.parseInt(txtQuantidade.getText()));}
							else{
							JOptionPane.showMessageDialog(null, "Você pediu uma quantidade maior do que o disponivel no estoque");	
							}}}

						String dataAtuak = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(new Date());
						pst.setString(5, dataAtuak);
						pst.setString(6, "true");
						pst.setString(7, (String) cbEmpresa.getSelectedItem());
						
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
					}
					catch (SQLException e1) {
						txtQuantidade.requestFocus();
					} finally {
						conexao.desconecta();
					}
				}
			});

		} // fecha public void actin listener
	}); // fecha new acticon listener

	}
}
	
				
				
				
				
				
				
				
				
				
				
