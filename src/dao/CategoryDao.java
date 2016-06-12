package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DaoConnector;

public class CategoryDao {

	int id;
	String name;
	
	public CategoryDao(int i, String n)
	{
		id = i;
		name = n;
	}
	
	public static void insert(String name)
	{
		Connection cn 	= new DaoConnector().getConnection();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "INSERT INTO category(ctg_name) VALUES('" + name + "');";
			
			stmt.executeUpdate(sql);
			
			cn.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void delete(String name)
	{
		Connection cn 	= new DaoConnector().getConnection();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "DELETE FROM category WHERE ctg_name='" + name + "';";
			
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
	
	public static CategoryDao find(int id)
	{
		Connection cn 	= new DaoConnector().getConnection();
		CategoryDao categ = null;
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM category WHERE ctg_id = " + String.valueOf(id);
			ResultSet res 	= stmt.executeQuery(sql);
			
			if(res.next())
			{
				categ = new CategoryDao(
						res.getInt("ctg_id"),
						res.getString("ctg_name")
						);
			}
			cn.close();
			return categ;
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String findAll()
	{
		Connection cn 	= new DaoConnector().getConnection();
		HashMap<String, Integer> categories = new HashMap<String, Integer>();
		
		try {
			Statement stmt 	= (Statement) cn.createStatement();
			String sql 		= "SELECT * FROM category";
			ResultSet res 	= stmt.executeQuery(sql);
			
			while(res.next())
			{
				categories.put(res.getString("ctg_name"), res.getInt("ctg_id"));
			}
			cn.close();
			
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(categories);
		
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
	
	
	
}
