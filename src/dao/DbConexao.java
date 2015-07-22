package dao;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;
import dto.DtoParametros;

public class DbConexao {
	
	private static Connection conexao = null;
	
	static public Connection getConexao() throws Exception
	{
		if(conexao == null)
		{
			try
			{
				OracleDataSource ds = new OracleDataSource();
				ds.setURL(DtoParametros.url);
				ds.setPassword(DtoParametros.senha);
				ds.setUser(DtoParametros.usuario);
				conexao = ds.getConnection();
				
			}
			catch (Exception e)
			{
				conexao = null;
				throw new Exception("Não foi possível abrir conexão com o banco de dados: " + e.getMessage());
			}
			
		}
		
		return conexao; 
	}
	
	static public void Desconectar() throws SQLException
	{
		if(conexao != null)
		{
			try
			{
				conexao.close();
			} 
			catch (SQLException e) 
			{
				throw new SQLException("Não foi possível abrir conexão com o banco de dados: " + e.getMessage());
			}
		}
	}
}
