package face;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utilitarios.ConectaBanco;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaInicialUsuario extends JFrame{

	private JPanel contentPane;
	String[] setores = new String[9];
	String[] permissao = new String[2];
	ConectaBanco conexao = new ConectaBanco();
	JButton btnFazerPedido = new JButton();

	public TelaInicialUsuario() {
		super("Tela Inicial - Controle de Estoque");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1281, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// INSTANCIOU-SE UMA VARIAVEL DO TIPO CONECTA BANCO
		final ConectaBanco conexao = new ConectaBanco();
		
		// ARRAY DAS OPÇÕES DE SETOR
		String setores[] = {"","COMERCIAL","C&M","TI","FENIX","FINANCEIRO","LABORATORIOS","PRESIDENCIA","RECEBIMENTO","RECEPCAO"};
		
		//ARRAY DAS PERMISSOES
		String permissao[] = {"","ADMINISTRADOR","USUARIO"};
		
		btnFazerPedido.setBounds(163, 24, 110, 70);
		btnFazerPedido.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/fazerpedido.png")));
		btnFazerPedido.setToolTipText("CADASTRO DE FUNCION\u00C1RIOS");
		contentPane.add(btnFazerPedido);
		
		JButton btnEstocar = new JButton();
		btnEstocar.setEnabled(false);
		
		JLabel lbl_CadastrodeFuncionarios = new JLabel("CADASTRAR FUNCION\u00C1RIOS");
		lbl_CadastrodeFuncionarios.setBounds(312, 86, 228, 50);
		lbl_CadastrodeFuncionarios.setBackground(new Color(0, 0, 0));
		lbl_CadastrodeFuncionarios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lbl_CadastrodeFuncionarios);
		
		JLabel lblPedido = new JLabel("PEDIDOS DE RETIRADA");
		lblPedido.setBounds(550, 86, 170, 50);
		lblPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblPedido);
		
		JLabel lblEstocar = new JLabel("ESTOCAR");
		lblEstocar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstocar.setBounds(778, 104, 110, 14);
		lblEstocar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblEstocar);
		
		JLabel lblSistemaDeControle = new JLabel("Sistema de Controle de Estoque");
		lblSistemaDeControle.setBounds(0, 147, 1275, 37);
		lblSistemaDeControle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeControle.setVerticalAlignment(SwingConstants.TOP);
		lblSistemaDeControle.setFont(new Font("Stencil Std", Font.PLAIN, 30));
		contentPane.add(lblSistemaDeControle);
		
		JButton brnPedidosDeRetirada = new JButton("");
		brnPedidosDeRetirada.setEnabled(false);
		
		brnPedidosDeRetirada.setBounds(576, 24, 110, 70);
		brnPedidosDeRetirada.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/pedido.png")));
		brnPedidosDeRetirada.setToolTipText("PEDIDOS DE RETIRADA");
		contentPane.add(brnPedidosDeRetirada);
		JButton btnCadastrarFuncionariosMENU = new JButton("");
		btnCadastrarFuncionariosMENU.setEnabled(false);
		

		btnCadastrarFuncionariosMENU.setBounds(361, 24, 110, 70);
		btnCadastrarFuncionariosMENU.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/funcionario.png")));
		btnCadastrarFuncionariosMENU.setToolTipText("CADASTRO DE FUNCION\u00C1RIOS");
		contentPane.add(btnCadastrarFuncionariosMENU);
		
		JLabel lblVisualizarEstoque = new JLabel("DASHBOARD ESTOQUE");
		lblVisualizarEstoque.setBounds(952, 86, 170, 50);
		lblVisualizarEstoque.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblVisualizarEstoque);
		
		JButton btnDashBoard = new JButton("");
		btnDashBoard.setEnabled(false);
		btnDashBoard.setBounds(976, 24, 110, 70);
		btnDashBoard.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/grafico.png")));
		btnDashBoard.setToolTipText("DASHBOARD ESTOQUE");
		contentPane.add(btnDashBoard);
		
		JLabel lblFazerPedido = new JLabel(" FAZER PEDIDO");
		lblFazerPedido.setBounds(163, 86, 122, 50);
		lblFazerPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblFazerPedido);
		
		btnEstocar.setBounds(778, 24, 110, 70);
		btnEstocar.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/cadastrarprodutos.png")));
		btnEstocar.setToolTipText("DASHBOARD ESTOQUE");
		contentPane.add(btnEstocar);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setExtendedState(ICONIFIED);
			}
		});
		label.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/minimizar.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(1194, 5, 40, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		label_1.setIcon(new ImageIcon(TelaInicialUsuario.class.getResource("/face/fechar.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(1234, 0, 40, 42);
		contentPane.add(label_1);
		
		
		
		//BOTAO FAZER PEDIDO
		btnFazerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			int i=0;
			boolean aberto = TelaFazerPedidos.aberto;
			if(aberto==true){
				JOptionPane.showMessageDialog(null, "Módulo já aberto!");
				
			}else{				
				TelaFazerPedidos ttt = new TelaFazerPedidos();
				ttt.setUndecorated(true);
				ttt.setBounds(245,220, 926, 610);
				ttt.setVisible(true);
				ttt.setFocusableWindowState(true);
				}
			}
			
			
			
		});
	

	}
}
