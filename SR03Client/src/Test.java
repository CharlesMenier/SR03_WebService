import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import webservice.AnnuaireWebServiceProxy;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnuaireWebServiceProxy annuaireWebServiceProxy = new AnnuaireWebServiceProxy();
		ArrayList<HashMap<String, String>> annonces = new ArrayList<HashMap<String, String>>();
		
//		String annonceJson = annuaireWebServiceProxy.searchByName("abc");
		JSONObject a = new JSONObject();
		a.put("id", 1);
		a.put("name", "abc");
		JSONObject c = new JSONObject();
		c.put("id", 1);
		c.put("name", "auto");
		a.put("category", c);
		JSONObject ad = new JSONObject();
		ad.put("id", 1);
		ad.put("rue", "rue de Paris");
		ad.put("ville", "compiegne");
		ad.put("codepostal", "60200");
		a.put("adress", ad);
		a.put("phone", "06 03 22 34 55");
		
		JSONArray ja = new JSONArray();
		ja.put(0, a);
		System.out.println(ja.toString());
		JSONArray jsonArray = new JSONArray(ja.toString());
		for (int i=0; i<jsonArray.length(); i++) {
			JSONObject anc = jsonArray.getJSONObject(i);
			HashMap<String, String> annonce = new HashMap<String, String>();
			annonce.put("id", String.valueOf(anc.getInt("id")));
			annonce.put("name", anc.getString("name"));
			annonce.put("category", anc.getJSONObject("category").getString("name"));
			annonce.put("rue", anc.getJSONObject("adress").getString("rue"));
			annonce.put("ville", anc.getJSONObject("adress").getString("ville"));
			annonce.put("codepostal", anc.getJSONObject("adress").getString("codepostal"));
			annonces.add(annonce);
			
			for (String s : annonce.keySet()) {
				System.out.println(annonce.get(s));
			}
		}
	}

}
