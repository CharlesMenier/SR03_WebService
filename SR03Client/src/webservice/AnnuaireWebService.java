/**
 * AnnuaireWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface AnnuaireWebService extends java.rmi.Remote {
    public java.lang.String getCategories() throws java.rmi.RemoteException;
    public void updateCategory(java.lang.String id, java.lang.String name) throws java.rmi.RemoteException;
    public void deleteCategory(java.lang.String name) throws java.rmi.RemoteException;
    public void insertCategory(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String searchByAdress(java.lang.String ville, java.lang.String rue, java.lang.String codepostal) throws java.rmi.RemoteException;
    public java.lang.String searchByName(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String searchByCategory(int categoryId) throws java.rmi.RemoteException;
    public void insertAnnounceAndNewAdress(java.lang.String name, int categId, java.lang.String rue, java.lang.String ville, java.lang.String cp, java.lang.String phone) throws java.rmi.RemoteException;
    public void insertAnnounceWithExistingAdress(java.lang.String name, int categId, int adressId, java.lang.String phone) throws java.rmi.RemoteException;
    public java.lang.String searchByCategoryName(java.lang.String categoryName) throws java.rmi.RemoteException;
}
