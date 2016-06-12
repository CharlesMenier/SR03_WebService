package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnector {

	private Connection cn;
	
	public DaoConnector()
	{
		cn 		= null;
		
		try {
			String driver 	= "com.mysql.jdbc.Driver";//props.getProperty("driver");
//			String url 		= "jdbc:mysql://tuxa.sme.utc:3306/sr03p002";//props.getProperty("url");
			String url		= "jdbc:mysql://localhost/sr03_03";
			String user		= "root";//props.getProperty("user");
			String password	= "root";//"oVVEgn8n";//props.getProperty("password");
			
			if(driver != null)
				Class.forName(driver);
			
			cn = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e)
		{
			System.out.println("Error during the MySQL connection");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return cn;
	}
	
	public void closeConection()
	{
		try {
			cn.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
