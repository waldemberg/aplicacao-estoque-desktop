package face;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utilitarios.ConectaBanco;

import java.awt.event.KeyAdapter;
public class TelaLogin extends JFrame{
	
	private JPanel contentPane = new JPanel(); 
		
	private JLabel lblLogin = new JLabel("Login: ");
	private JLabel lblSenha = new JLabel("Senha: ");
	static String usuarioLogado;
	
	static JTextField txtLogin = new JTextField();
	public JPasswordField txtSenha = new JPasswordField();
	private JButton btnOk = new JButton("Acessar!");
	boolean privilegio;
	boolean acesso;
	String loginn; 
    String senhaa;
    String permissao;
    Toolkit tk = Toolkit.getDefaultToolkit();
    private final JLabel label_1 = new JLabel("");
    //JTextFieldUpper txtLogin = new JTextFieldUpper();
    
    
	public TelaLogin() {
		super("Login - Controle de Estoque");
		this.setBounds(200, 100, 420, 300);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   // tk.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
		this.setFocusableWindowState(true); 
	 	
		lblLogin.setBounds(89,172,45,20);
		getContentPane().add(lblLogin);
		lblSenha.setBounds(89,198,45,25);
		getContentPane().add(lblSenha);
		
		txtLogin.setBounds(144,172,150,20);
		getContentPane().add(txtLogin);
		txtSenha.setBounds(144,200,150,20);
		getContentPane().add(txtSenha);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConectaBanco conexao = new ConectaBanco();
			 if(txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()){
				 JOptionPane.showMessageDialog(null, "nao pode campos vazios");
			 }
			 else{
				 try{
					 conexao.conexao();
					 Statement stm = conexao.conn.createStatement();
ResultSet rs = stm.executeQuery("select * FROM funcionario WHERE login='"+txtLogin.getText()+"' and senha='"+txtSenha.getText()+"';");
				if(rs.next()){
				 	 					 	
						if (rs.getString(4).equals(txtLogin.getText()) && rs.getString(5).equals(txtSenha.getText()) && rs.getString(6).equals("ADMINISTRADOR")){
							
							JOptionPane.showMessageDialog(null, "Logado com sucesso!");
							TelaInicialAdm adm = new TelaInicialAdm();
							
							adm.setLocationRelativeTo(null);
							adm.setResizable(false);
							adm.setUndecorated(true);
							adm.setVisible(true);
				
				} 
					 	if (rs.getString(4).equals(txtLogin.getText()) && rs.getString(5).equals(txtSenha.getText()) && rs.getString(6).equals("USUARIO")){
							
					 		JOptionPane.showMessageDialog(null, "Logado com sucesso!");
					 		
							TelaInicialUsuario usu = new TelaInicialUsuario();
					 		usu.setLocationRelativeTo(null);
					 		usu.setResizable(false);
					 		usu.setUndecorated(true);
					 		usu.setVisible(true);
					 	}
				}else{
			 		JOptionPane.showMessageDialog(null, "Login ou Senha incorretos!");
				}
				
				 
				 }catch(SQLException ex){
					 JOptionPane.showMessageDialog(null, "Erro no Banco de dados!"+ex.getMessage());
				 }
				 finally{
					 conexao.desconecta();
				 }
			}
			}
			}); 
		
		
		btnOk.setBounds(144,231,150,25);
		getContentPane().add(btnOk);
		label_1.setIcon(new ImageIcon(TelaLogin.class.getResource("/face/login.png")));
		label_1.setBounds(308, 157, 83, 99);
		
		getContentPane().add(label_1);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaLogin.class.getResource("/face/Login-ControleDeEstoque.png")));
		label.setBounds(-34, -15, 465, 369);
		getContentPane().add(label);
		

		
		txtSenha.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent ke ) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {  
					ConectaBanco conexao = new ConectaBanco();
					 if(txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()){
						 JOptionPane.showMessageDialog(null, "nao pode campos vazios");
					 }
					 else{
						 try{
							 conexao.conexao();
							 Statement stm = conexao.conn.createStatement();
		ResultSet rs = stm.executeQuery("select * FROM funcionario WHERE login='"+txtLogin.getText()+"' and senha='"+txtSenha.getText()+"';");
						if(rs.next()){
						 	 					 	
								if (rs.getString(4).equals(txtLogin.getText()) && rs.getString(5).equals(txtSenha.getText()) && rs.getString(6).equals("ADMINISTRADOR")){
									
									JOptionPane.showMessageDialog(null, "Logado com sucesso!");
									TelaInicialAdm adm = new TelaInicialAdm();
									
									adm.setLocationRelativeTo(null);
									adm.setResizable(false);
									adm.setUndecorated(true);
									adm.setVisible(true);
									dispose();
									
						
						} 
							 	if (rs.getString(4).equals(txtLogin.getText()) && rs.getString(5).equals(txtSenha.getText()) && rs.getString(6).equals("USUARIO")){
									
							 		JOptionPane.showMessageDialog(null, "Logado com sucesso!");
							 		
									TelaInicialUsuario usu = new TelaInicialUsuario();
							 		usu.setLocationRelativeTo(null);
							 		usu.setResizable(false);
							 		usu.setUndecorated(true);
							 		usu.setVisible(true);
							 		dispose();
							 	}
						}else{
					 		JOptionPane.showMessageDialog(null, "Login ou Senha incorretos!");
						}
						 }catch(SQLException ex){
							 JOptionPane.showMessageDialog(null, "Erro no Banco de dados!"+ex.getMessage());
						 }
						 finally{
							 conexao.desconecta();
						 }
					 }
						 }
			}			
		});
	}
}
	
			



	

