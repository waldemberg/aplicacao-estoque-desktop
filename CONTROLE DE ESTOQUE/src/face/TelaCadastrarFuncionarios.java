package face;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utilitarios.ConectaBanco;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	static boolean aberto4;
	
	public TelaCadastrarFuncionarios() {
		super("Cadastro de funcionarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastro de Funcion\u00E1rios", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setFocusableWindowState(true);
		
		aberto4 = true;
		
		JLabel lblFechar = new JLabel("");
		lblFechar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
					aberto4 = false;
					dispose();
			}
		});
				// ARRAY DAS OPÇÕES DE SETOR
				String setores[] = {"","COMERCIAL","C&M","TI","FENIX","FINANCEIRO","LABORATORIOS","PRESIDENCIA","RECEBIMENTO","RECEPCAO"};
				
				//ARRAY DAS PERMISSOES
				String permissao[] = {"","ADMINISTRADOR","USUARIO"};
				
		final JLabel lblCadastrodeFuncionarios = new JLabel("CADASTRO DE FUNCION\u00C1RIOS");
		lblCadastrodeFuncionarios.setBounds(10, 39, 897, 26);
		lblCadastrodeFuncionarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrodeFuncionarios.setFont(new Font("Stencil Std", Font.PLAIN, 25));
		contentPane.add(lblCadastrodeFuncionarios);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(302, 95, 105, 26);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(451, 91, 176, 30);
		txtNome.setColumns(10);
		contentPane.add(txtNome);
		
		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetor.setBounds(302, 132, 105, 30);
		lblSetor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblSetor);
		
		final JComboBox cbSetor = new JComboBox();
		cbSetor.setBounds(451, 132, 176, 30);
		contentPane.add(cbSetor);
		cbSetor.setModel(new DefaultComboBoxModel(setores));

		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(302, 173, 105, 30);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(302, 214, 105, 33);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblSenha);
		
		JLabel lblPermissao = new JLabel("Permiss\u00E3o");
		lblPermissao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermissao.setBounds(302, 254, 105, 34);
		lblPermissao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblPermissao);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(451, 173, 176, 30);
		contentPane.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(451, 213, 176, 30);
		contentPane.add(txtSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(477, 319, 150, 38);
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnCadastrar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(312, 319, 113, 38);
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNovo);
		
		final JLabel lblLoginJExistente = new JLabel("Login j\u00E1 existente!");
		lblLoginJExistente.setForeground(Color.RED);
		lblLoginJExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginJExistente.setBounds(637, 169, 105, 38);
		contentPane.add(lblLoginJExistente);
		lblLoginJExistente.setVisible(false);
		
		
		final JComboBox cbPermissao = new JComboBox();
		cbPermissao.setBounds(451, 254, 176, 30);
		contentPane.add(cbPermissao);
		cbPermissao.setModel(new DefaultComboBoxModel(permissao));
		
		lblFechar.setIcon(new ImageIcon(TelaCadastrarFuncionarios.class.getResource("/face/fechar.png")));
		lblFechar.setBounds(867, 14, 40, 35);
		contentPane.add(lblFechar);
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectaBanco conexao = new ConectaBanco();
				conexao.conexao();
				try {
					PreparedStatement pst = conexao.conn.prepareStatement("INSERT INTO funcionario (nome, setor,login,senha,permissao) VALUES(?,?,?,?,?); ");
					pst.setString(1, txtLogin.getText());	
					pst.setString(2, (String) cbSetor.getSelectedItem());
					try{
						conexao.conexao();
						Statement stm = conexao.conn.createStatement();
						ResultSet rs = stm.executeQuery("SELECT login FROM funcionario WHERE login='"+txtLogin.getText()+"';");
						if(rs.next()){
							lblLoginJExistente.setVisible(true);
							txtLogin.setEditable(false);
							txtNome.setEditable(false);
							txtSenha.setEditable(false);
							cbPermissao.setEditable(false);
							cbSetor.setEditable(false);
						} else {
					pst.setString(3, txtLogin.getText());
						  }
					} catch (SQLException exx){
						JOptionPane.showMessageDialog(null, "Erro no Banco de Dados");
						
					}
					pst.setString(4, txtSenha.getText());
					pst.setString(5, (String) cbPermissao.getSelectedItem());
					pst.executeUpdate();
					
					txtLogin.setEditable(false);
					txtNome.setEditable(false);
					txtSenha.setEditable(false);
					cbPermissao.setEnabled(false);
					cbSetor.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
				}				
					catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario");
					} 
				finally {
					conexao.desconecta();
				}
				
				
			}
		});
		
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLogin.setEditable(true);
				txtLogin.setText("");
				txtNome.setEditable(true);
				txtNome.setText("");
				txtSenha.setEditable(true);
				txtSenha.setText("");
				cbPermissao.setSelectedIndex(0);
				cbSetor.setSelectedIndex(0);
				lblLoginJExistente.setVisible(false);
				cbPermissao.setEditable(true);
				cbPermissao.setEnabled(true);
				cbSetor.setEditable(true);
				cbSetor.setEnabled(true);
				
			}
		});
	

	}
}
