package utilitarios;

import java.sql.*;

import javax.swing.JOptionPane;


		public class ConectaBanco {
	
	public Statement stm; // RESPONSAVEL POR PREPARAR E REALIZAR PESQUISAS NO BANCO DE DADOS
	
	public ResultSet rs; // RESPONSAVEL POR ARMAZENAR O RESULTADO DE UMA PESQUISA PASSADA PARA O STATEMENT
	
	private String driver = "org.postgresql.Driver"; // RESPONSAVEL POR IDENTIFICAR O SERVIÇO DE BANCO DE DADOS
	
	private String caminho = "jdbc:postgresql://containers-us-west-148.railway.app:7085/railway"; // RESPONSAVEL POR SETAR O LOCAL DO BANDO DE DADOS
	
	private String login = "postgres"; // USUARIO DO POSTEGRES
	
	private String senha = "9JlBEw7zRpx0mmWf3CRt"; // SENHA DO POSTGRES
	
	public Connection conn; // RESPONSAVEL POR REALIZAR A CONEXAO COM O BANCO DE DADOS
	
		
		public void conexao	() { // METODO RESPONSAVEL POR REALIZAR A CONEXAO COM O BANCO
			
			try {
				System.setProperty("jdbc.Drivers", driver); // SETA A PROPRIEDADE DO DRIVER DE CONEXAO
			//	JOptionPane.showMessageDialog(null, "Conectado com sucesso");
				conn = DriverManager.getConnection(caminho, login, senha);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERRO DE CONEXAO \n ERRO: "+ ex.getMessage());

				ex.printStackTrace();
			}
		}
		
		public void desconecta() {
			
			try {
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERRO AO DESCONECTAR \n ERRO: "+ ex.getMessage());

				ex.printStackTrace();
			}
			
			
		}
		
	
		public void executaSQL(String sql) {
			 try {
		            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
		            rs = stm.executeQuery(sql);
		        } catch (SQLException ex){
		        	JOptionPane.showMessageDialog(null, "Erro"+ex.getMessage());
		        }
			
		}
		}
