package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdressDao {

	int id;
	String rue;
	String ville;
	String codepostal;
	
	public AdressDao(int i, String r, String v, String c)
	{
		id = i;
		rue = r;
		ville = v;
		codepostal = c;
	}
	
	public static int insert(String rue, String ville, String cp)
	{
		Connection cn 	= new DaoConnector().getConnection();
		int key = 0;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "INSERT INTO adress(adr_rue, adr_ville, adr_codepostal) VALUES('" + rue + "', '" + ville + "', '" + cp + "');";
			ResultSet rs;
			
			System.out.println(sql);
			
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			rs = stmt.getGeneratedKeys();
			
			if(rs.next())
			{
				key = rs.getInt(1);
			}
			
			cn.close();
			
			return key;
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return key;
		}
	}
	
	public static AdressDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		AdressDao adress = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM adress WHERE adr_id = " + String.valueOf(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				adress = new AdressDao(
						res.getInt("adr_id"),
						res.getString("adr_rue"),
						res.getString("adr_ville"),
						res.getString("adr_codepostal")
						);
			}
			cn.close();
			return adress;
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}
	
	
	
}
