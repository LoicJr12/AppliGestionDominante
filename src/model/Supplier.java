package model;

/**
 * Classe Supplier
 * @author ESIGELEC - TIC Department
 * @version 1.2
 * */
public class Supplier {
	/** 
	 * reference du fournisseur
	 */
	private int id;		
	/**
	 * nom
	 */
	private String name;	
	/**
	 * adresse postale
	 */
	private String address;		
	/**
	 * email
	 */
	private String email;
	/**
	 * liste d'articles
	 */
	//private ArrayList<Article> listArticles;

	/**
	 * Constructor
	 * @param id identifiant du fournisseur
	 * @param name nom (ou raison sociale)
	 * @param address adresse postale
	 * @param email adresse mail du contact
	 */
	public Supplier(int id, String name, String address, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		//listArticles = new ArrayList<Article>();
	}
	
	/**
	 * getter pour l'attribut reference
	 * @return valeur de la reference fournisseur
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter pour l'attribut name
	 * @return valeur du nom
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter pour l'attribut name
	 * @param name : nouvelle valeur du nom du fournisseur
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter pour l'attribut address
	 * @return valeur de l'adresse postale
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * setter pour l'attribut address
	 * @param address : nouvelle valeur de l'adresse postale
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * getter pour l'attribut email
	 * @return valeur de l'adresse mail
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter pour l'attribut email
	 * @param qteStock : nouvelle valeur de l'adresse mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter pour l'attribut listArticles
	 * @return liste des articles du fournisseur
	 */
	//public ArrayList<Article> getListArticles(){
	//	return listArticles;
	//}
	
	/**
	 * setter pour l'attribut listArticles
	 * @param liste des articles du fournisseur
	 */
	//public void setListArticles(ArrayList<Article> listArticles){
	//	this.listArticles = listArticles;
	//}
	
	/**
	 * permet d'ajouter un article dans la liste du fournisseur
	 * @param article : reference de l'article a ajouter
	 */
	//public void addArticle(Article article) {
	//	if(!listArticles.contains(article)) {
	//		listArticles.add(article);
	//	}
	//}

	/**
	 * Redefinition de la methode toString permettant de definir la traduction de l'objet en String
	 * pour l'affichage dans la console par exemple
	 */
	@Override
	public String toString() {
		return "Fournisseur [ref : " + id + ", " + name
				+ ", " + address + ", " + email + "]";
	}
}
