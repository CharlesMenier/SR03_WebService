package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnnounceDao {

	int id;
	String name;
	CategoryDao category;
	AdressDao adress;
	String phone;

	public AnnounceDao(String n, CategoryDao c, AdressDao a, String p)
	{
		name = n;
		category = c;
		adress = a;
		phone  = p;
	}
	
	public AnnounceDao(String n, int cId, AdressDao a, String p)
	{
		name = n;
		category = CategoryDao.find(cId);
		adress = a;
		phone  = p;
	}
	
	public AnnounceDao(String n, int cId, int aId, String p)
	{
		name = n;
		category = CategoryDao.find(cId);
		adress = AdressDao.find(aId);
		phone  = p;
	}
	
	public void insert()
	{
		Connection cn 	= new DaoConnector().getConnection();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= 	"INSERT INTO announce(anc_id, anc_name, anc_categoryId, anc_adressId, anc_phone) " +
								"VALUES('" + id + "'," + 
								"'" + name + "'," +
								"'" + category.id + "'," +
								"'" + adress.id + "'," +
								"'" + phone + "');";
			
			stmt.executeUpdate(sql);
			
			cn.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void delete(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "DELETE FROM announce WHERE anc_id='" + id + "';";
			
			stmt.executeUpdate(sql);
			
			cn.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void update(String id, String name)
	{
		Connection cn 	= new DaoConnector().getConnection();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "UPDATE category SET ctg_name='" + name + "' WHERE ctg_id='" + id + "';";
			
			stmt.executeUpdate(sql);
			
			cn.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static String findByName(String name)
	{
		Connection cn 	= new DaoConnector().getConnection();
		List<AnnounceDao> announces = new ArrayList<AnnounceDao>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM announce WHERE anc_name LIKE '%" + name + "%'";
			ResultSet res 	= stmt.executeQuery(sql);
			
			while(res.next())
			{
				announces.add(new AnnounceDao(res.getString("anc_name"),
						res.getInt("anc_categoryId"),
						res.getInt("anc_adressId"),
						res.getString("anc_phone")));
			}
			cn.close();
			
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(announces);
		
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return "{}";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	public static String findByAdress(String ville, String rue, String codepostal)
	{
		Connection cn 	= new DaoConnector().getConnection();
		List<AnnounceDao> announces = new ArrayList<AnnounceDao>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM announce WHERE anc_adressId IN " +
								"( SELECT adr_id FROM adress WHERE adr_ville LIKE '%" + ville + "%'" +
								" OR adr_rue LIKE '%" + rue + "%'" +
								" OR adr_codepostal LIKE '%" + codepostal + "%')";
			ResultSet res 	= stmt.executeQuery(sql);
			
			while(res.next())
			{
				announces.add(new AnnounceDao(res.getString("anc_name"),
						res.getInt("anc_categoryId"),
						res.getInt("anc_adressId"),
						res.getString("anc_phone")));
			}
			cn.close();
			
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(announces);
		
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return "{}";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	public static String findByCategory(int categoryId)
	{
		Connection cn 	= new DaoConnector().getConnection();
		List<AnnounceDao> announces = new ArrayList<AnnounceDao>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM announce WHERE anc_categoryId = " + categoryId;
			ResultSet res 	= stmt.executeQuery(sql);
			
			while(res.next())
			{
				announces.add(new AnnounceDao(res.getString("anc_name"),
						res.getInt("anc_categoryId"),
						res.getInt("anc_adressId"),
						res.getString("anc_phone")));
			}
			cn.close();
			
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(announces);
		
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return "{}";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDao getCategory() {
		return category;
	}

	public void setCategory(CategoryDao category) {
		this.category = category;
	}

	public AdressDao getAdress() {
		return adress;
	}

	public void setAdress(AdressDao adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
