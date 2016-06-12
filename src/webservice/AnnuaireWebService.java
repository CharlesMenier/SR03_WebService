package webservice;

import dao.AdressDao;
import dao.AnnounceDao;
import dao.CategoryDao;

public class AnnuaireWebService {
	
	public String getCategories()
	{
		return CategoryDao.findAll();
	}
	
	public void insertCategory(String name)
	{
		CategoryDao.insert(name);
	}
	
	public void deleteCategory(String name)
	{
		CategoryDao.delete(name);
	}
	
	public void updateCategory(String id, String name)
	{
		CategoryDao.update(id, name);
	}
	
	
	public void insertAnnounceWithExistingAdress(String name, int categId, int adressId, String phone)
	{
		AnnounceDao an = new AnnounceDao(name, categId, adressId, phone);
		an.insert();
	}
	
	public void insertAnnounceAndNewAdress(String name, int categId, String rue, String ville, String cp, String phone)
	{
		int id = AdressDao.insert(rue, ville, cp);
		AnnounceDao an = new AnnounceDao(name, categId, AdressDao.find(id), phone);
		an.insert();
	}
	
	public String searchByCategory(int categoryId)
	{
		return AnnounceDao.findByCategory(categoryId);
	}
	
	public String searchByCategoryName(String categoryName) {
		return AnnounceDao.findByCategory(categoryName);
	}
	
	public String searchByAdress(String ville, String rue, String codepostal)
	{
		return AnnounceDao.findByAdress(ville, rue, codepostal);
	}
	
	public String searchByName(String name)
	{
		return AnnounceDao.findByName(name);
	}
}
