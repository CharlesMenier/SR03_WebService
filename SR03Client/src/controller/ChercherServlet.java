package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import webservice.AnnuaireWebServiceProxy;

/**
 * Servlet implementation class RechercherServlet
 */
@WebServlet(
		name = "ChercherServlet",
		urlPatterns={"/nom", "/categorie", "/adresse"}
		)
public class ChercherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<HashMap<String, String>> annonces;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChercherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		AnnuaireWebServiceProxy annuaireWebServiceProxy = new AnnuaireWebServiceProxy();
		annonces = new ArrayList<HashMap<String, String>>();
		
		String url = request.getRequestURI();
		if (url.contains("nom")) {
			String name = request.getParameter("annonce_nom");
			String annonceJson = annuaireWebServiceProxy.searchByName(name);
			handleJSON(annonceJson, request, response);
//			handleJSON("", request, response);
		} else if (url.contains("categorie")) {
			String categoryName = request.getParameter("annonce_categorie");
			String annonceJson = annuaireWebServiceProxy.searchByCategoryName(categoryName);
			handleJSON(annonceJson, request, response);
			
		} else if (url.contains("adresse")) {
			String rue = request.getParameter("annonce_rue");
			String ville = request.getParameter("annonce_ville");
			String codepostal = request.getParameter("annonce_codepostal");
			String annonceJson = annuaireWebServiceProxy.searchByAdress(ville, rue, codepostal);
			handleJSON(annonceJson, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void handleJSON(String string, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		JSONObject a = new JSONObject();
//		a.put("id", 1);
//		a.put("name", "abc");
//		JSONObject c = new JSONObject();
//		c.put("id", 1);
//		c.put("name", "auto");
//		a.put("category", c);
//		JSONObject ad = new JSONObject();
//		ad.put("id", 1);
//		ad.put("rue", "rue de Paris");
//		ad.put("ville", "compiegne");
//		ad.put("codepostal", "60200");
//		a.put("adress", ad);
//		a.put("phone", "06 03 22 34 55");
//		
//		JSONArray ja = new JSONArray();
//		ja.put(0, a);
//		JSONArray jsonArray = new JSONArray(ja.toString());
		JSONArray jsonArray = new JSONArray(string);
		
		for (int i=0; i<jsonArray.length(); i++) {
			JSONObject anc = jsonArray.getJSONObject(i);
			HashMap<String, String> annonce = new HashMap<String, String>();
			annonce.put("id", String.valueOf(anc.getInt("id")));
			annonce.put("name", anc.getString("name"));
			annonce.put("category", anc.getJSONObject("category").getString("name"));
			annonce.put("rue", anc.getJSONObject("adress").getString("rue"));
			annonce.put("ville", anc.getJSONObject("adress").getString("ville"));
			annonce.put("codepostal", anc.getJSONObject("adress").getString("codepostal"));
			annonce.put("phone", anc.getString("phone"));
			annonces.add(annonce);
			
			for (String s : annonce.keySet()) {
				System.out.println(annonce.get(s));
			}
		}
		request.setAttribute("pageNum", ((jsonArray.length()-1)/5)+1);
		request.setAttribute("annonces", annonces);
		request.getRequestDispatcher("/chercher.jsp").forward(request, response);;
	}
	
}
