package webservice;

public class AnnuaireWebServiceProxy implements webservice.AnnuaireWebService {
  private String _endpoint = null;
  private webservice.AnnuaireWebService annuaireWebService = null;
  
  public AnnuaireWebServiceProxy() {
    _initAnnuaireWebServiceProxy();
  }
  
  public AnnuaireWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAnnuaireWebServiceProxy();
  }
  
  private void _initAnnuaireWebServiceProxy() {
    try {
      annuaireWebService = (new webservice.AnnuaireWebServiceServiceLocator()).getAnnuaireWebService();
      if (annuaireWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)annuaireWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)annuaireWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (annuaireWebService != null)
      ((javax.xml.rpc.Stub)annuaireWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.AnnuaireWebService getAnnuaireWebService() {
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService;
  }
  
  public java.lang.String getCategories() throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService.getCategories();
  }
  
  public void updateCategory(java.lang.String id, java.lang.String name) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    annuaireWebService.updateCategory(id, name);
  }
  
  public void deleteCategory(java.lang.String name) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    annuaireWebService.deleteCategory(name);
  }
  
  public void insertCategory(java.lang.String name) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    annuaireWebService.insertCategory(name);
  }
  
  public java.lang.String searchByAdress(java.lang.String ville, java.lang.String rue, java.lang.String codepostal) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService.searchByAdress(ville, rue, codepostal);
  }
  
  public java.lang.String searchByName(java.lang.String name) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService.searchByName(name);
  }
  
  public java.lang.String searchByCategory(int categoryId) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService.searchByCategory(categoryId);
  }
  
  public void insertAnnounceAndNewAdress(java.lang.String name, int categId, java.lang.String rue, java.lang.String ville, java.lang.String cp, java.lang.String phone) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    annuaireWebService.insertAnnounceAndNewAdress(name, categId, rue, ville, cp, phone);
  }
  
  public void insertAnnounceWithExistingAdress(java.lang.String name, int categId, int adressId, java.lang.String phone) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    annuaireWebService.insertAnnounceWithExistingAdress(name, categId, adressId, phone);
  }
  
  public java.lang.String searchByCategoryName(java.lang.String categoryName) throws java.rmi.RemoteException{
    if (annuaireWebService == null)
      _initAnnuaireWebServiceProxy();
    return annuaireWebService.searchByCategoryName(categoryName);
  }
  
  
}